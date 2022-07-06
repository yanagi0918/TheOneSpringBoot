$(function() {
	$('#wrongInput').click(function() {
		$('#userid').val('33A2345678')
		$('#birth').val('2022-08-01')
		$('#email').val('lin.gmail')
	})

	$('#correctInput').click(function() {
	//	$('#userid').val('Z123456789')
		$('#pwd').val('abc123zzz')
		$('#username').val('唐洋基')
		$('#gender').val('男')
		$('#birth').val('2022-05-20')
		$('#tele').val('0287654321')
		$('#phone').val('0910654321')
		$('#address').val('台東縣蘭嶼鄉25號')
		$('#email').val('hiremeplz@gmail.com')
	})

	$('.btn-memberUpdate').click(function() {
		location.href = `./MemberServlet?UpdateId=${$(this).val()}`;
	})

	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/member";
	})

	$('#btn-goBack').click(function() {
		location.href = "/dashboard/members";
	})

//圖片上傳同步顯示
	$("#imgInp").change(function() {
		readURL(this);
	})
	
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview_progressbarTW_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}








//數字的正規表達式 = /^\d+$/;
//欄位驗證
    function checkID() {
        var userid = $('#userid').val();
        var postData = {"userid": userid};
        
        $.ajax({
            type: "post",
            url: "/dashboard/members/checkID",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(postData),
            success: function (data) {
                if (data != null) {
                    $('#idError').text(`帳號不可重複 !`)
                    $('#userid').attr("class", "form-control is-invalid")
                    $('#btn-submit').attr("disabled",true);
                }else {
                    $('#btn-submit').attr("disabled",false);
                }
            }
        })
    }

//if (!IdRegex.test($("#userid").val()))
function validateId() {
		checkID()
		let IdRegex = /^[a-z,A-Z][1-2,8-9]\d{8}$/;
		 if ($("#userid").val() === "") {
            $('#userid').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else if (!IdRegex.test($("#userid").val())) {
            $('#useridError').text("身分證格式不符，請輸入正確身分證字號")
            $('#userid').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#userid').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        } 
			
	}		


		
		
function validateBirth() {		
		let birthday = new Date($("#birth").val());
  		let nowDate = new Date();
   		if ($('#birth').val() == "") {
            $('#birthError').text("請輸入生日!!")
            $('#birth').attr("class", "form-control is-invalid")
            return false;
        } else if (birthday > nowDate) {
            $('#birthError').text("生日不可在未來!!")
            $('#birth').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#birth').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }
function validateTele() {		
	let teleregex = /^\d+$/;
 	 if ($("#tele").val() === "") {
            $('#tele').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else if(!teleregex.test($("#tele").val())) {
            $('#teleError').text("請輸入數字")
            $('#tele').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#tele').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }
    

function validatePhone() {		
	let phoneregex = /^\d+$/;
 	 if ($("#phone").val() === "") {
            $('#phone').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else if(!phoneregex.test($("#phone").val())) {
            $('#phoneError').text("email格式不符，請重新輸入")
            $('#phone').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#phone').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }    
  		
// if(!emailregex.test($("#email").val()))
function validateEmail() {		
	let emailregex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 	 if ($("#email").val() === "") {
            $('#email').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else if(!emailregex.test($("#email").val())) {
            $('#emailError').text("email格式不符，請重新輸入")
            $('#email').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#email').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }

function validatePwd() {
        if ($('#pwd').val() == "") {
            $('#pwd').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#pwd').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }
    
function validateUsername() {
        if ($('#username').val() == "") {
            $('#username').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#username').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }  

function validateGender() {
        if ($('#gender').val() == "") {
            $('#gender').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#gender').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    }  

function validateAddress() {
        if ($('#address').val() == "") {
            $('#address').attr("class", "form-control is-invalid")
            $('#btn-submit').attr("disabled",true);
            return false;
        } else {
            $('#address').attr("class", "form-control is-valid")
            $('#btn-submit').attr("disabled",false);
            return true;
        }
    } 


 //觸發event事件
    $('#userid').keyup(function () {
        validateId();
    })
    $('#birth').change(function () {
        validateBirth();
    })
	$('#tele').keyup(function () {
        validateTele();
    })
	$('#phone').keyup(function () {
        validatePhone();
    })

    $('#email').keyup(function () {
        validateEmail();
    })
    $('#pwd').keyup(function () {
        validatePwd();
    })
    $('#username').keyup(function () {
        validateUsername();
    })
    $('#gender').keyup(function () {
        validateGender();
    })
    $('#address').keyup(function () {
        validateAddress();
    })
    
     $('#pwdcheck').click(function () {
        ShowPwd();
    })


//驗證包
function checkPackage() {
        let checkMemberForm = false;
        checkMemberForm = validateId();
        checkMemberForm = validateBirth();
        checkMemberForm = validateTele();;
        checkMemberForm = validatePhone();;
        checkMemberForm = validateEmail();
        checkMemberForm = validatePwd();
        checkMemberForm = validateUsername();
        checkMemberForm = validateGender();
        checkMemberForm = validateAddress();

        return checkMemberForm;
        
    }



//表單送出檢查
	$('#btn-submit').on('click',function() {
		let checkMemberForm = true;
		
		checkMemberForm = checkPackage();
		});
//		if (checkMemberForm) {
//            Swal.fire({
//                title: '確認送出!?',
//                text: "",
//                icon: 'question',
//                showCancelButton: true,
//                confirmButtonColor: '#3085d6',
//                cancelButtonColor: '#d33',
//                confirmButtonText: '確定',
//                cancelButtonText: '取消'
//            }).then((result) => {
//                if (result.isConfirmed) {
//                    Swal.fire({
//                        icon: 'success',
//                        title: '完成!',
//                        showConfirmButton: false,
//                        timer: 800
//                    })
//                    setTimeout(() => {
//                        $('#form').submit();
//                    }, 800)
//                }
//            })
//        } else {
//            Swal.fire({
//                icon: 'error',
//                title: '表單格式錯誤',
//                html: ''
//            })
//        }
//    });
	
	


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











});