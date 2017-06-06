setInterval(function(){
  textAreaUitlezen();
}, 5000);

console.log("javaScript running")
var textAreaValue = "test";

function textAreaUitlezen(){
	var nameInput = document.getElementById('textArea').value;
	if (nameInput != textAreaValue){
		console.log(nameInput);
		textAreaValue = nameInput;
	}
 
}
