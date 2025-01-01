<%--
 * action-2.jsp
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

<jstl:if test="${!empty message}">${message}</jstl:if>
<security:authorize access="hasRole('MANAGER')" var="isManager"/>
<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>
<security:authorize access="hasRole('CUSTOMER')" var="isCustomer"/>
<security:authorize access="hasRole('TRAINER')" var="isTrainer"/>

<form:form action="customer/create.do" modelAttribute="customer">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="personAnnotations"/>
    <form:hidden path="userAccount.id"/>
    <form:hidden path="userAccount.version"/>
    <form:hidden path="customerRegistrations"/>
    <form:hidden path="customerActivities"/>
    <form:hidden path="customerCreditCards"/>

    <form:label path="name">
        <spring:message code="profile.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
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
    </br>
    <form:label path="creditCards">
            <spring:message code="profile.cards"/>
        </form:label>
        <form:select id="creditCards" path="creditCards">
            <form:option value="0" label="----" />
            <form:options items="${creditCards}" itemValue="id" itemLabel="name" />
        </form:select>
        <br />
        <form:label path="activities">
                <spring:message code="profile.activity"/>
            </form:label>
            <form:select id="activities" path="activities" multiple="true">
                <form:option value="0" label="----" />
                <form:options items="${activities}" itemValue="id" itemLabel="title" />
            </form:select>
           <br />


    <input type="submit" name="save"
        value="<spring:message code="profile.save" />"/>


</form:form>