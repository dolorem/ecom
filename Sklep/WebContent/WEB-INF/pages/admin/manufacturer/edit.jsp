<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<script type="text/javascript" src="/media/js/jQuery.js"></script>
<script type="text/javascript" src="/media/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/media/js/editManufacturer.js"></script>
<form:form modelAttribute="manufacturerFormModel"
	action="/administrator/manufacturers/edit.htm" method="POST"
	class="form-horizontal">
	<form:hidden path="id" />
	<fieldset>
		<legend>Edycja producenta</legend>
		<div class="control-group">
			<form:label for="name" path="name" class="control-label">Nazwa</form:label>
			<div class="controls">
				<form:input path="name" />
				<form:errors path="name" class="alert alert-error" />
			</div>
		</div>
		<div class="control-group">
			<form:label for="description" path="description"
				class="control-label">Opis</form:label>
			<div class="controls">
				<form:textarea path="description" />
				<form:errors path="description" class="alert alert-error" />
			</div>
		</div>
		<div class="form-actions">
			<button class="btn btn-primary" type="submit">Zapisz
				producenta</button>
		</div>
	</fieldset>
</form:form>
<%@include file="../after.jsp"%>
