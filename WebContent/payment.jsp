<%@ page import="com.Payment"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Componets/jquery-3.2.1.min.js"></script>
<script src="Componets/payment.js"></script>

</head>
<body class="container">
	<div class="row my-3">
	 	<div class="col-md-12">
	
		<h1 style="color:darkred">Payment Management</h1>
		   <form id="formPayment" name="formPayment" method="post" action="payment.jsp">
				
			 	
				<input id="paymentID" name="paymentID" type="hidden"
				 class="form-control form-control-sm" readonly style="border:none; background:none; ">
				<br>
				
				<p style="color:blue;"> Payment details </p>
				 Bill ID:
				<input id="billID" name="billID" type="text"
				 class="form-control form-control-sm">
				<br>
				 Payment Date:
				<input id="payDate" name="payDate" type="date"
				 class="form-control form-control-sm">
				<br> 
				
				 Amount:
				<input id="amount" name="amount" type="number"
				 class="form-control form-control-sm">
				<br>
				<br>
				 <p style="color:blue;"> Card details </p>
				 Card-holder Name:
				<input id="cardHolderName" name="cardHolderName" type="text"
				 class="form-control form-control-sm">
				<br>
				 Card No:
				<input id="cardNo" name="cardNo" type="text"
				 class="form-control form-control-sm">
				<br>
				Exp-Month:
				<input id="month" name="month" type="number"
				 class="form-control form-control-sm">
				<br>
				Exp-Year:
				<input id="year" name="year" type="number"
				 class="form-control form-control-sm">
				<br>
				 CcvNo:
				<input id="ccvNo" name="ccvNo" type="number"
				 class="form-control form-control-sm">
				<br>
				<br>
				<input id="btnSave" name="btnSave" type="button" value="Save"
				 class="btn btn-primary">
				<input type="hidden" id="hidepayIDSave" name="hidepayIDSave" value="">
				
			  </form>	
	
			<div id="alertSuccess" class="alert alert-success"></div>
		    <div id="alertError" class="alert alert-danger"></div>
	
		</div>
	 
	 </div>


<div class="row my-3">
 
		<div class="col-md-12">
				<div id="divItemsGrid">
				 <%
				 Payment itemObj = new Payment();
				 		 out.print(itemObj.readPayment());
				 %>
				 </div>
		</div>
 
</div>

	
		<br> 
	

</body>
</html>