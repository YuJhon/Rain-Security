package com.jhon.rain.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * <p>功能描述</br> Restful API形式的 测试类</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RestExampleControllerTest
 * @date 2017/10/15 16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestExampleControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {
		String restult = mockMvc.perform(MockMvcRequestBuilders.get("/rest")
						//.param("username","JhonRain")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
						.andReturn().getResponse().getContentAsString();
		System.out.println(restult);
		//[{"id":null,"username":"Jhon1","birthday":1508137735101},{"id":null,"username":"Jhon2","birthday":1508137735101},{"id":null,"username":"Jhon3","birthday":1508137735101}]
	}

	@Test
	public void whenQuerySuccess2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest2")
						.param("username", "JhonRain")
						.param("age", "18")
						.param("ageTo", "60")
						.param("gender", "男")
						.param("size", "10")
						.param("page", "1")
						.param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

	@Test
	public void whenGetUserInfoSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/1")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Rain"))
						.andReturn().getResponse().getContentAsString();

	}

	@Test
	public void whenGetUserInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/a")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void whenCreateSuccess() throws Exception {
		String content = "{\"username\":\"tom\",\"password\":\"jhonrain1321\",\"birthday\":" + new Date().getTime() + "}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/rest")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
						.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}


	@Test
	public void whenPutSuccess() throws Exception {
		String content = "{\"id\":\"2\", \"username\":\"tom\",\"password\":null,\"birthday\":"+new Date().getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.put("/rest/2")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
						.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/rest/1")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
