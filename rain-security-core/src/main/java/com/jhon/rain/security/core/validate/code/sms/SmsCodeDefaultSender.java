package com.jhon.rain.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>功能描述</br> 默认发送短信的接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCodeDefaultSender
 * @date 2017/10/23 16:52
 */
@Slf4j
public class SmsCodeDefaultSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		log.info("向手机号：{} 发送验证码：{}", mobile, code);
	}
}
