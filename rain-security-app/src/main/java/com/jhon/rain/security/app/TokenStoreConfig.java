package com.jhon.rain.security.app;

import com.jhon.rain.security.app.jwt.RainJWTTokenEnhancer;
import com.jhon.rain.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> RedisToken的存储配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName TokenStoreConfig
 * @date 2017/10/29 10:32
 */
@Configuration
public class TokenStoreConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;


	@Bean
	@ConditionalOnProperty(prefix = "rainbow.security.oauth",name = "storeType",havingValue = "redis")
	public TokenStore redisTokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}

	/**
	 * JWT的Token配置
	 */
	@Configuration
	@ConditionalOnProperty(prefix = "rainbow.security.oauth",name = "storeType",havingValue = "jwt",matchIfMissing = true)
	public static class JwtTokenConfig{

		@Autowired
		private SecurityProperties securityProperties;

		@Bean
		public TokenStore jwtTokenStore(){
			return new JwtTokenStore(jwtAccessTokenConverter());
		}

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
			accessTokenConverter.setSigningKey(securityProperties.getOauth().getJwtSignInKey());//签名用的秘钥
			return accessTokenConverter;
		}

		@Bean
		@ConditionalOnMissingBean(name="jwtTokenEnhancer")
		public TokenEnhancer jwtTokenEnhancer(){
			return new RainJWTTokenEnhancer();
		}
	}
}
