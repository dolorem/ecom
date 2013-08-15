<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@page import="com.smiechmateusz.utils.Pair" %>
<%@page import="com.smiechmateusz.model.Category" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<c:choose>
	<c:when test=""></c:when>
</c:choose>
<form action="/administrator/categories/create.htm" method="POST" class="form-horizontal">
	<fieldset>
		<legend>Dodawanie kategorii</legend>
		<div class="control-group">
			<label class="control-label" for="name">Nazwa kategorii</label>
			<div class="controls">
				<input type="text" id="name" name="name" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="primaryCategory">Kategoria nadrzędna</label>
			<div class="controls">
				<select name="primaryCategory" id="primaryCategory">
					<option value="0">Brak kategorii</option>
					<c:forEach var="c" items="${categories}">
						<option value="${c.getLeft().getId()}"><c:forEach var="i" begin="0" end="${c.getRight()}">&nbsp;</c:forEach>${c.getLeft().getName()}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Dodaj artykuł</button>
		</div>
	</fieldset>
</form>
<%@include file="../after.jsp" %>
