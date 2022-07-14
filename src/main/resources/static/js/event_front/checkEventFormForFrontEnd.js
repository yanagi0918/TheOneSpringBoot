$(function() {
	$('#btn-goBack').click(function() {
		location.href = "/enterprise/events";
	})

	$('#eventWrongInput').click(function() {
		$('#eventLinkURL').val('http://localhost:8080/job/jobdeatail/1')
		$('#postStart').val('2022-08-20')
		$('#postEnd').val('2022-06-15')
	})

	$('#eventCorrectInput').click(function() {
		$('#eventLinkURL').val('http://localhost:8080/job/jobdeatail/1')
		$('#postStart').val('2022-06-15')
		$('#postEnd').val('2022-08-20')
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
	
	//事件資料檢查
	
	$('#eventLinkURL').keyup(function() {
		if ($('#eventLinkURL').val() == "") {
			$('#eventLinkURL').attr("class","form-control is-invalid")
		}else{
			$('#eventLinkURL').attr("class","form-control is-valid")
		}
	})
	
	$('#postStart').change(function() {
		let postStartDate = new Date($("#postStart").val());
		if ($('#postStart').val() == "") {
			$('#postStartError').text("請輸入刊登開始日期")
			$('#postStart').attr("class","form-control is-invalid")
		/*} else if(postStartDate < new Date()){
			$('#postStartError').text("刊登開始日期不可設於今日之前")
			$('#postStart').attr("class","form-control is-invalid")*/
		} else {
			$('#postStart').attr("class","form-control is-valid")
		}
	})
	
	$('#postEnd').change(function() {
		let postStartDate = new Date($("#postStart").val());
		let postEndDate = new Date($("#postEnd").val());
		if ($('#postEnd').val() == "") {
			$('#postEndError').text("請輸入刊登結束日期")
			$('#postEnd').attr("class","form-control is-invalid")
		}else if (postStartDate > postEndDate) {
			$('#postEndError').text("刊登開始日期不可設於刊登結束日期之後")
			$('#postEnd').attr("class","form-control is-invalid")
		}else{
			$('#postEnd').attr("class","form-control is-valid")
		}
	})
	
	
	
	
	//submit資料確認
	$('#btn-submit').click(function() {
		let checkEventForm = true;
		let warningStr = "";

		if ($('#imgInp').val() == "") {
			warningStr = "請上傳廣告圖"
			checkEventForm = false;
		}
		
		if ($('#eventLinkURL').val() == "") {
			$('#eventLinkURL').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#eventLinkURL').attr("class","form-control is-valid")
		}


		let postStartDate = new Date($("#postStart").val());
		let postEndDate = new Date($("#postEnd").val());
		if ($('#postStart').val() == "") {
			$('#postStartError').text("請輸入刊登開始日期")
			$('#postStart').attr("class","form-control is-invalid")
			checkEventForm = false;
		/*} else if (postStartDate < new Date()) {
			$('#postStartError').text("刊登開始日期不可設於今日之前")
			$('#postStart').attr("class","form-control is-invalid")
			checkEventForm = false;*/
		} else {
			$('#postStart').attr("class","form-control is-valid")
		}
		
		
		if ($('#postEnd').val() == "") {
			$('#postEndError').text("請輸入刊登結束日期")
			$('#postEnd').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else if (postStartDate > postEndDate) {
			$('#postEndError').text("刊登開始日期不可設於刊登結束日期之後")
			$('#postEnd').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#postEnd').attr("class","form-control is-valid")
		}



		let confirmStr = '確認新增廣告?';
		
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
	})

});

