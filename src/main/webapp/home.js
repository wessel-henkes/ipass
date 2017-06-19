window.onload = loadWedstrijden();

function loadWestrijden(){
	
	$.ajax({
		url: "restservices/app/wedstrijden",
		method: "POST",
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		data:{ team_id: window.sessionStorage.getItem("team_id")},
		success: function (data) {
		/* Handle countryList */
			$.each(data,function(i, item){
				console.log(item);
//				var city = item.capital;
//				var test = '"';
//				var link = "onclick=''";
//				var x =  "style="+test+"display: none;"+test;
//				var deleteCountry = "onclick=' deleteCountry("+test+item.code+test+")'";
//				var wijzigCountry = "onclick='updateCountry("+test+item.code+test+","+test+item.name+test+","+test+item.continent+test+","+test+item.region+test+","+test+item.surfacearea+test+","+test+item.indepyear+test+","+test+item.population+test+","+test+item.lifeexpectancy+test+","+test+item.gnp+test+","+test+item.gnpold+test+","+test+item.localname+test+","+test+item.governmentform+test+","+test+item.headofstate+test+","+test+item.code2+test+","+test+item.latitude+test+","+test+item.longitude+test+","+test+item.capital+test+")'";
//				$("table").append("<tr><td class="+test+"code"+test+">" + item.code +"</td><td class="+test+"name"+test+">" + item.name + "</td><td class="+test+"continent"+test+">" + item.continent + "</td><td class="+test+"region"+test+">" + item.region + "</td><td class="+test+"surf"+test+">" + item.surfacearea + "</td><td class="+test+"ind"+test+x+">" + item.indepyear + "</td><td class="+test+"pop"+test+">" + item.population + "</td><td class="+test+"life"+test+x+">" + item.lifeexpectancy + "</td><td class="+test+"gnp"+test+x+">" + item.gnp + "</td><td class="+test+"gnpold"+test+x+">" + item.gnpold + "</td><td class="+test+"localname"+test+x+">" + item.localname + "</td><td class="+test+"gov"+test+x+">" + item.governmentform + "</td><td class="+test+"head"+test+x+">" + item.headofstate + "</td><td class="+test+"code2"+test+">" + item.code2 + "</td><td class="+test+"latitude"+test+x+">" + item.latitude + "</td><td class="+test+"longitude"+test+x+">" + item.longitude + "</td><td class="+test+"capital"+test+">" + item.capital + "</td><td class="+test+"wijzig"+test+" "+wijzigCountry+"><b>Wijzig</b> </td><td class="+test+"delete"+test+" "+deleteCountry+"><b>Delete</b> </td></tr>")
			})
		}})
//		table();
		console.log("countries loaded");
}