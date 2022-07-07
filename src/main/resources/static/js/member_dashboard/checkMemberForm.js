

$(function() {


	$('#wrongInput').click(function() {

		$('#userid').val('33A2345678')
		$('#birth').val('2022-08-01')
		$('#email').val('lin.gmail')
	})

	$('#correctInput').click(function() {
		$('#userid').val('V123456789')
		$('#pwd').val('abc123zzz')
		$('#username').val('唐洋基')
		$('#gender').val('男')
		$('#birth').val('2022-05-20')
		$('#tele').val('0287654321')
		$('#phone').val('0910654321')
		$('#address').val('台東縣蘭嶼鄉25號')
		$('#email').val('hiremeplz@gmail.com')
	})

	$('.btn-memberUpdate').click(function() {
		location.href = `./MemberServlet?UpdateId=${$(this).val()}`;
	})

	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/member";
	})

	$('#btn-goBack').click(function() {
		location.href = "/dashboard/members";
	})

	//圖片上傳同步顯示
	$("#imgInp").change(function() {
		readURL(this);
	})

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview_progressbarTW_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}


	$('#userid').keyup(function() {
		validateId();
		checkID()
	})

	$('#pwd').keyup(function() {
		validatePwd();
	})

	$('#pwdcheck').click(function() {
		ShowPwd();
	})

	$('#username').keyup(function() {
		validateUsername();
	})

	$('#gender').keyup(function() {
		validateGender();
	})

	$('#birth').change(function() {
		validateBirth();
	})


	$('#tele').keyup(function() {
		validateTele();
	})

	$('#phone').keyup(function() {
		validatePhone();
	})

	$('#address').keyup(function() {
		validateAddress();
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
			url: "/dashboard/members/checkID",
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


	function validateGender() {
		if ($('#gender').val() == "") {
			$('#gender').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#gender').attr("class", "form-control is-valid")
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


	function validateTele() {
		let teleregex = /^\d+$/;
		if ($("#tele").val() === "") {
			$('#tele').attr("class", "form-control is-invalid")
			return false;
		} else if (!teleregex.test($("#tele").val())) {
			$('#teleError').text("請輸入數字")
			$('#tele').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#tele').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validatePhone() {
		let phoneregex = /^\d+$/;
		if ($("#phone").val() === "") {
			$('#phone').attr("class", "form-control is-invalid")
			return false;
		} else if (!phoneregex.test($("#phone").val())) {
			$('#phoneError').text("email格式不符，請重新輸入")
			$('#phone').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#phone').attr("class", "form-control is-valid")
			return true;
		}
	}


	function validateAddress() {
		if ($('#address').val() == "") {
			$('#address').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#address').attr("class", "form-control is-valid")
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


	//datatable
	$(document).ready(function() {
		$('#table_id').DataTable();
	});


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
		
		checkMemberForm = checkPackage()&& checkID();
		console.log(checkMemberForm)
		if (checkMemberForm) {
			$('#form').submit();
		}
	});




	//驗證包
	function checkPackage() {
		let checkMemberForm = false;
		let v1 = validateId();
		let v2 = validateBirth()
		let v3 = validateTele()
		let v4 = validatePhone()
		let v5 = validateEmail()
		let v6 = validatePwd()
		let v7 = validateUsername()
		let v8 = validateGender()
		let v9 = validateAddress()
		if (v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8 && v9) {
			checkMemberForm = true;
		}
		return checkMemberForm;
	}


});