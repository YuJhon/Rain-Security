package com.jhon.rain.security.core.properties.code;

import com.jhon.rain.security.core.properties.code.image.ImageCodeProperties;
import com.jhon.rain.security.core.properties.code.sms.SmsCodeProperties;
import lombok.Data;

/**
 * <p>功能描述</br> 验证码的属性配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeProperties
 * @date 2017/10/20 15:38
 */
@Data
public class ValidateCodeProperties {

	/**
	 * 图片验证码的属性
	 */
	private ImageCodeProperties image = new ImageCodeProperties();

	/**
	 * 短信验证码的属性
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

}
