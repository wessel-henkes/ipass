$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$("#post").click(function(event){
	var data = $("#login").serialize();
	console.log("data = "+data);
	$.post("restservices/app/team",data,function(response){
		console.log(data)
		console.log(response)
	})
//		window.sessionStorage.setItem("teamID", response);
		
	$.post("restservices/authentication",data,function(response){
		window.sessionStorage.setItem("sessionToken", response);

		sessionStorage.setItem('sessionToken',response);
		console.log("sessionToken = "+response);
//		window.location.href = 'les8_practicum1.html';
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log(textStatus);
		console.log(errorThrown);
		});

})

$('#guest').click(function(){
	window.location.href = 'les8_practicum1.html';
});


function test(){
	$.ajax({
		url: "restservices/countries/team/username=qwerty&password=admin",
		method: "POST",
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		success: function (data) {console.log(data);}

		})}

function test2(){
	 $.post("restservices/countries/team",
			    {
			        username: "paul",
			        password: "qwerty"
			    },
			    function(data, status){
			        alert("Data: " + data + "\nStatus: " + status);
			    });
}
