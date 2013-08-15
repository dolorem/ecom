<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../before.jsp" %>
<!-- <style>
.pagingItem {
font-weight: normal;
text-decoration: none;
color: #747474;
margin: 0 2px;
padding: 0 2px;
background-color: #eeeeee;
border: 1px solid #bababa;
font-size: 0.9em;
line-height: 1.5em;
}
.pagingItemCurrent {
padding: 0 2px;
margin: 0 2px;
font-weight: normal;
color: #FFFFFF;
background-color: #bfbfbf;
border: 1px solid #bfbfbf;
font-size: 0.9em;
}
</style>-->
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%-- // use our pagedListHolder --%>
<jsp:useBean id="pagedListHolder" scope="request" 
   type="org.springframework.beans.support.PagedListHolder"/>
<%-- // create link for pages, "~" will be replaced 
   later on with the proper page number --%>
<c:url value="/administrator/articles/edit.htm" var="pagedLink">
	<c:param name="page" value="~"/>
</c:url>
<%-- // show only current page worth of data --%>
<table>
<tr>
<th>No.</th>
<th>Random Number</th>
</tr>
<c:forEach items="${pagedListHolder.pageList}" var="item">
<tr>
<td>${item.getId()}</td>
<td >${item.getName()}</td>
</tr>
</c:forEach>
</table>
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
<%@include file="../after.jsp" %>
