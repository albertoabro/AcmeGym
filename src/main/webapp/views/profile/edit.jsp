<%--
 *
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('MANAGER')" var="isManager"/>
<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>
<security:authorize access="hasRole('CUSTOMER')" var="isCustomer"/>
<security:authorize access="hasRole('TRAINER')" var="isTrainer"/>

<form:form action="profile/${path}/edit.do" modelAttribute="person">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="personAnnotations"/>
    <form:hidden path="userAccount.authorities"/>
    <form:hidden path="userAccount.username"/>
    <form:hidden path="userAccount.id"/>
    <form:hidden path="userAccount.version"/>
    <form:hidden path="userAccount.password"/>

    <form:label path="name">
        <spring:message code="profile.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>
    
    <form:label path="lastname">
        <spring:message code="profile.lastname"/>
    </form:label>
    <form:input path="lastname"/>
    <form:errors cssClass="error" path="lastname"/>
    <br/>

    <form:label path="email">
        <spring:message code="profile.email"/>
    </form:label>
    <form:input path="email"/>
    <form:errors cssClass="error" path="email"/>
    <br/>

    <form:label path="phone">
        <spring:message code="profile.phone"/>
    </form:label>
    <form:input path="phone"/>
    <form:errors cssClass="error" path="phone"/>
    <br/>

    <form:label path="cp">
        <spring:message code="profile.cp"/>
    </form:label>
    <form:input path="cp"/>
    <form:errors cssClass="error" path="cp"/>
    <br/>

    <form:label path="city">
        <spring:message code="profile.city"/>
    </form:label>
    <form:input path="city"/>
    <form:errors cssClass="error" path="city"/>
    <br/>

    <form:label path="country">
        <spring:message code="profile.country"/>
    </form:label>
    <form:input path="country"/>
    <form:errors cssClass="error" path="country"/>
    <br/>


    <jstl:if test="${isManager}">
        <form:hidden path="managerGyms"/>
        <form:hidden path="managerAdmin"/>
        <form:hidden path="banned"/>
    </jstl:if>
    <jstl:if test="${isTrainer}">
        <form:hidden path="trainerCvs"/>
        <form:hidden path="trainerActivities"/>
        <form:hidden path="trainerGyms"/>
    </jstl:if>

    <jstl:if test="${isAdmin}">
        <form:hidden path="adminManagers"/>
        <form:hidden path="adminGyms"/>
    </jstl:if>


    <jstl:if test="${isCustomer and authority==1}">
        <form:hidden path="customerRegistrations"/>
        <form:hidden path="customerActivities"/>
        <form:hidden path="customerCreditCards"/>
    </jstl:if>

    <input type="submit" name="save"
        value="<spring:message code="profile.save" />"/>


</form:form>