package com.coeding.springmvc.interceptors;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WrapperFilter extends OncePerRequestFilter {
	protected Logger log = LoggerFactory.getLogger(WrapperFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);
		log.debug("=============== Wrapper Filter ===================");
		filterChain.doFilter(wrappingRequest, wrappingResponse);
		if (wrappingResponse.getContentType() != null
				&& wrappingResponse.getContentType().contains("application/json")) {
			if (wrappingResponse.getContentAsByteArray() != null
					&& wrappingResponse.getContentAsByteArray().length != 0) {
				ObjectMapper objectMapper = new ObjectMapper();
				log.debug("Response Body : {}", objectMapper.readTree(wrappingResponse.getContentAsByteArray()));
			}
		}
		wrappingResponse.copyBodyToResponse();
//		Intercepter 의 afterCompletion 에서 접근 못함
//		ERROR: org.springframework.web.servlet.HandlerExecutionChain - HandlerInterceptor.afterCompletion threw exception
//		java.lang.ClassCastException: class org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse cannot be cast to class org.springframework.web.util.ContentCachingResponseWrapper (org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse and org.springframework.web.util.ContentCachingResponseWrapper are in unnamed module of loader org.apache.catalina.loader.ParallelWebappClassLoader @6622fc65)
	}
}
