$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$("#post").click(function(event){
	var data = $("#login").serialize();
	console.log("data = "+data);
	$.post("restservices/app/team",data,function(response){
		console.log(response)
		window.sessionStorage.setItem("team_id", response.team_id);
	})	
	$.post("restservices/authentication",data,function(response){
		window.sessionStorage.setItem("sessionToken", response);

		sessionStorage.setItem('sessionToken',response);
		console.log("sessionToken = "+response);
		window.location.href = 'les8_practicum1.html';
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log(textStatus);
		console.log(errorThrown);
		});

})

$('#guest').click(function(){
	window.location.href = 'les8_practicum1.html';
});


