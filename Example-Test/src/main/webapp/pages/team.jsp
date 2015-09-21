<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Create a new team!</h1>

<form:form method="post" modelAttribute="createTeam" action="createTeam" id="createTeam" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter Your Information</legend>

        
        <c:set var="name"><form:errors path="name"/></c:set>
        <div class="control-group<c:if test="${not empty name}"> error</c:if>">
            <label class="control-label" for="field-name">Team Name</label>
            <div class="controls">
                <form:input path="name" id="field-name" tabindex="1" maxlength="35" placeholder="Team Name"/>
                <form:errors path="name" cssClass="help-inline" element="span"/>
            </div>
        </div>
   
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Sign up</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>

<c:import url="template/footer.jsp" />