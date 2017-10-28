package com.jhon.rain.security.core.properties.code.base;

import lombok.Data;

/**
 * <p>功能描述</br> 验证码的基础属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BaseCodeProperties
 * @date 2017/10/20 15:45
 */
@Data
public class BaseCodeProperties {
	/**
	 * 验证码内容的长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = 60;
	/**
	 * 拦截的接口地址[哪些地址需要验证码拦截]
	 */
	private String url;

}
