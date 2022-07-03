$(function() {
	$('#wrongInput').click(function() {
		$('#userid').val('33A2345678')
		$('#email').val('lin.gmail')
		$('#point').val('三百點')
	})

	$('#correctInput').click(function() {
		$('#userid').val('Z123456789')
		$('#pwd').val('abc123zzz')
		$('#username').val('唐洋基')
		$('#gender').val('男')
		$('#birth').val('2022-05-20')
		$('#tele').val('0287654321')
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
			Swal.fire({
				title: '提示!',
				text: "請輸入正確身分證字號",
				icon: 'warning',
			})
			checkMemberForm = false;
		}

		let pointRegex = /^\d+$/;
		if (!pointRegex.test($("#point").val())) {
			Swal.fire({
				title: '提示!',
				text: "\"會員點數\"只能輸入阿拉伯整數",
				icon: 'warning',
			})
			checkMemberForm = false;
			return checkMemberForm;
		}
		
		
		
		
		let birthday = new Date($("#birth").val());
  		let nowDate = new Date();
 		 if (birthday > nowDate) {
   		Swal.fire({
   		 title: '提示!',
   		 text: "\"生日\"不可在未來",
   		 icon: 'warning',
  		 })
   			checkMemberForm = false;
   			return checkMemberForm;
  }
		
		
	let regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 	 if(!regex.test($("#email").val())) {
   	 	Swal.fire({
   		 title: '提示!',
   		 text: "\"請輸入正確email\"",
   		 icon: 'warning',
  		 })
   	 	checkMemberForm = false;
   	 	return checkMemberForm;
		}
	
	})




});

//datatable

$(document).ready( function () {
    $('#table_id').DataTable();
} );


//密碼顯示或是隱藏

	 function ShowPwd() {
            var x = document.getElementById("pwd");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }

	}
	

//只准輸入數字
 function my_key(e) {
          var key;
          if (window.event) {
            key = e.keyCode;
          } else if (e.which) {
            key = e.which;
          } else {
            return true;
          }
          if (8 == key || 46 == key) {//8:backspace 46:delete (倒退鍵和刪除鍵也允許作用)
            return true;
          }
          var keychar = String.fromCharCode(key);
          var reg = /\d/;
          return reg.test(keychar);
        }

//確認帳號是否重複
 function checkUserId(){
	var userid = document.getElementById("userid").value;
	$.ajax({
		type:"POST",
		url:"CheckMember",
		data:"userid=" + userid,
		success:function(data){
			if(data== true){
				document.getElementById("userid").innerHTML = "<font color = 'green'>帳號可用</font>";
				return true;
			}else{
				document.getElementById("userid").innerHTML = "<font color = 'red'>此帳號已存在，請更換帳號</font>";
				return false;			
			}
		}
	});
	
	
	
	
}
	