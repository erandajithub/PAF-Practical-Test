<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title> 
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/Hospital.js"></script> 
</head> 
<body> 
<div class="row">  
		<div class="col-6"> 
			<h1>Hospital Management</h1>
				<form id="formHospital" name="formHospital" method="post" action="Hospital.jsp">  
					Hospital Code:  
					<input id="HospitalCode" name="HospitalCode" type="text" class="form-control form-control-sm">  
					<br> Hospital Name:  
					<input id="HospitalName" name="HospitalName" type="text" class="form-control form-control-sm">  
					<br> Hospital Address:  
					<input id="HospitalAddress" name="HospitalAddress" type="text" class="form-control form-control-sm">  
					<br> Hospital Phone No:  
					<input id="HospitalPhoNo" name="HospitalPhoNo" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divHospitalGrid">
					<%
					Hospital HosObj = new Hospital();
					out.print(HosObj.readHospital());
					%>
				</div>
				
				 
			</div>
		</div>

 </body>
</html>