package cn.skyjilygao.springboot.core.interceptor;

import cn.skyjilygao.springboot.controller.SkyException;
import cn.skyjilygao.springboot.core.enums.HttpStatus;
import cn.skyjilygao.springboot.core.request.RequestUtil;
import cn.skyjilygao.springboot.core.request.ServletRequestPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取请求信息
 * @author skyjilygao
 * @since 1.0
 * @date 20200305
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		ServletRequestPojo pojo = new ServletRequestPojo(request);
		// 记录请求信息，可以最终数据。注意：这里只能针对get请求，post请求无效。
		log.info("request info=" + pojo.toString());
		String ip = RequestUtil.getIPAddress(request);
		// ip控制访问
		if(!RequestUtil.isAllow(ip)){
			log.info("error_code: 2550, ip=" + ip + ",不再白名单，无法访问");
			throw new SkyException(HttpStatus.FORBIDDEN, new Exception("ip=" + ip + ",不再白名单，无法访问"));
		}
		return true;
	}
}
