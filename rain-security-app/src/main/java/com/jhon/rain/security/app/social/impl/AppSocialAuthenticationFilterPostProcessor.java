package com.jhon.rain.security.app.social.impl;

import com.jhon.rain.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 后处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AppSocialAuthenticationFilterPostProcessor
 * @date 2017/10/28 20:59
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

	@Autowired
	private AuthenticationSuccessHandler rainAuthenticationSuccessHandler;

	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		socialAuthenticationFilter.setAuthenticationSuccessHandler(rainAuthenticationSuccessHandler);
	}
}
