package com.tarangini;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tarangini.model.Connection;
import com.tarangini.restapi.ConnectionApi;
import com.tarangini.service.ConnectionService;
import com.tarangini.test.TestUtil;





@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ConnectionApi.class)
public class ConnectionApiTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ConnectionService conServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		assertThat(this.conServiceMock).isNotNull();

		List<Connection> conList = new ArrayList<>();
		conList.add(new Connection());

		when(conServiceMock.getAllConnections()).thenReturn(conList);

		mockMvc.perform(get("/connections")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetConnectionAction() throws Exception {
		assertThat(this.conServiceMock).isNotNull();
		String name = "Bhargavi";
		
		Connection con = new Connection();
		
		con.setCid(1234);
		con.setName("Bhargavi");
		con.setpTitle("PL113");
		con.setMbno("9052540723");
		con.setTimeSlot("2-4");
		//con.setDor(LocalDate.of(2018,10,30));
		con.setAddress("Madhapur");
		
		when(conServiceMock.getConnectionByName(name)).thenReturn(con);

		mockMvc.perform(get("/connections/Bhargavi")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testAddConnectionAction() throws Exception {
		assertThat(this.conServiceMock).isNotNull();

		Connection con = new Connection();
		
		con.setCid(1234);
		con.setName("Bhargavi");
		con.setpTitle("PL113");
		con.setMbno("9052540723");
		con.setTimeSlot("2-4");
		//con.setDor(LocalDate.of(2018,10,30));
		con.setAddress("Madhapur");

		Connection con1 = new Connection();
		
		con1.setCid(5678);
		con1.setName("Kaushik");
		con1.setpTitle("PL113");
		con1.setMbno("9052540723");
		con1.setTimeSlot("2-4");
		//con1.setDor(LocalDate.of(2018,10,30));
		con1.setAddress("Madhapur");

		System.out.println(con);

		when(conServiceMock.addConnection(Mockito.any(Connection.class))).thenReturn(con1);

		mockMvc.perform(post("/connections").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(con))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}
	

}
