package com.jhon.rain.security.core.validate.code.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>功能描述</br> 验证码属性类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BaseValidateCode
 * @date 2017/10/20 9:21
 */
@Data
public class BaseValidateCode implements Serializable {

	private static final long serialVersionUID = -822036038363272345L;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 过期时间
	 */
	private LocalDateTime expireTime;

	public BaseValidateCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public BaseValidateCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}

	public boolean isExpired(){
		return LocalDateTime.now().isAfter(expireTime);
	}

}
