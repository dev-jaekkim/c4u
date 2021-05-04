<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	@import url('https://fonts.googleapis.com/css?family=Raleway&display=swap');
    body *{
        box-sizing: border-box;
        font-family:'Raleway', sans-serif;
        color : #222;
        line-height : 1.5em;
        font-weight:300;
    }
    
    div.loginAction{
        margin-left: 40%;
        margin-top: 40%;
        margin-bottom: 40%;
    }
    
    span{
        display: inline-block;
    }
    
    span.loginText{
        font-size: 22px;
        font-weight: bold;
        margin-bottom: 15px;
        left: 50px;
    }

    div.loginAction>span{
        list-style: none;
        padding-left: 0px;
        position: relative;
        display: inline-block;
    }

    span.emailInputSpan, span.pwdInputSpan{
        width: 250px;
        margin-bottom: 10px;
        border-radius: 4px;
        margin-left: 2px;
    }
    
    input.emailInput{
    	margin-left: 52px;
    }
    
    input.btLogin{
        width: 226px;
        margin-bottom: 10px;
        background-color: rgb(255, 87, 87);
        color:white;
        font-weight: bold;
        border: 0px;
    }

    span.signIn, span.findPwd{
        font-size: 8px;
        line-height: 20px;
        letter-spacing: -0.015em;
    }
    span.signIn{
        margin-left: 38px;
    }
    span.findPwd{
        margin-left: 48px;
    }
   
</style>
</head>
<body>
<section>
	<div class="loginAction">
		<form method="post" action="${pageContext.request.contextPath}/login">
			<span class="loginText">로그인</span><br>
			<span class="emailInputSpan">ID : <input type="text" name="username" placeholder="이메일을 입력하세요" class="emailInput"></span><br>
			<span class="pwdInputSpan">Password: <input type="password" name="password" placeholder="비밀번호를 입력하세요" ></span><br>
			<span><input name="${_csrf.parameterName}" 
		       type="hidden" 
		       value="${_csrf.token}"></span>
			<span><input type="submit" value="login" class="btLogin"></span>
		</form>
		<span class="signIn">아직 계정이 없으신가요? <a href="signup1.0.html">회원가입</a></span><br>
        <span class="findPwd"><a href="findpwd.html">혹시 비밀번호를 잊으셨나요?</a></span>
	</div>
</section>
</body>
</html>