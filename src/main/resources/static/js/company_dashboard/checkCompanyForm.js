function returncheck(){
	let returncheck = false;
	 returncheck=checkcompid();
	 returncheck=checkcompwd();
	 returncheck=checkcomptele();
	 returncheck=checkwebsite();
	 returncheck=checkempnumber();
	 return returncheck;
}
function checkcompid() {
	var compid = document.getElementById("compid").value;
	let compIdRegex = /^\d{8}$/;
	var compidck = compIdRegex.test(compid);
	if(compidck){
		document.getElementById("s_userName").innerHTML = "<font color='green'></font>";
	$.ajax({
		type: "POST",
		url: "CheckUser",
		data: "compid=" + compid,
		success: function(data) {
			if (data == true) {
				document.getElementById("show_compid").innerHTML = "<font color='green'>帳號可用</font>";
				document.getElementById('submit').disabled = false;
				return true;
			} else {
				document.getElementById("show_compid").innerHTML = "<font color='red'>請更換</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}
	});
}else{
	document.getElementById("s_userName").innerHTML = "<font color='red'>帳號格式錯誤</font>";
	document.getElementById('submit').disabled=true;
	return false;
}
}

function checkcompwd() {
	var compwd = document.getElementById("compwd").value;
	let pwdRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
	var flag5 = pwdRegex.test(compwd);
			if (flag5) {
				document.getElementById("show_compwd").innerHTML = "<font color='green'></font>";
				document.getElementById('submit').disabled = false;
				return true;
			} else {
				document.getElementById("show_compwd").innerHTML = "<font color='red'>格式錯誤</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}

function checkcomptele() {
	var comptele = document.getElementById("comptele").value;
	let ce = /^[0-9]{10}$/g;
	var compteleck = ce.test(comptele);
			if (compteleck) {
				document.getElementById("show_comptele").innerHTML = "<font color='green'></font>";
				document.getElementById('submit').disabled = false;
				return true;
			} else {
				document.getElementById("show_comptele").innerHTML = "<font color='red'>格式錯誤</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}

function checkwebsite() {
	var website = document.getElementById("website").value;
	let pattern = /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/;
	var websiteck = pattern.test(website);
			if (websiteck) {
				document.getElementById("show_website").innerHTML = "<font color='green'></font>";
				document.getElementById('submit').disabled = false;
				return true;
			} else {
				document.getElementById("show_website").innerHTML = "<font color='red'>格式錯誤</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}

function checkempnumber() {
	var empnumber = document.getElementById("empnumber").value;
	let EmpNumRegex = /^\d+$/;
	var empnumberck = EmpNumRegex.test(empnumber);
			if (empnumberck) {
				document.getElementById("show_empnumber").innerHTML = "<font color='green'></font>";
				document.getElementById('submit').disabled = false;
				return true;
			} else {
				document.getElementById("show_empnumber").innerHTML = "<font color='red'>格式錯誤</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}

function checkCompanyForm() {
	let checkResult = true;
	let checkJobForm = true;
	
	if (!pwdRegex.test($("#pwdagain").val())) {
	Swal.fire('Warning!',
			'再次輸入密碼的格式錯誤!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	


	return checkResult;
	

}


$('#wrongCompany').click(function() {
	$('#compid').val('A7654321')
	$('#compwd').val('abc123')
	$('#pwdagain').val('abc123')
	$('#corpname').val('麥噹勞')
	$('#owner').val('麥先生')
	$('#contact').val('麥小姐')
	$('#comptele').val('0939-39-3939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300人')
	$('#website').val('http//123.456')
	$('#capital').val('1200萬')

})

$('#correctCompany').click(function() {
	$('#compwd').val('Aabc123zzz')
	$('#pwdagain').val('Aabc123zzz')
	$('#corpname').val('幸福企業有限公司')
	$('#owner').val('吳先生')
	$('#contact').val('白小姐')
	$('#comptele').val('0939393939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300')
	$('#website').val('http://www.giigle.com')
	$('#capital').val('1200萬')
})
$('#companyUpdate').click(function() {
	$('#compwd').val('Az145145')
	$('#corpname').val('XX株式會社')
	$('#owner').val('陳先生')
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

$('#lee').DataTable({
    "lengthMenu": [5, 10, 15,20]
});


//密碼顯示或是隱藏

	 function ShowPwd() {
            var x = document.getElementById("compwd");
            var pwdagain = document.getElementById("pwdagain");
            
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
            
            if (pwdagain.type === "password") {
                pwdagain.type = "text";
            } else {
                pwdagain.type = "password";
            }

	}
//確認兩次密碼

          function validate() {
              var pwd1 = document.getElementById("compwd").value;
              var pwd2 = document.getElementById("pwdagain").value;
             if(pwd1 == pwd2) {
               document.getElementById("msg").innerHTML="<font color='green'>正確</font>";
               document.getElementById("submit").disabled = false;
              }
              else {
               document.getElementById("msg").innerHTML="<font color='red'>密碼不相同，再輸入一次</font>";
                document.getElementById("submit").disabled = true;
              }
          }
	
