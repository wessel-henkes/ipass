window.onload = load();
function getSpelers(team_id){
$.ajax({
		url: "restservices/app/spelers",
		method: 'POST',
		data:{"team_id":team_id},
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		
		success: function (data) {
			console.log(data);
			/* Handle data */
			
			$.each(data,function(i, item){
				console.log(item);
				$("#Spelers").append('<input type="checkbox" class="checked"name="speler_id" value="'+item.id+'"/>'+item.naam+'<br>')
		})
			$("#Spelers").append('<button id="submit">opslaan</button>')
				
		}
	})
}	
function load(){
	var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
	console.log(item);
	var deel = window.sessionStorage.getItem("WedstrijdStatus");
	console.log(deel);
	if (deel == "aanwezig_thuis"){
		$(".header").append('<h1>Aanwezigheid '+item.team_thuis+'</h1>');
		getSpelers(item.team_thuis_id);
	} else if (deel == "aanwezig_uit"){
		$(".header").append('<h1>Aanwezigheid '+item.team_uit+'</h1>');
		getSpelers(item.team_uit_id);
	}
}


$("#submit").click(function(e) {
    if ($(":checked").length >= 9){
        // Submit your form here
    	console.log($('input[type=checkbox]:checked').map(function(_, el) {	    console.log($(el).val());return $(el).val();}).get(););
    	
    	
	}else {
        // Display warning here
    	alert("Er moeten minimaal 9 spelers aanwezig zijn.");
    }
});



function SetSpelers(team_id){
	$.ajax({
			url: "restservices/app/spelers",
			method: 'POST',
			data:{"team_id":team_id},
			beforeSend: function (xhr) {
			var token = window.sessionStorage.getItem("sessionToken");
			xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},
			
			success: function (data) {
				console.log(data);
				/* Handle data */
				
				$.each(data,function(i, item){
					console.log(item);
					$("#Spelers").append('<input type="checkbox" name="aanwezig" value="'+item.id+'"/>'+item.naam+'<br>')
			})
				$("#Spelers").append('<input type="submit">')
					
			}
		})
	}	


