package com.jhon.rain.security.core.social.weixin.api;

/**
 * <p>功能描述</br> 微信API接口调用 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName Weixin
 * @date 2017/10/26 10:39
 */
public interface Weixin {

	/**
	 * <pre>获取用户信息</pre>
	 * @param openId 用户的openId
	 * @return
	 */
	WeixinUserInfo getUserInfo(String openId);
}
