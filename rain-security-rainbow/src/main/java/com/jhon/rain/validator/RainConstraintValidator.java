package com.jhon.rain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>功能描述</br> 校验类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainConstraintValidator
 * @date 2017/10/16 22:14
 */
public class RainConstraintValidator implements ConstraintValidator<RainValidator, Object> {

	// @Autowired 可以自动注入


	@Override
	public void initialize(RainValidator rainValidator) {
		System.out.println("validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println("Validate Value Is "+value);
		/** 校验逻辑 TODO **/
		return true;
	}
}
