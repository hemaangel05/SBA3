package com.tarangini;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tarangini.model.Plan;
import com.tarangini.restapi.PlanApi;
import com.tarangini.service.PlanService;






@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlanApi.class)
public class PlanApiTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private PlanService planServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	@Test
	public void testGetAllPlans() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());

	}
	
	
	
	
	@Test
	public void testGetAllPlanss() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getPlansBySpeed(4)).thenReturn(planList);

		mockMvc.perform(get("/plans/speed/4")).andExpect(status().isOk()).andDo(print());

	}
}
