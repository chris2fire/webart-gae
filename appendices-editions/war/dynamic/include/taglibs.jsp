<%@ page language="java" errorPage="error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/custom" prefix="custom"%>
<fmt:setBundle var="bundle" basename="msg" />
<c:set var="language" value="${bundle.locale.language}" />
<c:set var="datePattern"><fmt:message key="date.format" /></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


