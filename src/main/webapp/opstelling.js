window.onload = load();
function getSlagvolgorde(team_id,wedstrijd_id,team){
$.ajax({
		url: "restservices/app/slagvolgorde",
		method: 'POST',
		data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id},
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		
		success: function (data) {
			console.log(data);
			/* Handle data */
			if (team =="thuis"){
				$.each(data,function(i, item){
					console.log(item);
					$("#slagvolgorde_uit").append('<li value="'+item.slagvolgorde+'"/>'+item.naam+'</li>')
				})
			} else if (team =="uit"){
				$.each(data,function(i, item){
					console.log(item);
					$("#slagvolgorde_uit").append('<li value="'+item.slagvolgorde+'"/>'+item.naam+'</li>')
				})
			}
		},
		fail: function(){
			alert("van deze wedstrijd is de opstelling nog niet ingevoerd.");
			window.location.href = 'home.html';
			
		}
	})
}	

function getVeldpositie(team_id,wedstrijd_id,team){
	$.ajax({
			url: "restservices/app/opstelling",
			method: 'POST',
			data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id},
			beforeSend: function (xhr) {
			var token = window.sessionStorage.getItem("sessionToken");
			xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},

			success: function (data) {
				console.log(data);
				/* Handle data */
				if (team =="thuis"){
					$.each(data,function(i, item){
						i = i+1;
						console.log(item);
						$("#veldpositie_thuis").append('<li value="'+i+'"data-speler_id="'+item.speler_id+'">'+item.speler_naam+'</li>');
				        if ( i >9){
				          $("#veldpositie_thuis").append('<li>R</li>');
				        }
					})
				} else if (team =="uit"){
					$.each(data,function(i, item){
						i = i+1;
						console.log(item);
						$("#veldpositie_uit").append('<li value="'+i+'"data-speler_id="'+item.speler_id+'">'+item.speler_naam+'</li>');
				        if ( i > 9){
				          $("#veldpositie_uit").append('<li>R</li>');
				        }
					})
				}
			},
			error: function (jqXHR, exception){
				console.log(exception);
				alert("de veldposities zijn nog niet bekend.");
				$("#veldpositie").hide;
			}
	})
}

function load(){
	var role =window.sessionStorage.getItem("role");
	console.log("role="+role);
	if (role != "admin"){window.location.href = 'home.html';}
	
	var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
	console.log(item);
	var x = "'"
	$("#wedstrijd").append('<div class="col-3 col-m-3 "><div class="card"><div class="container"><h4><b>'+item.team_thuis+' tegen '+item.team_uit+' </b></h4><p>datum: '+item.datum+'</p><p>tijd:'+item.tijd+'</p></div></div></div>');
	getSlagvolgorde(item.team_thuis_id,item.id,"thuis");
	getSlagvolgorde(item.team_uit_id,item.id,"uit");
	getVeldpositie(item.team_thuis_id,item.id,"thuis");
	getVeldpositie(item.team_uit_id,item.id,"uit");
	$(".header").append('<h1>Opstelling '+item.team_thuis+' tegen '+item.team_uit+'</h1>');
	$(".thuis").append('<h2>'+item.team_thuis+'</h2>');
	$(".uit").append('<h2>'+item.team_uit+'</h2>');
}



$('#terug').click(function () {
	window.location.href = 'home.html';
});


