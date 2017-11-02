package com.jhon.rain.security.core.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * <p>功能描述</br> 认证相关的扩展点配置，配置在这里的Bean,业务系统都可以通过声明同类型或者同名的bean来覆盖安全</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AuthenticationBeanConfig
 * @date 2017/11/2 20:17
 */
@Configuration
public class AuthenticationBeanConfig {

	/**
	 * <pre>默认的密码处理器</pre>
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * <pre>默认的认证器</pre>
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService userDetailsService() {
		return new DefaultUserDetailsService();
	}

	/**
	 * <pre>默认的社交认证器</pre>
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SocialUserDetailsService.class)
	public SocialUserDetailsService socialUserDetailsService() {
		return new DefaultSocialUserDetailsService();
	}

}
