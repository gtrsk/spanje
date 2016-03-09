package com.abcd.efg.components;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.hasItems;
import java.util.ArrayList;
import java.util.List;


public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/home")).andExpect(view().name("home"));
	}
	
	
	
	
	@Test
	public void shouldShowRequests() throws Exception {
		List<Request> expectedRequests = createRequestList(20);
		RequestRepository mockRepository = mock(RequestRepository.class);
		when(mockRepository.findRequests("Request1")).thenReturn(expectedRequests);
		RequestController controller = new RequestController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/requests.jsp")).build();
		mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedRequests.toArray())));
	}
	
	private List<Request> createRequestList(int count) 
	{
		List<Request> requests= new ArrayList<Request>();
		for (int i=0; i < count; i++) 
		{
			requests.add(new Request("Request" + Integer.toString(i), "email"));
		}
		return requests;
	}
}