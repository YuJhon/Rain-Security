package com.jhon.rain.security.app;

import com.jhon.rain.security.app.social.impl.AppSignUpUtils;
import com.jhon.rain.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AppSecurityController
 * @date 2017/10/28 21:24
 */
@RestController
public class AppSecurityController {

	@Autowired
	private AppSignUpUtils appSignUpUtils;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@GetMapping("/social/signUp")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getUserInfo(HttpServletRequest request) {

		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeading(connection.getImageUrl());

		appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
		return userInfo;
	}
}
