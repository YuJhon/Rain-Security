package com.jhon.rain.security.core.constants;

/**
 * <p>功能描述</br> 安全配置的常量设置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainSecurityConstants
 * @date 2017/10/19 13:36
 */
public class RainSecurityConstants {

	/**
	 * <pre>默认的处理验证码的url前缀</pre>
	 */
	public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

	/**
	 * <pre>当请求需要身份认证的时候，默认跳转的url</pre>
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * <pre>默认用户名密码登录请求的url</pre>
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

	/**
	 * <pre>默认手机验证码登录请求url</pre>
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

	/**
	 * <pre>默认的openid登录处理的接口地址</pre>
	 */
	public static final String DEFAULT_LOGIN_PROCESS_URL_OPENID = "/authentication/openid";

	/**
	 * <pre>默认登录页面</pre>
	 */
	public static final String DEFAULT_LOGIN_PAGE_URL = "/rain-signIn.html";

	/**
	 * <pre>验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称,表单属性名称</pre>
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

	/**
	 * <pre>验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称,表单属性名称</pre>
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

	/**
	 * <pre>发送短信验证码 或 验证短信验证码时，传递手机号的的参数的名称,表单属性名称</pre>
	 */
	public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * <pre>Session 失效默认跳转页面</pre>
	 */
	public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

	/**
	 * <pre>openId参数名称</pre>
	 */
	public static final String DEFAULT_PARAMETER_NAME_OPENID = "openId";

	/**
	 * <pre>服务提供商的参数名称</pre>
	 */
	public static final String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";


}
