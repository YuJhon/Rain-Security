package com.jhon.rain.security.core.properties;

/**
 * <p>功能描述</br> 安全配置的常量设置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SecurityConstants
 * @date 2017/10/19 13:36
 */
public class SecurityConstants {

	/**
	 * 当请求需要身份认证的时候，默认跳转的url
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * 默认用户名密码登录请求的url
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

	/**
	 * 默认手机验证码登录请求url
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

	/**
	 * 默认登录页面
	 */
	public static final String DEFAULT_LOGIN_PAGE_URL = "/rain-signIn.html";

	/**
	 * Session 失效默认跳转页面
	 */
	public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";
}
