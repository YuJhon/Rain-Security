package com.jhon.rain.security.app;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.properties.oauth2.OAuth2ClientProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> 认证服务器配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainAuthorizationServerConfig
 * @date 2017/10/28 13:41
 */
@Configuration
@EnableAuthorizationServer
public class RainAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private TokenStore tokenStore;

	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
						.userDetailsService(userDetailsService)
						.tokenStore(tokenStore);

		if (jwtAccessTokenConverter != null && jwtTokenEnhancer!=null){
			TokenEnhancerChain chain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			chain.setTokenEnhancers(enhancers);

			endpoints.tokenEnhancer(chain)
							.accessTokenConverter(jwtAccessTokenConverter);
		}
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		if (ArrayUtils.isNotEmpty(securityProperties.getOauth().getClients())){
			for (OAuth2ClientProperties config : securityProperties.getOauth().getClients()) {
				builder.withClient(config.getClientId())
						.secret(config.getClientSecret())
						.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
						.refreshTokenValiditySeconds(config.getRefreshTokenValiditySeconds())
						.authorizedGrantTypes("password","refresh_token")
						.scopes("all","read","write");
			}
		}
	}
}
