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
	if (!compIdRegex.test($("#comp_id").val())) {
		Swal.fire('Warning!',
			'統編為8個數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	
}

$('#jobInput').click(function() {
	$('#comp_id').val('87654321')
	$('#title').val('Java Engineer')
	$('#qualification').val('相關領域工作2年經驗')
	$('#required_number').val('5')
	$('#description').val('1.蒐集並釐清專案需求、進行系統分析設計並提供解決方案 \n2.梳理使用者需求內容，進行系統或線上問題分析與討論 \n3.依據BA文件及需求訪談會議紀錄進行系統分析設計，並與系統設計師溝通系統分析規格 \n4.產製SA系統分析文件、使用案例、功能規格設計文件')
})

$('#jobUpdate').click(function() {
	$('#title').val('Java Engineer')
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