<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/media/js/addArticle.js"></script>
<form action="/administrator/articles/edit.htm" method="POST" class="form-horizontal" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${article.getId()}" />
	<fieldset>
		<legend>Edycja artykułu</legend>
		<div class="control-group">
			<label class="control-label" for="name">Nazwa produktu</label>
			<div class="controls">
				<input type="text" id="name" name="name" value="${article.getName()}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description">Opis produktu</label>
			<div class="controls">
				<textarea id="description" rows="10" cols="15" name="description">${article.getDescription()}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="categories" name="categories">Kategorie</label>
			<div class="controls">
				<select multiple="multiple" name="categories" id="categories">
					<option value="0">Brak kategorii</option>
					<c:forEach var="c" items="${categories}">
						<option value="${c.getLeft().getId()}" <c:if test="${article.getCategories().contains(c.getLeft())}">selected="selected"</c:if>><c:forEach var="i" begin="0" end="${c.getRight()}">&nbsp;</c:forEach>${c.getLeft().getName()}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="available">Dostępny</label>
			<div class="controls">
				<input type="checkbox" name="available" id="available" <c:if test="${article.isAvailable()}">checked</c:if>>
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Zapisz zmiany</button>
		</div>
	</fieldset>
</form>
<%@include file="../after.jsp" %>
