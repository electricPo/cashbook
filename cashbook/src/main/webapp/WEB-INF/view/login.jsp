<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASH BOOK</title>
</head>
<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<body class="loginBody">
	    <div id="login">
	        <h3 class="text-center text-white pt-5">Login form</h3>
	        <div class="container">
	            <div id="login-row" class="row justify-content-center align-items-center">
	                <div id="login-column" class="col-md-6">
	                    <div id="login-box" class="col-md-12">
	                        <form id="login-form" class="form" action="${pageContext.request.contextPath}/login" method="post">
	                            <h3 class="text-center text-info">Login</h3>
	                            <div class="form-group">
	                                <label for="username" class="text-info">Username:</label><br>
	                                <input type="text" name="memberId" id="username" class="form-control" value="${loginMember}">
	                            </div>
	                            <div class="form-group">
	                                <label for="password" class="text-info">Password:</label><br>
	                                <input type="password" name="memberPw" id="password" class="form-control">
	                            </div>
	                            <div class="form-group">
	                                <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="saveId" type="checkbox" value="y"></span></label><br>
	                                 <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
	                            </div>
	                            <br>
	                            <div id="register-link" class="text-right">
	                                <a href="${pageContext.request.contextPath}/addMember" class="text-info">Register here</a>
	                            </div>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
</body>

<script>
	$.ajax({
		async: false,
		url : '/HomeRest',
		data : {id : 'id'},
		success : function(){
			console.log('success');
		},
		error : function(){
			console.log('error');
		}
	})
</script>
</html>
