function changeoption1(){
	var advsearchbar = document.getElementById("advsearchbar");
	var searchtype = document.getElementById("searchtype");
	advsearchbar.placeholder = "Enter Genre";
	searchtype.value = "genre"
}
function changeoption2(){
	advsearchbar.placeholder = "Enter Author's Name";
	searchtype.value = "author"
}
function changeoption3(){
	advsearchbar.placeholder = "Enter Keyword";
	searchtype.value = "keyword"
}
