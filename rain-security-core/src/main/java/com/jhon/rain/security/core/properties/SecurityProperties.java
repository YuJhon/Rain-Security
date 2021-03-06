package com.jhon.rain.security.core.properties;

import com.jhon.rain.security.core.properties.browser.BrowserProperties;
import com.jhon.rain.security.core.properties.code.ValidateCodeProperties;
import com.jhon.rain.security.core.properties.oauth2.OAuth2Properties;
import com.jhon.rain.security.core.properties.social.SocialProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>功能描述</br> 配置文件信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SecurityProperties
 * @date 2017/10/19 11:23
 */
@Data
@ConfigurationProperties(prefix = "rainbow.security")
public class SecurityProperties {

	/**
	 * 浏览器配置属性
	 **/
	private BrowserProperties browser = new BrowserProperties();

	/**
	 * 验证码配置属性
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

	/**
	 * 社交配置属性
	 */
	private SocialProperties social = new SocialProperties();

	/**
	 * OAuth2配置属性
	 */
	private OAuth2Properties oauth = new OAuth2Properties();

}
