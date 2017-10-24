#### Spring中@Autowired 注解 注入map、list与@Qualifier
##### 1.接口定义
```java
package com.jhon.rain.security.core.validate.code.base;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 校验码的生成器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BaseValidateCodeGenerator
 * @date 2017/10/20 9:43
 */
public interface ValidateCodeGenerator {

	/**
	 * <pre>生成校验码</pre>
	 *
	 * @param request request对象
	 * @return
	 */
	BaseValidateCode generate(ServletWebRequest request);
}
```
##### 2.接口实现
```java
package com.jhon.rain.security.core.validate.code.image;

import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.base.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>功能描述</br> 图片验证码的生成器【默认调用方式】 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ImageCodeGenerator
 * @date 2017/10/23 16:23
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

	/**
	 * 系统配置项
	 */
	private SecurityProperties securityProperties;

	@Override
	public ImageCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
						securityProperties.getCode().getImage().getWidth());

		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
						securityProperties.getCode().getImage().getHeight());

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
		for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		g.dispose();

		return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
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
```

##### 3.代码注入
注意：对于向map中注入，bean注入后string为该bean的id。

![SpringAutowired——Map](../photos/SpringAutowired%20Map.png)
```java
package com.jhon.rain.security.core.validate.code.base;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * <p>功能描述</br> 验证码处理的抽象类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractValidateCodeProcessor
 * @date 2017/10/23 20:10
 */
public abstract class AbstractValidateCodeProcessor<C extends BaseValidateCode> implements ValidateCodeProcessor {

	/**
	 * 操作Session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Override
	public void create(ServletWebRequest request) throws Exception {
		/** 1.生成 **/
		C validateCode = generate(request);
		/** 2.保存到session **/
		save(request, validateCode);
		/** 3.发送 **/
		send(request, validateCode);
	}

	/**
	 * <pre>发送验证码 ：抽象类，子类自己实现 </pre>
	 *
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * <pre>保存验证码属性到session中 </pre>
	 *
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
	}

	@Override
	public void validate(ServletWebRequest request) {
		/** 获取属性 **/
		ValidateCodeTypeEnum processorType = getValidateCodeType(request);
		String sessionKey = getSessionKey(request);
		C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

		/** 在http request中携带的验证码的值 **/
		String codeInRequest;
		try {
			/** processorType.getParamNameOnValidate() 是Form表单中的属性名称 **/
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
							processorType.getParamNameOnValidate());

		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, sessionKey);
	}

	/**
	 * 获取session中的信息
	 *
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
	}

	/**
	 * <pre>根据请求的url获取校验码的类型</pre>
	 *
	 * @param request
	 * @return
	 */
	private ValidateCodeTypeEnum getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeTypeEnum.valueOf(type.toUpperCase());
	}

	/**
	 * <pre>生成验证码</pre>
	 *
	 * @param request
	 * @return
	 */
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器{" + generatorName + "}不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

}
```
##### 4.调试信息
![Spring_Autowired_Map-Debug-Info](../photos/SpringAutoMapDebugInfo.png)


