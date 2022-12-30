<%@ include file="./common/header.jspf"%>
<%-- <%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %> --%>
<link href="<c:url value="/css/style2.css" />" rel="stylesheet">
<%-- <%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm");
String date = sdf.format(new Date());
%> --%>
</head>
<body>
	<%@ include file="./common/navigation.jspf"%>
	<c:set value="${fn:length(listTodos)}" var="len" />
	<c:set value="${fn:length(err) }" var="errLen" />
	<div class="container main-container">
		<!-- todo container -->
		
		<div class="p-2 row d-flex flex-row">
			<h1 class="mr-auto p-4">Hi, ${name }</h1>
			<a href="/todo" type="submit" class="btn btn-primary align-self-center"> Create
				ToDo </a>
		</div>
		<div class="row d-flex flex-row p-2">
			<div class=" col-1" id="id">ID</div>
			<div class=" col-3" id="target">TARGET</div>
			<div class=" col mr-auto" id="desc">DESCRIPTION</div>
			<div class=" col-2" id="status">DONE</div>
			<div class="col-2 align-self-end" id="buttonarea"></div>
		</div>
		<c:if test="${len le 0}">
			<div class="row align-items-baseline justify-content-center">
				<div style="height: 150px"></div>
				<h1 class="text-muted text-center">Empty ToDo
					list!</h1>
			</div>
		</c:if>
		<c:forEach items="${listTodos}" var="todo">
			<div class="row d-flex flex-row p-2">
				<div class=" col-1">
					<c:out value="${todo.id }" />
				</div>
				<div class=" col-3">${todo.target }</div>
				<div class=" col mr-auto">${todo.desc }</div>
				<div class=" col-2">
					<input type="checkbox" name="status" id="noclickcheckbox"
						${(todo.done) ? 'checked="checked"':''} 
						onclick="{alert('RESTRICTED\n\n Please \'EDIT\' todo to update todo status'); return false;}"/>
				</div>
				<div class="col-2 align-self-end">
					<div class="row p-1">
						<a href='<c:out value="/todo?tid=${todo.id}"/>' type="submit"
							class="btn btn-warning">EDIT</a>
					</div>
					<div class="row p-1">
						<a href='<c:out value="/delete?tid=${todo.id}"/>' type="submit"
							class="btn btn-danger">DELETE</a>
					</div>

				</div>
			</div>
		</c:forEach>

	</div>

	<%@ include file="./common/footer.jspf"%>
	<c:if test="${errLen gt 0 }">
		<script>
			alert('${err}');
		</script>
	</c:if>
</body>
</html>