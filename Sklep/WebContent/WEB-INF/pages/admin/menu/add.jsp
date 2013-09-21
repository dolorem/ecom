<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ include file="../before.jsp"%>
<form:form modelAttribute="menuFormModel"
	action="/administrator/menu/edit.htm" method="POST"
	class="form-horizontal" enctype="multipart/form-data">
	<form:hidden path="id" />
	<fieldset>
		<legend>Edycja elementu menu</legend>
		<div class="control-group">
			<form:label class="control-label" path="description" for="description">Nazwa</form:label>
			<div class="controls">
				<form:input path="description" />
				<form:errors class="alert alert-error" path="description" />
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="link"
				path="link">Link</form:label>
			<div class="controls">
				<form:input path="link" />
				<form:errors path="link" class="alert alert-error" /> 
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="parentId"
				path="parentId">Element nadrzędny</form:label>
			<div class="controls">
				<form:select path="parentId">
					<option value="0">Brak</option>
					<tg:optionsMenuOffset categories="${menuOffset}"
						checked="${menuFormModel.getParentAsArrayList()}" indentsPerLevel="2" />
				</form:select>
				<form:errors path="parentId" class="alert alert-error" />
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Zapisz zmiany</button>
		</div>
	</fieldset>
</form:form>
<%@ include file="../after.jsp"%>