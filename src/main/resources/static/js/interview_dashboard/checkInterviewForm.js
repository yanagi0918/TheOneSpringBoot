function checkIntvForm() {
	let checkIntvForm = true;

	let userID = /^[a-z,A-Z]{1}[1-2,8-9]{1}\d{8}$/; 
	if (!userID.test($("#userId").val())) {
		Swal.fire('Warning!',
			'身分證格式錯誤!',
			'warning');
		checkIntvForm = false;
		return checkIntvForm;
	}

		let postDate = new Date($('#intTime').val());
		let nowDate = new Date();
		if (postDate > nowDate) {
			Swal.fire('Warning!',
			'刊登開始日期不可於刊登結束日期之後!',
			'warning');
			checkIntvForm = false;
			return checkIntvForm;
		}
}
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

		




$(function() {

	$('.btn-intvDelete').click(function() {
		Swal.fire({
			title: '確認是否刪除?',
			text: "刪除後將無法回復!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '永久刪除',
			cancelButtonText: '取消'
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire({
					icon: 'success',
					title: '已刪除!',
					showConfirmButton: false,
					timer: 1500
				})
				setTimeout(() => {
					location.href = `./InterViewServletDS?DeleteId=${$(this).val()}`;
				}, 1500)
			}
		})
	})
	
	$('.btn-intvUpdate').click(function() {
		location.href = `./InterViewServletDS?UpdateId=${$(this).val()}`;
	})
	
	



});
