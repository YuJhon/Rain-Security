package com.jhon.rain.security.browser;

import com.jhon.rain.security.core.authentication.FormAuthenticationConfig;
import com.jhon.rain.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.jhon.rain.security.core.authorize.AuthorizeConfigManager;
import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * <p>功能描述</br> PC端安全配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName FormAuthenticationConfig
 * @date 2017/10/18 19:44
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private SpringSocialConfigurer rainSocialSecurityConfig;

	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;

	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;

	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/** 基础的配置
		 * 1.自定义登录请求地址
		 * 2.自定义登录验证的接口
		 * 3.登录成功和失败的处理
		 * **/
		formAuthenticationConfig.configure(http);
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
					.rememberMe()
					.tokenRepository(persistentTokenRepository())
					.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
					.userDetailsService(userDetailsService)
				.and()
					.sessionManagement()
						.invalidSessionStrategy(invalidSessionStrategy)
						.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
						.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionPreventsLogin())
						.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
					.logout()
						/** 自定义logout的配置 **/
						.logoutUrl("/signOut")
						/** logoutSuccessUrl 和 logoutSuccessHandler 两个配置是互斥的 **/
						//.logoutSuccessUrl("rain-logout.html")
						.logoutSuccessHandler(logoutSuccessHandler)
						.deleteCookies("JSESSIONID")
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
							"/user/register"
					).permitAll()
					// 简单配置角色和访问权限
					.antMatchers(HttpMethod.GET,"/user*//*").hasRole("ADMIN")
					.anyRequest()
					.authenticated()*/
				.and()
			.csrf().disable(); /** CSRF 功能禁用 **/

			authorizeConfigManager.configure(http.authorizeRequests());
	}


	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		/** 项目启动的时候创建表 **/
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
