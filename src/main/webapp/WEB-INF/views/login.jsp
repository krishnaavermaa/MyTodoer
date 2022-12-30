<%@ include file="./common/header.jspf"%>
<link href="<c:url value="/css/style1.css" />" rel="stylesheet">
</head>
<body>
	<%-- <c:set var="name" value="${null }"/> --%>
	<%@ include file="./common/navigation.jspf"%>

	<div class="flex-container">
		<div class="form-container">
			<div style="height: 150px"></div>
			<h2 class="mini-bar">Sign in:</h2>

			<form class="container" id="form" action="/login" method="post">
				<fieldset class="row">
						<label class="col-4">EMAIL ID</label>
						<input class="form-control col-8" type="email" name="email" />
				</fieldset>
				<fieldset class="row">
						<label class="col-4">PASSWORD</label>
						<input class=" form-control col-8" type="password"
							name="pass" />
				</fieldset>
				<input type="submit" class="submit btn btn-success">
			</form>

			<div class="form-text text-danger" style="color: red; padding: 10px">${err }</div>
			<a class="btn btn-primary" href="/signup" type="button">Sign Up</a>
		</div>
	</div>
	<%@ include file="./common/footer.jspf"%>
</body>
</html>