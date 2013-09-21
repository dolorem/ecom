<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:forEach var="cssItem" items="${css}">
	<link rel="stylesheet" href="${cssItem}" />
</c:forEach>
<c:forEach var="jsItem" items="${js}">
	<script type="text/javascript" src="${jsItem}"></script>
</c:forEach>
<style>
body {
	padding-top: 60px;
}

ul.nav li.dropdown:hover ul.dropdown-menu {
	display: block;
	margin: 0px;
}

.myMenuNav {
	float: none;
	text-align: center;
}

.navbar .nav,.navbar .nav>li {
	float: none;
	display: inline-block;
	vertical-align: top;
}

.navbar-inner {
	text-align: center;
}

.masthead .navbar .nav li a {
	text-align: center;
	border-left: 1px solid rgba(255, 255, 255, .75);
	border-right: 1px solid rgba(0, 0, 0, .1);
}

/* Customize the navbar links to be fill the entire space of the .navbar */

ul.nav li.dropdown:hover ul.dropdown-menu ul.dropdown-menu
{
 display: none;   
}

ul.nav li.dropdown li.dropdown-submenu:hover ul.dropdown-menu
{
    display: block;
    margin: 0px;
}

ul.nav li.dropdown:hover ul.dropdown-menu ul.dropdown-menu ul.dropdown-menu
{
	display: none;
}

ul.nav li.dropdown li.dropdown-submenu li.dropdown-submenu:hover ul.dropdown-menu
{
	display: block;
	margin: 0px;
}

/* Customize the navbar links to be fill the entire space of the .navbar */

.container .navbar .navbar-inner {
    padding: 0;
}

.container .navbar .nav {
    margin: 0;
    display: table;
    width: 100%;
}

.container .navbar .nav > li {
    display: table-cell;
    width: 1%;
    float: none;
    text-align: center;
}


.container .navbar .nav li:first-child a {
    border-left: 0;
    border-radius: 3px 0 0 3px;
}

.container .navbar .nav li:last-child a {
    border-right: 0;
    border-radius: 0 3px 3px 0;
}

.dropdown-menu{
left:auto;
}
</style>
<meta charset="UTF-8">
</head>
<body>
	<c:choose>
		<c:when test="${logged}">
			<%@include file="headerLogged.jsp"%>
		</c:when>
		<c:otherwise>
			<%@include file="headerNotLogged.jsp"%>
		</c:otherwise>
	</c:choose>
	<div class="container">
		<%@include file="menu.jsp"%>
		<div class="row">
			<div class="span3"><%@include file="sidebar.jsp"%></div>
			<div class="span9">