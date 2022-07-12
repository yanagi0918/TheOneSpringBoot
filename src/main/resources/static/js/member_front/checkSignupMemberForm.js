

$(function() {

	$('#correctInput').click(function() {
		$('#userid').val('A123456789')
		$('#pwd').val('12345')
		$('#username').val('唐洋基')
		$('#birth').val('2022-05-20')
		$('#email').val('hiremeplz@gmail.com')
	})



	$('#userid').keyup(function() {
		validateId();
		checkID()
	})

	$('#pwd').keyup(function() {
		validatePwd();
	})

//	$('#pwdcheck').click(function() {
//		ShowPwd();
//	})

	$('#username').keyup(function() {
		validateUsername();
	})


	$('#birth').change(function() {
		validateBirth();
	})


	$('#email').keyup(function() {
		validateEmail();
	})


	function validateId() {

		let IdRegex = /^[a-z,A-Z][1-2,8-9]\d{8}$/;
		if ($("#userid").val() === "") {
			$('#userid').attr("class", "form-control is-invalid")
			return false;
		} else if (!IdRegex.test($("#userid").val())) {
			$('#idError').text("身分證格式不符，請輸入正確身分證字號")
			$('#userid').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#userid').attr("class", "form-control is-valid")
			return true;
		}

	}

	function checkID() {
		var userid = $('#userid').val();
		var postData = { "userid": userid };
		let result = false;

		$.ajax({
			type: "post",
			url: "/user/members/checkID",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(postData),
			async: false,
			success: function(data) {
				if (data != null) {
					console.log(data)
					$('#idError').text('帳號不可重複 !')
					$('#userid').attr("class", "form-control is-invalid")
					result = false;
				} else {
					result = true;
				}
			}
		})
		console.log('checkID'+result)
		return result;
	}

	function validatePwd() {
		if ($('#pwd').val() == "") {
			$('#pwd').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#pwd').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateUsername() {
		if ($('#username').val() == "") {
			$('#username').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#username').attr("class", "form-control is-valid")
			return true;
		}
	}



	function validateBirth() {
		let birthday = new Date($("#birth").val());
		let nowDate = new Date();
		if ($('#birth').val() == "") {
			$('#birthError').text("請輸入生日!!")
			$('#birth').attr("class", "form-control is-invalid")
			return false;
		} else if (birthday > nowDate) {
			$('#birthError').text("生日不可在未來!!")
			$('#birth').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#birth').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateEmail() {
		let emailregex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if ($("#email").val() === "") {
			$('#email').attr("class", "form-control is-invalid")
			return false;
		} else if (!emailregex.test($("#email").val())) {
			$('#emailError').text("email格式不符，請重新輸入")
			$('#email').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#email').attr("class", "form-control is-valid")
			return true;
		}
	}



	//密碼顯示或是隱藏

	function ShowPwd() {
		var x = document.getElementById("pwd");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}

	}


	//表單送出檢查
	$('#btn-submit').on('click', function() {

		let checkMemberForm = false;
		
		checkMemberForm = checkPackage();
//		console.log(checkPackage())
//		console.log(checkID())
//		console.log(checkMemberForm)
		if (checkMemberForm) {
			$('#form').submit();
		}
	});




	//驗證包
	function checkPackage() {
		let checkMemberForm = false;
		let v1 = validateId();
		let v2 = validateBirth()
		let v3 = validateEmail()
		let v4 = validatePwd()
		let v5 = validateUsername()
		if (v1 && v2 && v3 && v4 && v5) {
			checkMemberForm = true;
		}
		return checkMemberForm;
	}


});