window.onload = initPage();

function initPage(){
	loadCountries();
	$.get("http://ip-api.com/json",function(data){
		var test = '"';
		var link = "onclick='showWeather("+data.lat+","+data.lon+","+test+data.city+test+")'";
		
		$(countryCode).after(("<label id='dataIP'>"+ data.countryCode+"</label>"))

		$(country).after(("<label id='dataIP'>"+ data.country+"</label>"))

		$(regionName).after(("<label id='dataIP'>"+ data.regionName +"</label>"))

		$(city).after(("<label id='dataIPCity'"+link+">"+ data.city+"</label>"))

		$(zip).after(("<label id='datav'>"+ data.zip+"</label>"))

		$(lat).after(("<label id='dataIP'>"+ data.lat+"</label>"))

		$(lon).after(("<label id='dataIP'>"+ data.lon+"</label>"))

		$(ip).after(("<label id='dataIP'>"+ data.query+"</label>"))
		
		showWeather(data.lat, data.lon, data.city);
		
	})
	}

function showWeather(lat, lon, city){
	localStorage.clear();
	removeElement();removeElement();removeElement();removeElement();removeElement();removeElement();removeElement();
	var api = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&APPID=4bf812e2a2f01920813170866a5b28fe";
	var d = new Date();
	if (parseInt(localStorage.getItem("data.time"+city)) > (d.getTime()-600000) && localStorage.getItem("data.time"+city) != null){
		
		console.log(parseInt(localStorage.getItem("data.time"+city)) +" < " + (d.getTime()-600000) + " = true");
		data = JSON.parse(localStorage.getItem("data"+city));
		//console.log(data);
		var tempK = parseInt((data.main.temp));
		var tempC = tempK - 273.15;
		$(temp).after(("<label id='data'>"+ round(tempC, 1) + "&#8451 </label>"))

		$(humid).after(("<label id='data'>"+ data.main.humidity + "%"+"</label>"))

		var windMS = parseInt(data.wind.speed);
		var windKmH = windMS * 3.6
		$(windspeed).after(("<label id='data'>"+ round(windKmH, 1) +" km/u </label>"))

		var deg = parseInt(data.wind.deg);
		$(windDirection).after(("<label id='data'>"+ degToCompass(deg) +"</label>"))

		$(sunRise).after(("<label id='data'>"+ secToDate(data.sys.sunrise)+"</label>"))

		$(sunSet).after(("<label id='data'>"+ secToDate(data.sys.sunset)+"</label>"))
		$(weer).append("<label id='data' width=auto>"+city+"<label>")	
	} else{
	$.get(api,function(data){
			localStorage.removeItem("data"+city);
			localStorage.removeItem("data.time"+city);
			localStorage.setItem("data"+city, JSON.stringify(data)); 
			localStorage.setItem("data.time"+city, d.getTime());
			//console.log(localStorage.getItem("data"+city));
			var tempK = parseInt((data.main.temp));
			var tempC = tempK - 273.15;
			$(temp).after(("<label id='data'>"+ round(tempC, 1) + "&#8451 </label>"))
	
			$(humid).after(("<label id='data'>"+ data.main.humidity + "%"+"</label>"))
	
			var windMS = parseInt(data.wind.speed);
			var windKmH = windMS * 3.6
			$(windspeed).after(("<label id='data'>"+ round(windKmH, 1) +" km/u </label>"))
	
			var deg = parseInt(data.wind.deg);
			$(windDirection).after(("<label id='data'>"+ degToCompass(deg) +"</label>"))
	
			$(sunRise).after(("<label id='data'>"+ secToDate(data.sys.sunrise)+"</label>"))
	
			$(sunSet).after(("<label id='data'>"+ secToDate(data.sys.sunset)+"</label>"))
			$(weer).append("<label id='data' width=10%>"+city+"<label>")
		})
	}	
}

function round(value, decimals) {
	  return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
	}

function degToCompass(num) {
    while( num < 0 ) num += 360 ;
    while( num >= 360 ) num -= 360 ;
    val= Math.round( (num -11.25 ) / 22.5 ) ;
    arr=["Noord","NNO","Noordoost","ONO","Oost","OZO", "Zuidoost",
          "ZZO","Zuid","ZZW","Zuidwest","WZW","West","WNW","Noordwest","NNW"] ;
    return arr[ Math.abs(val) ] ;
}

function secToDate(sec){
	var date = new Date(null);
	date.setSeconds(sec); // specify value for SECONDS here
	return date.toISOString().substr(11, 8);
}

function loadCountries(){
		$.get("http://localhost:8888/firstapp/restservices/countries/" ,function(data){
			$.each(data,function(i, item){
				var city = item.capital;
				var test = '"';
				var link = "onclick='showWeather("+item.latitude+","+item.longitude+","+test+city+test+")'";
				$("table").append("<tr ><td "+link+" >" + item.name +"</td><td "+link+">" + item.capital + "</td><td "+link+">" + item.region + "</td><td "+link+">" + item.surfacearea + "</td><td "+link+">" + item.population +"</td></tr>")
				
			})
		})
}


function removeElement(){
	try{
		var element = document.getElementById("data");
		element.outerHTML = "";
		delete element;
	}
	catch(err){
	}
}

function sortTable(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("table");
	  switching = true;
	  //Set the sorting direction to ascending:
	  dir = "asc"; 
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("TR");
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[n];
	      y = rows[i + 1].getElementsByTagName("TD")[n];
	      /*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
	      if (dir == "asc") {
	        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
	        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	      //Each time a switch is done, increase this count by 1:
	      switchcount ++; 
	    } else {
	      /*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
	      if (switchcount == 0 && dir == "asc") {
	        dir = "desc";
	        switching = true;
	      }
	    }
	  }
	}

















