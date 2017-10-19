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
	 * <p>上传文件处理</p>
	 *
	 * @param file 文件流
	 * @return 文件的信息
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
	 * <p>下载文件</p>
	 *
	 * @param id       文件的名称
	 * @param request  请求对象
	 * @param response 响应对相关
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public void download(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try (InputStream inputStream = new FileInputStream(new File("E:/IdeaWork/rain-security/rain-security-rainbow", id + ".txt"));
		     OutputStream outputStream = response.getOutputStream();) {

			response.setContentType("application/x-download");
			response.addHeader("Content-Dispositon", "attachment;filename=text.txt");

			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
