package com.jhon.rain.security.core.social.qq.connect;

import com.jhon.rain.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * <p>功能描述</br> QQ 工厂类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QQConnectionFactory
 * @date 2017/10/25 15:02
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * <pre>构造方法的重写 </pre>
	 * @param providerId 用户唯一标识id
	 * @param appId 应用的appid
	 * @param appSecret 应用的appsecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
	}
}
