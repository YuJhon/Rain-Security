package com.jhon.rain.security.core.social.weixin.connect;

import com.jhon.rain.security.core.social.weixin.api.Weixin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * <p>功能描述</br> 微信连接工厂 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinConnectionFactory
 * @date 2017/10/26 11:07
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<Weixin> {

	/**
	 * <pre>构造函数</pre>
	 * @param providerId 服务提供者
	 * @param appId 应用id
	 * @param appSecret 应用secret
	 */
	public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeixinServiceProvider(appId,appSecret), new WeixinAdapter());
	}

	/**
	 * <pre>由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取</pre>
	 * @param accessGrant
	 * @return
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if (accessGrant instanceof WeixinAccessGrant) {
			return ((WeixinAccessGrant) accessGrant).getOpenId();
		}
		return null;
	}

	@Override
	public Connection<Weixin> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<Weixin>(getProviderId(),extractProviderUserId(accessGrant),accessGrant.getAccessToken(),
						accessGrant.getRefreshToken(),accessGrant.getExpireTime(),getOAuth2serviceProvider(),getApiAdapter(extractProviderUserId(accessGrant)));
	}

	@Override
	public Connection<Weixin> createConnection(ConnectionData data) {
		return new OAuth2Connection<Weixin>(data,getOAuth2serviceProvider(),getApiAdapter(data.getProviderUserId()));
	}

	private ApiAdapter<Weixin> getApiAdapter(String providerUserId){
		return new WeixinAdapter(providerUserId);
	}

	private OAuth2ServiceProvider<Weixin> getOAuth2serviceProvider(){
		return (OAuth2ServiceProvider<Weixin>) getServiceProvider();
	}
}
