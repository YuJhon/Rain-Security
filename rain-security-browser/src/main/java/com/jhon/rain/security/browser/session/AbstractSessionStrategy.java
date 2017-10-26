package com.jhon.rain.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhon.rain.security.browser.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> Session处理的策略</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractSessionStrategy
 * @date 2017/10/26 16:50
 */
@Slf4j
public class AbstractSessionStrategy {

	/**
	 * 跳转的url
	 */
	private String destinationUrl;

	/**
	 * 重定向策略
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 跳转前是否创建新的session
	 */
	private boolean createNewSession = true;
	/**
	 * json处理工具
	 */
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * <pre>构造函数</pre>
	 *
	 * @param invalidSessionUrl Session失效重定向的url
	 */
	public AbstractSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	/**
	 * <pre>Session是失效处理 </pre>
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;

		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			targetUrl = destinationUrl + ".html";
			log.info("session失效,跳转到" + targetUrl);
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			String message = "session已失效";
			if (isConcurrency()) {
				message = message + "，有可能是并发登录导致的";
			}
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			SimpleResponse simpleResponse = new SimpleResponse();
			simpleResponse.setContent(message);
			response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));
		}
	}

	/**
	 * Determines whether a new session should be created before redirecting (to
	 * avoid possible looping issues where the same session ID is sent with the
	 * redirected request). Alternatively, ensure that the configured URL does
	 * not pass through the {@code SessionManagementFilter}.
	 *
	 * @param createNewSession defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

	/**
	 * Session失效是否并发导致
	 *
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

}
