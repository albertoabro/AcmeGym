<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 14/05/2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="managers" requestURI="${requestUri}" id="row">

    <!-- Attributes -->

    <spring:message code="manager.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="manager.lastname" var="lastnameHeader" />
    <display:column property="lastname" title="${lastnameHeader}" sortable="true" />

    <spring:message code="admin.manager.action" var="actionHeader" />
    <display:column title="${actionHeader}" >
    	<form action="admin/manager/ban.do" method="post">
        	<input type="text" value="${row.id}" name="idManager" hidden="${row.id}">
	        <jstl:choose>
	            <jstl:when test="${row.banned}">
	            <input type="submit" value="
	                <spring:message code="admin.manager.unban" />"/>
	            </jstl:when>
	            <jstl:otherwise>
	                <input type="submit" value="
	                <spring:message code="admin.manager.ban" />"/>
	            </jstl:otherwise>
	        </jstl:choose>
        	
        </form>
        </a>
    </display:column>

</display:table>
