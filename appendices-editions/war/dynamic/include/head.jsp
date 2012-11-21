<!DOCTYPE html>
<%@ include file="taglibs.jsp"%>
<html lang="<c:out value="${language}" />">

<head>
<title>Appendices.fr</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language"	content="<c:out value="${language}" />">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="author" content="Appendices Team - Appendices.fr" />
<meta name="description" content="<fmt:message key="head.meta.description" />" />
<meta name="keywords" content="<fmt:message key="head.meta.keywords" />" />
<meta name="geo.placename" content="Montpellier, 34, France" />

<!--[if IE]>
		<script type="text/javascript">
			document.createElement("header");
			document.createElement("footer");
			document.createElement("section");
			document.createElement("aside");
			document.createElement("nav");
			document.createElement("article");
			document.createElement("figure");
			document.createElement("figcaption");
		
</script>
<![endif]-->

<!--[if IE]><script src="js/roundies-0.0.2a.js"></script><![endif]-->

<link href="favicon.ico" type="image/x-icon" rel="shortcut icon" />

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
   <!--[if lt IE 9]>
     <script src="<c:url value="/js/html5shiv.min.js" />"></script>
   <![endif]-->

<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
<link rel="stylesheet" href="<c:url value="/css/default.css" />" />


</head>