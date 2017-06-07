window.onload = loadCountries();



function loadCountries(){
		
	$.ajax({
		url: "restservices/countries/",
		method: "GET",
		beforeSend: function (xhr) {
		var token = window.sessionStorage.getItem("sessionToken");
		xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		},
		success: function (data) {
		/* Handle countryList */
			$.each(data,function(i, item){
				//console.log(item);
				var city = item.capital;
				var test = '"';
				var link = "onclick=''";
				var x =  "style="+test+"display: none;"+test;
				var deleteCountry = "onclick=' deleteCountry("+test+item.code+test+")'";
				var wijzigCountry = "onclick='updateCountry("+test+item.code+test+","+test+item.name+test+","+test+item.continent+test+","+test+item.region+test+","+test+item.surfacearea+test+","+test+item.indepyear+test+","+test+item.population+test+","+test+item.lifeexpectancy+test+","+test+item.gnp+test+","+test+item.gnpold+test+","+test+item.localname+test+","+test+item.governmentform+test+","+test+item.headofstate+test+","+test+item.code2+test+","+test+item.latitude+test+","+test+item.longitude+test+","+test+item.capital+test+")'";
				$("table").append("<tr><td class="+test+"code"+test+">" + item.code +"</td><td class="+test+"name"+test+">" + item.name + "</td><td class="+test+"continent"+test+">" + item.continent + "</td><td class="+test+"region"+test+">" + item.region + "</td><td class="+test+"surf"+test+">" + item.surfacearea + "</td><td class="+test+"ind"+test+x+">" + item.indepyear + "</td><td class="+test+"pop"+test+">" + item.population + "</td><td class="+test+"life"+test+x+">" + item.lifeexpectancy + "</td><td class="+test+"gnp"+test+x+">" + item.gnp + "</td><td class="+test+"gnpold"+test+x+">" + item.gnpold + "</td><td class="+test+"localname"+test+x+">" + item.localname + "</td><td class="+test+"gov"+test+x+">" + item.governmentform + "</td><td class="+test+"head"+test+x+">" + item.headofstate + "</td><td class="+test+"code2"+test+">" + item.code2 + "</td><td class="+test+"latitude"+test+x+">" + item.latitude + "</td><td class="+test+"longitude"+test+x+">" + item.longitude + "</td><td class="+test+"capital"+test+">" + item.capital + "</td><td class="+test+"wijzig"+test+" "+wijzigCountry+"><b>Wijzig</b> </td><td class="+test+"delete"+test+" "+deleteCountry+"><b>Delete</b> </td></tr>")
			})
		}})
		table();
		console.log("countries loaded");
}

function updateCountry(code,name,continent,region,surfacearea,indepyear,population,lifeexpectancy,gnp,gnpold,localname,governmentform,headofstate,code2,latitude,longitude,capital){
	console.log("updateCountry");
	console.log(code);
	$("#code3").val(code);
	$("#name2").val(name);
	$("#continent2").val(continent);
	$("#region2").val(region);
	$("#surfacearea2").val(surfacearea);
	$("#indepyear2").val(indepyear);
	$("#population2").val(population);
	$("#lifeexpectancy2").val(lifeexpectancy);
	$("#gnp2").val(gnp);
	$("#gnpold2").val(gnpold);
	$("#localname2").val(localname);
	$("#governmentform2").val(governmentform);
	$("#headofstate2").val(headofstate);
	$("#code22").val(code2);
	$("#latitude2").val(latitude);
	$("#longitude2").val(longitude);
	$("#capital2").val(capital);
	updateTogle();
	scroll();
	
}
function scroll(){
	$('html,body').animate({ scrollTop: 9999 }, 'slow');
}
function updateTogle(){
$header2 = $(".header2");
console.log($header2);
//getting the next element
$content2 = $header2.next();
//open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
$content2.slideToggle(500, function () {
    //execute this after slideToggle is done
    //change text of header based on visibility of content div
    $header2.text(function () {
        //change text based on condition
        return $content2.is(":visible") ? "" : "";
//        $("#response3").text("");
    });
});

};

function sort(myInput,x) {
  // Declare variables 
  var input, filter, table, tr, td, i;
  input = document.getElementById(myInput);
  filter = input.value.toUpperCase();
  table = document.getElementById("table");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[x];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    } 
  }
}


function deleteCountry(country){
	var uri = "restservices/countries/"+country;
	$.ajax(uri, {
		type: "delete",
		beforeSend: function (xhr) {
			var token = window.sessionStorage.getItem("sessionToken");
			xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},
		success: function(response) {
				alert("Country deleted!");
			},
			error: function(response) {
				alert("Could not delete Country!");
			}
		});
	}

$("#updateCountryForm").on('submit',function (e) {
//	.click(function() {
//}
	console.log("update starting")
	console.log( $("#updateCountryForm").serialize());
	var uri = "/restservices/countries/" + $("#code3").val();
	$.ajax(uri, {
		type: "put",
		beforeSend: function (xhr) {
			var token = window.sessionStorage.getItem("sessionToken");
			xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},
		data: $("#updateCountryForm").serialize(),
		success: function(response) {
			$("#response2").text("Country saved!");
			updateTogle();
		},
		error: function(response) {
			$("#response2").text("Could not update country!");
		}
	}); 
	e.preventDefault();
	
});


$("#newCountryForm").on('submit',function (e) {
	
	var uri = "/restservices/countries/";
	$.ajax(uri, {
		type: "post",
		beforeSend: function (xhr) {
			var token = window.sessionStorage.getItem("sessionToken");
			xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			},
		data: $("#newCountryForm").serialize(),
		success: function(response) {
			console.log(response)
			if (response == $("#newCountryForm").serialize()) 
			$("#response").text(response);
		},
		error: function(response) {
			$("#response").text("Could not insert new country!");
		}
	}); 
	
//	var data = $("#newCountryForm").serialize();
//	console.log(data);
//	$.post("/firstapp/restservices/countries/", data, function(response) {
//	$("#response").text(JSON.stringify(response));
//	});
	 e.preventDefault();
})





	
	

$(".header").click(function(){
    $header = $(this);
    //getting the next element
    console.log($header);
    $content = $header.next();console.log($content);
    //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
    $content.slideToggle(500, function () {
        //execute this after slideToggle is done
        //change text of header based on visibility of content div
        $header.text(function () {
            //change text based on condition
            return $content.is(":visible") ? "Nieuw land aanmaken." : "Nieuw land aanmaken";
            $("#response").text("");
        });
    });

});



$(".c1").change(function(event) {
	console.log(event.target.name);
    var checkbox = event.target;
    if (checkbox.checked) {
        //Checkbox has been checked
    	$("."+checkbox.name).css("display", "table-cell");
    } else {
        //Checkbox has been unchecked
    	$("."+checkbox.name).css("display", "none");    	
    }
    table();
});

function table(){
//Change the selector if needed
//var $table = $('table'),
//    $bodyCells = $table.find('tbody tr:first').children(),
//    colWidth;
//
//// Get the tbody columns width array
//colWidth = $bodyCells.map(function() {
//    return $(this).width();
//}).get();
//
//// Set the width of thead columns
//$table.find('thead tr').children().each(function(i, v) {
//    $(v).width(colWidth[i]);console.log($(v).width(colWidth[i]));
//});    

//Change the selector if needed
var $table = $('table'),
    $bodyCells = $table.find('tbody tr:first').children(),
    colWidth;

// Get the tbody columns width array
colWidth = $bodyCells.map(function() {
    return $(this).width();
}).get();

// Set the width of thead columns
$table.find('thead tr').children().each(function(i, v) {
    $(v).width(colWidth[i]);
});    
}


$(".header1").click(function () {

    $header1 = $(this);
    //getting the next element
    $content1 = $header1.next();
    //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
    $content1.slideToggle(500);
    });





