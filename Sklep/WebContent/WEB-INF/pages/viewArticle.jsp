<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="before.jsp"%>
<h1>${article.getName()}</h1>
<div class="row">
	<div class="span6">
		<img src="/media/uploadedImages/${article.getMainImage().getPath()}" />
	</div>
	<div class="span3">span3</div>
</div>
<%@include file="after.jsp"%>