package com.jhon.rain.web.controller;

import com.jhon.rain.security.core.validate.code.image.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * <p>功能描述</br> 验证码的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeController
 * @date 2017/10/20 10:12
 */
@RestController
public class ValidateCodeController {

	private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	/**
	 * <pre>获取验证码图片的接口</pre>
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException
	 */
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/** 1.根据随机数生成图片 **/
		ServletWebRequest webRequest = new ServletWebRequest(request);
		ImageCode imageCode = createImageCode(webRequest);
		/** 2.将随机码放入到session中 **/
		sessionStrategy.setAttribute(webRequest,SESSION_KEY,imageCode);
		/** 3.再将生成的图片写到接口的响应中 **/
		ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
	}

	/**
	 * <pre>生成图像验证码</pre>
	 * @param request
	 * @return
	 */
	private ImageCode createImageCode(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
						67);
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
						23);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		g.dispose();

		return new ImageCode(image, sRand, 6000);
	}

	/**
	 * 生成随机背景条纹
	 *
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
