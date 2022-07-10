function checkcompid() {
	var compId = document.getElementById("compId").value;
	$.ajax({
		type: "POST",
		url: "CheckUserFromCompId",
		data: "compId=" + compId,
		success: function(data) {
			if (data == true) {
				document.getElementById("show_compid").innerHTML = "<font color='red'>此統編不存在，請先註冊</font>";
				document.getElementById('submit').disabled = true;
				return true;
			} else {
				document.getElementById("show_compid").innerHTML = "<font color='red'></font>";
				document.getElementById('submit').disabled = false;
				return false;
			}
		}
	});
}





$(function(){
	$('#jobcreateform').validate({
		rules:{
			compId: {
				required: true,
				maxlength:8,
				minlength:8,
				digits:true,
			},
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
	
	let compIdRegex = /^\d{8}$/;
	if (!compIdRegex.test($("#compId").val())) {
		Swal.fire('Warning!',
			'統編為8個數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	
}

$('#jobInput').click(function() {
	$('#compId').val('87654321')
	$('#title').val('Java Engineer')
	$('#qualification').val('相關領域工作2年經驗')
	$('#required_number').val('5')
	$('#description').val('1.蒐集並釐清專案需求、進行系統分析設計並提供解決方案 \n2.梳理使用者需求內容，進行系統或線上問題分析與討論 \n3.依據BA文件及需求訪談會議紀錄進行系統分析設計，並與系統設計師溝通系統分析規格 \n4.產製SA系統分析文件、使用案例、功能規格設計文件')
})

$('#jobwrongInput').click(function() {
	$('#compId').val('Z1234567')
	$('#title').val('')
	$('#qualification').val('')
	$('#required_number').val('5人')
	$('#description').val('主要負責完成公司承接的軟體程式開發案及後續更新維護等工作。對金融交易有高度興趣者，歡迎加入我們的行列，共同創造高效能、穩定、業界最好的金融交易系統。')
})

$('#jobUpdate').click(function() {
	$('#title').val('JAVA程式設計師')
	$('#qualification').val('曾經製作過相關專題')
	$('#required_number').val('1')
	$('#description').val('1. 負責 APP 設計及程式開發 \n2. 負責 APP 程式精進與版本更新 \n3. 負責 APP 資安項目開發與調校 \n4. 負責行動 APP 解決方案規劃與設計 \n5. 金融科技之行動領域創新研究 \n6. 其他主管交辦事項')
})

$(document).ready( function () {
    $('#lee').DataTable();
} );
$('#lee').DataTable({
    "lengthMenu": [5, 10, 15,20]
});