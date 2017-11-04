package com.jhon.rain.rbac.web.controller.support;

import lombok.Data;

/**
 * <p>功能描述</br> 返回对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SimpleResponse
 * @date 2017/11/4 14:33
 */
@Data
public class SimpleResponse {

	public SimpleResponse(Object content) {
		this.content = content;
	}

	private Object content;

}
