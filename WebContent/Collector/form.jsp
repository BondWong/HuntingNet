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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HuntingWeb Data Collector</title>
</head>
<body>
	<div id="illustration-filed">
		<c:forEach var="domain" items="${requestScope.reps }">
			<p>
				名字:
				<c:out value="${domain.name }"></c:out>
				| 位置:
				<c:out value="${domain.place }"></c:out>
			</p>
		</c:forEach>
	</div>
	<form>
		名字: <input id="name" /> 位置: <select id="place">
			<option value="">请选择</option>
			<option value="volvo">Volvo</option>
			<option value="saab">Saab</option>
			<option value="fiat">Fiat</option>
			<option value="audi">Audi</option>
		</select> <a id="submit-button" href="javascript:void(0)">提交</a>

	</form>

	<script type="text/javascript"
		src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.4.min.js"></script>
	<script>
		$("#submit-button").click(
				function() {
					var name = $("#name").val();
					var place = $("#place").val();
					if (name != null && place != "")
						$.ajax({
							url : "../collector/service/" + name + "/" + place,
							type : "POST",
							dataType : "json",
							cache : false,
							async : false,
							success : function(data, textStatus, jqXHR) {
								// success message
								var content = "<p>名字: " + data["name"]
										+ "| 位置: " + data["place"] + "</p>";
								$("#illustration-filed").append(content);
							},
							error : function(jqXHR, textStatus, error) {
								// fail message
								alert(textStatus);
							}

						});
					else
						alert("名字和位置不能为空");
				});
	</script>
</body>
</html>