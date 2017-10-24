package com.jhon.rain.security.core.validate.code.base;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 验证码处理器辅助类 ：根据验证的类型（imageCode/smsCode）分别来获取不同的验证码的处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeProcessorHolder
 * @date 2017/10/23 22:19
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * <pre>获取处理器 </pre>
	 * @param type 验证码类型（image/sms）
	 * @return
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeTypeEnum type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	/**
	 * <pre>获取处理器</pre>
	 * @param type 验证码类型
	 * @return 验证码处理器
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}
}
