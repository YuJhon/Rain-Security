package com.jhon.rain.security.core.validate.code.config;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.base.ValidateCodeGenerator;
import com.jhon.rain.security.core.validate.code.image.ImageCodeGenerator;
import com.jhon.rain.security.core.validate.code.sms.SmsCodeDefaultSender;
import com.jhon.rain.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>功能描述</br> 校验码的Bean初始化 检查和默认配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeBeanConfig
 * @date 2017/10/23 16:44
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new SmsCodeDefaultSender();
	}
}
