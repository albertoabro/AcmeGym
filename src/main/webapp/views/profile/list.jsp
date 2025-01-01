<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 09/05/2023
  Time: 10:27
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

<!-- security -->
<security:authorize access="hasRole('MANAGER')" var="isManager"/>
<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>
<security:authorize access="hasRole('CUSTOMER')" var="isCustomer"/>
<security:authorize access="hasRole('TRAINER')" var="isTrainer"/>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="person" requestURI="${requestUri}" id="row">

    <!-- Attributes -->

    <spring:message code="profile.name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" />
    
    <spring:message code="profile.lastname" var="lastnameHeader"/>
    <display:column property="lastname" title="${lastnameHeader}" />

    <spring:message code="profile.email" var="emailHeader" />
    <display:column property="email" title="${emailHeader}" />

    <spring:message code="profile.phone" var="phoneHeader" />
    <display:column property="phone" title="${phoneHeader}"/>

    <spring:message code="profile.cp" var="cpHeader" />
    <display:column property="cp" title="${cpHeader}"/>

    <spring:message code="profile.city" var="cityHeader" />
    <display:column property="city" title="${cityHeader}" />

    <spring:message code="profile.country" var="countryHeader" />
    <display:column property="country" title="${countryHeader}"/>

    <spring:message code="profile.action" var="actionHeader" />
    <display:column title="${actionHeader}">
        <jstl:choose>
            <jstl:when test="${isManager}">
                <a href="profile/manager/edit.do?idPerson=${row.id}"><spring:message code="profile.edit"/> </a>
            </jstl:when>
            <jstl:when test="${isAdmin}">
            <a href="profile/admin/edit.do?idPerson=${row.id}"><spring:message code="profile.edit"/> </a>
            </jstl:when>
            <jstl:when test="${isTrainer}">
                <a href="profile/trainer/edit.do?idPerson=${row.id}"><spring:message code="profile.edit"/> </a>
            </jstl:when>
            <jstl:when test="${isCustomer}">
                <a href="profile/customer/edit.do?idPerson=${row.id}"><spring:message code="profile.edit"/> </a>
            </jstl:when>
        </jstl:choose>

    </display:column>

</display:table>