$(function () {
    
    $('#OrderwrongInput').click(function () {
    $('#userId').val('12A345675')
    $('#orderDate').val('2022-07-22')
    $("#state").val('保留');
    $('#productId').val('JLPT N2 日檢養成班')
    $('#totalPrice').val('6666K')
})

$('#OrdercorrectInput').click(function () {
    $('#userId').val('J123456789')
    $('#orderDate').val('2022-07-22')
    $("#state").val('完成'); 
    $('#productId').val('IELTS 雅思檢定班')
    $('#totalPrice').val('86000')
})

    $('.btn-orderUpdate').click(function() {
		location.href = `./OrdersManager?UpdateId=${$(this).val()}`;
	})
	
	$('#btn-toCreate').click(function() {
		location.href = "/dashboard/order";
	})
	
    $('#btn-goBack').click(function() {
		location.href = "/dashboard/orders";
	})
	
	//datatable
	$(document).ready( function () {
    $('#table_id').DataTable({
	columnDefs: [
     {
      "targets":[ 0,1,2,3,4,5,6 ],
      "className": "text-center",
     }
    ],
	"language":{
    "processing": "處理中...",
    "loadingRecords": "載入中...",
    "lengthMenu": "顯示 _MENU_ 項結果",
    "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
    "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
    "infoFiltered": "(從 _MAX_ 項結果中過濾)",
    "search": "搜尋:",
    "paginate": {
        "first": "第一頁",
        "previous": "上一頁",
        "next": "下一頁",
        "last": "最後一頁"
    },
    "aria": {
        "sortAscending": ": 升冪排列",
        "sortDescending": ": 降冪排列"
    },
    "emptyTable": "目前沒有資料",
    "datetime": {
        "previous": "上一頁",
        "next": "下一頁",
        "hours": "時",
        "minutes": "分",
        "seconds": "秒",
        "amPm": [
            "上午",
            "下午"
        ],
        "unknown": "未知",
        "weekdays": [
            "週日",
            "週一",
            "週二",
            "週三",
            "週四",
            "週五",
            "週六"
        ],
        "months": [
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月"
        ]
    },
    "searchBuilder": {
        "add": "新增條件",
        "condition": "條件",
        "deleteTitle": "刪除過濾條件",
        "button": {
            "_": "複合查詢 (%d)",
            "0": "複合查詢"
        },
        "clearAll": "清空",
        "conditions": {
            "array": {
                "contains": "含有",
                "equals": "等於",
                "empty": "空值",
                "not": "不等於",
                "notEmpty": "非空值",
                "without": "不含"
            },
            "date": {
                "after": "大於",
                "before": "小於",
                "between": "在其中",
                "empty": "為空",
                "equals": "等於",
                "not": "不為",
                "notBetween": "不在其中",
                "notEmpty": "不為空"
            },
            "number": {
                "between": "在其中",
                "empty": "為空",
                "equals": "等於",
                "gt": "大於",
                "gte": "大於等於",
                "lt": "小於",
                "lte": "小於等於",
                "not": "不為",
                "notBetween": "不在其中",
                "notEmpty": "不為空"
            },
            "string": {
                "contains": "含有",
                "empty": "為空",
                "endsWith": "字尾為",
                "equals": "等於",
                "not": "不為",
                "notEmpty": "不為空",
                "startsWith": "字首為",
                "notContains": "不含",
                "notStarts": "開頭不是",
                "notEnds": "結尾不是"
            }
        },
        "data": "欄位",
        "leftTitle": "群組條件",
        "logicAnd": "且",
        "logicOr": "或",
        "rightTitle": "取消群組",
        "title": {
            "_": "複合查詢 (%d)",
            "0": "複合查詢"
        },
        "value": "內容"
    },
    "editor": {
        "close": "關閉",
        "create": {
            "button": "新增",
            "title": "新增資料",
            "submit": "送出新增"
        },
        "remove": {
            "button": "刪除",
            "title": "刪除資料",
            "submit": "送出刪除",
            "confirm": {
                "_": "您確定要刪除您所選取的 %d 筆資料嗎？",
                "1": "您確定要刪除您所選取的 1 筆資料嗎？"
            }
        },
        "error": {
            "system": "系統發生錯誤(更多資訊)"
        },
        "edit": {
            "button": "修改",
            "title": "修改資料",
            "submit": "送出修改"
        },
        "multi": {
            "title": "多重值",
            "info": "您所選擇的多筆資料中，此欄位包含了不同的值。若您想要將它們都改為同一個值，可以在此輸入，要不然它們會保留各自原本的值。",
            "restore": "復原",
            "noMulti": "此輸入欄需單獨輸入，不容許多筆資料一起修改"
        }
    },
    "autoFill": {
        "cancel": "取消"
    },
    "buttons": {
        "copySuccess": {
            "_": "複製了 %d 筆資料",
            "1": "複製了 1 筆資料"
        },
        "copyTitle": "已經複製到剪貼簿",
        "excel": "Excel",
        "pdf": "PDF",
        "print": "列印",
        "copy": "複製"
    },
    "searchPanes": {
        "collapse": {
            "_": "搜尋面版 (%d)",
            "0": "搜尋面版"
        },
        "emptyPanes": "沒搜尋面版",
        "loadMessage": "載入搜尋面版中...",
        "clearMessage": "清空"
    },
    "stateRestore": {
        "emptyError": "名稱不能空白。"
    },
    "select": {
        "columns": {
            "_": "選擇了 %d 欄資料"
        },
        "rows": {
            "1": "選擇了 1 筆資料",
            "_": "選擇了 %d 筆資料"
        }
    },
    "zeroRecords": "沒有符合的資料"
	}
	});
});

//Form rule
$(function() {

	$('#form').validate({
		rules: {
			totalPrice: {
				required: true,
				digits: true,
				range: [0, 9999999999],
			},
		},
	})

})


//分頁

function getPageList(totalPages, page, maxLength) {
	function range(start, end) {
		return Array.from(Array(end - start + 1), (_, i) => i + start);
	}

	var sideWidth = maxLength < 9 ? 1 : 2;
	var leftWidth = (maxLength - sideWidth * 2 - 3) >> 1;
	var rightWidth = (maxLength - sideWidth * 2 - 3) >> 1;

	if (totalPages <= maxLength) {
		return range(1, totalPages);
	}

	if (page <= maxLength - sideWidth - 1 - rightWidth) {
		return range(1, maxLength - sideWidth - 1).concat(0, range(totalPages - sideWidth + 1, totalPages));
	}

	if (page >= totalPages - sideWidth - 1 - rightWidth) {
		return range(1, sideWidth).concat(0, range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages));
	}

	return range(1, sideWidth).concat(0, range(page - leftWidth, page + rightWidth), 0, range(totalPages - sideWidth + 1, totalPages));
}

$(function () {
	var numberOfItems = $(".blog_area").length;
	var limitPerPage = 5;
	var totalPages = Math.ceil(numberOfItems / limitPerPage);
	var paginationSize = 5;
	var currentPage;

	function showPage(whichPage) {
		if (whichPage < 1 || whichPage > totalPages) return false;

		currentPage = whichPage;

		$(".blog_area").hide().slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage).show();

		$(".pagination li").slice(1, -1).remove();

		getPageList(totalPages, currentPage, paginationSize).forEach(item => {
			$("<li>").addClass("page-item").addClass(item ? "current-page" : "dots")
				.toggleClass("active", item === currentPage).append($("<a>").addClass("page-link")
					.attr({ href: "javascript:void(0)" }).text(item || "...")).insertBefore(".next-page");
		});

		$(".previous-page").toggleClass("disable", currentPage === 1);
		$(".next-page").toggleClass("disable", currentPage === totalPages);
		return true;
	}

	$(".pagination").append(
		$("<li>").addClass("page-item").addClass("previous-page").append($("<a>").addClass("page-link").attr({ href: "javascript:void(0)" }).text("Prev")),
		$("<li>").addClass("page-item").addClass("next-page").append($("<a>").addClass("page-link").attr({ href: "javascript:void(0)" }).text("Next"))
	);

	$(".blog_area").show();
	showPage(1);

	$(document).on("click", ".pagination li.current-page:not(.active)", function () {
		return showPage(+$(this).text());
	});

	$(".next-page").on("click",function(){
		return showPage(currentPage + 1);
	});
	
	$(".previous-page").on("click",function(){
		return showPage(currentPage - 1);
	});
});


	$('#btn-submit').click(function() {
		let checkOrderForm = true;
		
		//會員
//		let IdRegex = /^[a-z,A-Z]{1}[1-2,8-9]{1}\d{8}$/; 
//    if (!IdRegex.test($("#userId").val())) {
//			Swal.fire(
//  				'格式錯誤',
//  				'請輸入正確身分證字號',
//  				'error'
//			)
//			$('#userId').attr("class","form-control is-invalid")
//        checkOrderForm = false;
//   		 }else{
//			$('#userId').attr("class","form-control is-valid")
//		 }
		
		//日期
    if ($('#orderDate').val() == "") {
			$('#orderDate').attr("class","form-control is-invalid")
			checkOrderForm = false;
		}else{
			$('#orderDate').attr("class","form-control is-valid")
		}
		
		//狀態
	if ($('#state').val() == "") {
			$('#state').attr("class","form-select is-invalid")
			checkOrderForm = false;
		}else{
			$('#state').attr("class","form-select is-valid")
		}
		
		//課程
	if ($('#productId').val() == "") {
			$('#productId').attr("class","form-select is-invalid")
			checkOrderForm = false;
		}else{
			$('#productId').attr("class","form-select is-valid")
		}
		
		//總價
    let priceRegex = /^\d*$/;
    if (!priceRegex.test($("#totalPrice").val())) {
		Swal.fire(
  				'格式錯誤',
  				'總價只能輸入有效數字',
  				'error'
			)
		$('#totalPrice').attr("class","form-control is-invalid")
        checkOrderForm = false;
   		 }else{
			$('#totalPrice').attr("class","form-control is-valid")
		}

		let confirmStr = '確認修改訂單?';
		if ($('#btn-submit').val() == 0) {
			confirmStr = '確認新增訂單?';
		}

		if (checkOrderForm) {
			Swal.fire({
				title: confirmStr,
				text: "",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '確認',
				cancelButtonText: '取消'
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire({
						icon: 'success',
						title: '已完成!',
						showConfirmButton: false,
						timer: 1500
					})
					setTimeout(() => {
						$('#form').submit();
					}, 1500)
				}
			})
		} 
	})

});




