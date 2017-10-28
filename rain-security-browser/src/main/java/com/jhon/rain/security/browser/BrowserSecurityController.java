package com.jhon.rain.security.browser;

import com.jhon.rain.security.core.support.SimpleResponse;
import com.jhon.rain.security.core.support.SocialUserInfo;
import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> PC端安全校验的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BrowserSecurityController
 * @date 2017/10/19 11:08
 */
@Slf4j
@RestController
public class BrowserSecurityController {

	/** 将请求缓存在session中 **/
	private RequestCache requestCache = new HttpSessionRequestCache();

	/** **/
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * <pre>当需要身份认证时，跳转到这里 </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
					throws IOException {

		/** 获取缓存的请求 **/
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {

			String targetUrl = savedRequest.getRedirectUrl();

			log.info("引发跳转的请求是：{}", targetUrl);

			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
		SimpleResponse simpleResponse = new SimpleResponse();
		simpleResponse.setContent("访问的服务需要身份认证，请引导用户到登录页");
		return simpleResponse;
	}

	/**
	 * <pre>社交获取用户信息【从Session中获取的】</pre>
	 * @param request
	 * @return
	 */
	@GetMapping("/social/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeading(connection.getImageUrl());
		return userInfo;
	}
}
