window.onload = load();
var exitSubmit = false;
function getSlagvolgorde(data,team){
	console.log(Object.keys(Object.keys(data)).length);
	
	console.log(data);
		if(Object.keys(Object.keys(data)).length == 0){
				alert("Van deze wedstrijd is de opstelling nog niet ingevoerd.");
				window.location.href = 'home.html';
				return;
				} else{
				if (team =="thuis"){
					$.each(data,function(i, item){
						console.log(item);
						if (item.slagvolgorde == 0){
							 exitSubmit = true;
		                      return;
						}else{$("#slagvolgorde_thuis").append('<li value="'+item.slagvolgorde+'">'+item.speler_naam+'</li>')}
						
					});
					if (exitSubmit) {
						alert("Voor deze wedstrijd is de opstelling nog niet ingevoerd.");
//						window.location.assign('home.html');
					    return;
					}
				} else if (team =="uit"){
					$.each(data,function(i, item){
						console.log(item);
						if (item.slagvolgorde == 0){
							 exitSubmit = true;
		                      return;
						}else{
							
						$("#slagvolgorde_uit").append('<li value="'+item.slagvolgorde+'">'+item.speler_naam+'</li>')
						}
					});
					if (exitSubmit) {
						alert("Voor deze wedstrijd is de opstelling nog niet ingevoerd.");
//						window.location.assign('home.html');
					    return;
					}
				}
			}
}
		




function getVeldpositie(data,team){
		console.log(data);
		if (Object.keys(Object.keys(data)).length == 0){
			alert("De veldposities van het "+team+" spelende team is nog niet bekend.");
			if (team =="thuis"){
				$("#veldpositie1").hide();
			} else if (team =="uit"){
				$("#veldpositie2").hide();
			}
			} else{
					if (team =="thuis"){
						$.each(data,function(i, item){
							i = i+1;
							console.log(item);
							$("#veldpositie_thuis").append('<li value="'+i+'"data-speler_id="'+item.speler_id+'">'+item.speler_naam+'</li>');
					        if ( i > 9){
					          $("#index_thuis").append('<li>R</li>');
					        }
						})
					} else if (team =="uit"){
						$.each(data,function(i, item){
							i = i+1;
							console.log(item);
							$("#veldpositie_uit").append('<li value="'+i+'"data-speler_id="'+item.speler_id+'">'+item.speler_naam+'</li>');
					        if ( i > 9){
					          $("#index_uit").append('<li>R</li>');
					        }
						})
					}
				}
}


function load(){
	$(".card").hide();
	$.ajax({
		url: "restservices/app/oopstelling",
		method: 'POST',
		data:{"team_thuis_id":1,"team_uit_id":2,"wedstrijd_id":3},
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		
		success: function (data) {
			console.log(data);
			$.each(data,function(i, item){
				getSlagvolgorde(item.slagvolgorde_thuis,"thuis");
				getSlagvolgorde(item.slagvolgorde_uit,"uit");
				getVeldpositie(item.veldpositie_thuis,"thuis");
				getVeldpositie(item.veldpositie_uit,"uit");
				$(".card").show();
			})
		},
		error: function (jqXHR, exception){
			alert("Van deze wedstrijd is de opstelling nog niet ingevoerd.");
			window.location.href = 'home.html';
			
			console.log(exception);
		}
	})
	
	var role =window.sessionStorage.getItem("role");
	console.log("role="+role);
//	if (role != "admin" && role !="user"){window.location.href = 'home.html';}
	
	var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
	console.log(item);
	var x = "'"
	$("#wedstrijd").append('<div class="col-3 col-m-3 " style="width:100%;"><div class="card"><div class="container"><h4><b>'+item.team_thuis+' tegen '+item.team_uit+' </b></h4><p>datum: '+item.datum+'</p><p>tijd:'+item.tijd+'</p></div></div></div>');
	
	$(".header").append('<h1>Opstelling '+item.team_thuis+' tegen '+item.team_uit+'</h1>');
	$(".thuis").append('<h2>'+item.team_thuis+'</h2>');
	$(".uit").append('<h2>'+item.team_uit+'</h2>');
}



$('#terug').click(function () {
	window.location.href = 'home.html';
});


