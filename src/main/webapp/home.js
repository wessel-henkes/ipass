window.onload = loadWedstrijden();

function loadWedstrijden(){


	$.ajax({
		url: "restservices/app/wedstrijden",
		method: 'POST',
		data:{"team_id":window.sessionStorage.getItem("team_id")},
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		
		success: function (data) {
			console.log(data)
			/* Handle data */
			
			$.each(data,function(i, item){
				console.log(item);
				$("#wedstrijden").append('<div class="col-3 col-m-3 "><div class="card"><div class="container"><h4><b>'+item.team_thuis+' tegen '+item.team_uit+' </b></h4><p>datum: '+item.datum+'</p><p>tijd:'+item.tijd+'</p><button class="button"><span><b>opstelling bekijken</b></span></button><button class="button" onclick="opstellingInvoeren('+item+')><span><b>opstelling invoeren</b></span></button><button class="button" onclick=""><span><b>opstelling aanpassen</b></span></button></div></div></div>');
			})	
		}
	})
}

function opstellingInvoeren(item){
	window.sessionStorage.setItem("WedstrijdData", JSON.stringify(item));
	window.sessionStorage.setItem("WedstrijdStatus", "aanwezig_thuis");
	window.location.href = 'aanwezig.html';
}
		
		
		
		
		
		
		
		