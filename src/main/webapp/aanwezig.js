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
				$("#Spelers").append('<input id="Speler_Checkbox'+i+'" class="Speler_Checkbox" type="checkbox" value="'+item.id+'"/>'+item.naam+'<br>')
			})
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


//$("#submit").click(function(e) {
//    if ($(":checked").length >= 9){
//        // Submit your form here
//    	$('input[type=checkbox]:checked').map(function(_, el) {
//    	    console.log($(el).val());
//    		return $(el).val();
//    	}).get();
//    	
//    	
//	}else {
//        // Display warning here
//    	alert("Er moeten minimaal 9 spelers aanwezig zijn.");
//    }
//});

//$(document).ready(function() {
//	$('#save_value').click(function () {
//		console.log("saving");
//	    var arr = $('.Speler_Checkbox:checked').map(function () {
//	    	console.log(this.value);
//	        return this.value;
//	    }).get();
//	    console.log(arr);
//	    if (arr.length()>9){
//	    	console.log("submit");
//	    }else {
//	        // Display warning here
//	    	alert("Er moeten minimaal 9 spelers aanwezig zijn.");
//	    }
//	});
//})

$('#save_value').click(function () {
    var arr = $('.Speler_Checkbox:checked').map(function () {
        return this.value;
    }).get();
    console.log(arr);
    if (arr.length>9){
	console.log("submit");
	SetSpelers(arr);
    }else {
    	// Display warning here
    	alert("Er moeten minimaal 9 spelers aanwezig zijn.");
    }
});

function SetSpelers(speler_id_arr){
	var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
	var deel = window.sessionStorage.getItem("WedstrijdStatus");
	if (deel == "aanwezig_thuis"){
		var team_id = item.team_thuis_id;
		window.sessionStorage.setItem("WedstrijdStatus","aanwezig_uit")
		var wedstrijd_id =item.id;
		
		$.ajax({
				url: "restservices/app/aanwezig",
				method: 'POST',
				data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
				beforeSend: function (xhr) {
					var token = window.sessionStorage.getItem("sessionToken");
					xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
				},
				
				success: function (data) {
					console.log(data);
	
				}
		})
		
	}else if (deel == "aanwezig_uit"){
		var team_id = item.team_uit_id;
		$.ajax({
			url: "restservices/app/spelers",
			method: 'POST',
			data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
			beforeSend: function (xhr) {
				var token = window.sessionStorage.getItem("sessionToken");
				xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},
			
			success: function (data) {
				console.log(data);
				/* Handle data */
				
				$.each(data,function(i, item){
					console.log(item);
					$("#Spelers").append('<input type="checkbox" name="aanwezig" value="'+item.id+'"/>'+item.naam+'<br>');
				})
				$("#Spelers").append('<input type="submit">');
				window.location.href = 'slagvolgorde.html';
			}
			
	})
	}
	
}
