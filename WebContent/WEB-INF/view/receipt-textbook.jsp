<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="ui segment">
	<div id="header">
	<sec:authorize access="hasRole('PUBLISHER')">	
		<h3>Παράδοση Συγγραμμάτων</h3>
	</sec:authorize>
	<sec:authorize access="hasRole('SECRETARY')">
		<h3>Κατάσταση Δήλωσης</h3>	
	</sec:authorize>
	</div>

					
				 	<a href="<c:url value="/publisher/students"></c:url>">Υπόλοιποι Φοιτητές</a>
				 	
			<table class="ui celled  striped table">
				<tr>
					<th>Τίτλος Συγγράμματος</th>
					<th>Παραδόθηκε</th>
					
				</tr>
				<tr>
				<c:forEach var="tempTextBookProfile" items="${textbookprofiles}">
					<c:if test="${tempTextBookProfile.student.id == student.id}">

						
							<td>${tempTextBookProfile.textbook.title}</td>	
							
							<c:if test="${tempTextBookProfile.confirmationReceived == true}">				
								<td>Έχει παραδοθεί</td>
							</c:if>

							<c:if test="${tempTextBookProfile.confirmationReceived == false}">	
					
					
					  <!--<td><a href="<c:url value="/publisher/confirmation/${tempTextBookProfile.id}"></c:url>">Παράδοση</a></td>  --> 
<sec:authorize access="hasRole('PUBLISHER')">	
					   
					  <td> <form action="${pageContext.request.contextPath}/publisher/confirmation/${stid}/${tempTextBookProfile.id}" method="get">
							<input value="Παράδοση" type="submit">
						</form>
					  </td> 
</sec:authorize>   
<sec:authorize access="hasRole('SECRETARY')">	
					   <td>Δεν το έχει παραλάβει</td>
</sec:authorize>
					</c:if> 	 
				</c:if>
	
				</c:forEach>
				</tr>
			</table>


<sec:authorize access="hasRole('SECRETARY')">

	<p><a href="https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&hl=el&service=mail#identifier">Αποστολή email</a></p>

	<br><br>
	<form action="${pageContext.request.contextPath}/secretary/studentStatement" method="get">
		<input value="OK" type="submit">
	</form>
</sec:authorize>

</div>
