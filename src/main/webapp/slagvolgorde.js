$('.sortable').sortable().bind('sortupdate', function() {
    //Triggered when the user stopped sorting and the DOM position has changed.
	var i = 1;
	$('#spelers li').each(function(){
		this.value(i);
		i++;
		
	})
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
						i = i+1;
						console.log(item);
						$("#spelers").append('<li value="'+i+'"draggable="true" data-speler_id="'+item.speler_id+'">'+item.speler_naam+'</li>');
					})
				
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
			$('#spelers li').each(function(){
				console.log(this.value +", "+this.getAttribute("data-speler_id"));
			});
//			// select the list items
//			var lists = ol.getElementsByTagName('li');
//			// now loop through the items and set a custom property 'index'
//			var l = lists.length; // total items
//
//			for (var i=1;i<=l;i++){
//			  console.log(list[i].value, list[i].data );
//			}
//		    console.log(arr);
//		    
//			console.log("submit");
//			SetSpelers(arr);
//		    
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