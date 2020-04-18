var reservemodal = document.getElementById("reservemodal");
var cancelmodal = document.getElementById("cancelmodal");
var span = document.getElementById("close");
var reservedmodal = document.getElementById("reservedmodal");
var cancelledmodal = document.getElementById("cancelledmodal");
var addbookmodal = document.getElementById("addbookmodal");
var addedmodal = document.getElementById("addedmodal");

function showModal(modal){
	modal.style.display = "block";
}

function showReservedModal(){
	closeModal(reservemodal);
	reservedmodal.style.display = "block";
}
function showCancelledModal(){
	closeModal(cancelmodal);
	cancelledmodal.style.display = "block";
}
function showAddedModal(){
	closeModal(addbookmodal);
	addedmodal.style.display = "block";
}
function closeModal(modal){
	modal.style.display = "none";
}

function drop(x){
	console.log(x);
}