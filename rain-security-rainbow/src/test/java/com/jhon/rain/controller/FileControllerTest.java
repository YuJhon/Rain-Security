package com.jhon.rain.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p>功能描述</br> 文件上传下载Controller单元测试 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName FileControllerTest
 * @date 2017/10/18 10:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
						.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello Spring boot upload".getBytes("UTF-8"))))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
}
