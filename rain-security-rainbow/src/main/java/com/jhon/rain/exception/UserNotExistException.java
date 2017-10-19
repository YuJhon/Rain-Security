package com.jhon.rain.exception;

import lombok.Data;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName UserNotExistException
 * @date 2017/10/16 23:03
 */
@Data
public class UserNotExistException extends RainbowException {

	private String id;

	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}
}
