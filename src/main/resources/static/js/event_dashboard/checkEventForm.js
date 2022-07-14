$(function() {
	$('#btn-goBack').click(function() {
		location.href = "/dashboard/events";
	})

	$('#eventWrongInput').click(function() {
		$('#compId').val('1234567A')
		$('#eventLinkURL').val('https://www.google.com.tw/')
		$('#postStart').val('2022-08-20')
		$('#postEnd').val('2022-06-15')
	})

	$('#eventCorrectInput').click(function() {
		$('#compId').val('87654321')
		$('#eventLinkURL').val('https://www.google.com.tw/')
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
	$('#compId').keyup(function() {
		let compIdRegex = /^\d{8}$/;
		if (!compIdRegex.test($("#compId").val())) {
			$('#compIdError').text("刊登公司統編為8位數字")
			$('#compId').attr("class","form-control is-invalid")
		}else{
			$.ajax({
                 url: '/dashboard/event/compid_exist/'+$('#compId').val(),
                 type: 'GET',
                 success: function(data) {
					if (data==true) {
						$('#compId').attr("class","form-control is-valid")
					}else{
						$('#compIdError').text("此公司尚未註冊")
						$('#compId').attr("class","form-control is-invalid")
					}
                 }
            });
		}
	})
	/*
	$('#price').keyup(function() {
		let priceRegex = /^\d+$/;
		if (!priceRegex.test($("#price").val())) {
			$('#price').attr("class","form-control is-invalid")
		}else{
			$('#price').attr("class","form-control is-valid")
		}
	})
	*/
	$('#imgInp').change(function() {
		if ($('#imgInp').val() == "" && $('#btn-submit').val() == 0) {
			$('#imgInp').attr("class","form-control is-invalid")
		}else{
			$('#imgInp').attr("class","form-control is-valid")
		}
	})
	
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
		//let warningStr = "";
		
		let compIdRegex = /^\d{8}$/;
		if (!compIdRegex.test($("#compId").val())) {
			$('#compIdError').text("刊登公司統編為8位數字")
			$('#compId').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$.ajax({
                 url: '/dashboard/event/compid_exist/'+$('#compId').val(),
                 type: 'GET',
                 async: false,
                 success: function(data) {
					if (data==true) {
						$('#compId').attr("class","form-control is-valid")
					}else{
						$('#compIdError').text("此公司尚未註冊")
						$('#compId').attr("class","form-control is-invalid")
						checkEventForm = false;
					}
                 }
            });
		}

		/*
		let priceRegex = /^\d+$/;
		if (!priceRegex.test($("#price").val())) {
			$('#price').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#price').attr("class","form-control is-valid")
		}
		*/

		if ($('#imgInp').val() == "" && $('#btn-submit').val() == 0) {
			$('#imgInp').attr("class","form-control is-invalid")
			checkEventForm = false;
		}else{
			$('#imgInp').attr("class","form-control is-valid")
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



		let confirmStr = '確認修改廣告?';
		let text = '';
		let title = '';
		if ($('#btn-submit').val() == 0) {
			confirmStr = '確認新增廣告?';
			text = '';
			title = '已完成!';
		}
		
		if ($('#btn-submit').val() == 1) {
			confirmStr = '確認修改廣告?';
		    text = '';
		    title = '已完成!';
		    if ($('#state').val()==1||$('#state').val()==2){
				text = "審核結果將寄送E-mail通知";
		    	title = "審核結果通知寄送中";
			}
		}
		
		if (checkEventForm) {
			Swal.fire({
				title: confirmStr,
				text: text,
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
						title: title,
						showConfirmButton: false,
						timer: 800
					})
					$.ajax({
                		url: '/dashboard/event/sendemail?id='+$('#eventId').val()+
                											'&result='+$('#state').val()+
                											'&remark='+$('#floatingTextarea').val(),
                		type: 'GET'
            		});
					setTimeout(() => {
						$('#form').submit();
					}, 800)
				}
			})
		} else {
			Swal.fire({
				icon: 'error',
				title: '格式錯誤',
				html: '',
			})
		}
	})

});

