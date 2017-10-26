package com.jhon.rain.security.core.validate.code.image;

import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * <p>功能描述</br> 图片验证码 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ImageCode
 * @date 2017/10/20 9:27
 */
@Data
public class ImageCode extends BaseValidateCode {

	private static final long serialVersionUID = 3581890885561813775L;
	/**
	 * 图片资源
	 */
	private BufferedImage image;

	/**
	 * <pre>图形验证码构造器</pre>
	 * @param image 图片资源
	 * @param code 验证码
	 * @param expireIn 过期的时间(s)
	 */
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	/**
	 * <pre>图形验证码构造器</pre>
	 * @param image 图形资源
	 * @param code 验证码
	 * @param expireTime 过期日期
	 */
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
}
