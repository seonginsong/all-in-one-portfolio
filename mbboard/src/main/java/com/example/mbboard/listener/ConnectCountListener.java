package com.example.mbboard.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mbboard.dto.ConnectCount;
import com.example.mbboard.service.IRootService;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConnectCountListener implements HttpSessionListener {
    @Autowired IRootService rootService;
	
	public void sessionCreated(HttpSessionEvent se)  { 
		log.info("새로운 세션 생성");
		
		// currentConnectCount ++
		se.getSession().getServletContext().setAttribute("currentConnectCount", (Integer)(se.getSession().getServletContext().getAttribute("currentConnectCount")) + 1);
		
    	// 처음 세션이 만들어졌을때
    	// 클라이언트(쿠키) - 서버(세션)
    	// 처음 - 쿠키(empty) - new 세션.id - response함께 클라이언트에 전송
		ConnectCount cc = new ConnectCount();
		cc.setMemberRole("ANONYMOUS");
		if(rootService.getConnectDateByKey(cc) == null) {
			rootService.addConnectCount(cc);
		} else {
			rootService.modifyConnectCount(cc);
		}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// session.invalidate()일때 작동 or session timeout시
    	// currentConnectCount
    	se.getSession().getServletContext().setAttribute("currentConnectCount", (Integer)(se.getSession().getServletContext().getAttribute("currentConnectCount")) - 1);
    	// JSP : application.getAttribute("currencConnectCount")
    	// ${application.getA~~}
    }
	
}
