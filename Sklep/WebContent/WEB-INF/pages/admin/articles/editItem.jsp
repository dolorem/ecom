<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/media/js/addArticle.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form modelAttribute="article" action="/administrator/articles/edit.htm" method="POST" class="form-horizontal" enctype="multipart/form:form-data">
	<form:hidden path="id" />
	<fieldset>
		<legend>Edycja artykułu</legend>
		<div class="control-group">
			<form:label class="control-label" path="name" for="name">Nazwa produktu</form:label>
			<div class="controls">
				<form:input path="name" />
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="description" path="description">Opis produktu</form:label>
			<div class="controls">
				<form:textarea path="description" />
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label" for="categories" name="categories">Kategorie</label>
			<div class="controls">
				<select multiple="multiple" name="categories" id="categories">
					<option value="0">Brak kategorii</option>
					<c:forEach var="c" items="${categories}">
						<option value="${c.getLeft().getId()}" <c:if test="${article.getCategories().contains(c.getLeft())}">selected="selected"</c:if>><c:forEach var="i" begin="0" end="${c.getRight()}">&nbsp;</c:forEach>${c.getLeft().getName()}</option>
					</c:forEach>
				</select>
			</div>
		</div>-->
		<div class="control-group">
			<form:label class="control-label" for="categories" path="categories">Kategorie</form:label>
			<div class="controls">
				<form:select multiple="true" path="categories">
					<option value="0">Brak kategorii</option>
					<c:forEach var="c" items="${categories}">
						<option value="${c.getLeft().getId()}" <c:if test="${article.getCategories().contains(c.getLeft())}">selected="selected"</c:if>><c:forEach var="i" begin="0" end="${c.getRight()}">&nbsp;</c:forEach>${c.getLeft().getName()}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="available" path="available">Dostępny</form:label>
			<div class="controls">
				<form:checkbox path="available" />
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Zapisz zmiany</button>
		</div>
	</fieldset>
</form:form>


<%@include file="../after.jsp" %>
