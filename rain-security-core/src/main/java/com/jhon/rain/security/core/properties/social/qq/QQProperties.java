package com.jhon.rain.security.core.properties.social.qq;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * <p>功能描述</br> QQ登录属性</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QQProperties
 * @date 2017/10/20 18:27
 */
@Data
public class QQProperties extends SocialProperties {

	private String providerId = "qq";
}
