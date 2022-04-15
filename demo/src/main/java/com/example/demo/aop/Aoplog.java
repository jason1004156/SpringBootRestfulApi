package com.example.demo.aop;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

@Aspect
@Component
public class Aoplog {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String AOPWEBLOG = "aopWebLog()";

	// 執行緒局部變數，用於解決多執行緒衝突
	ThreadLocal<Long> startTime = new ThreadLocal<>();

	// 定義切點
	@Pointcut("bean(*Controller)")
	public void aopWebLog() {

	}

	@Before(AOPWEBLOG)
	public void doBefore(JoinPoint j) {
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes == null) {
			return;
		}
		HttpServletRequest r = attributes.getRequest();
		log.info("URL:" + r.getRequestURL().toString());
		log.info("HTTP方法:" + r.getMethod());
		log.info("IP位址:" + r.getRemoteAddr());
		log.info("類別的方法:" + j.getSignature().getDeclaringTypeName() + "." + j.getSignature().getName());
		log.info("參數:" + r.getQueryString());
        log.info("request body:" + getPayload(r));
		
	}

	@AfterReturning(pointcut = AOPWEBLOG, returning = ("retObject"))
	public void doAfterReturning(Object retObject) {
		log.info("回應值:" + retObject);
		log.info("費時:" + (System.currentTimeMillis() - startTime.get()));
	}
	
	@AfterThrowing(pointcut = AOPWEBLOG, throwing = "e")
	public void doAfterThorowing(JoinPoint j,Exception e) {
		log.error("執行例外", e);
	}
	
	 private String getPayload(HttpServletRequest request) {
	        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
	        if (wrapper != null) {
	            byte[] buf = wrapper.getContentAsByteArray();
	            if (buf.length > 0) {
	                try {
	                    int length = Math.min(buf.length, 1024);// 最多只印出1024長度
	                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
	                } catch (UnsupportedEncodingException ex) {
	                    return "[unknown]";
	                }
	            }
	        }
	        return "";
	    }
}
