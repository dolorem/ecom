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
					<c:forEach var="menuItem" items="${menuList}">
						<c:choose>
							<c:when test="${menuItem.getChildren() != null && menuItem.getChildren().size() > 0}">
								<li class="dropdown"><a href="${menuItem.getLink()}"
									class="dropdown-toggle">${menuItem.getDescription()}&nbsp;<b
										class="caret"></b></a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach var="dropdownMenuItem"
											items="${menuItem.getChildren()}">
											<c:choose>
											<c:when test="${dropdownMenuItem.getChildren() != null && dropdownMenuItem.getChildren().size() > 0}">
												<li class="dropdown-submenu"><a href="#" tabindex="-1">${dropdownMenuItem.getDescription()}</a>
												<ul class="dropdown-menu">
												<c:forEach var="dropdownSubmenuItem" items="${dropdownMenuItem.getChildren()}">												
													<li><a href="${dropdownSubmenuItem.getLink()}">${dropdownSubmenuItem.getDescription()}</a></li>
												</c:forEach>
												</ul>
												</li>
											</c:when>											
											<c:otherwise>
												<li><a href="${dropdownMenuItem.getLink()}">${dropdownMenuItem.getDescription()}</a></li>
											</c:otherwise>
											</c:choose>
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