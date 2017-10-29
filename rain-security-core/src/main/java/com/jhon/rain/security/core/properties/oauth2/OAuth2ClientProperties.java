package com.jhon.rain.security.core.properties.oauth2;

import lombok.Data;

/**
 * <p>功能描述</br> OAuth2Client的配置属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OAuth2ClientProperties
 * @date 2017/10/29 9:40
 */
@Data
public class OAuth2ClientProperties {

	/**
	 * 应用名称
	 */
	private String clientId;

	/**
	 * 应用密码
	 */
	private String clientSecret;

	/**
	 * token过期时间
	 */
	private int accessTokenValiditySeconds;//如果为0则永远不会过期
}
