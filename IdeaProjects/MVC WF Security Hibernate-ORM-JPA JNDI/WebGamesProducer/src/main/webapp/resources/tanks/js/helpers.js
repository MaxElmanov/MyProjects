//global variables
var canvas, ctx, width, height, box;
var  enemies = [], bullets = [],  walls = [];
var imgBoom, imgTanksPlayer = [], imgTank, imgGround, imgWall, imgTankEnemy = [], imgEnemy, massImgBreak = [], massImgEagle = [];
var audFon, audRip, audShootPlayer, audShootEnemy, audBrokenWall, audGameOver, audTick, switchFontMusic, audioImg;

//functions are helpres
function init() {
	canvas = document.getElementById("canvas");
	ctx = canvas.getContext('2d');
 	width = canvas.width;
 	height = canvas.height;

 	switchFontMusic = false;

 	audFon = new Audio("/resources/tanks/audio/fon.mp3");
 	audFon.loop = true;
 	audFon.volume = 1;
 	audRip = new Audio("/resources/tanks/audio/RIP.mp3");
 	audRip.volume = 0.1;
 	audShootPlayer = new Audio("/resources/tanks/audio/shootPlayer.wav");
 	audShootPlayer.volume = 0.2;
 	audShootEnemy = new Audio("/resources/tanks/audio/shootEnemy.wav");
 	audShootEnemy.volume = 0.1;
 	audBrokenWall = new Audio("/resources/tanks/audio/brokenWall.wav");
 	audGameOver = new Audio("/resources/tanks/audio/gameover.mp3");
 	audTick = new Audio("/resources/tanks/audio/tick.wav");
 	audTick.volume = 0.2;

 	imgTanksPlayer = ["/resources/tanks/img/player/up.png", "/resources/tanks/img/player/down.png", "/resources/tanks/img/player/left.png", "/resources/tanks/img/player/right.png"];
 	imgTankEnemy = ["/resources/tanks/mg/enemy/upE.png", "/resources/tanks/img/enemy/downE.png", "/resources/tanks/img/enemy/leftE.png", "/resources/tanks/img/enemy/rightE.png"];
 	massImgBreak = ["/resources/tanks/img/enemy/breakV.png", "/resources/tanks/img/enemy/breakH.png"];
 	massImgEagle = ["/resources/tanks/img/eagle/0.jpg", "/resources/tanks/img/eagle/30.jpg", "/resources/tanks/img/eagle/60.jpg", "/resources/tanks/img/eagle/100.jpg"];

 	audioImg = document.getElementById('audio');
 	audioImg.src = "/resources/tanks/img/fonOn.png";

 	imgGround = new Image();
 	imgGround.src = "/resources/tanks/img/ground.png";

 	imgTank = new Image();
 	imgTank.src = "/resources/tanks/img/player/up.png";

 	imgWall = new Image();
 	imgWall.src = "/resources/tanks/img/wall.png";

 	imgBoom = new Image();
  imgBoom.src = "/resources/tanks/img/boom-3.png";

  imgEnemy = new Image();
  imgEnemy.src = "/resources/tanks/img/enemy/mainEnemy.png";

 	box = 32;//size of one cell on map(ground)

 	initializeObjectsWalls(walls);

 	for (var i = 0; i < 5/*amount of enemies*/; i++) {
		enemies.push(new Enemy());
		enemies[enemies.length - 1].checkNormalPos();
 	}
}

var clear = function() {
	ctx.clearRect(0, 0, width, height);
};

var drawCircle = function(x, y, r, c) { ///changed
	ctx.fillStyle = c;
	ctx.beginPath();
	ctx.arc(x, y, r, 0, Math.PI*2, false);
	ctx.fill();
	ctx.closePath();
};

function drawRect(x, y, v, c) {
	ctx.fillStyle = c;
	ctx.fillRect(x, y, v, v);
}

function drawGraund() {
	ctx.drawImage(imgGround, 0, 0);
}

function GameOver() {
	if(player.hp < 1 || eagle.hp < 1) {
		gameLoop = null;
		audFon.audioStop();
		audGameOver.play();
		gameOverText();
	}
}

function gameOverText() {
	ctx.beginPath();
	ctx.font = "80px Calibri";
	ctx.fillStyle = "#5F98F6";
	ctx.strokeStyle = "#C1FFFA";
	ctx.fillText("Game over", width/4, height/2);
	ctx.strokeText("Game over", width/4, height/2);
	ctx.closePath();
	ctx.font = "20px Calibri";
	ctx.fillStyle = "#C1FFFA";
	//ctx.strokeStyle = "#C1FFFA";
	ctx.fillText("Press 'Enter' to try again", width/3, height - box);
	//ctx.strokeText("Press 'Enter' to try again", width/4, height/0.5);
}



function scoreText() {
	var x = width - 45;
	ctx.font = "45px AR DESTINE";
	ctx.fillStyle = "white";
	ctx.strokeStyle = "black";

	if(player.score > 9) {
		x = width - 55;
	}

	if(player.score > 99) {
		player.score = 99;
	}
	ctx.fillText(player.score, x, height - 20);
	ctx.strokeText(player.score, x, height - 20);
}

function HpText() {
	ctx.font = "45px AR DESTINE";
	ctx.fillStyle = "white";
	ctx.strokeStyle = "black";
	ctx.fillText(player.hp, width- 45, height - 570);
	ctx.strokeText(player.hp, width- 45, height - 570);
}

HTMLAudioElement.prototype.audioStop = function() {
	this.pause();
	this.currentTime = 0.0;
}	

function checkLocation (target) 
{
	if (typeof gameLoop == "function") {
		if (target == "audio") {
			if (switchFontMusic) {
				audioImg.src = "/resources/tanks/img/fonOn.png";
				audFon.play();
				switchFontMusic = false;
			}
			else {
				audioImg.src = "/resources/tanks/img/fonOff.png";
				audFon.audioStop();
				switchFontMusic = true;
			}
		}
	}
}

window.onclick = function (e) {
	checkLocation(e.target.getAttribute('id'));
}

