<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 09/05/2023
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="gym/manager/edit.do" modelAttribute="gym">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="gymRegistrations"/>
    <form:hidden path="deleted"/>
    <form:hidden path="gymTrainers"/>
    <form:hidden path="gymTrainings"/>
    <form:hidden path="gymActivities"/>

    <form:label path="name">
        <spring:message code="gym.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <form:label path="logo">
        <spring:message code="gym.logo"/>
    </form:label>
    <form:input path="logo"/>
    <form:errors cssClass="error" path="logo"/>
    <br/>

    <form:label path="address">
        <spring:message code="gym.address"/>
    </form:label>
    <form:input path="address"/>
    <form:errors cssClass="error" path="address"/>
    <br/>

    <form:label path="price">
        <spring:message code="gym.price"/>
    </form:label>
    <form:input path="price"/>
    <form:errors cssClass="error" path="price"/>
    <br/>

    <form:label path="gymAdmins">
        <spring:message code="gym.admin"/>
    </form:label>
    <form:select id="admins" path="gymAdmins" multiple="true">
        <form:option value="0" label="----" />
        <form:options items="${admins}" itemValue="id" itemLabel="name" />
    </form:select>
    <br />
    <form:label path="gymManagers">
        <spring:message code="gym.manager"/>
    </form:label>
    <form:select id="managers" path="gymManagers" multiple = "true">
        <form:option value="0" label="----" />
        <form:options items="${managers}" itemValue="id" itemLabel="name" />
    </form:select>

    <input type="submit" name="save"
           value="<spring:message code="gym.save" />"/>
    <br/>
    <input type="submit" name="delete"
           value="<spring:message code="gym.delete" />"
           onclick="return confirm('<spring:message code="gym.confirm.delete" />')" />&nbsp;


</form:form>