package test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import test.service.MessageService;
import test.service.PostService;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private MessageService messageService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/index.page")
	public String welcome(ModelMap model) {
		logger.debug("Going into IndexController.welcome()");
		model.addAttribute("messages", messageService.findImportantMessages());
		model.addAttribute("posts", postService.findLatestPosts());
		return "/index.html";
	}

	@RequestMapping(value="/newMessage.action")
	public String newMessage(ModelMap model) {
		logger.debug("Going into IndexController.newMessage()");
		messageService.createImportantMessage();
		return "redirect:/index.page";
	}
	
	@RequestMapping(value="/newPost.action")
	public String newPost(ModelMap model) {
		logger.debug("Going into IndexController.newPost()");
		postService.createPost();
		return "redirect:/index.page";
	}

}
