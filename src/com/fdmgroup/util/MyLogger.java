package com.fdmgroup.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyLogger {
	
	public static Logger logger = (Logger) LogManager.getLogger("ProjectLogger");
	
	public static void trace(String msg){
		logger.trace(msg);
	}
	public static void debug(String msg){
		logger.debug(msg);
	}	
	public static void info(String msg){
		logger.info(msg);
	}	
	public static void warning(String msg){
		logger.warn(msg);
	}
	public static void error(String msg){
		logger.error(msg);
	}
	public static void fatal(String msg){
		logger.fatal(msg);
	}
}
