package com.astontech.hr;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Way to fix the cors issue
//@Component
//public class CorsFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
//        httpServletResponse.addHeader("Access-Control-Expose-Headers", "xsrf-token");
//        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
//            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//    }
//}
