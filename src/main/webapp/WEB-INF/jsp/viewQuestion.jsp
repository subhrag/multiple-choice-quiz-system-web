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

<title>Questions and Answers</title>
</head>
<body class="question-body">
	<div class='container question-container-div'>
		<h4 class='h4-title'>Please answer the below questions :</h4>
		<form:form class='main-form' modelAttribute='questionAndAnswer'
			method='POST' action='submit-answer'>
			

				<c:forEach items="${questions}" var="var_question">
				<div class='row'>
					<div class='col-sm-6'>
					${var_question.questionId} . ${var_question.questionDescription}
					
					</div>
					</div>	
					<c:forEach items="${var_question.answerList}" var="answer"
						varStatus="status">
					 <div class='row'>
					 <div class='col-sm-1'></div>
						<div class='col-sm-6'>
							<input class="inpt-radio" type="radio"
								name="questionAnswerMap[${var_question.questionId}]"
								value="${answer.answerId}" />${answer.answerText}
						</div>
					</div>
					</c:forEach>
					

				</c:forEach>

				<div class='row hidden-row' ></div>
				<div class='row'>
				<div class='col-sm-6' align="right">
				<button class='btn btn-primary btn-submit-answers' type="submit">Submit</button>
				</div>
				<div class='col-sm-6' align="left">
				<button class='btn btn-primary btn-reset' type="reset" value="Reset">Reset</button>
				</div>
			</div>
		</form:form>




	</div>
</body>
</html>