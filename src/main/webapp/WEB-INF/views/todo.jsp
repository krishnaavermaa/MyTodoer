<%@ include file="./common/header.jspf"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<link href="<c:url value="/css/style2.css" />" rel="stylesheet">

</head>
<body>
	<%@ include file="./common/navigation.jspf"%>
	<%
	String tid=null;
	if(request.getAttribute("err")==null)
		tid=request.getParameter("tid");
	%>
	<c:set var="tid" value="<%=tid %>"></c:set>
	<div class="container main-container">
		<!-- todo container -->
		<div class="row">
			<h1 class="mr-auto p-4">Hi, ${name }</h1>
			<a href="/welcome" type="success" class="btn btn-primary align-self-center"> List ToDos </a>
		</div>
		<div class="row d-flex flex-row p-4 flex-nowrap">
			<div class="form-control col-2 m-2" id="id">ID</div>
			<div class="form-control col-3 m-2" id="target">TARGET</div>
			<div class="form-control col m-2" id="desc">DESCRIPTION</div>
			<c:if test="${tid ne null}">
				<div class=" col-2 m-2" id="status">DONE</div>
			</c:if>
		</div>


			<form:form modelAttribute="todo" id="form"
				action="/todo" method="post">
				<div class=" row d-flex flex-row p-4 flex-wrap">
				
					<fieldset class="p-0 m-2 col-2" id="tid">
						<form:input class="form-control" type="text" path="id"
							placeholder="auto" readonly="true" />
					</fieldset>
					
					<fieldset class="p-0 m-2 col-3">
						<form:input id="datetime" class="form-control" type="datetime-local"
							path="target" />
					</fieldset>
					
					<fieldset class="p-0 m-2 col">
						<form:input class="form-control" type="text" path="desc" />
					</fieldset>
					
					<c:if test="${tid ne null}">
						<fieldset class="p-0 m-2 col-2">
							<input type="checkbox" ${(todo.done) ? 'checked="checked"': ''} name="status" />
						</fieldset>
					</c:if>
					<form:hidden path="done"/>

				</div>
				<div class="row d-flex flex-row p-4 flex-wrap">
				
					<fieldset class="input-group col-2 m-2" id="tid">
						<form:errors class="text-warning" path="id"/>
					</fieldset>
					
					<fieldset class="input-group col-3 m-2">
						<form:errors class="text-warning" path="target"/>
					</fieldset>
					
					<fieldset class="input-group col m-2">
						<form:errors class="text-warning" path="desc"/>
					</fieldset>
					
					<fieldset class="input-group col-2 m-2">
						<form:errors class="text-warning" path="done"/>
					</fieldset>

				</div>
				<div class="row d-flex flex-row p-4">
					<input type="submit" class="submit btn btn-success "
						${(tid ne null)?'value="Update"':'value="Create"'} />
				</div>

			</form:form>

		<div class="form-text" style="color: red; padding: 10px">${err }</div>
	</div>

	<%@ include file="./common/footer.jspf"%>
</body>
</html>