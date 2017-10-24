package com.jhon.rain.security.core.validate.code.sms;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;
import com.jhon.rain.security.core.validate.code.base.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 短信验证码的生成器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCodeGenerator
 * @date 2017/10/23 16:37
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public SmsCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new SmsCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
