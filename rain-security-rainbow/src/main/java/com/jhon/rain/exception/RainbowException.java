package com.jhon.rain.exception;

import lombok.Data;

/**
 * <p>功能描述</br> 基础的异常类定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainbowException
 * @date 2017/10/16 22:21
 */
@Data
public class RainbowException extends RuntimeException {

	private String msg;

	public RainbowException(String msg) {
		super(msg);
	}


}
