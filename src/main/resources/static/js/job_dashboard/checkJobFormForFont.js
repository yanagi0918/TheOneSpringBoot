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
}

$('#jobInput').click(function() {
	$('#title').val('食品安全專員')
	$('#qualification').val('熟悉食品安全相關法規')
	$('#required_number').val('2')
	$('#description').val('1.國內外食安相關政策、趨勢之資訊蒐集、彙整、解析及因應方案建議提出2.食安示警事項監視、影響評估、彙整分析、疑慮提出與因應方案追蹤管理3.法源依據諮詢及原則建議4.食安相關政策/法規研討會參加5.食安內部教育訓練6.食安預警資訊庫建置與維護')})

$('#jobwrongInput').click(function() {
//	$('#compId').val('Z1234567')
	$('#title').val('')
	$('#qualification').val('')
	$('#required_number').val('5人')
	$('#description').val('1.國內外食安相關政策、趨勢之資訊蒐集、彙整、解析及因應方案建議提出2.食安示警事項監視、影響評估、彙整分析、疑慮提出與因應方案追蹤管理3.法源依據諮詢及原則建議4.食安相關政策/法規研討會參加5.食安內部教育訓練6.食安預警資訊庫建置與維護')})

$('#jobUpdate').click(function() {
	$('#title').val('保健食品/食品專員')
	$('#qualification').val('公共衛生相關、藥學相關、食品營養相關')
	$('#required_number').val('1')
	$('#description').val('1. 研究國內外食品市場資訊收集2. 研究開發保健營養食品配方設計、商品行銷規劃3. 協助保健食品及食品的新品開發與國外代工生產管理(成本估算/樣品試製)4. 熟悉特殊營養食品、健康食品及食品等相關法規 (包含產品標籤/外盒包裝/說明書/原料表/營養標示/商標...等規範) 5. 負責國外營養品進口的查驗登記資料作業/展延申請 6.負責保健食品教育訓練、諮詢及教材製作及對內對外相關產品教育訓練、話術教育訓練')
})

$(document).ready( function () {
    $('#lee').DataTable();
} );
$('#lee').DataTable({
    "lengthMenu": [5, 10, 15,20]
});