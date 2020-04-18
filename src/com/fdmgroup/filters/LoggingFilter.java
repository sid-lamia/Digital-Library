package com.fdmgroup.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("^^^^^^^LOGGING FILTER");

		String ip = request.getRemoteAddr();
		String dateTime = LocalDateTime.now().toString();
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String incomingUrl = httpRequest.getServletPath();
		
		System.out.println("IP: " + ip + "Date: " + dateTime + "Requested URL: " + incomingUrl);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
