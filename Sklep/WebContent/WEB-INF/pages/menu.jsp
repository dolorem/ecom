<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smiechmateusz.model.Menu"%>
<div class="masthead">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li><a href="#">&nbsp;</a></li>
					<c:forEach var="menuItem" items="${menuList}">
						<c:choose>
							<c:when test="${menuItem.getChildren().size() > 0}">
								<li class="dropdown"><a href="${menuItem.getLink()}"
									class="dropdown-toggle">${menuItem.getDescription()}&nbsp;<b
										class="caret"></b></a>
									<ul class="dropdown-menu">
										<c:forEach var="dropdownMenuItem"
											items="${menuItem.getChildren()}">
											<li><a href="${dropdownMenuItem.getLink()}">${dropdownMenuItem.getDescription()}</a></li>
										</c:forEach>
									</ul></li>
							</c:when>
							<c:otherwise>
								<li><a href="${menuItem.getLink()}">${menuItem.getDescription()}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>