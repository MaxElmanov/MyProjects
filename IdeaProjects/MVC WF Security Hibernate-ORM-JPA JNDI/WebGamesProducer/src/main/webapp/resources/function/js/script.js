
var div = document.getElementById("funTest");

    document.getElementById("getFunAnswer").onclick = function(){

    if($("#funTest").has(".h2")){
        $(".h2").remove();
    }

    var x = document.getElementById("x").value;
    var y = document.getElementById("y").value;

    var res = x / (x*x + 2*y*y +1);

    var newNode = document.createElement('h2');
    newNode.setAttribute("class", "h2");
    newNode.innerHTML = res;
    div.appendChild( newNode );
};