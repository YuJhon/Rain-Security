package com.jhon.rain.security.core.validate.code.repository;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 验证码的数据逻辑 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeRepository
 * @date 2017/10/28 16:54
 */
public interface ValidateCodeRepository {

	/**
	 * <pre>验证码的存储</pre>
	 * @param request
	 * @param code
	 * @param validateCodeType
	 */
	void save(ServletWebRequest request, BaseValidateCode code, ValidateCodeTypeEnum validateCodeType);

	/**
	 * <pre>获取验证码</pre>
	 * @param request
	 * @param validateCodeType
	 * @return
	 */
	BaseValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType);

	/**
	 * <pre>移除验证码</pre>
	 * @param request
	 * @param validateCodeType
	 */
	void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType);

}
