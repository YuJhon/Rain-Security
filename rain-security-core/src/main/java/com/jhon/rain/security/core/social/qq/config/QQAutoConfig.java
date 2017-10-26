package com.jhon.rain.security.core.social.qq.config;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.properties.social.qq.QQProperties;
import com.jhon.rain.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * <p>功能描述</br> QQ的自定义配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QQAutoConfig
 * @date 2017/10/25 15:54
 */
@Configuration
@ConditionalOnProperty(prefix = "rainbow.security.social.qq",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
	}
}
