package com.jhon.rain.security.core.enums;

import com.jhon.rain.security.core.constants.RainSecurityConstants;

/**
 * <p>功能描述</br> 校验码的类型枚举 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeTypeEnum
 * @date 2017/10/20 9:51
 */
public enum ValidateCodeTypeEnum {

	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return RainSecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	/**
	 * 图片验证码
	 */
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return RainSecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};


	/**
	 * <pre>校验时从请求中获取的参数名称</pre>
	 *
	 * @return
	 */
	public abstract String getParamNameOnValidate();

}
