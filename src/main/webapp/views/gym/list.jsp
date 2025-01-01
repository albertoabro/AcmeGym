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

<!-- Security declarations -->
<security:authorize access="hasRole('MANAGER')" var="isManager"/>
<security:authorize access="hasRole('CUSTOMER')" var="isCustomer"/>
<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="gyms" requestURI="${requestUri}" id="row">

    <!-- Attributes -->

    <spring:message code="gym.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="gym.address" var="addressHeader" />
    <display:column property="address" title="${addressHeader}" sortable="true" />

    <spring:message code="gym.price" var="priceHeader" />
    <display:column property="price" title="${priceHeader}" sortable="true" />

    <spring:message code="gym.logo" var="logoHeader" />
    <display:column property="logo" title="${logoHeader}"	sortable="false" />

    <spring:message code="activity.activities" var="activityHeader"/>
    <display:column title="${activityHeader}">
        <jstl:choose>
            <jstl:when test="${isManager}">
                <a href="activity/manager/listByGym.do?idGym=${row.id}">
                    <spring:message code="activity.activities"/>
                </a>
            </jstl:when>
            <jstl:otherwise>
                <a href="activity/listByGym.do?idGym=${row.id}">
                    <spring:message code="activity.activities"/>
                </a>
            </jstl:otherwise>
        </jstl:choose>
    </display:column>

    <jstl:if test="${isManager}">
        <spring:message code="gym.action" var="gymHeader"/>
        <display:column title="${gymHeader}">
            <a href="gym/manager/edit.do?idGym=${row.id}">
                <spring:message code="gym.edit"/>
            </a>
        </display:column>
    </jstl:if>
    <jstl:if test="${isCustomer}">
        <spring:message code="gym.action" var="actionHeader"/>
        <display:column title="${actionHeader}">
            <a href="gym/customer/edit.do?idGym=${row.id}">
                <spring:message code="gym.signUp"/>
            </a>
        </display:column>
    </jstl:if>
</display:table>

<jstl:if test="${isManager}">
    <a href="gym/manager/create.do">
        <spring:message code="gym.create"/>
    </a>
</jstl:if>