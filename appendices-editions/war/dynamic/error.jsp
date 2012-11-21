<%@ include file="include/head.jsp"%>

<body>

	<!--[if lte IE 7]>
	<link rel="stylesheet" href="css/styles-ie.css" />
	<div class="message center">
		<div class="alert-ie">
			<fmt:message key="security.alert.ie" />
		 </div>
	</div>
	<![endif]-->

	<!-- IE6 PNG -->
	<!--[if lte IE 6]> <style type="text/css">img, div { behavior: url(lib/iepngfix.htc) } </style> 
	<![endif]-->

	<div class="container">

		<section class="span12 left-none-margin">

			<div class="row" style="height: 20px">
				<div class="span6"></div>
				<div class="span6"></div>
			</div>

			<div class="row">
				<div class="span12">
					<h4><fmt:message key="home.error" /> <a href="<c:url value="/" />">GO HOME</a><br /></h4>
				</div>
			</div>

			<div class="row" style="height: 20px">
				<div class="span6"></div>
				<div class="span6"></div>
			</div>

		</section>

	</div>

</body>
</html>