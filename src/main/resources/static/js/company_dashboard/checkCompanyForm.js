$(function(){
	$('#companycreateform').validate({
		rules:{
			compid: {
				required: true,
				checkcompid:true,
			},
			compwd: {
				required: true,
				checkcompwd:true,
			},
			ckeckpwd: {
				required: true,
				equalTo:compwd,
			},
			corpname: {
				required: true,
			},
			contact: {
				required: true,
			},
			compaddress: {
				required: true,
			},
			empnumber: {
				required: true,
				digits:true,
			},
			capital: {
				required: true,
			},
			website: {
				email:true,
			},
			comptele: {
				digits:true,
				required: true,
			},
			
			
			
		},
	})
$.validator.addMethod("checkcompwd",function(value,element){ 
var checkcompwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/; 
return this.optional(element)||(checkcompwd.test(value)); 
},"*請輸入正確的密碼格式！");	

$.validator.addMethod("checkcompid",function(value,element){ 
var checkcompid = /^\d{8}$/;
return this.optional(element)||(checkcompid.test(value)); 
},"*統編為8個數字！");	
	
	
})


function returncheck(){
	let returncheck = false;
	 returncheck=checkcompid();
	 return returncheck;
}
function checkcompid() {
	var compid = document.getElementById("compid").value;
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
				document.getElementById("show_compid").innerHTML = "<font color='red'>帳號重複請更換</font>";
				document.getElementById('submit').disabled = true;
				return false;
			}
		}
	});
}


$('#wrongCompany').click(function() {
	$('#compid').val('A7654321')
	$('#compwd').val('abc123')
	$('#pwdagain').val('abc123')
	$('#corpname').val('廣憶有限公司')
	$('#owner').val('何先生')
	$('#contact').val('王小姐')
	$('#comptele').val('0939-39-3939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300人')
	$('#website').val('ispan100gmail.com')
	$('#capital').val('1200萬')

})

$('#correctCompany').click(function() {
	$('#compwd').val('Aabc123zzz')
	$('#pwdagain').val('Aabc123zzz')
	$('#corpname').val('凜東企業有限公司')
	$('#owner').val('吳先生')
	$('#contact').val('黃小姐')
	$('#comptele').val('0939393939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300')
	$('#website').val('iSpan4505@gmail.com')
	$('#capital').val('1200萬')
})
$('#companyUpdate').click(function() {
	$('#compwd').val('Az145145')
	$('#corpname').val('友線株式會社')
	$('#owner').val('陳先生')
	$('#contact').val('林小姐')
	$('#comptele').val('0977101565')
	$('#fax').val('07-1325462')
	$('#compaddress').val('台北市大安區26號')
	$('#empnumber').val('199')
	$('#website').val('iSpan4505@gmail.com')
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
