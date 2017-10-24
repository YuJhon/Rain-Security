package com.jhon.rain.security.core.authentication;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * <p>功能描述</br> Security认证的公共配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BaseChannelSecurityConfig
 * @date 2017/10/23 16:00
 */
public class BaseChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler rainAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler rainAuthenticationFailureHandler;

	/**
	 * <pre>公共的认证配置 </pre>
	 * @param http
	 * @throws Exception
	 */
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
				/** 自定义登录请求地址**/
				.loginPage(RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				/** 自定义登录验证的接口 **/
				.loginProcessingUrl(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
				/** 登录成功处理器 **/
				.successHandler(rainAuthenticationSuccessHandler)
				/** 登录失败处理器 **/
				.failureHandler(rainAuthenticationFailureHandler);
	}
}
