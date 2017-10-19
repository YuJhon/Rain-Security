package com.jhon.rain.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * <p>功能描述</br> WireMock服务器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName MockServer
 * @date 2017/10/18 14:39
 */
public class MockServer {

	public static void main(String[] args) throws Exception {
		WireMock.configureFor(8090);
		WireMock.removeAllMappings();

		mock("/mock01","mock01");
		mock("/mock02","mock02");
	}

	/**
	 * 模拟接口访问
	 * @param url 路径
	 * @param file 文件
	 * @throws IOException
	 */
	public static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
	}
}
