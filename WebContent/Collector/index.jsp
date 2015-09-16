<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="collector.Domain"%>
<%@ page import="collector.EntityManagerFactoryUtil"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="javax.persistence.TypedQuery"%>
<%
	EntityManager em = EntityManagerFactoryUtil.getInstance()
	.getEntityManagerFactory().createEntityManager();
	TypedQuery<Domain> query = em.createQuery("SELECT d FROM Domain d",
	Domain.class);
	List<Domain> results = query.getResultList();
	List<Map<String, Object>> resultReps = new LinkedList<Map<String, Object>>();
	for (Domain domain : results)
		resultReps.add(domain.toRep());
	request.setAttribute("reps", resultReps);
%>
<!DOCTYPE html>
<html>
<head>
<title>HuntingWeb Data Collector</title>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="App Sign in Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements" ./>
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 









































</script>
</script>
<!--webfonts-->
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
</head>
<body>
	<h1>朋友位置墙</h1>
	<div class="app-cam">
		<div class="cam">
			<img src="images/cam.png" class="img-responsive" alt="" />
		</div>
		<div class="bloard">
			<c:forEach var="domain" items="${requestScope.reps }">
				<div class="people">
					<span id="name"> <c:out value="${domain.name }"></c:out></span> <span
						id="address"> <c:out value="${domain.place }"></c:out>
					</span>
				</div>
			</c:forEach>
		</div>
		<form>
			<input type="place" id="place" placeholder="请填写城市[只写城市,如:广州]">
			<input type="name" placeholder="姓名" id="mz">
			<div class="submit">
				<input type="button" id="submit_button" value="添加">
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<!--start-copyright-->
	<div class="copy-right">
		<p>
			Copyright &copy; 2015.猎网工作室 All rights reserved. <a
				href="http://www.huntingweb.net/" target="_blank" title="模板之家">猎网工作室</a>
		</p>
	</div>
	<!--//end-copyright-->

	<script type="text/javascript"
		src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.4.min.js"></script>
	<script>
		$("#submit_button")
				.click(
						function() {
							var name = $("#mz").val();
							var place = $("#place").val();
							if (name != null && name != "" && place != null
									&& place != "")
								$
										.ajax({
											url : "../collector/service/"
													+ name + "/" + place,
											type : "POST",
											dataType : "json",
											cache : false,
											async : false,
											success : function(data,
													textStatus, jqXHR) {
												// success message
												var content = "<div class='people'><span id='name'>"
														+ data["name"]
														+ " "
														+ "</span><span id='address'>"
														+ data["place"]
														+ "</span></div>";
												$(".bloard").append(content);
											},
											error : function(jqXHR, textStatus,
													error) {
												// fail message
												alert(textStatus);
											}

										});
							else
								alert("名字和位置不能为空");
							$("#mz").val("");
							$("#place").val("");
						});
	</script>

</body>
</html>