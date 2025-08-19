<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>WAVE</title>

  <!-- CSS -->
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>

<body>
  <!-- 타이틀 -->
  <div class="title-banner">
    <img src="<c:url value='/resources/images/title.png'/>" alt="타이틀 이미지">
  </div>

  <!-- 뉴스카드 -->
  <div class="news-grid">
    <a href="https://news.naver.com" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/naver-news-icon.png'/>" alt="네이버뉴스" class="news-logo">
      <div class="ch-name">네이버뉴스</div>
    </a>

    <a href="https://biz.chosun.com/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/chosun-biz.webp'/>" alt="조선비즈" class="news-logo">
      <div class="ch-name">조선비즈</div>
    </a>

    <a href="https://www.khan.co.kr/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/khan-news.svg'/>" alt="경향신문" class="news-logo">
      <div class="ch-name">경향신문</div>
    </a>

    <a href="https://www.joongang.co.kr/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/joongang-news.png'/>" alt="중앙일보" class="news-logo">
      <div class="ch-name">중앙일보</div>
    </a>

    <a href="https://www.donga.com/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/donga-news.jpg'/>" alt="동아일보" class="news-logo">
      <div class="ch-name">동아일보</div>
    </a>

    <a href="https://www.hankyung.com/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/hankyung-news.png'/>" alt="한국경제" class="news-logo">
      <div class="ch-name">한국경제</div>
    </a>

    <a href="https://news.daum.net/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/daum-news.png'/>" alt="다음" class="news-logo">
      <div class="ch-name">다음</div>
    </a>

    <a href="https://imnews.imbc.com/" target="_blank" class="news-card">
      <img src="<c:url value='/resources/images/mbc-news.png'/>" alt="MBC뉴스" class="news-logo">
      <div class="ch-name">MBC뉴스</div>
    </a>
  </div>

  <!-- 스크립트 -->
  <script>
    // 컨텍스트패스가 필요한 Ajax 호출에 쓰기
    const CTX = '${ctx}';
    // 예: fetch(`${CTX}/member/listMembers.do`)
  </script>
  <script src="<c:url value='/resources/js/main.js'/>"></script>

  
</body>
</html>
