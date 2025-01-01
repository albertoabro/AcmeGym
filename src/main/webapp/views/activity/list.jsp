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

<!--Search-->
<form action="activity/search.do" method="post">
    <label>
        <spring:message code="activity.word"/>
    </label>
    <input type="text" name="word" required/>

    <select id="daySelected" required  name="day">
        <option selected><spring:message code="activity.day"/></option>
        <jstl:choose>
            <jstl:when test="${!empty activities}">
                <jstl:forEach var="activity" items="${activities}">
                    <option value="${activity.day}">${activity.day}</option>
                </jstl:forEach>
            </jstl:when>
        </jstl:choose>
    </select>

    <select id="timeSelected" required  name="time">
        <option selected><spring:message code="activity.time"/></option>
        <jstl:choose>
            <jstl:when test="${!empty activities}">
                <jstl:forEach var="activity" items="${activities}">
                    <option value="${activity.startingTime}">${activity.startingTime}</option>
                </jstl:forEach>
            </jstl:when>
        </jstl:choose>
    </select>

    <input type="submit" value="<spring:message code="activity.search"/>"/>
</form>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="activities" requestURI="${requestUri}" id="row">

    <!-- Attributes -->

    <spring:message code="activity.title" var="titleHeader" />
    <display:column property="title" title="${titleHeader}" sortable="true" />

    <spring:message code="activity.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" sortable="true" />

    <spring:message code="activity.day" var="dayHeader" />
    <display:column property="day" title="${dayHeader}" sortable="true" />

    <spring:message code="activity.startingTime" var="startingTimeHeader" />
    <display:column property="startingTime" title="${startingTimeHeader}"	sortable="false" />

    <spring:message code="activity.endingTime" var="endingTimeHeader" />
    <display:column property="endingTime" title="${endingTimeHeader}"	sortable="false" />

    <spring:message code="activity.capacity" var="capacityHeader" />
    <display:column property="capacity" title="${capacityHeader}"	sortable="false" />

    <spring:message code="trainer.trainers" var="trainerHeader" />
    <display:column title="${trainerHeader}"	sortable="false" >
        <a href="trainer/listByIdActivity.do?idActivity=${row.id}">
            <spring:message code="trainer.trainers"/>
        </a>
    </display:column>

    <spring:message code="gym.gyms" var="gymHeader" />
    <display:column title="${gymHeader}"	sortable="false" >
        <a href="gym/listByIdActivity.do?idActivity=${row.id}">
            <spring:message code="gym.gyms"/>
        </a>
    </display:column>

    <jstl:if test="${isManager and notPermission}">
        <spring:message code="activity.action" var="actionHeader"/>
        <display:column title="${actionHeader}">
            <jstl:choose>
                <jstl:when test="${!row.isDeleted()}">
                    <a href="activity/manager/edit.do?idGym=${row.id}">
                        <spring:message code="activity.cancelate"/>
                    </a>
                </jstl:when>
                <jstl:otherwise>
                    <a href="activity/manager/edit.do?idGym=${row.id}">
                        <spring:message code="activity.enable"/>
                    </a>
                </jstl:otherwise>
            </jstl:choose>
        </display:column>
    </jstl:if>

</display:table>