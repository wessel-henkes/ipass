		$(function() { //voor sortable list
			$('.sortable').sortable();
			$('.handles').sortable({
				handle: 'span'
			});
			$('.connected').sortable({
				connectWith: '.connected'
			});
			$('.exclude').sortable({
				items: ':not(.disabled)'
			});
		});
		
window.onload = load();
function getSpelers(team_id,wedstrijd_id){
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
					
					$.each(data,function(i, item){
						console.log(item);
						$("#spelers").append('<li draggable="true"value="'+item.speler_id+'">'+item.speler_naam+'</li>');
					})
				}
			})
}	
		function load(){
			var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
			console.log(item);
			var deel = window.sessionStorage.getItem("WedstrijdStatus");
			console.log(deel);
			if (deel == "slagvolgorde_thuis"){
				$(".header").append('<h1>Slagvolgorde '+item.team_thuis+'</h1>');
				getSpelers(item.team_thuis_id,item.id);
			} else if (deel == "slagvolgorde_uit"){
				$(".header").append('<h1>Slagvolgorde '+item.team_uit+'</h1>');
				getSpelers(item.team_uit_id);
			}
		}



		$('#save_value').click(function () {
		    var arr = $('.Speler_Checkbox:checked').map(function () {
		        return this.value;
		    }).get();
		    console.log(arr);
		    if (arr.length>=9){
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
			var wedstrijd_id =item.id;
			if (deel == "slagvolgorde_thuis"){
				var team_id = item.team_thuis_id;
				
				
				$.ajax({
						url: "restservices/app/opstelling/aanwezig",
						method: 'PUT',
						data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
						beforeSend: function (xhr) {
							var token = window.sessionStorage.getItem("sessionToken");
							xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
						},
						
						success: function (data) {
							console.log(data);
							window.sessionStorage.setItem("WedstrijdStatus","slagvolgorde_uit");
							window.location.href = 'aanwezig.html';
						}
				})
				
			}else if (deel == "slagvolgorde_uit"){
				var team_id = item.team_uit_id;
				$.ajax({
					url: "restservices/app/opstelling/aanwezig",
					method: 'PUT',
					data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
					beforeSend: function (xhr) {
						var token = window.sessionStorage.getItem("sessionToken");
						xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
					},
					
					success: function (data) {
						console.log(data);
						window.sessionStorage.setItem("WedstrijdStatus","veldpositie_thuis");
						window.location.href = 'slagvolgorde.html';
					}
					
			})
			}
			
		}