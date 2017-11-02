package com.jhon.rain.security.app;

import com.jhon.rain.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.jhon.rain.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.jhon.rain.security.core.authorize.AuthorizeConfigManager;
import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * <p>功能描述</br> 资源服务器的安全配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceServerConfig
 * @date 2017/10/28 14:44
 */
@Configuration
@EnableResourceServer
public class RainResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler rainAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler rainAuthenticationFailureHandler;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private SpringSocialConfigurer rainSocialSecurityConfig;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/** 基础的配置
		 * 1.自定义登录请求地址
		 * 2.自定义登录验证的接口
		 * 3.登录成功和失败的处理
		 * **/
		http.formLogin()
						/** 自定义登录请求地址**/
						.loginPage(RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
						/** 自定义登录验证的接口 **/
						.loginProcessingUrl(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
						/** 登录成功处理器 **/
						.successHandler(rainAuthenticationSuccessHandler)
						/** 登录失败处理器 **/
						.failureHandler(rainAuthenticationFailureHandler);

		http
				/** 添加图片验证码验证的过滤器 **/
				.apply(validateCodeSecurityConfig)
				.and()
				/** 添加短信验证码验证的过滤器 **/
				.apply(smsCodeAuthenticationSecurityConfig)
				.and()
				/** 添加社交安全配置 **/
				.apply(rainSocialSecurityConfig)
				.and()
				/**openid登录方式配置 **/
				.apply(openIdAuthenticationSecurityConfig)
				/*.and()
				.authorizeRequests()
				.antMatchers(
								*//** 默认未授权处理接口地址 **//*
								RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
								*//** 登录的页面【默认是系统默认的，可以自定义配置】 **//*
								securityProperties.getBrowser().getLoginPage(),
								*//** 默认手机验证码接口处理地址 **//*
								RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
								*//** 生成验证码的接口地址 **//*
								RainSecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "*//*",
								securityProperties.getBrowser().getSignUpUrl(),
								*//** session失效跳转的链接地址 **//*
								securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
								securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
								*//** 自定义退出地址 **//*
								securityProperties.getBrowser().getSignOutUrl(),
								"/user/register","/social/signUp"
				).permitAll()
				.anyRequest()
				.authenticated()*/
				.and()
				.csrf().disable(); /** CSRF 功能禁用 **/

		authorizeConfigManager.configure(http.authorizeRequests());
	}

}
