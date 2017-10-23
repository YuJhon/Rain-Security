package com.jhon.rain.security.core.properties.social.weixin;

import lombok.Data;

/**
 * <p>功能描述</br> 微信相关的属性配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinProperties
 * @date 2017/10/20 18:27
 */
@Data
public class WeixinProperties {

	/**
	 * 第三方Id,用来决定发起第三方登录的url，默认是weixin
	 */
	private String providerId = "weixin";

}
