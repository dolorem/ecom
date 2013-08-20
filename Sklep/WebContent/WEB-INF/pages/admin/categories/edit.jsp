<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.smiechmateusz.utils.Pair"%>
<%@ page import="com.smiechmateusz.model.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../before.jsp"%>
<form:form modelAttribute="categoryFormModel"
	action="/administrator/categories/edit.htm" method="POST"
	class="form-horizontal">
	<form:hidden path="id" />
	<fieldset>
		<legend>Edycja kategorii</legend>
		<div class="control-group">
			<form:label class="control-label" for="name" path="name">Nazwa</form:label>
			<div class="controls">
				<form:input path="name" />
				<form:errors path="name" class="alert alert-error" />
			</div>
		</div>
		<div class="control-group">
			<form:label class="control-label" for="parentId" path="parentId">Kategoria nadrzędna</form:label>
			<div class="controls">
				<form:select path="parentId">
					<option value="0">Brak kategorii</option>
					<tg:optionsCategoryOffset categories="${categories}"
						checked="${category.getParentAsArrayList()}" indentsPerLevel="2" />
				</form:select>
				<form:errors path="parentId" class="alert alert-error" />
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Zapisz
				kategorię</button>
		</div>
	</fieldset>
</form:form>
<%@include file="../after.jsp"%>
