package com.jhon.rain.security.core.social;

import com.jhon.rain.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * <p>功能描述</br> TODO</p>
 *
 * UserConnection表结构--SpringSocial提供的
 * CREATE TABLE Rain_UserConnection (
       userId VARCHAR(255) NOT NULL,
			 providerId VARCHAR(255) NOT NULL,
			 providerUserId VARCHAR(255),
			 rank INT NOT NULL,
			 displayName VARCHAR(255),
			 profileUrl VARCHAR(512),
			 imageUrl VARCHAR(512),
			 accessToken VARCHAR(512) NOT NULL,
			 secret VARCHAR(512),
			 refreshToken VARCHAR(512),
			 expireTime BIGINT,
			 PRIMARY KEY (userId, providerId, providerUserId));
			 CREATE UNIQUE INDEX UserConnectionRank ON Rain_UserConnection(userId, providerId, rank);
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SocialConfig
 * @date 2017/10/25 16:17
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	/** 用户可以自定义是否需要实现:如果没有实现的话，则默认授权完成之后会跳转到signup页面 **/
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return usersConnectionRepository(connectionFactoryLocator);
	}


	@Bean
	public SpringSocialConfigurer rainSocialSecurityConfig(){
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		RainSpringSocialConfigurer configurer = new RainSpringSocialConfigurer(filterProcessesUrl);
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return configurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
		return new ProviderSignInUtils(connectionFactoryLocator,
						usersConnectionRepository(connectionFactoryLocator));
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator){
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("rain_");
		if (connectionSignUp != null){
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

}
