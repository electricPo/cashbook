package cashbook.controller;

import java.io.IOException;

import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.service.CounterService;
import cashbook.vo.Member;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	//service 초기화
	private CounterService counterService = new CounterService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		
		//로그인 실패시 -> login doGet으로 보냄
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// view 에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance();
		
		// 출력하고자 하는 년도와 월, 일의 기본값은 이번달 1일
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		firstDay.set(Calendar.DATE, 1); // 1일
		
		// 출력하고자 하는 년도와 월이 매개값으로 넘어왔다면
		if(request.getParameter("targetYear") != null
				&& request.getParameter("targetMonth") != null) {
			
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			
			firstDay.set(Calendar.YEAR, targetYear);
			// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월 1, 년 +1
			// API에서 자동으로 Calendar.MONTH값으로 -1이 입력되면 월 12, 년 -1
			firstDay.set(Calendar.MONTH, targetMonth);
			targetYear = firstDay.get(Calendar.YEAR);
			targetMonth = firstDay.get(Calendar.MONTH);
		}
				
		// 오늘 날짜 
		Calendar today = Calendar.getInstance();
		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		
		
		//현재 방문자 수, 누적 방문자 수
		this.counterService = new CounterService();
		
		int counter = counterService.getCounter();
		int totalCounter = counterService.getCounterAll();
		//currentCounter -> countListner의 application
		int currentCounter = (int) getServletContext().getAttribute("currentCounter");
		
		System.out.println(counter +"<-counter / home");
		System.out.println(totalCounter +"<-totalCounter / home");
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("today", today);
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("todayMonth", todayMonth);
		request.setAttribute("memberId", member.getMemberId());
	    request.setAttribute("counter", counter);
	    request.setAttribute("totalCounter", totalCounter);
	    request.setAttribute("currentCounter", currentCounter);
	    
	    System.out.println(member.getMemberId() + "<-memberId");
		//어플리케이션은 jsp에서 호출 가능하다
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		
	}

	
}
