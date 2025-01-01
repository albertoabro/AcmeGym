<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<!--
    Template
    Sustituye Entity por el nombre de la entidad, y X por el nombre del campo de la tabla
    <spring:message code="entity.X" var="XtHeader" />
    <display:column property="X" title="${XHeader}" sortable="true" />
-->


<!-- Texto, fecha y calificacion -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="annotations" requestURI="${requestUri}" id="row">


    <spring:message code="annotation.text" var="textHeader" />
    <display:column property="text" title="${textHeader}" sortable="true" />


    <spring:message code="annotation.date" var="dateHeader" />
    <display:column property="date" title="${dateHeader}" sortable="true" format="{0,date,dd/MM/yyyy}" />


    <spring:message code="annotation.score" var="textHeader" />
    <display:column property="text" title="${textHeader}" sortable="true" />



</display:table>