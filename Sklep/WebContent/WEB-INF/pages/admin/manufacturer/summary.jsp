<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/media/js/browseManufacturers.js"></script>
<table class="table">
	<tr>
		<th>Nazwa producenta</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach var="m" items="${manufacturers}">
		<tr>
			<td>${m.getName()}</td>
			<td><a href="/administrator/manufacturers/edit/${m.getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="removeSingleItem" id="item${m.getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>

<div id="myModal" class="modal hide fade in">
	<div class="modal-header">Potwierdzenie</div>
	<div class="modal-body">Czy na pewno usunąć tego producenta?</div>
	<div class="modal-footer">
		<button class="btn btn-danger" id="accept">Tak</button>
		<button class="btn btn-primary" id="reject">Nie</button>
	</div>
</div>

<%@include file="../after.jsp"%>
