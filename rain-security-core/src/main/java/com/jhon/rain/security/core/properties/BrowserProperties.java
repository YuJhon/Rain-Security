package com.jhon.rain.security.core.properties;

import com.jhon.rain.security.core.enums.LoginResponseTypeEnums;
import lombok.Data;

/**
 * <p>功能描述</br> 浏览器安全属性配置</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BrowserProperties
 * @date 2017/10/19 14:14
 */
@Data
public class BrowserProperties {

	private SessionProperties session = new SessionProperties();
	/** 注册页面 **/
	private String signUpUrl = "/rain-signUp.html";

	/** 默认为未授权的url SecurityConstants.DEFAULT_UNAUTHENTICATION_URL**/
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	/** 返回值类型枚举 **/
	private LoginResponseTypeEnums loginType = LoginResponseTypeEnums.JSON;
	/** 记住我的时间:一小时 **/
	private int rememberMeSeconds = 3600;
}
