package com.jhon.rain.security.core.validate.code.sms;

import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;

import java.time.LocalDateTime;

/**
 * <p>功能描述</br> 短信验证码 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCode
 * @date 2017/10/20 9:40
 */
public class SmsCode extends BaseValidateCode {

	/**
	 * <pre>短信验证码构造器</pre>
	 * @param code 验证码
	 * @param expireIn 过期的时间(s)
	 */
	public SmsCode(String code, int expireIn) {
		super(code, expireIn);
	}

	/**
	 * <pre>短信验证码构造器</pre>
	 * @param code 验证码
	 * @param expireTime 过期日期
	 */
	public SmsCode(String code, LocalDateTime expireTime) {
		super(code, expireTime);
	}
}
