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
					<h4>With</h4>
					<h6>_<a href="http://www.appendices.fr" target="_blank">Appendices</a>_5</h6>
				</div>
			</div>

			<div class="row" style="height: 20px">
				<div class="span6"></div>
				<div class="span6"></div>
			</div>

		</section>

		<section class="span12 left-none-margin">

			<div class="row">

				<div class="span6" id="left" style="width: 460px; height: 370px; text-align: center; vertical-align: middle;">
					<div>
						<img src="img/ajax-loader.gif" />
					</div>
				</div>

				<div class="span6 left-none-margin" id="right" style="width: 460px; height: 370px; text-align: center; vertical-align: middle;">
					<div>
						<img src="img/ajax-loader.gif" />
					</div>
				</div>

			</div>

		</section>

		<br /> <br /> <br />

		<section class="span12 left-none-margin">

			<div class="row" style="height: 20px">
				<div class="span6"></div>
				<div class="span6"></div>
			</div>

			<div class="row">
				<div class="span12">
					<fmt:message key="home.numero5.text1" />
					<br />
					<fmt:message key="home.numero5.text2" />
				</div>
			</div>
			<div class="row">
				<div class="span6"></div>
				<div class="span6 left-none-margin" style="text-align: right;">
					<a href="javascript:void(0);" id="playLink"><fmt:message key="home.numero5.link" /></a>
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<fmt:message key="home.numero5.open" />
					<a href="https://github.com/chris2fire/webart-gae">https://github.com/chris2fire/webart-gae</a><br />
					<fmt:message key="home.numero5.credits" />
					<em>With</em>, 2012<br />
					<fmt:message key="home.numero5.copyleft" />
					<a href="http://www.artlibre.org">http://www.artlibre.org</a><br />
				</div>
			</div>

		</section>

	</div>

	<script src="<c:url value="/js/jquery.min.js" />"></script>
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>

	<%@ include file="include/dynscript.jsp" %>

</body>

</body>
