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
<form action="trainer/search.do" method="post">
    <label>
        <spring:message code="trainer.word"/>
    </label>
    <input type="text" name="word" required/>
    <input type="submit" value="<spring:message code="trainer.search"/>"/>

</form>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="trainers" requestURI="${requestUri}" id="row">

    <!-- Attributes -->

    <spring:message code="trainer.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="trainer.lastname" var="lastnameHeader" />
    <display:column property="lastname" title="${lastnameHeader}" sortable="true" />

</display:table>

<jstl:if test="${isManager and permission}">
    <a href="trainer/manager/create.do"><spring:message code="trainer.create" /></a>
</jstl:if>