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


<security:authorize access="hasRole('MANAGER')" var="isManager"/>


<form:form action="trainer/manager/edit.do" modelAttribute="trainer">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="personAnnotations"/>
    <form:hidden path="userAccount.id"/>
    <form:hidden path="userAccount.version"/>
    <form:hidden path="trainerCvs"/>

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
    <br />

    <form:label path="userAccount.username">
        <spring:message code="security.username"/>
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="userAccount.username"/>
    <br />

    <form:label path="userAccount.password">
        <spring:message code="security.password"/>
    </form:label>
    <form:input path="userAccount.password"/>
    <form:errors cssClass="error" path="userAccount.password"/>
    <br />
    
    <form:label path="userAccount.authorities">
        <spring:message code="security.authority"/>
    </form:label>
    <form:select id="authorities" path="userAccount.authorities">
        <form:options items="${authority}" itemLabel="authority" />
    </form:select>
    <br />

    <form:label path="trainerGyms">
        <spring:message code="trainer.gym"/>
    </form:label>
    <form:select id="gyms" path="trainerGyms">
        <form:option value="0" label="----" />
        <form:options items="${gyms}" itemValue="id" itemLabel="name" />
    </form:select>
    <br />

    <form:label path="trainerActivities">
        <spring:message code="trainer.activity"/>
    </form:label>
    <form:select id="activities" path="trainerActivities" multiple="true">
        <form:option value="0" label="----" />
        <form:options items="${activities}" itemValue="id" itemLabel="title" />
    </form:select>
   <br />

    <input type="submit" name="save"
           value="<spring:message code="trainer.save" />"/>


</form:form>