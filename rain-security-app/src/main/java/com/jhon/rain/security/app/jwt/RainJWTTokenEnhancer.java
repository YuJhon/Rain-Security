package com.jhon.rain.security.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述</br> 自定义实现TokenEnhancer</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainJWTTokenEnhancer
 * @date 2017/10/29 11:33
 */
public class RainJWTTokenEnhancer  implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String,Object> info = new HashMap<>();
		info.put("company","rain");
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
