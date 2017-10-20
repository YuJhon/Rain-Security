package com.jhon.rain.security.core.properties.code.image;

import com.jhon.rain.security.core.properties.code.base.BaseCodeProperties;
import lombok.Data;

/**
 * <p>功能描述</br> 图片验证码的属性 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ImageCodeProperties
 * @date 2017/10/20 15:39
 */
@Data
public class ImageCodeProperties extends BaseCodeProperties {

	/**
	 * 图片的宽度
	 */
	private int width = 67;

	/**
	 * 图片的高度
	 */
	private int height = 23;

	/**
	 * 默认的图片验证码的内容的长度（4个字符）
	 */
	public ImageCodeProperties(){
		setLength(4);
	}
}
