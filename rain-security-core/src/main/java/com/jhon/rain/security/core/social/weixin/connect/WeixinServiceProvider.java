package com.jhon.rain.security.core.social.weixin.connect;

import com.jhon.rain.security.core.social.weixin.api.Weixin;
import com.jhon.rain.security.core.social.weixin.api.WeixinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * <p>功能描述</br> 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinServiceProvider
 * @date 2017/10/26 10:56
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {

	/**
	 * 微信获取授权码code的url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


	/**
	 * <pre></pre>
	 * @param appId 应用的appid
	 * @param appSecret 应用的appsecret
	 */
	public WeixinServiceProvider(String appId, String appSecret) {
		super(new WeixinOAuth2template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
	}

	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinImpl(accessToken);
	}
}
