package com.example.mbboard.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;


@WebListener
public class ContextBootListener implements ServletContextListener {

  
    public ContextBootListener() {
        
    }


    public void contextInitialized(ServletContextEvent sce)  { 
    	// 외부저장공간 : DB
        // (TOMCAT) WAS 안에 저장공간(web server) : application, session, request
    	// 웹브라우저 안에 저장 공간 : cookie, api
    	ServletContext sc= sce.getServletContext(); // sce 이벤트가 발생한 객체 -> TOMCAT
    	sc.setAttribute("currentConnectCount", 0); // JSP : application.setAttribute();
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }
	
}
