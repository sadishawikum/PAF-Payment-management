$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
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
	
		var status = validateBillForm();
		if (status != true)
	  {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	   }
	
		
	var type = ($("#hidepayIDSave").val().trim() == "") ? "POST" : "PUT";
	console.log(`\n\n\n>>>> FORM SUBMIT METHOD = ${type}\n\n\n`);
	$.ajax( 
			{ 
				 url : "PaymentsAPI", 
				 type : type, 
				 data : $("#formPayment").serialize(), 
				 dataType : "text",
				 complete : function(response, status)
				 { 
				 	console.log(`>>>> RES ${response}`);
					 onBillSaveComplete(response.responseText, status); 
				 } 
			}
		);
		
	});
	
	
	
	//Billsavecomplefunction
	


	function onBillSaveComplete(response, status) 
	{ 
	  if (status == "success") 
	   { 
				 var resultSet = JSON.parse(response); 
				 
				 if (resultSet.status.trim() == "success") 
				 
				 { 
					 $("#alertSuccess").text("Successfully saved."); 
					 $("#alertSuccess").show(); 
					 $("#divItemsGrid").html(resultSet.data); 
					 
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
		
	
		 $("#hidepayIDSave").val(""); 
		 $("#formPayment")[0].reset(); 
	}
	



	   
	
	$(document).on("click", ".btnUpdate", function(event) 
	{ 
		  
		 $("#hidepayIDSave").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#paymentID").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#billID").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#payDate").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#amount").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#cardHolderName").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#cardNo").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#month").val($(this).closest("tr").find('td:eq(6)').text()); 
		 $("#year").val($(this).closest("tr").find('td:eq(7)').text()); 
		 $("#ccvNo").val($(this).closest("tr").find('td:eq(8)').text()); 
		
	
		
	});
	
	
	
//delete
	
$(document).on("click", ".btnRemove", function(event) 
	{ 
		 $.ajax( 
			 { 
					 url : "PaymentsAPI", 
					 type : "DELETE", 
					 data : "id=" + $(this).data("id"),
					 dataType : "text", 
					 complete : function(response, status) 
				 { 
			     onBillDeleteComplete(response.responseText, status); 
			     } 
		 }); 
	});
	


//deletecompletion

function onBillDeleteComplete(response, status) 
{ 
	  if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 
			 if (resultSet.status.trim() == "success") 
				 { 
					 $("#alertSuccess").text("Successfully deleted."); 
					 $("#alertSuccess").show(); 
					 
					 $("#divItemsGrid").html(resultSet.data); 
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
		
	// CLIENT-MODEL================================================================
	
	function validateBillForm()
	{
	
	 
		
		if ($("#billID").val().trim() == "")
		 {
			 return "Insert bill ID.";
		 }
	 
		
		if ($("#payDate").val().trim() == "")
		 {
		 	return "Insert payment date.";
		 }
		 
		
		if ($("#amount").val().trim() == "")
		 {
			 return "Insert amount.";
		 }
		 
		 if ($("#cardHolderName").val().trim() == "")
		 {
		 	return "Insert card-holde name.";
		 }
		 
		 
		  if ($("#cardNo").val().trim() == "")
		 {
		 	return "Insert Card-No.";
		 }
		 
		  if ($("#month").val().trim() == "")
		 {
		 	return "Insert Card exp-month.";
		 }
		 
		  if ($("#year").val().trim() == "")
		 {
		 	return "Insert Card exp-year";
		 }
		 
		  if ($("#ccvNo").val().trim() == "")
		 {
		 	return "Insert ccvNo.";
		 }
		 
		 
		
		return true;
		


}
