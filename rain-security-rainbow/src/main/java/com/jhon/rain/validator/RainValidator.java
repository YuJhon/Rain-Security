package com.jhon.rain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>功能描述</br> 自定义校验规则 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainValidator
 * @date 2017/10/16 22:11
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RainConstraintValidator.class)
public @interface RainValidator {

	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
