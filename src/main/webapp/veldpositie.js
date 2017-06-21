$('.sortable').sortable().bind('sortupdate', function() {
    //Triggered when the user stopped sorting and the DOM position has changed.
	var i = 1;
	$('#spelers li').each(function(){
		this.setAttribute("value",i);
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
						$('#sortable1, #sortable2').sortable({
						    connectWith: '.connected'
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
			if (deel == "veldpositie_thuis"){
				$(".header").append('<h1>Veldpositie '+item.team_thuis+'</h1>');
				getSpelers(item.team_thuis_id,item.id);
			} else if (deel == "veldpositie_uit"){
				$(".header").append('<h1>veldpositie '+item.team_uit+'</h1>');
				getSpelers(item.team_uit_id);
			}
		}



		$('#save_value').click(function () {
			var speler_id_arr = [];
			$('#spelers li').each(function(){
				var a =this.getAttribute("data-speler_id");
//				console.log(this.value +", "+a);
				speler_id_arr.push(a);
			});
			console.log(speler_id_arr);
			SetSpelers(speler_id_arr);
		});

		function SetSpelers(speler_id_arr){
			var item = JSON.parse(window.sessionStorage.getItem("WedstrijdData"));
			var deel = window.sessionStorage.getItem("WedstrijdStatus");
			var wedstrijd_id =item.id;
			if (deel == "veldpositie_thuis"){
				var team_id = item.team_thuis_id;


				$.ajax({
						url: "restservices/app/opstelling/veldpositie",
						method: 'PUT',
						data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
						beforeSend: function (xhr) {
							var token = window.sessionStorage.getItem("sessionToken");
							xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
						},

						success: function (data) {
							console.log(data);
							window.sessionStorage.setItem("WedstrijdStatus","veldpositie_uit");
							window.location.href = 'veldpositie.html';
						}
				})

			}else if (deel == "veldpositie_uit"){
				var team_id = item.team_uit_id;
				$.ajax({
					url: "restservices/app/opstelling/veldpositie",
					method: 'PUT',
					data:{"team_id":team_id,"wedstrijd_id":wedstrijd_id,"speler_id_arr":speler_id_arr},
					beforeSend: function (xhr) {
						var token = window.sessionStorage.getItem("sessionToken");
						xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
					},

					success: function (data) {
						console.log(data);
						window.sessionStorage.setItem("WedstrijdStatus","veldpositie_thuis");
						window.location.href = 'opstelling.html';
					}

			})
			}

		}
