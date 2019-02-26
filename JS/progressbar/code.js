
var x = 5;
var pb = document.getElementsByClassName('inside')[0];	
var border = document.getElementsByClassName('border')[0];
pb.style.width = 0 + "px";

var timerId = setTimeout(func, 500);

function func() {

	if(parseInt(pb.style.width) < 400) {
			pb.style.width = x + "px";
			x += 5;
			timerId = setTimeout(func, 500);
  }
  else{
 			clearTimeout(timerId);
  }

 
}