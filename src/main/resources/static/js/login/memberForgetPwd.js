//$(function() {
//
//	$('#userid').keyup(function() {
//		validateId();
//		checkID()
//	})
//
//	$('#email').keyup(function() {
//		validateEmail();
//	})
//
//
//	function validateId() {
//
//		let IdRegex = /^[a-z,A-Z][1-2,8-9]\d{8}$/;
//		if ($("#userid").val() === "") {
//			$('#userid').attr("class", "form-control is-invalid")
//			return false;
//		} else if (!IdRegex.test($("#userid").val())) {
//			$('#idError').text("身分證格式不符，請輸入正確身分證字號")
//			$('#userid').attr("class", "form-control is-invalid")
//			return false;
//		} else {
//			$('#userid').attr("class", "form-control is-valid")
//			return true;
//		}
//
//	}
//
//	function checkID() {
//		var userid = $('#userid').val();
//		var postData = { "userid": userid };
//		let result = false;
//		console.log('這邊有沒有?')
//		
//		$.ajax({
//			type: "post",
//			url: "/forgetpwd123",
//			dataType: "json",
//			contentType: "application/json;charset=utf-8",
//			data: JSON.stringify(postData),
//			async: true,
//			success: function(data) {
//				console.log(data);
//				if (data) {
//					console.log("invaliate")
////					$('#idError').text('帳號不可重複 !')
//					$('#userid').attr("class", "form-control is-invalid")
//					$('#email').attr("class", "form-control is-invalid")
////					result = false;
//				} else {
//					console.log("valiate")
//					$('#userid').attr("class", "form-control is-valid")
//					$('#email').attr("class", "form-control is-valid")
////					result = true;
//				}
//				
//				
////			console.log('有進來function')
////				if (!data) {
////					console.log(data)
//////					$('#idError').text('帳號不可重複 !')
////					$('#userid').attr("class", "form-control is-invalid")
////					$('#email').attr("class", "form-control is-invalid")
////					result = false;
////				} else {
////					$('#userid').attr("class", "form-control is-valid")
////					$('#email').attr("class", "form-control is-valid")
////					result = true;
////				}
//			}
//		})
//		
//		console.log('checkID'+result)
//		return result;
//	}
//
//	
//
//
//	function validateEmail() {
//		let emailregex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
//		if ($("#email").val() === "") {
//			$('#email').attr("class", "form-control is-invalid")
//			return false;
//		} else if (!emailregex.test($("#email").val())) {
//			$('#emailError').text("email格式不符，請重新輸入")
//			$('#email').attr("class", "form-control is-invalid")
//			return false;
//		} else {
//			$('#email').attr("class", "form-control is-valid")
//			return true;
//		}
//	}
//
//
//
//
//
//	//表單送出檢查
//	$('#btn-submit').on('click', function() {
//
//		let checkMemberForm = false;
//		
//		checkMemberForm = checkPackage() && checkID();
////		console.log(checkPackage())
////		console.log(checkID())
////		console.log(checkMemberForm)
//		if (checkMemberForm) {
//			$('#form').submit();
//		}
//	});
//
//
//
//
//	//驗證包
//	function checkPackage() {
//		let checkMemberForm = false;
//		let v1 = validateId();
//		let v2 = validateEmail()
//
//		if (v1 && v2) {
//			checkMemberForm = true;
//		}
//		return checkMemberForm;
//	}
//
//
//});