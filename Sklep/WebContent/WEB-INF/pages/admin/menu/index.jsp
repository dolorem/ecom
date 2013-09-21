<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/media/js/browseMenu.js"></script>
<table class="table">
	<tr>
		<th>Nazwa elementu</th>
		<th>Link</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach var="m" items="${menuItems}">
		<tr>
			<td><c:forEach var="i" begin="1" end="${m.getNestingLevel()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
			${m.getDescription()}</td>
			<td><a href="${m.getLink()}">${m.getLink()}</a></td>
			<td><a href="/administrator/menu/edit/${m.getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="delete" id="delete${m.getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>

<div id="myModal" class="modal hide fade in">
	<div class="modal-header">Potwierdzenie</div>
	<div class="modal-body">Czy na pewno usunąć wybrany element?</div>
	<div class="modal-body alert alert-danger">Wszystkie podelementy zostaną usunięte</div>
	<div class="modal-footer">
		<button class="btn btn-danger" id="accept">Tak</button>
		<button class="btn btn-primary" id="reject">Nie</button>
	</div>
</div>

<%@ include file="../after.jsp"%>