
$(function() {

	$('#correctInput').click(function() {
		$('#resumeName').val('for國貿人員')
		//$('#userId').val('N123456789')
		$('#edu').val('大學')
		$('#school').val('國立東華大學')
		$('#dept').val('國際企業學系')
		$('#autobiography').val('我是王曉明，個性熱情活潑...')
		$('#workExp').val('數據分析')
		$('#skills').val('多益825')
	});

	$('#updateInput').click(function() {
		$('#resumeName').val('java工程師')
		//$('#userId').val('N123456789')
		$('#edu').val('研究所')
		$('#school').val('國立台灣大學')
		$('#dept').val('資訊管理學系')
		$('#autobiography').val('我是王曉明，個性熱情活潑...')
		$('#workExp').val('Java開發3年經驗')
		$('#skills').val('Java認證')
	});

	//	$('.btn-resumeUpdate').click(function() {
	//		location.href = `./ResumeServlet?UpdateId=${$(this).val()}`;
	//	})
	//
	//	$('#btn-toCreate').click(function() {
	//		location.href = "/dashboard/resume";
	//	})

	$('#btn-goBack').click(function() {
		location.href = "/user/resumes";
	})



	//event事件
	$('#resumeName').keyup(function() {
		validateResumename();
	})

	$('#edu').keyup(function() {
		validateEdu();
	})

	$('#school').change(function() {
		validateSchool();
	})


	$('#dept').keyup(function() {
		validateDept();
	})

	$('#autobiography').keyup(function() {
		validateAutobiography();
	})

	$('#workExp').keyup(function() {
		validateWorkexp();
	})

	$('#skills').keyup(function() {
		validateSkills();
	})

	function validateResumename() {
		if ($('#resumeName').val() == "") {
			$('#resumeName').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#resumeName').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateEdu() {
		if ($('#edu').val() == "") {
			$('#edu').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#edu').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validateSchool() {
		if ($('#school').val() == "") {
			$('#school').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#school').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateDept() {
		if ($('#dept').val() == "") {
			$('#dept').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#dept').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validateAutobiography() {
		if ($('#autobiography').val() == "") {
			$('#autobiography').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#autobiography').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateWorkexp() {
		if ($('#workExp').val() == "") {
			$('#workExp').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#workExp').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateSkills() {
		$('#skills').attr("class", "form-control is-valid")
		return true;
	}




	$('#btn-submit').click(function() {
		let checkResumeForm = false;
		checkResumeForm = checkPackage();
		if (checkResumeForm) {

			$('#form').submit();
		}
	})

	//驗證包
	function checkPackage() {
		let checkResumeForm = false;
		let v1 = validateResumename();
		let v2 = validateEdu()
		let v3 = validateSchool()
		let v4 = validateDept()
		let v5 = validateAutobiography()
		let v6 = validateWorkexp()
		let v7 = validateSkills()
		if (v1 && v2 && v3 && v4 && v5 && v6 && v7) {
			checkResumeForm = true;
		}
		return checkResumeForm;
	}




});





//datatable
//$(document).ready(function() {
//	$('#table_id').DataTable();
//});