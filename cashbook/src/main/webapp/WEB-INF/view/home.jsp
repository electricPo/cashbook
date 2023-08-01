<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASH BOOK</title>
<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<!-- Chart.js cdn -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>
<script>
    $(document).ready(function() {
    	
		const targetYear = ${targetYear};
		const targetMonth = ${targetMonth};
		const memberId = "${memberId}";
		console.log(targetYear+"trtrtr");
		console.log(targetMonth+"mmm");
		console.log(memberId+"ddd");
		
		//동기호출로 x와 y 값을 셋팅한다
		
		
		const x = [];
		const y = [];
	
		$.ajax({
			async : false, //true (비동기, 기본값이다), false(동기)
		    url : '/HomeRest',
		    type : 'get',
		    datatype : "json",
		    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		    data:{  targetYear: targetYear,
		            targetMonth: targetMonth,
		            memberId: memberId
		          },
		    success : function(model) {
		      
		      // JSON 데이터를 객체로 변환
		      const parsedData = JSON.parse(model);
		      console.log(parsedData);
		      
		      $.each(parsedData, function(index, item) {
		        // chart 모델 생성
		        x.push(item.cashword);
		        y.push(item.cnt);
		      })
		      
		      console.log("ajax성공");
		      //chart 모델 생성
		      //원래는 map 타입의 리스트였지만 각 타입마다 배열이 생성
		      
		    },
		    error : function() {
		      console.log("아작스실패");
		    }
		  }); 
			  
		  new Chart("target2", {
		    type: "bar",
		    data: {
		      labels: x,
		      datasets: [{
		        //backgroundColor: barColors,
		        data: y
		      }]
		    }
		    //options: {...}
		  });
    })

	</script>
<body class="home-body">

<jsp:include page="/layout/header.jsp"></jsp:include>
	<div class="row row-first">
		<section id="chart" class="col-lg-8  col-lg-offset-3">
			<div class="container">
				<h3>${memberId}님의 월별 해시태그 사용 비율</h3>
					<table>
						<thead>
							<tr>
								<th>이름</th>
								<th>해시태그 수</th>
							</tr>
						</thead>
						<tbody id="target">
						</tbody>
					</table>
					<canvas id="target2" style="width:100%;max-width:800px"></canvas>
			</div>
		</section>

		<section class="latest-expense col-lg-4 col-lg-offset-3">
			<div class="container">
				최신 소비 지출 내역(10개?)
			</div>
		</section>
	</div>
	<section id="counter" class="counter section-bg">
	    <div class="container" data-aos="fade-up">
	      <div class="section-title">
	        <div class="row">
	          <div class="counter-info">
	            <ul class="counter-list">
	              <li>현재접속자: ${currentCounter}</li>
	              <li>오늘접속자 수: ${counter}</li>
	              <li>누적접속자 수: ${totalCounter}</li>
	            </ul>
	          </div>
	        </div>
	      </div>
	    </div>
	</section>
    
 
<jsp:include page="/layout/footer.jsp"></jsp:include>
</body>
</html>