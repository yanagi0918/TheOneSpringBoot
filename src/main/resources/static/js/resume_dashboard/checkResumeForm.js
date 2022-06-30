$(function() {
		
		$('#correctInput').click(function() {
		$('#user_id').val('A123456789')
		$('#edu').val('大學')
		$('#school').val('國立東華大學')
		$('#dept').val('國際企業學系')
		$('#work_exp').val('數據分析')
		$('#skills').val('多益825')
	});




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
		
}

	$('.btn-resumeUpdate').click(function() {
		location.href = `./ResumeServlet?UpdateId=${$(this).val()}`;
	})
	
	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/resume";
	})
	
	$('#btn-goBack').click(function() {
		location.href = "/dashboard/resumes";
	})
	
});	
	