package cashbook.model;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cashbook.vo.Member;



public class MemberDao {
	//회원 탈퇴
	public int removeMember(String memberId, String memberPw) {
		int row = 0;
		
		//db접속
		Connection conn = null;
		PreparedStatement stmt = null;
		
		//SQL 가져오기
		String sql ="DELETE FROM member WHERE member_id=? AND member_pw= PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			row = stmt.executeUpdate();
			
			
			
		}catch(Exception e1){
			e1.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	//회원 상세정보
	public Member selectMemberOne(String memberId)  {
		Member member = null;
		//db접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//SQL 가져오기
		String sql = "SELECT member_id memberId, member_pw memberPw, updatedate, createdate FROM member where member_id=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs  = stmt.executeQuery();
				if(rs.next()) {
					member = new Member();
					member.setMemberId(rs.getString("memberId"));
					member.setMemberPw(rs.getString("memberPw"));
					member.setCreatedate(rs.getString("createdate"));
					member.setUpdatedate(rs.getString("updatedate"));
					
				}
		}catch(Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return member;
	}
	
	
	
	//회원가입 메서드
	public int insertMember(Member member) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO member VALUES(?, PASSWORD(?), NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row += stmt.executeUpdate(); //성공했을 때
			
			return row;

		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
		
	}
	
	//로그인 메서드
	public Member selectMemberById(Member paramMember) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw = PASSWORD(?)";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}		
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return returnMember;
	}
}

