package cashbook.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.MemberDao;
import cashbook.vo.Member;



@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	//로그인 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		// session 유효성 검사 : 로그인 되어있을 때 home로 이동
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		//쿠키에 저장된 아이디가 있다면 request 속성에 저장
		Cookie[] cookies = request.getCookies();
			if(cookies != null) {
			
			for(Cookie c : cookies) {
				if(c.getName().equals("loginMember") == true) {
					request.setAttribute("loginMember", c.getValue());
				}
			}
		}
		
		//로그인 안 되어있을 때 login폼으로 이동
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	//로그인 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//파라미터 값 받아오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("입력id: "+ memberId);
		
		//dao 호출
		Member member = new Member(memberId, memberPw, null, null);
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
		
		//로그인 실패시 -> login doGet으로 보냄
		if(loginMember == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		
		//로그인 성공시 :cookieId에 저장
		//로그인 세션저장 + 로그인id를 쿠키(만료기간설정)에 저장 -> loginId로 저장
		//성공하면 어디로? -> 로그인 성공페이지로 redirect -> / home
		
		//idsave 체크값이 넘어왔다면
		if(request.getParameter("saveId") != null) {
			Cookie loginIdCookie = new Cookie("loginMember", memberId);
			loginIdCookie.setMaxAge(60*30);//초*분*시간 -> 30분
			response.addCookie(loginIdCookie);
		}
		
		// 로그인 성공시 : session에 저장 후 cashbook.jsp으로
		HttpSession session = request.getSession();
		System.out.println("로그인 성공 -> home으로 이동");
		session.setAttribute("loginMember", loginMember);
		
		//출력하는 뷰로 포워딩하기
	  	request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

}
