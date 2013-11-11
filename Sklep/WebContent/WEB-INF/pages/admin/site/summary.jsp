<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../before.jsp" %>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/media/js/browseSite.js"></script>
<table class="table">
	<tr>
		<th>Nazwa</th>
		<th>Link</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach var="site" items="${sites}">
		<tr>
			<td>${site.getTitle()}</td>
			<td><a href="${site.getPath()}" target="_blank">${site.getPath()}</a></td>
			<td><a href="/administrator/site/edit/${site.getId()}.htm">Edytuj</a></td>
			<td><a href="#" class="delete" id="delete${site.getId()}">Usuń</a></td>
		</tr>
	</c:forEach>
</table>

<div id="myModal" class="modal hide fade in">
	<div class="modal-header">Potwierdzenie</div>
	<div class="modal-body">Czy na pewno usunąć wybraną stronę?</div>
	<div class="modal-footer">
		<button class="btn btn-danger" id="accept">Tak</button>
		<button class="btn btn-primary" id="reject">Nie</button>
	</div>
</div>

<%@ include file="../after.jsp" %>