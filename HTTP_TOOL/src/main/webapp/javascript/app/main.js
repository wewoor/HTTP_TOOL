/**
 * @author Ziv 
 * 
 */


$(document).ready(function() {
	//change method
	changeReqMeth();
	//send req
	sendReq();
});

//send event
function sendReq() {
	$("#btn_send").click(function() {
					
		if ($("#reqURL").val() != "") {//If the url is not null	

			var params = {
				url : $("#reqURL").val(),
				method : $("#choice_method").html(),
				body :  $("#req_body").val(),
				header : $("#req_header").val()
			};
			//Ajax request
			$.ajax({
				url : "service/doRequest",
				method : "POST",
				data : params,
				dataType: 'json',
				success : function(data) {//success ajax	
					if (data.exception != "" && data.exception != undefined) {
						alert(data.exception);
					} else {							
						$("#st_code").val(data.status);	
						$("#resp_return").html(data.bodyString);								
					}
				}
			});
			
		} else {
			alert("The URL is can't null!");
		}
	});
}

function changeReqMeth() {
	$("#req_method li").click(function(){		
		$("#choice_method").html($(this).children().html());	
	});
}