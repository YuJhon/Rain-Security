package com.jhon.rain.security.core.properties.oauth2;

import lombok.Data;

/**
 * <p>功能描述</br> OAuth2的配置属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OAuth2Properties
 * @date 2017/10/29 9:40
 */
@Data
public class OAuth2Properties {

	/**
	 * OAuth2认证的客户端配置属性[支持多个应用的配置]
	 */
	private OAuth2ClientProperties[] clients = {};

	/**
	 * JWT的秘钥
	 */
	private String jwtSignInKey = "jhonrain";
}
