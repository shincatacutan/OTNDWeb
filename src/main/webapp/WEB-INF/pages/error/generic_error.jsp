<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>OTND Web - Error</title>
	<meta name="GENERATOR" content="Microsoft Visual Studio .NET 7.1">
	<meta name="vs_targetSchema"
		content="http://schemas.microsoft.com/intellisense/ie5">
	<link rel="shortcut icon" href="<c:url value="/resources/optum_favicon.png" />">
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery.dataTables.css" />"
		rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.css" />"
		rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.theme.css" />"
		rel="stylesheet">
	<%-- 
	    <link href="<c:url value="/resources/css/jquery-ui.structre.css" />" rel="stylesheet"> --%>
	
	<script src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui-min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-validate-min.js" />"></script>
	<script src="<c:url value="/resources/js/additional-methods.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</head>

<body>
	<div id="body_container">
		<jsp:include page="../includes/header.jsp" />
			
		<div id="body_dv">
			<div class="error_dv">
			Error Code:<br/> <span>${errCode}</span><br/><br/>
			Error Message:<br/> <span>${errMsg}</span></div>
		</div>

		<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>
