
//錯誤輸入
$('#InterviewwrongInput').click(function () {
     $('#userId').val('123456789')
    $('#intTime').val('2022-12-30')
    $('#compName').val('多拉A夢共和國')
    $('#jobName').val('竹蜻蜓修復員')
    $('#offer').val('否')
    $('#test').val('有')
    $('#qA').val('出了一張A4紙上面是個綜合試題國文/英文/數學/邏輯'); 
    $('#share').val('現場 一入廠先去警衛室進行換證 被小夫帶至會議室進行人事資料表填寫考智力測驗，不說我還以為在寫什麼綜合試題 國文/英文/數學/邏輯 邏輯爆幹難 我嚴重懷疑是我智商不足 接著就面主管，看起來好像胖虎,之後就自我介紹完後進行簡報分享' 
 )
})
//正確輸入
$('#InterviewcorrectInput').click(function () {
    $('#userId').val('A123456789')
    $('#intTime').val('2022-05-20')
    $('#compName').val('多拉A夢共和國')
    $('#jobName').val('竹蜻蜓修復員')
    $('#offer').val('2')
    $('#test').val('1')
    $('#qA').val('出了一張A4紙上面是個綜合試題國文/英文/數學/邏輯'); 
    $('#share').val('現場 一入廠先去警衛室進行換證 被小夫帶至會議室進行人事資料表填寫考智力測驗，不說我還以為在寫什麼綜合試題 國文/英文/數學/邏輯 邏輯爆幹難 我嚴重懷疑是我智商不足 接著就面主管，看起來好像胖虎,之後就自我介紹完後進行簡報分享' 
 )
})

		
//Star rating js 星星產生器
$.raty.path = '/img';

$(function() {

	$('#compScore').raty({
		targetScore: '#compScore',
	});

	$('#oneInput').raty({
		score: 5
	});

	$('.listComp').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('#confirmComp').raty({
		readOnly: true
	});
})





	

$(function() {

	$('#userId').keyup(function() {
		validateuserId();
		
	})
	$('#intTime').keyup(function() {
		validateintTime();
		
	})
	

	$('#compName').keyup(function() {
		validatecompName();
	})

	$('#jobName').keyup(function() {
		validatejobName();
	})

	$('#qA').change(function() {
		validateqA();
	})


	$('#share').keyup(function() {
		validateshare();
	})

	


	function validateuserId() {
		if ($('#userId').val() == "") {
			$('#userId').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#userId').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validateintTime() {
		let intTime = new Date($("#intTime").val());
		let nowDate = new Date();
		if ($('#intTime').val() == "") {
			
			$('#intTime').attr("class", "form-control is-invalid")
			return false;
		}  else {
			$('#intTime').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validatecompName() {
		if ($('#compName').val() == "") {
			$('#compNameError').text("請輸入公司名稱")
			$('#compName').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#compName').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validatejobName() {
		if ($('#jobName').val() == "") {
			$('#jobNameError').text("請輸入工作內容")
			$('#jobName').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#jobName').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validateqA() {
		if ($('#qA').val() == "") {
			$('#qA').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#qA').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validateshare() {
		if ($('#share').val() == "") {
			$('#share').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#share').attr("class", "form-control is-valid")
			return true;
		}
	}

	//表單送出檢查
	$('#intv_submit').click(function() {
		let checkintvForm = false;
		checkintvForm = checkPackage();
		
		if (checkintvForm) {
				Swal.fire({
			title: '提示',
			text: '確定要新增?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '確定',
			cancelButtonText: '取消'
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire({
					icon: 'success',
					title: '已新增!',
					showConfirmButton: false,
					timer: 1500
				})
				setTimeout(() => {
					$('#idform').submit();
				}, 1500)
			} else {
			}

		})
		}
	});




	//驗證包
	function checkPackage() {
		let checkintvForm = false;
		let v1 = validateuserId();
		let v2 = validateintTime()
		let v3 = validatecompName()
		let v4 = validatejobName()
		let v5 = validateqA()
		let v6 = validateshare()
		console.log(v2)
		if (v1 && v2 && v3 && v4 && v5 && v6 ) {
			checkintvForm = true;
		}
		return checkintvForm;
	}


});
	
	
//	$('#intv_submit').click(function() {
//		Swal.fire({
//			title: '提示',
//			text: '確定要新增?',
//			icon: 'question',
//			showCancelButton: true,
//			confirmButtonColor: '#3085d6',
//			cancelButtonColor: '#d33',
//			confirmButtonText: '確定',
//			cancelButtonText: '取消'
//		}).then((result) => {
//			if (result.isConfirmed) {
//				Swal.fire({
//					icon: 'success',
//					title: '已新增!',
//					showConfirmButton: false,
//					timer: 1500
//				})
//				setTimeout(() => {
//					$('#form').submit();
//				}, 1500)
//			} else {
//			}
//
//		})
//
//	});
	


	

	



