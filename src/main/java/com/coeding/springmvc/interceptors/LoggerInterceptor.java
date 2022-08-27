package com.coeding.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @see servlet-context.xml
 */

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	protected Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================          PRE         ======================================");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================           POST         ======================================\n");
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (log.isDebugEnabled()) {
			// Security 에서도 RequestWrapper 를 통하여 @AuthenticationPrincipal를 통해 들어오는 정보를 포함하는 작업을 하기 때문에 중복 충돌 방지
			//	Intercepter 에서는 Filter 에서의 ContentCachingRequestWrapper 를 접근 못함
			//	java.lang.ClassCastException: HeaderWriterFilter$HeaderWriterResponse cannot be cast to ContentCachingResponseWrapper
//			if (!request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
//				log.debug(" Request URI \t:  " + request.getRequestURI());
//				ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
//				if (cachingRequest.getContentType() != null
//						&& cachingRequest.getContentType().contains("application/json")) {
//					if (cachingRequest.getContentAsByteArray() != null
//							&& cachingRequest.getContentAsByteArray().length != 0) {
//						ObjectMapper objectMapper = new ObjectMapper();
//						log.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
//					}
//				}
//			}
//			if (!response.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
//				ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
//				if (cachingResponse.getContentType() != null
//						&& cachingResponse.getContentType().contains("application/json")) {
//					if (cachingResponse.getContentAsByteArray() != null
//							&& cachingResponse.getContentAsByteArray().length != 0) {
//						ObjectMapper objectMapper = new ObjectMapper();
//						log.debug("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
//					}
//				}
//			}
			log.debug("======================================           AFTER          ======================================\n");
		}
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
