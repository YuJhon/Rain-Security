package com.jhon.rain.security.core.properties;

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
@ConfigurationProperties(prefix = "rainbow.security")
@Data
public class SecurityProperties {

	/**
	 * 浏览器配置属性
	 **/
	private BrowserProperties browser = new BrowserProperties();


}
