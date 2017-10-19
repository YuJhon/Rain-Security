package com.jhon.rain.dto;

import lombok.Data;

/**
 * <p>功能描述</br> 文件信息载体 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName FileInfo
 * @date 2017/10/18 10:24
 */
@Data
public class FileInfo {

	/**
	 * 文件路径
	 */
	private String path;

	public FileInfo(String path) {
		this.path = path;
	}
}
