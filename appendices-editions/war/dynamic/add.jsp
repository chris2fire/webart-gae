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

				<div class="span12">

					<fieldset>

						<form method="post" action="work" id="workForm" class="form-horizontal">

							<legend>
								<fmt:message key="home.numero5.work" />
							</legend>
							<div class="control-group">
								<label class="control-label" for="title"><fmt:message key="home.numero5.work.title" /></label>
								<div class="controls">
									<input class="input-large" id="title" type="text" name="title" placeholder="<fmt:message key="home.numero5.work.title" />" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="author"><fmt:message key="home.numero5.work.author" /></label>
								<div class="controls">
									<input class="input-large" id="author" type="text" name="author" placeholder="<fmt:message key="home.numero5.work.author" />" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="type"><fmt:message key="home.numero5.work.type" /></label>
								<div class="controls">
									<select id="type" name="type" class="input-small">
										<option value="IMAGE">IMAGE</option>
										<option value="MUSIC">MUSIC</option>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="value"><fmt:message key="home.numero5.work.value" /></label>
								<div class="controls">
									<input class="input-large" id="value" type="text" name="value" placeholder="<fmt:message key="home.numero5.work.value" />" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="time"><fmt:message key="home.numero5.work.time" /></label>
								<div class="controls">
									<input class="input-large" id="time" type="text" name="time" placeholder="<fmt:message key="home.numero5.work.time" />" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="authToken"><fmt:message key="home.numero5.work.authToken" /></label>
								<div class="controls">
									<input class="input-large" id="authToken" type="text" name="authToken" placeholder="<fmt:message key="home.numero5.work.authToken" />" />
								</div>
							</div>

							<div class="form-actions">
								<button type="submit" class="btn btn-medium btn-primary" id="processButton" data-original-title="<fmt:message key="home.numero5.work.add" />"
									title="<fmt:message key="home.numero5.work.add" />">
									<fmt:message key="home.numero5.work.add" />
								</button>
							</div>

						</form>

					</fieldset>


				</div>

			</div>

		</section>

		<br />
		<br />
		<br />

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
			<div class="row" style="height: 20px">
				<div class="span6"></div>
				<div class="span6"></div>
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
	<script type="text/javascript" src="<c:url value="/js/jquery.validate.min.js" />"></script>
	<script type="text/javascript">
			$(document).ready(function() {
				$("#workForm").validate({
					submitHandler: function(form) {
						$("#processButton").attr('disabled', 'disabled');
						form.submit();
					},
					rules : {
						title : {
							required : true,
							rangelength : [4, 128]
						},
						author : {
							required : true,
							rangelength : [4, 128]
						},
						type : {
							required : true
						},
						value : {
							required : true,
							rangelength : [5, 128]
						},
						time : {
							required : false,
							number : true
						}
					},
					messages : {
						title : {
							required : "*",
							rangelength : ""
						},
						author : {
							required : "*",
							rangelength : ""
						},
						type : {
							required : "*"
						},
						value : {
							required : "*",
							rangelength : ""
						},
						time : {
							required : "*",
							number : ""
						}
					},
					errorClass : "help-inline",
					errorElement : "span",
					highlight : function(element, errorClass, validClass) {
						$(element).parents('.control-group').addClass('error');
					},
					unhighlight : function(element, errorClass, validClass) {
						$(element).parents('.control-group').removeClass('error');
					}
				});
			});
		</script>

</body>

</body>
