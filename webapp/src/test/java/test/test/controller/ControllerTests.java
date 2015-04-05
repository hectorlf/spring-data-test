package test.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import test.test.BaseMvcTest;

public class ControllerTests extends BaseMvcTest {

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/index.page"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
	}

}
