package com.fdmgroup.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private List<String> excludedUrls;
	private List<String> excludedExtensions;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("^^^^^^^AUTHENTICATION FILTER");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String incomingUrl = httpRequest.getServletPath();
		if (session != null && session.getAttribute("loggedInUser") == null && !excludedUrls.contains(incomingUrl)
				&& !isResource(incomingUrl)){
			request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);
		}
		else{
		// pass the request along the filter chain
		chain.doFilter(request, response);
		}
	}
	private boolean isResource(String path){
		for (String ext : excludedExtensions) {
			if(path.endsWith(ext)){
				return true;
			}
		}
		return false;	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//filter will not be executed on the following urls and extensions
		excludedUrls = new ArrayList<String>();
		excludedUrls.add("//WEB-INF/views/Home.jsp");
		excludedUrls.add("//WEB-INF/views/Registration.jsp");
		excludedUrls.add("/register");
		excludedUrls.add("/Home.jsp");
		excludedUrls.add("/authenticate");

		excludedExtensions = new ArrayList<String>();
		excludedExtensions.add(".css");
		excludedExtensions.add(".jpg");
		excludedExtensions.add(".js");
		excludedExtensions.add(".png");

		
	}

}
