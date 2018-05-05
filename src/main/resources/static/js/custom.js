function organizationRegistration() {
	var x = document.getElementById("organization");
	var y = document.getElementById("isAdmin");

	if (x.style.display === "block") {
		x.style.display = "none";
		y.value = "false"
	} else {
		x.style.display = "block";
		y.value = "true"
	}
}

//var serverContext = [[@{/}]];

$(document).ready(function () {
	$('form').submit(function(event) {
		register(event);
	});
});

function register(event){
	console.log($('form').serialize())
}
/*function register(event){
	event.preventDefault();
	$(".alert").html("").hide();
	$(".error-list").html("");
    if($("#password").val() != $("#matchPassword").val()){
    	$("#globalError").show().html("Passwords Do not match");
    	return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "/signup", formData, function(data){
        if(data.message == "success"){
        	window.location.href = serverContext +"profileEdit.html";
        }
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "/emailError.html";
        }
        else if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = serverContext + "/index.html?message=" + data.responseJSON.message;
        }
        else if(data.responseJSON.error == "UserAlreadyExist"){
            $("#emailError").show().html(data.responseJSON.message);
        }
        else{
            var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#"+item.field+"Error").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br>");
            });
        }	
    });
}*/