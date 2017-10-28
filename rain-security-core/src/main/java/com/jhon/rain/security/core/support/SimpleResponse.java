package com.jhon.rain.security.core.support;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>功能描述</br> 简单的响应对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SimpleResponse
 * @date 2017/10/19 11:54
 */
//@Data
public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = 3884010765763620754L;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
