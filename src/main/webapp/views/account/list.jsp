<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 17/05/2023
  Time: 11:45
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



<!-- Texto, fecha y calificacion -->
<security:authorize access="isAuthenticated()" var="isLoged"/>
<security:authorize access="isAnonymous()" var="isNotLoged"/>

<jstl:if test="${isNotLoged}">
    <form action="profile/customer/create.do">
        <input type="submit" value="<spring:message code="account.customer" />"/>
    </form>

    <form action="profile/manager/create.do">
        <input type="submit" value="<spring:message code="account.manager" />"/>
    </form>
</jstl:if>