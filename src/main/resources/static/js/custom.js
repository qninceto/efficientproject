function myFunction() {
		var pass1 = document.getElementById("pass1").value;
		var pass2 = document.getElementById("pass2").value;
		var ok = true;
		if (pass1 != pass2) {
			alert("Passwords Do not match");
			document.getElementById("pass1").style.borderColor = "#E34234";
			document.getElementById("pass2").style.borderColor = "#E34234";
			ok = false;
		}
		return ok;
	}

	function myFunction2() {
		var x = document.getElementById("organization");
		 var y = document.getElementById("isAdmin"); 
		
		if (x.style.display === "block") {
			x.style.display = "none";
		 	y.value="false" 
		} else {
			x.style.display = "block";
		 	y.value="true"
		}
	}