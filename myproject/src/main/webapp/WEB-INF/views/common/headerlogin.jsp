<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>헤더</title>
<!-- CSS -->
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
  <!-- 카테고리 바 -->
  <div class="category-bar">
    <img src="<c:url value='/resources/images/main_icon.png'/>" alt="WAVE 로고" class="logo">

    <ul class="category-list">
      <li class="category-item">
        <a href="<c:url value='/resources/site/mypage.html'/>">마이페이지</a>
      </li>
      <li class="category-item" data-sub="sub-politics">정치</li>
      <li class="category-item" data-sub="sub-economy">경제</li>
      <li class="category-item" data-sub="sub-society">사회</li>
      <li class="category-item" data-sub="sub-culture">문화</li>
      <li class="category-item" data-sub="sub-world">세계</li>
      <li class="category-item" data-sub="sub-sports">스포츠</li>
    </ul>

    <!-- 카테고리바 내부 -->
    <div class="subcategory-container">
      <div class="subcategory" id="sub-politics">
        <span>선거</span><span>외교</span><span>의회</span>
      </div>
      <div class="subcategory" id="sub-economy">
        <span>증권</span><span>부동산</span><span>산업</span>
      </div>
      <div class="subcategory" id="sub-society">
        <span>교육</span><span>노동</span><span>사건사고</span>
      </div>
      <div class="subcategory" id="sub-culture">
        <span>영화</span><span>방송</span><span>전시</span>
      </div>
      <div class="subcategory" id="sub-world">
        <span>미국</span><span>중국</span><span>일본</span>
      </div>
      <div class="subcategory" id="sub-sports">
        <span>축구</span><span>야구</span><span>e스포츠</span>
      </div>
    </div>
  </div>

</body>
</html>