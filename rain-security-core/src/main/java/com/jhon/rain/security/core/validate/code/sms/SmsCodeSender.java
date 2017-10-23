package com.jhon.rain.security.core.validate.code.sms;

/**
 * <p>功能描述</br> 短信发送接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCodeSender
 * @date 2017/10/23 16:50
 */
public interface SmsCodeSender {

	/**
	 * <pre>发送短信验证码</pre>
	 * @param mobile 手机号
	 * @param code 验证码
	 */
	void send(String mobile, String code);
}
