package com.jhon.rain.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * <pre>功能描述</br> QQ接口的实现类 ：QQ 互联 </pre>
 * APP ID:1106499826
 * APP KEY:Xdz0ovPT8JzqfyqB
 * @author jiangy19
 * @version v1.0
 * @FileName QQImpl
 * @date 2017/10/25 13:54
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	/** 获取QQ OpenId的链接地址 **/
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	/** 获取QQ UserInfo的链接地址 **/
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	/**
	 * 应用的appId
	 */
	private String appId;

	/**
	 * 应用的openId
	 */
	private String openId;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * <pre>构造函数</pre>
	 * @param accessToken 令牌
	 * @param appId 应用的appId
	 */
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID,accessToken);
		String result = getRestTemplate().getForObject(url,String.class);
		log.info("QQ接口请求获取结果：{}",result);
		this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
	}

	@Override
	public QQUserInfo getUserInfo() {
		String url = String.format(URL_GET_USERINFO,appId,openId);
		String result = getRestTemplate().getForObject(url,String.class);
		log.info("QQ获取用户信息：{}",result);

		QQUserInfo userInfo = null;
		try{
			userInfo = objectMapper.readValue(result,QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		}catch(Exception e){
		    throw new RuntimeException("获取用户信息失败",e);
		}
	}
}
