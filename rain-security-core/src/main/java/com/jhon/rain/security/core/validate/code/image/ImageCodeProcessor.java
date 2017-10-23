package com.jhon.rain.security.core.validate.code.image;

import com.jhon.rain.security.core.validate.code.base.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * <p>功能描述</br> 图片验证码的处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ImageCodeProcessor
 * @date 2017/10/23 16:23
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	@Override
	protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
		ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}
}
