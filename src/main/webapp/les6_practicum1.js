

function init() {
	var txt = document.getElementById("textArea");
	txt.onkeyup = function(){
	    localStorage.setItem('txt', txt.value);
	    console.log(txt.value);
	};
}
window.onload = init;