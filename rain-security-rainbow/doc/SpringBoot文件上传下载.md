#### Spring Boot File Upload & Download
* 代码示例
```java
package com.jhon.rain.web.controller;

import com.jhon.rain.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * <p>功能描述</br> 文件上传控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName FileController
 * @date 2017/10/18 10:23
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private String folder = "./";

	/**
	 * 上传文件处理
	 * @param file 文件流
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {

		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		File localFile = new File(folder, new Date().getTime() + ".txt");

		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	/**
	 * 下载文件
	 * @param id 文件的名称
	 * @param request 请求对象
	 * @param response 响应对相关
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public void download(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
		     OutputStream outputStream = response.getOutputStream();){
			
            response.setContentType("application/x-download");
            response.addHeader("Content-Dispositon", "attachment;filename=text.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
		}
	}
}

```
* 单元测试
```java
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
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
						.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello Spring boot upload".getBytes("UTF-8"))))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
```

* 执行结果
![SpringBoot文件的上传下载](../../photos/springboot%20File%20upload%20success.png)