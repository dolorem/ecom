<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList" %>
<%@ tag import="com.smiechmateusz.model.Menu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="categories" required="true" type="java.util.ArrayList" description="List of categories retrieved with MenuDAO.getItemOffsetAlphabeticalList()" %>
<%@ attribute name="checked" required="false" type="java.util.List" description="List of checked categories" %>
<%@ attribute name="indentsPerLevel" required="false" type="java.lang.Integer" description="Number of indents per nesting level." %>
<c:if test="${indentsPerLevel == null}">
	<% indentsPerLevel = 4; %>
</c:if>
<c:if test="${checked == null}">
	<% checked = new ArrayList<Menu>(); %>
</c:if>
<c:forEach var="c" items="${categories}">
	<option value="${c.getId()}" 
		<c:if test="${checked.contains(c)}">selected="selected"</c:if>>
		<c:forEach var="i" begin="1" end="${c.getNestingLevel()}">
			<c:forEach var="j" begin="1" end="${indentsPerLevel}">
				&nbsp;
			</c:forEach>			
		</c:forEach>
		${c.getDescription()}
	</option>
</c:forEach>
