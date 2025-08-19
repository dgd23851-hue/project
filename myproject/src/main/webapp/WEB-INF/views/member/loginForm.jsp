<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>로그인 | WAVE 뉴스</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>" />
</head>
<body>
  <div class="login-bg">
    <div class="login-container">
      <div class="login-tabs">
        <button class="tab-btn active" id="loginTab" type="button">로그인</button>
        <button class="tab-btn" id="signupTab" type="button">회원가입</button>
      </div>

      <!-- 에러 표시 -->
      <c:if test="${not empty error}">
        <div class="error-box">아이디 또는 비밀번호가 올바르지 않습니다.</div>
      </c:if>

      <!-- 로그인 -->
      <form class="login-form" id="loginForm" method="post"
            action="<c:url value='/member/login.do'/>">
        <label for="login-id">아이디</label>
        <input type="text" id="login-id" name="id" required />

        <label for="login-pw">비밀번호</label>
        <input type="password" id="login-pw" name="pwd" required />

        <button type="submit" class="login-action-btn">로그인</button>
      </form>

      <!-- 회원가입 -->
      <form class="signup-form" id="signupForm" method="post" style="display:none"
            action="<c:url value='/member/addMember.do'/>"
            onsubmit="return validateSignup();">
        <label for="signup-id">아이디</label>
        <input type="text" id="signup-id" name="id" required />

        <label for="signup-pw">비밀번호</label>
        <input type="password" id="signup-pw" name="pwd" required />

        <label for="signup-pw2">비밀번호 확인</label>
        <input type="password" id="signup-pw2" required />

        <label for="signup-name">이름</label>
        <input type="text" id="signup-name" name="name" required />

        <label for="signup-email">이메일</label>
        <input type="email" id="signup-email" name="email" required />

        <button type="submit" class="login-action-btn">회원가입</button>
      </form>
    </div>
  </div>

  <script>
    const loginTab=document.getElementById('loginTab');
    const signupTab=document.getElementById('signupTab');
    const loginForm=document.getElementById('loginForm');
    const signupForm=document.getElementById('signupForm');
    loginTab.onclick=()=>{loginTab.classList.add('active');signupTab.classList.remove('active');loginForm.style.display='';signupForm.style.display='none';};
    signupTab.onclick=()=>{signupTab.classList.add('active');loginTab.classList.remove('active');loginForm.style.display='none';signupForm.style.display='';};
    function validateSignup(){const p=document.getElementById('signup-pw').value.trim();const p2=document.getElementById('signup-pw2').value.trim();if(p!==p2){alert('비밀번호가 일치하지 않습니다.');return false;}return true;}
  </script>
</body>
</html>
