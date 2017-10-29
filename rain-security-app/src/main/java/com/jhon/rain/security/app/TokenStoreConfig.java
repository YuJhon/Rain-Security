package com.jhon.rain.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
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
@Component
public class TokenStoreConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public TokenStore redisTokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}
}
