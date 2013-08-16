<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/browseCategories.js"></script>
<c:if test="${success != null}">
	<div class="row">
		<div class="alert alert-success">${success}</div>
	</div>
</c:if>
<c:if test="${error != null}">
	<div class="row">
		<div class="alert alert-error">${error}</div>
	</div>
</c:if>
<table class="table">
	<tr>
		<th>Nazwa kategorii</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach var="c" items="${categories}">
		<tr>
			<td>
				<c:forEach var="i" begin="1" end="${c.getRight()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
						${c.getLeft().getName()}
			</td>
			<td><a href="/administrator/categories/edit/${c.getLeft().getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="removeSingleItem" id="delete${c.getLeft().getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>
<%@include file="../after.jsp" %>