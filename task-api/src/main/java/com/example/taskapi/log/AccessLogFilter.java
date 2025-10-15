package com.example.taskapi.log;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccessLogFilter implements Filter{
    private static final Logger log = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws   IOException, ServletException {

        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            chain.doFilter(req,res);
        } finally {
            long ms = System.currentTimeMillis() - start;
            log.info("ACCESS method={} path={} status={} durationMs={}",
                request.getMethod(), request.getRequestURI(), response.getStatus(), ms);
        }
    }
}
