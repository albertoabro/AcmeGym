<%--
  Created by IntelliJ IDEA.
  User: alber
  Date: 11/05/2023
  Time: 10:38
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
<form action="training/search.do" method="post">
  <label>
    <spring:message code="training.word"/>
  </label>
  <input type="text" name="word" required/>  
  <input type="submit" value="<spring:message code="training.search"/>"/>

</form>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="trainings" requestURI="${requestUri}" id="row">

  <!-- Attributes -->

  <spring:message code="training.title" var="titleHeader" />
  <display:column property="title" title="${titleHeader}" sortable="true" />

  <spring:message code="training.description" var="descriptionHeader" />
  <display:column property="description" title="${descriptionHeader}" sortable="true" />

  <jstl:if test="${isManager}">
    <spring:message code="training.action" var="actionHeader" />
    <display:column title="${actionHeader}">
      <a href="training/manager/edit.do?idTraining=${row.id}">
        <spring:message code="training.edit"/>
      </a>
    </display:column>

  </jstl:if>

</display:table>

  <jstl:if test="${isManager}">
    <a href="training/manager/create.do">
      <spring:message code="training.create"/>
    </a>
  </jstl:if>

