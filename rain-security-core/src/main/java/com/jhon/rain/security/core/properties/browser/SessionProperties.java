package com.jhon.rain.security.core.properties.browser;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import lombok.Data;

/**
 * <p>功能描述</br> Session相关的属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SessionProperties
 * @date 2017/10/19 14:22
 */
@Data
public class SessionProperties {

	/**
	 * 同一个用户在系统中最大的Session数，默认是1
	 */
	private int maximumSessions = 1;

	/**
	 * 达到最大session时，是否阻止新的登录请求，默认是false,不阻止，新的登陆会将旧的登录失效
	 */
	private boolean maxSessionPreventsLogin;

	/**
	 * session失效时跳转的地址
	 */
	private String sessionInvalidUrl = RainSecurityConstants.DEFAULT_SESSION_INVALID_URL;
}
