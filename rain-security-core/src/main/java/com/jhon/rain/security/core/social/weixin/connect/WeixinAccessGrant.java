package com.jhon.rain.security.core.social.weixin.connect;

import lombok.Data;
import org.springframework.social.oauth2.AccessGrant;

/**
 * <p>功能描述</br>
 * 1.微信的access_token信息，与标准的OAuth2协议不同，
 *   微信在获取access_token是会同时返回openId,
 *   并没有单独的通过accessToken换取openId的服务
 * 2.所以在这里继承了编制的AccessGrant,添加了openId字段，作为对微信access_token信息的封装
 * </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinAccessGrant
 * @date 2017/10/26 10:04
 */
@Data
public class WeixinAccessGrant extends AccessGrant {

	private static final long serialVersionUID = -2420849938009169689L;

	/**
	 * 微信的唯一标识
	 */
	private String openId;

	public WeixinAccessGrant() {
		super("");
	}

	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}
}
