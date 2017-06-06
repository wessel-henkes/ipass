console.log("JavaScript started");

type = null;
value = null;

document.getElementById('btn_0').onclick = function(){setValue(0)};
document.getElementById('btn_1').onclick = function(){setValue(1)};
document.getElementById('btn_2').onclick = function(){setValue(2)};
document.getElementById('btn_3').onclick = function(){setValue(3)};
document.getElementById('btn_4').onclick = function(){setValue(4)};
document.getElementById('btn_5').onclick = function(){setValue(5)};
document.getElementById('btn_6').onclick = function(){setValue(6)};
document.getElementById('btn_7').onclick = function(){setValue(7)};
document.getElementById('btn_8').onclick = function(){setValue(8)};
document.getElementById('btn_9').onclick = function(){setValue(9)};

document.getElementById('btn_plus').onclick = function(){setType("+")};
document.getElementById('btn_min').onclick = function(){setType("-")};
document.getElementById('btn_prod').onclick = function(){setType("*")};
document.getElementById('btn_div').onclick = function(){setType("/")};
document.getElementById('btn_eq').onclick = function(){setType("=")};

document.getElementById('btn_clear').onclick = function(){clear()};

function setValue(getal){
	var currentValue = document.getElementById('display').innerHTML;
	if (currentValue != "0"){
		currentValue += getal;
		document.getElementById('display').innerHTML = currentValue;
	}else {
		currentValue = getal;
		document.getElementById('display').innerHTML = currentValue;
	}
	
};

function saveValue(){
	value = document.getElementById('display').innerHTML;
	console.log("value is " + value);
};

function setType(Type){
	console.log(Type);
	if (type == null){
		if (Type == "="){
			var currentValue = document.getElementById('display').innerHTML;
			console.log(currentValue +" = "+ currentValue);
		}else {
			type = Type;
			saveValue();
			document.getElementById('display').innerHTML = 0;
		}
	}else if (type != null){
		if (Type == "/" && document.getElementById('display').innerHTML == "0"){
			document.getElementById('display').innerHTML = "delen door 0 kan niet";
		}else if(Type == "="){
			calculate();
		}else {
			calculate();
			type = Type;
			document.getElementById('display').innerHTML = 0;
		}
		
	}
};

function clear(){
	document.getElementById('display').innerHTML = "0";
	console.log("clear");
	value = null;
	type = null;
	
	
};

function calculate(){	
	var currentValue = document.getElementById('display').innerHTML;
	var antwoord = null;
	if (type != null){
		if (type == "+"){
			 antwoord = value + currentValue; 
		 }else if (type == "-"){
			 antwoord = value - currentValue; 
		 }else if (type == "*"){
			 antwoord = value * currentValue; 
		 }else if (type == "/"){
			 antwoord = value / currentValue; 
		 }
	}
	document.getElementById('display').innerHTML = antwoord;
	console.log(value + " " + type + " " + currentValue + " = " + antwoord);
	saveValue();
	type = null;
};
