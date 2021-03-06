package test.integration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.web.context.ContextLoaderListener;

public class TransactionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(TransactionFilter.class);

	private static final String TRANSACTION_FILTER_MARKER_ATTRIBUTE = "requestContextTransactionMarker";
	
    public TransactionFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
		logger.debug("Initializing TransactionFilter...");
	}
	public void destroy() {
		logger.debug("Destroying TransactionFilter...");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request.getAttribute(TRANSACTION_FILTER_MARKER_ATTRIBUTE) != null) {
			doRecurringFilter(request, response, chain);
		} else {
			doFirstTimeFilter(request, response, chain);
		}
	}

	private void doFirstTimeFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute(TRANSACTION_FILTER_MARKER_ATTRIBUTE, TRANSACTION_FILTER_MARKER_ATTRIBUTE);
		ApplicationContext ctx = ContextLoaderListener.getCurrentWebApplicationContext();
		PlatformTransactionManager txManager = (PlatformTransactionManager)ctx.getBean("transactionManager");
		TransactionAttribute def = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus tx = null;
		try {
			tx = txManager.getTransaction(def);
		} catch(TransactionException txe) {
			logger.error("FATAL: not able to start a transaction to service this request.");
			throw new ServletException(txe);
		}
		try {
			chain.doFilter(request, response);
		} catch(Exception e) {
			logger.error("Exception occurred servicing this request, rolling back the current transaction...");
			try {
				txManager.rollback(tx);
			} catch(TransactionException txe) {
				logger.error("Exception occurred rolling back the current transaction: " + txe.getMessage());
			}
			throw new ServletException(e);
		} finally {
			if (!tx.isCompleted()) {
				try {
					txManager.commit(tx);
				} catch(TransactionException txe) {
					logger.error("Exception occurred committing the current transaction: " + txe.getMessage());
					throw new ServletException(txe);
				}
			}
		}
	}
	private void doRecurringFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}
}
