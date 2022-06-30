  $(function() {
    $('.register-submit').on('click', function() {
      $.ajax({
        data : {
          account: $('#compid').val()
        },
        success: function(res) {
          if(res=='1') {
            alert('申請帳號重複，請輸入其他帳號');
          }
        }
      });
    });
  });


function checkCompanyForm() {
	let checkResult = true;
	let checkJobForm = true;
	
	let compIdRegex = /^\d{8}$/;
	if (!compIdRegex.test($("#compid").val())) {
		Swal.fire('Warning!',
			'統編為8個數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	let pattern = /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/;
	if (!pattern.test($("#website").val())) {
		Swal.fire('Warning!',
			'網站格式錯誤',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	let pwdRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$/;
	if (!pwdRegex.test($("#compwd").val())) {
	Swal.fire('Warning!',
			'密碼格式錯誤!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	let comptele = /^[0-9]{10}$/g;
	if(!comptele.test($("#comptele").val())){
		Swal.fire('Warning!',
			'電話格式錯誤!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}	
	
	let EmpNumRegex = /^\d+$/;
	if (!EmpNumRegex.test($("#empnumber").val())) {
		Swal.fire('Warning!',
			'員工人數只能輸入阿拉伯整數!',
			'warning');
		checkResult = false;
		return checkResult;
	}

	return checkResult;
	

}


$('#wrongCompany').click(function() {
	$('#compid').val('A7654321')
	$('#compwd').val('abc')
	$('#corpname').val('麥噹勞')
	$('#owner').val('麥先生')
	$('#industry').val('服務業')
	$('#contact').val('麥小姐')
	$('#comptele').val('0939-39-3939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300人')
	$('#website').val('http//123.456')
	$('#capital').val('1200萬')

})

$('#correctCompany').click(function() {
	$('#compid').val('87654321')
	$('#compwd').val('Aabc123zzz')
	$('#corpname').val('啃得機')
	$('#owner').val('啃先生')
	$('#industry').val('服務業')
	$('#contact').val('啃小姐')
	$('#comptele').val('0939393939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300')
	$('#website').val('http://www.giigle.com')
	$('#capital').val('1200萬')
})
$('#companyUpdate').click(function() {
	$('#compwd').val('Aabc123zzz')
	$('#corpname').val('XX株式會社')
	$('#owner').val('陳先生')
	$('#industry').val('服務業')
	$('#contact').val('林小姐')
	$('#comptele').val('0977101565')
	$('#fax').val('07-1325462')
	$('#compaddress').val('台北市大安區26號')
	$('#empnumber').val('199')
	$('#website').val('https://www.amaazzon.com')
	$('#capital').val('1300萬')
})
$(document).ready( function () {
    $('#lee').DataTable();
} );


//密碼顯示或是隱藏

	 function ShowPwd() {
            var x = document.getElementById("compwd");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }

	}
	
