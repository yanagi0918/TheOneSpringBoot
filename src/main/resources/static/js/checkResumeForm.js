
function checkResumeForm() {
	let checkResumeForm = true;

	let userID = /^[A-Z]{1}[1-2]{1}\d{8}$/;
	if (!userID.test($("#user_id").val())) {
		Swal.fire('Warning!',
			'身分證格式錯誤!',
			'warning');
		checkResumeForm = false;
		return checkResumeForm;
	}
	
		$('.btn-resumeUpdate').click(function() {
		location.href = `./ResumeServlet?UpdateId=${$(this).val()}`;
	})
	
	$('#btn-toCreate').click(function() {
		location.href = "/resume";
	})
	
	$('#btn-goBack').click(function() {
		location.href = "/resumes";
	})
}