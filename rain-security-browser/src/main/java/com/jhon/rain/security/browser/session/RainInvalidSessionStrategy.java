package com.jhon.rain.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> Session失效的策略 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainInvalidSessionStrategy
 * @date 2017/10/26 17:47
 */
public class RainInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	/**
	 * <pre>构造函数</pre>
	 *
	 * @param invalidSessionUrl Session失效重定向的url
	 */
	public RainInvalidSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		onSessionInvalid(request,response);
	}
}
