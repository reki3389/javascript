<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String empId = request.getParameter("empId");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>listEmployee.html</title>
	<script src="../jquery/jquery.min.js"></script>
	<script>
		var focusId = <%=empId%>;
		$.ajax({
			url: "../EmpServlet",
			dataType: "json",
			success: function (result) {
				console.log(result);
				var table, tr, th, td;
				table = $("<table />");
				table.attr("border", "1");
				// th 타이틀 가지고 오는 부분.
				tr = $("<tr />");
				for (title in result[0]) {
					th = $("<th />").text(title);
					tr.append(th);
				}
				tr.append($("<th />").text("삭제"))
				table.append(tr);
				// td 데이터를 가지고 보여주는 부분.
				$.each(result, function (i, o) {
					tr = $("<tr />").attr("id", o.employeeId).dblclick(changeFunc);
					tr.mouseenter(function () { $(this).css("backgroundColor", "yellow") });
					tr.mouseleave(function () { $(this).css("backgroundColor", "") });
					for (field in o) {
						//console.log(field);
						if (field == "employeeId") {
							var ahref = $("<a />").text(o[field]);
							ahref.attr("href", "updateEmployee.jsp?empId=" + o[field]);
							td = $("<td />").html(ahref);
							tr.append(td);
						} else {
							td = $("<td />").text(o[field]);
							tr.append(td);
						}
					}
					if (focusId == o.employeeId) {
						console.log(o)
						tr.css("background-color", "red");
						tr.focus();
					}
					var del = $("<button />").text("Del");
					del.click(delFunc);
					tr.append($("<td />").html(del));
					table.append(tr);
				})
				$("#show").append(table);
			}
		})
		function changeFunc() {
			var deptName = $(this).children().eq(0).text();
			var firstName = $(this).children().eq(1).text();
			var jobId = $(this).children().eq(2).text();
			var lastName = $(this).children().eq(3).text();
			var hireDate = $(this).children().eq(4).text();
			var empId = $(this).children().eq(5).text();
			var salary = $(this).children().eq(6).text();
			var email = $(this).children().eq(7).text();
			var tr = $("<tr />");
			tr.append($("<td />").text(deptName));
			tr.append($("<td />").text(firstName));
			tr.append($("<td />").text(jobId));
			tr.append($("<td />").text(lastName));
			tr.append($("<td />").text(hireDate));
			tr.append($("<td />").text(empId));
			tr.append($("<td />").html($("<input />").val(salary)));
			tr.append($("<td />").html($("<input />").val(email)));
			tr.append($("<td />").html($("<button />").text("변경")
				.click(updateFunc)));
			$("#" + empId).after(tr);
			$("#" + empId).css("display", "none");
		}
		function updateFunc() {
			console.log("변경.")
		}
		function delFunc() {
			console.log("delFunc");
			var empId = $(this).parent().parent().attr("id");
			$.ajax({
				url: "../DeleteEmpServ",
				data: "empId=" + empId,
				success: function () {
					$("#" + empId).remove();
					console.log("del success");
				}
			});
		}
	</script>
</head>

<body>
	<a href="insertEmployee.html">입력화면</a>
	<p id="show"></p>
</body>

</html>