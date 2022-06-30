$(function() {
	$('#wrongInput').click(function() {
		$('#userid').val('33A2345678')
		$('#point').val('三百點')
	})

	$('#correctInput').click(function() {
		$('#userid').val('Z123456789')
		$('#pwd').val('abc123zzz')
		$('#username').val('唐洋基')
		$('#gender').val('男')
		$('#birth').val('2022-05-20')
		$('#tele').val('02-87654321')
		$('#phone').val('0910654321')
		$('#address').val('台東縣蘭嶼鄉25號')
		$('#email').val('hiremeplz@gmail.com')
		$('#point').val('300')
	})

	$('.btn-memberUpdate').click(function() {
		location.href = `./MemberServlet?UpdateId=${$(this).val()}`;
	})

$("#imgInp").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview_progressbarTW_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}





	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/member";
	})

	$('#btn-goBack').click(function() {
		location.href = "/dashboard/members";
	})

	$('#btn-submit').click(function() {
		let checkMemberForm = true;

		let IdRegex = /^[a-z,A-Z][1-2,8-9]\d{8}$/;
		if (!IdRegex.test($("#userid").val())) {
			console.log($("#userid").val())
			alert("請輸入正確身分證字號");
			checkMemberForm = false;
		}

		let pointRegex = /^\d+$/;
		if (!pointRegex.test($("#point").val())) {
			alert("會員點數只能輸入阿拉伯整數");
			checkMemberForm = false;
		}
		
		return checkMemberForm;
	})

});