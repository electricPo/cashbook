package cashbook.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.CashbookDao;
import cashbook.vo.Cashbook;
import cashbook.vo.Member;


@WebServlet("/cashbook")
public class CashbookController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");	    
	    
		//로그인 실패시 -> login doGet으로 보냄
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		//view에 넘겨줄 날짜정보
		String memberId = loginMember.getMemberId();
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));

		//날짜에 해당하는 가계부 목록을 조회
		List<Cashbook>list=new CashbookDao().selectCashbookListByDate(memberId, targetYear, targetMonth, targetDate);
		System.out.println(list + "list");
		
		//조회된 가계부 목록과 목표 날짜를 request에 저장
		request.setAttribute("memberId", loginMember);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("list", list);
		
		//달력에 가계부 목록의 값을 설정
		// /cashbook 경로로 get 요청이 들어올 때 해당 날짜에 대한 가계부 목록을 조회 -> 결과를 cashbook.jsp 에 전달해 출력
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}
	
}
