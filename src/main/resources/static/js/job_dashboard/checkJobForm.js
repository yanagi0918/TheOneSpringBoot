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

$('#jobInput').click(function() {
	$('#comp_id').val('87654321')
	$('#title').val('Java Engineer')
	$('#qualification').val('相關領域工作2年經驗')
	$('#required_number').val('5')
})

$('#jobUpdate').click(function() {
	$('#title').val('Java Engineer')
	$('#qualification').val('曾經製作過相關專題')
	$('#required_number').val('1')
})

$(document).ready( function () {
    $('#lee').DataTable();
} );