<%@ include file="./common/header.jspf"%>
<link href="<c:url value="/css/style1.css" />" rel="stylesheet">
</head>
<body>
	<%@ include file="./common/navigation.jspf"%>

	<div class="flex-container">
		<div class="form-container">
			<div style="height: 100px"></div>
			<h2 class="mini-bar">Sign Up:</h2>

			<form:form modelAttribute="user" class="container" id="form"
				action="/signup" method="post">
				<fieldset>
					<div class="row">
						<form:label class="col-4" path="name">NAME</form:label>
						<form:input class="form-control col-8" type="text" path="name" />
					</div>
					<div class="row err">
						<div class="col-4"></div>
						<form:errors path="name" class="col-8 text-warning" />
					</div>
				</fieldset>
				<fieldset>
					<div class="row">
						<form:label path="email" class="col-4">EMAIL ID</form:label>
						<form:input class="form-control col-8" type="email" path="email" />
					</div>
					<div class="row err">
						<div class="col-4"></div>
						<form:errors path="email" class="col-8 text-warning"/>
					</div>
				</fieldset>
				<fieldset>
					<div class="row">
						<form:label path="phone" class="col-4">PHONE</form:label>
						<form:input class="form-control col-8" type="tel" path="phone" />
					</div>
					<div class="row err">
						<div class="col-4"></div>
						<form:errors path="phone" class="col-8 text-warning"/>
					</div>
				</fieldset>
				<fieldset>
					<div class="row">
						<form:label path="password" class="col-4">PASSWORD</form:label>
						<form:input class="form-control col-8" type="password"
							path="password" />
					</div>
					<div class="row err">
						<div class="col-4"></div>
						<form:errors path="password" class="col-8 text-warning"/>
					</div>
				</fieldset>
				<input type="submit" class="submit btn btn-success" />
			</form:form>

			<div class="form-text text-danger" id="err">${err }</div>
			<a class="btn btn-primary" href="/login" type="button">Login in</a>
		</div>
	</div>

	<%@ include file="./common/footer.jspf"%>
</body>
</html>