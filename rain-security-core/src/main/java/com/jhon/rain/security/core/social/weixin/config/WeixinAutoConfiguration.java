package com.jhon.rain.security.core.social.weixin.config;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.properties.social.weixin.WeixinProperties;
import com.jhon.rain.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * <p>功能描述</br> 微信登录配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinAutoConfiguration
 * @date 2017/10/26 11:36
 */
@Configuration
@ConditionalOnProperty(prefix = "rainbow.security.social.weixin",name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(),
						weixinConfig.getAppId(),weixinConfig.getAppSecret());
	}

}
