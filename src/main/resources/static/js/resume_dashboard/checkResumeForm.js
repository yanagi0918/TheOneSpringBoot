//自傳工作經驗欄不可為空
function checknull(id) {
  	var display = document.getElementById("user_id");
    var display = document.getElementById("school");
    var display = document.getElementById("dept");
    var display = document.getElementById("work_exp");
  if(id.value =="") {
	Swal.fire('Warning!',
			'必填欄位不可為空，請輸入!',
			'warning');
  }
  else{display.innerHTML=""}
}




$(function() {
		
		$('#correctInput').click(function() {
		$('#user_id').val('A123456789')
		$('#edu').val('大學')
		$('#school').val('國立東華大學')
		$('#dept').val('國際企業學系')
		$('#work_exp').val('數據分析')
		$('#skills').val('多益825')
	});

	$('.btn-resumeUpdate').click(function() {
		location.href = `./ResumeServlet?UpdateId=${$(this).val()}`;
	})
	
	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/resume";
	})
	
	$('#btn-goBack').click(function() {
		location.href = "/dashboard/resumes";
	})


$('#btn-submit').click(function() {
	let checkResumeForm = true;

	let userID = /^[A-Z]{1}[1-2]{1}\d{8}$/;
	if (!userID.test($("#user_id").val())) {
		Swal.fire('Warning!',
			'身分證格式錯誤!',
			'warning');
		checkResumeForm = false;
		return checkResumeForm;

	}  

	
	
	let work_expRegex = /^[]$/;
		if (work_expRegex.test($("#work_exp").val())) {
			Swal.fire({
				title: '提示!',
				text: "\"自傳/工作經驗\"不得為空",
				icon: 'warning',
			})
			checkMemberForm = false;
			return checkResumeForm;
		}
	
	})
	
});	





//datatable
$(document).ready( function () {
    $('#table_id').DataTable();
} );