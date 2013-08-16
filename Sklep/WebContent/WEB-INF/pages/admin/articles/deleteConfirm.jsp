<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<div class="alert alert-danger">
Czy na pewno chcesz usunąć przedmiot ${article.getName()}? Tej operacji nie da się odwrócić!
</div>
<div style="text-align: center;">
	<a href="/administrator/articles/delete/${article.getId()}/confirm.htm"><button class="btn btn-danger">Usuń</button></a>
	<buttn class="btn btn-primary">Anuluj</buttn>
</div>
<%@include file="../after.jsp" %>
