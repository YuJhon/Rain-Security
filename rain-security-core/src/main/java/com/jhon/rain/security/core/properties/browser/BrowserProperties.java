package com.jhon.rain.security.core.properties.browser;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.enums.LoginResponseTypeEnum;
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

	/** 默认为未授权的url RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL**/
	private String loginPage = RainSecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	/** 返回值类型枚举 **/
	private LoginResponseTypeEnum loginType = LoginResponseTypeEnum.JSON;

	/** 记住我的时间:一小时 **/
	private int rememberMeSeconds = 3600;
}
