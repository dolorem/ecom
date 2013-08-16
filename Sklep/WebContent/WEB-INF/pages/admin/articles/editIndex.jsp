<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags/" %>
<jsp:useBean id="pagedListHolder" scope="request" 
   type="org.springframework.beans.support.PagedListHolder"/>
<c:url value="/administrator/articles/edit.htm" var="pagedLink">
	<c:param name="page" value="~"/>
</c:url>
<table class="table">
	<tr>
		<th>Nazwa produktu</th>
		<th>Edytuj</th>
		<th>Usuń</th>
	</tr>
	<c:forEach items="${pagedListHolder.pageList}" var="item">
		<tr>
			<td>${item.getName()}</td>
			<td><a href="/administrator/articles/edit/${item.getId()}.htm">Edytuj</a></td>
			<td><a href="/administrator/articles/delete/${item.getId()}.htm">Usuń</a></td>
		</tr>
	</c:forEach>
</table>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
<%@include file="../after.jsp" %>
