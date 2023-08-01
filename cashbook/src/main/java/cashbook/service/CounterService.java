package cashbook.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import cashbook.model.*;
	//여기서 만드는 것: beginrow를 구하는 알고리즘 -> controller에서는 curdate, rowperpage를 보낸다
	//커넥션, 트랙잭션(connection conn) 처리
	//정형화 돼 있지 않다
public class CounterService {
	private CounterDao counterDao;
	//개별적으로 메서드를 4개 다 넣어주는게 아니라 counter service에만 넣어줄수 있다
	public void addCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		//BootListner 에서 classforname()실행하기 때문에 여기서 또 드라이버를 로드할 필요가 없다
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.insertCounter(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//연결을 닫는다
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.updateCounter(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//연결을 닫는다
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCounter() {
		int counter = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counter = counterDao.selectCounterCurdate(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//연결을 닫는다
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return counter;
	}
	
	public int getCounterAll() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		int totalCounter = 0;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			totalCounter = counterDao.selectCounterAll(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//연결을 닫는다
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalCounter;
	}
}

