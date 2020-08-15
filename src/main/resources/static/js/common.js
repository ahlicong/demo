function getUsername() {
	var username = window.location.search.split("&")[0];
	if (username == "" || username.length > 30) {
		username = "guest";
	} else {
		username = username.split("=")[1];
	}
	return username;
}
