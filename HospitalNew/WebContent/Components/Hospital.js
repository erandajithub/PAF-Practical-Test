$(document).ready(function() 
{   
	$("#alertSuccess").hide();  
	$("#alertError").hide(); 
}); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "HospitalAPI",  
		type : type,  
		data : $("#formHospital").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onHospitalSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onHospitalSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divHospitalGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidHospitalIDSave").val("");  
	$("#formHospital")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());     
	$("#HospitalCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#HospitalName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#HospitalAddress").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#HospitalPhoNo").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "HospitalAPI",   
		type : "DELETE",   
		data : "HospitalID=" + $(this).data("hospitalid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onHospitalDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onHospitalDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divHospitalGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateHospitalForm() 
{  
	// CODE  
	if ($("#HospitalCode").val().trim() == "")  
	{   
		return "Insert Hospital Code.";  
	} 
 
	  
	if ($("#HospitalName").val().trim() == "")  
	{   
		return "Insert Hospital Name.";  
	} 
	
	if ($("#HospitalAddress").val().trim() == "")  
	{   
		return "Insert HospitalAddress.";  
	} 

  
	if ($("#HospitalPhoNo").val().trim() == "")
	{   
		return "Insert HospitalPhoNo.";  
	} 

	  
	

	return true; 
}