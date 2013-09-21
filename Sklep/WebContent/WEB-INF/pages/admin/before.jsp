<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
<meta charset="UTF-8">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="dropdown"><a
							href="/administrator/articles/index.htm">Artykuły<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/administrator/articles/add.htm">Dodaj</a></li>
								<li><a href="/administrator/articles/edit.htm">Przeglądaj</a>
							</ul></li>
						<li class="dropdown"><a
							href="/administrator/categories/index.htm">Kategorie<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/administrator/categories/add.htm">Dodaj</a></li>
								<li><a href="/administrator/categories/edit.htm">Przeglądaj</a></li>
							</ul></li>
						<li class="dropdown"><a href="#">Producenci<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/administrator/manufacturers/add.htm">Dodaj</a></li>
								<li><a href="/administrator/manufacturers/view.htm">Przeglądaj</a></li>
							</ul>
						</li>
						<li	class="dropdown"><a href="#">Menu<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/administrator/menu/add.htm">Dodaj</a></li>
							<li><a href="/administrator/menu/edit.htm">Edytuj</a></li>
						</ul>
						</li>
					</ul>
					<ul class="nav pull-right">
						<li><a href="/auth/logout.htm">Wyloguj się</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span12">
				<c:if test="${success != null}">
					<div class="alert alert-success">${success}</div>
				</c:if>
				<c:if test="${error != null}">
					<div class="alert alert-danger">${error}</div>
				</c:if>