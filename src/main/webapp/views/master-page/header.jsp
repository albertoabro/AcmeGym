<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>
<security:authorize access="hasRole('MANAGER')" var="isManager"/>
<security:authorize access="hasRole('CUSTOMER')" var="isCustomer"/>
<security:authorize access="hasRole('TRAINER')" var="isTrainer"/>

<div>
	<a href="#"><img src="images/logo.png" alt="Sample Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li>
			<a class="fNiv" href="gym/list.do"><spring:message code="gym.gyms"/></a>
		</li>
		<li>
			<a class="fNiv" href="activity/list.do"><spring:message code="activity.activities"/></a>
		</li>
		<li>
			<a class="fNiv" href="training/list.do"><spring:message code="training.trainings"/></a>
		</li>
		<jstl:if test="${isAdmin}">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="admin/manager/list.do"><spring:message code="master.page.admin.manager" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
		</jstl:if>
		
		<jstl:if test="${isCustomer}">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</jstl:if>

		<jstl:if test="${isManager}">
			<li><a class="fNiv"><spring:message	code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="gym/manager/list.do"><spring:message code="master.page.manager.gyms" /></a></li>
					<li><a href="activity/manager/list.do"><spring:message code="master.page.manager.activities" /></a></li>
					<li><a href="trainer/manager/list.do"><spring:message code="master.page.manager.trainers" /></a></li>
					<li><a href="training/manager/list.do"><spring:message code="master.page.manager.trainings" /></a></li>
				</ul>
			</li>
		</jstl:if>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="account/list.do"><spring:message code="master.page.signup" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">

			<li>
				<a class="fNiv">
					<spring:message code="master.page.profile" />
					<security:authentication property="principal.username" var="username"/>
					(${username})
				</a>
				<ul>
					<li class="arrow"></li>

					<li><a href="profile/list.do?username=${username}"><spring:message code="master.page.profile" /> </a></li>

					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>

	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

