<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- 세션정보를 JSP 파일에서 받아서 넘길 때 사용되는 문법임 property까지는 동일하게 작성하여 사용할 수 있음, var만 변경하여 나머지는 고정으로 사용-->
<!-- 어플리케이션을 구현하다보면 jsp에서 분기처리를 해서 권한에 따라서 다른 상황이 발생한다. 이런 상활일때 스프링 시큐리티를 사용하여 권한관리를 하고 있다면 간단하게 구현 가능하다 -->
<!-- 권한 정보는 세션에 저장돼 있기 때문에 가져다가 사용해도 무방하지만, Security 태크를 사용하면 좀 더 가시성 좋게 코드를 구성할 수 있다. -->

<!-- 세션정보 접근 -->
<!-- 이제 어디에서든지 principal.~ 로 접근가능 -->
<!-- principal property가 UserDetails임-->
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>

    <!-- 제이쿼리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Style -->
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/story.css">
    <link rel="stylesheet" href="/css/popular.css">
    <link rel="stylesheet" href="/css/profile.css">
    <link rel="stylesheet" href="/css/upload.css">
    <link rel="stylesheet" href="/css/update.css">
    <link rel="shortcut icon" href="/images/insta.svg">

    <!-- Fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
</head>

<body>

<header class="header">
    <div class="container">
        <a href="/image/story" class="logo">
            <img src="/images/logo.jpg" alt="">
        </a>
        <nav class="navi">
            <ul class="navi-list">
                <li class="navi-item"><a href="/image/story">
                    <i class="fas fa-home"></i>
                </a></li>
                <li class="navi-item"><a href="/image/popular">
                    <i class="far fa-compass"></i>
                </a></li>
                <li class="navi-item"><a href="/user/1">
                    <i class="far fa-user"></i>
                </a></li>
            </ul>
        </nav>
    </div>
</header>
