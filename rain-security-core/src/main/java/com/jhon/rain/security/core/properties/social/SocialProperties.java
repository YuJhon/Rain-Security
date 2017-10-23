package com.jhon.rain.security.core.properties.social;

import com.jhon.rain.security.core.properties.social.qq.QQProperties;
import com.jhon.rain.security.core.properties.social.weixin.WeixinProperties;
import lombok.Data;

/**
 * <p>功能描述</br> 社交类型的属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SocialProperties
 * @date 2017/10/20 15:40
 */
@Data
public class SocialProperties {

	private String filterProcessesUrl = "/auth";

	private QQProperties qq = new QQProperties();

	private WeixinProperties weixin = new WeixinProperties();


}
