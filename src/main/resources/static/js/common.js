function getUsername() {
	var username = window.location.search.split("&")[0];
	if (username == "" || username.length > 50) {
		username = "guest";
	} else {
		username = username.split("=")[1];
	}
	return decodeURI(username);
}
