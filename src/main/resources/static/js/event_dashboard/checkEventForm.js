$(function() {
	$('#btn-goBack').click(function() {
		location.href = "/dashboard/events";
	})

	$('#eventWrongInput').click(function() {
		$('#compId').val('1234567A')
		$('#price').val('500W')
		$('#eventLinkURL').val('https://www.google.com.tw/')
		$('#postStart').val('2022-05-20')
		$('#postEnd').val('2022-05-15')
	})

	$('#eventCorrectInput').click(function() {
		$('#compId').val('12345678')
		$('#price').val('500')
		$('#eventLinkURL').val('https://www.google.com.tw/')
		$('#postStart').val('2022-05-15')
		$('#postEnd').val('2022-05-20')
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
	
	
	$('#compId').keyup(function() {
		let compIdRegex = /^\d{8}$/;
		if (!compIdRegex.test($("#compId").val())) {
			$('#compId').attr("class","form-control is-invalid")
		}else{
			$('#compId').attr("class","form-control is-valid")
		}
	})
	
	$('#price').keyup(function() {
		let priceRegex = /^\d+$/;
		if (!priceRegex.test($("#price").val())) {
			$('#price').attr("class","form-control is-invalid")
		}else{
			$('#price').attr("class","form-control is-valid")
		}
	})
	
	
	
	
/*
	$('#btn-submit').click(function() {
		let warningStr = "";
		let checkEventForm = true;

		let compIdRegex = /^\d{8}$/;
		if (!compIdRegex.test($("#compId").val())) {
			warningStr += "刊登公司統編為8位數字" + "<br>";
			$('#compId').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#compId').attr("class","form-control is-valid")
		}

		let priceRegex = /^\d+$/;
		if (!priceRegex.test($("#price").val())) {
			warningStr += "價格只能輸入有效數字" + "<br>";
			$('#price').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#price').attr("class","form-control is-valid")
		}

		if ($('#imgInp').val() == "" && $('#btn-submit').val() == 0) {
			warningStr += "請輸入廣告圖" + "<br>";
			checkEventForm = false;
		}

		if ($('#eventLinkURL').val() == "") {
			warningStr += "請輸入廣告連結" + "<br>";
			checkEventForm = false;
		}

		if ($('#postStart').val() == "" || $('#postEnd').val() == "") {
			warningStr += "請輸入日期" + "<br>";
			checkEventForm = false;
		}

		let postStartDate = new Date($("#postStart").val());
		let postEndDate = new Date($("#postEnd").val());
		if (postStartDate > postEndDate) {
			warningStr += "刊登開始日期不可於刊登結束日期之後" + "<br>";
			checkEventForm = false;
		}

		let confirmStr = '確認修改廣告?';
		if ($('#btn-submit').val() == 0) {
			confirmStr = '確認新增廣告?';
		}

		if (checkEventForm) {
			Swal.fire({
				title: confirmStr,
				text: "",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '確認',
				cancelButtonText: '取消'
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire({
						icon: 'success',
						title: '已完成!',
						showConfirmButton: false,
						timer: 800
					})
					setTimeout(() => {
						$('#form').submit();
					}, 800)
				}
			})
		} else {
			Swal.fire({
				icon: 'error',
				title: '格式錯誤',
				html: warningStr,
			})
		}
	})*/

});

