function check() {
	var result = confirm('Are you sure ?');
	if (result == false) {
		event.preventDefault();
	}	
}
