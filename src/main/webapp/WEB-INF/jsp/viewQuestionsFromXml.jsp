<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="js/javascript.js"></script>
<link rel="stylesheet" type="text/css" href="css/quiz.css">

<title>Upload file</title>
</head>
<body class="question-body">
	<div class='container file-container-div' >
	
		<form:form class='xml-form'	method='POST' action='add-questions' enctype="multipart/form-data">
			
				<div class="row">
				<div class="col-sm-3"></div> 
				<div class="form-group col-sm-6">
					<label>Select file to upload :</label> <input class='inpt-xml' name='xmlFile' type='file' />
				</div>
				<div class="col-sm-3"></div>
			</div>

				<div class='row hidden-row' ></div>
				<div class='row'>
				<div class='col-sm-6' align="right">
				<button class='btn btn-primary btn-submit-file' type="submit">Submit</button>
				</div>
				<div class='col-sm-6' align="left">
				<button class='btn btn-primary btn-reset' type="reset" value="Reset">Reset</button>
				</div>
			</div>
		</form:form>




	</div>
</body>
</html>