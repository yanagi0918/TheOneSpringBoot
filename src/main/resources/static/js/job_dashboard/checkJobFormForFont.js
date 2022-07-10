//function checkcompid() {
//	var compId = document.getElementById("compId").value;
//	$.ajax({
//		type: "POST",
//		url: "CheckUserFromCompId",
//		data: "compId=" + compId,
//		success: function(data) {
//			if (data == true) {
//				document.getElementById("show_compid").innerHTML = "<font color='red'>此統編不存在，請先註冊</font>";
//				document.getElementById('submit').disabled = true;
//				return true;
//			} else {
//				document.getElementById("show_compid").innerHTML = "<font color='red'></font>";
//				document.getElementById('submit').disabled = false;
//				return false;
//			}
//		}
//	});
//}





$(function(){
	$('#jobcreateform').validate({
		rules:{
			title: {
				required: true,
			},
			qualification: {
				required: true,
			},
			required_number: {
				required: true,
				digits:true,
			},
			description: {
				required: true,
				maxlength:500
			},
			
			
		},
		
		
		
	})
	
})



//撰寫一個限制字數方法
var checkStrLengths = function (str, maxLength) {
    var maxLength = maxLength;
    var result = 0;
    if (str && str.length > maxLength) {
        result = maxLength;
    } else {
        result = str.length;
    }
    return result;
}
//新增監聽輸入
$(".wishContent").on('input propertychange', function () {

    //獲取輸入內容
    var userDesc = $(this).val();

    //判斷字數
    var len;
    if (userDesc) {
        len = checkStrLengths(userDesc, 500);
    } else {
        len = 0
    }

    //顯示字數
    $(".wordsNum").html(len + '/500');
});

function checkJobForm() {
	let checkJobForm = true;
	
	let rnRegex = /^\d+$/;
	if(!rnRegex.test($("#required_number").val())){
		Swal.fire('Warning!',
			'需求人數只能輸入數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	
	}
	
//	let compIdRegex = /^\d{8}$/;
//	if (!compIdRegex.test($("#compId").val())) {
//		Swal.fire('Warning!',
//			'統編為8個數字!',
//			'warning');
//		checkJobForm = false;
//		return checkJobForm;
//	}
	
	
}

$('#jobInput').click(function() {
//	$('#compId').val('87654321')
	$('#title').val('流程規劃管理師')
	$('#qualification').val('商業及管理學科類、土木工程相關系所')
	$('#required_number').val('5')
	$('#description').val('1.協調公司各部門內及部門間之合作，與同仁共同規劃作業流程解決方案。\n2.參與資訊系統導入與產品開發，針對新需求進行分析、系統設計。\n3.與團隊進行系統功能測試計畫/案例編寫與測試執行和驗證。\n4.系統功能驗收、上線後教育訓練以及擔任系統操作諮詢窗口。')
})

$('#jobwrongInput').click(function() {
//	$('#compId').val('Z1234567')
	$('#title').val('')
	$('#qualification').val('')
	$('#required_number').val('5人')
	$('#description').val('主要負責完成公司承接的軟體程式開發案及後續更新維護等工作。對金融交易有高度興趣者，歡迎加入我們的行列，共同創造高效能、穩定、業界最好的金融交易系統。')
})

$('#jobUpdate').click(function() {
	$('#title').val('會計人員')
	$('#qualification').val('曾經製作過相關專題')
	$('#required_number').val('1')
	$('#description').val('1、成本帳務\n2、營業稅申報\n3、營收及一般公開公司上傳資料\n4、應收應付帳務處理\n5、主管交辦事項')
})

$(document).ready( function () {
    $('#lee').DataTable();
} );
$('#lee').DataTable({
    "lengthMenu": [5, 10, 15,20]
});