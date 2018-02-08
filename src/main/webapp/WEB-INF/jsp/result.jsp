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
<title>Result</title>
</head>
<body>
	<div class='container result-container-div'>

		<h4 class='h4-title'>Submitted Answers :</h4>

		<c:forEach items="${questions}" var="var_question">
			<div class='row'>
				<div class='col-sm-6'>${var_question.questionId} .
					${var_question.questionDescription}</div>
			</div>
			<c:set var='userQuestionId'>${var_question.questionId}</c:set>
			
			<c:set var='userAnswerId'>${submittedAnswers[userQuestionId]}</c:set>
			
			<c:forEach items="${var_question.answerList}" var="answer"
				varStatus="status">
				<div class='row'>
					<div class='col-sm-1'></div>
					<div class='col-sm-6'>
					<c:set var='answerOption' >${answer.answerId}</c:set>
						<input class="inpt-radio" type="radio" disabled="disabled" 
							<c:if test="${userAnswerId eq answerOption}"> checked="checked" </c:if>
							name="questionAnswerMap[${var_question.questionId}]"
							value="${answer.answerId}" />${answer.answerText}
					</div>
				</div>
			</c:forEach>


		</c:forEach>


		<div class="alert alert-info">
			<label>Your result is here !!</label>
			<h4 class='h4-title'>${message}</h4>
		</div>
		
		<div class='retake quiz' align="center" >
		 <button class="back-button btn btn-link" type='button'>Retake quiz</button>
		 
		</div>
			<form:form class='back-button-form' action='get-question' method='GET' />
	</div>
</body>
</html>