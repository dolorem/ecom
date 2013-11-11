<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/media/js/tinymceInitTextarea.js"></script>
<form:form modelAttribute="site"
	action="/administrator/site/edit.htm" method="POST"
	class="form-horizontal" enctype="multipart/form-data">
	<form:hidden path="id" />
	<fieldset>
		<legend>Edycja strony</legend>
		<div class="control-group">
			<form:label class="control-label" path="title" for="title">Tytuł</form:label>
			<div class="controls">
				<form:input path="title" />
				<form:errors class="alert alert-error" path="title" />
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="description" path="description">Opis</form:label>
			<div class="controls">
				<form:input path="description" />
				<form:errors path="description" class="alert alert-error" /> 
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="tags" path="tags">Tagi</form:label>
			<div class="controls">
				<form:input path="tags"></form:input>
				<form:errors class="alert alert-error" path="tags"></form:errors>
			</div>
		</div>	
		<div class="control-group">
			<form:label class="control-label" for="path" path="path">Adres</form:label>
			<div class="controls">
				<form:input path="path"></form:input>
				<form:errors class="alert alert-error" path="path"></form:errors>
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="content" path="content">Treść</form:label>
			<div class="controls">
				<form:textarea path="content"></form:textarea>
				<form:errors class="alert alert-error" path="content"></form:errors>
			</div>
		</div>	
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Zapisz zmiany</button>
		</div>
	</fieldset>
</form:form>
<%@ include file="../after.jsp"%>