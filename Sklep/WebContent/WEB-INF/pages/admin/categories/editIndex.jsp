<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@include file="../before.jsp"%>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/browseCategories.js"></script>
<table class="table">
	<tr>
		<th>Nazwa kategorii</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach var="c" items="${categories}">
		<tr>
			<td><c:forEach var="i" begin="1" end="${c.getNestingLevel()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
				${c.getName()}</td>
			<td><a href="/administrator/categories/edit/${c.getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="removeSingleItem" id="delete${c.getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>
<%@include file="../after.jsp"%>