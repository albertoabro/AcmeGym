<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 11/05/2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('MANAGER')" var="isManager"/>

<form:form action="training/manager/edit.do" modelAttribute="training">

    <form:hidden path="id" />
    <form:hidden path="version" />


    <form:label path="title">
        <spring:message code="training.title"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>
    <br/>

    <form:label path="description">
        <spring:message code="training.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="description"/>
    <br/>

    <jstl:choose>
        <jstl:when test="${create}">
            <form:label path="trainingSteps">
                <spring:message code="training.steps"/>
            </form:label>
            <form:select id="steps" path="trainingSteps" multiple="true">
                <form:option value="0" label="----" />
                <form:options items="${steps}" itemValue="id" itemLabel="title" />
            </form:select>
            <br />
            <form:label path="trainingGyms">
                <spring:message code="training.steps"/>
            </form:label>
            <form:select id="gyms" path="trainingGyms" multiple = "true">
                <form:option value="0" label="----" />
                <form:options items="${gyms}" itemValue="id" itemLabel="name" />
            </form:select>
        </jstl:when>
        <jstl:otherwise>
            <form:hidden path="trainingSteps"/>
            <form:hidden path="trainingGyms"/>
        </jstl:otherwise>
    </jstl:choose>

    <input type="submit" name="save"
           value="<spring:message code="profile.save" />"/>
    <input type="submit" name="delete" value="<spring:message code="training.delete"/>"/>

</form:form>