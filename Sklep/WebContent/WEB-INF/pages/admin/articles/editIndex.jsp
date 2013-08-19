<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<jsp:useBean id="pagedListHolder" scope="request"
	type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/administrator/articles/edit.htm" var="pagedLink">
	<c:param name="page" value="~" />
</c:url>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/browseArticles.js"></script>
<table class="table">
	<tr>
		<th>&nbsp;</th>
		<th>Nazwa produktu</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach items="${pagedListHolder.pageList}" var="item">
		<tr>
			<td><input type="checkbox" id="item${item.getId()}" /></td>
			<td>${item.getName()}</td>
			<td><a href="/administrator/articles/edit/${item.getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="deleteSingleItem"
				id="singleItem${item.getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>
<div class="row">
	<div class="span3">
		<a href="#" id="checkAll">Zaznacz wszystkie</a>
	</div>
	<div class="span9">
		<ul class="inline unstyled">
			<li>Zaznaczone:</li>
			<li><a href="#" id="deleteAllChecked">Usuń</a></li>
		</ul>
	</div>
</div>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
<%@include file="../after.jsp"%>
