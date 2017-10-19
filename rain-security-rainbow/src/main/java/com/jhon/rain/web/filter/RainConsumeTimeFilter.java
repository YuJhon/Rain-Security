package com.jhon.rain.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * <p>功能描述</br> 接口消耗时间过滤器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainConsumeTimeFilter
 * @date 2017/10/17 11:34
 */
public class RainConsumeTimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init time filter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		long start = new Date().getTime();
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println("Time Filter 耗时：" + (new Date().getTime() - start));
	}

	@Override
	public void destroy() {
		System.out.println("destory time filter");
	}
}
