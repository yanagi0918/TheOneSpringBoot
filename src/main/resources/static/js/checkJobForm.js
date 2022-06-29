function checkJobForm() {
	let checkJobForm = true;
	let compIdRegex = /^\d{8}$/;
	if (!compIdRegex.test($("#comp_id").val())) {
		Swal.fire('Warning!',
			'統編為8個數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
}
$(document).ready( function () {
    $('#lee').DataTable();
} );