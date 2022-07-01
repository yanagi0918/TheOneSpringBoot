$(function () {

	//前往新增視圖
    $('#btn-toCreateCourse').click(function () {
        location.href = "/dashboard/toCreatePage";
    })

	//取消回去courselist
    $('#btn-cancel').on('click', function () {
        location.href = "/dashboard/courses";
    })

	//function deleteByNo(courseNo) {
	$('#delete').on('click',function () {
		var courseNo = $('#deletevalue').val();
		console.log(courseNo);
		Swal.fire({
			title: '確認是否刪除?',
			text: "刪除後將無法回復!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '永久刪除',
			cancelButtonText: '取消'
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire({
					icon: 'success',
					title: '已刪除!',
					showConfirmButton: false,
					timer: 1500
				})
				setTimeout(() => {
					$.ajax({
						url: '/dashboard/courses/'+courseNo,
						type: 'DELETE',
						success: function(result) {
							window.location.href='/dashboard/courses'
						}
					});
				}, 1500)
			}
		})
	})

//     $('#delete').on('click',function () {
//     $('.btn-courseDelete').on('click', function () {
//         Swal.fire({
//             title: '警告',
//             text: "確定要刪除?!",
//             icon: 'warning',
//             showCancelButton: true,
//             confirmButtonColor: '#3085d6',
//             cancelButtonColor: '#d33',
//             confirmButtonText: '永久刪除',
//             cancelButtonText: '取消'
//         }).then((result) => {
//             if (result.isConfirmed) {
//                 Swal.fire({
//                     icon: 'success',
//                     title: '已刪除!',
//                     timer: 1500
//                 })
//                 setTimeout(() => {
// //					location.href = `./CourseController?courseNo=${$(this).val()}&Delete=del`;
//                     location.href = `./courses/delete/${$(this).val()}`;
// //??				//location.href = './CourseController?courseNo=${$(this).val()}&Delete=del';
//                 }, 1500)
//             }
//         })
//     })

	//submit 確認(create & update)
    $('#btn-submit').on('click', function () {

		let checkResult = true;

		let scoreRegex = /^[\d.]+$/;  //let scoreRegex = /[0-9]+(.[0-9])/;
		if (!scoreRegex.test($("#score").val())) {
			Swal.fire({
				title: '提示!',
				text: "\"評分\"只能輸入有效數字(0.0 ~ 9.9)",
				icon: 'warning',
			})
			checkResult = false;
		}

		let score = $("#score").val();
		if (score >= 10) {
			Swal.fire({
				title: '提示!',
				text: "\"評分\"只能輸入有效數字(0.0 ~ 9.9)，需小於10",
				icon: 'warning',
			})
			checkResult = false;
		}

		let priceRegex = /^\d+$/;
		if (!priceRegex.test($("#price").val())) {
			Swal.fire({
				title: '提示!',
				text: "\"價格\"只能輸入有效數字",
				icon: 'warning',
			})
			checkResult = false;
		}

		let postDate = new Date($("#date").val());
		let nowDate = new Date();
		if (postDate < nowDate) {
			Swal.fire({
				title: '提示!',
				text: "\"上架日期\"不可在今日之前",
				icon: 'warning',
			})
			checkResult = false;
		}

		if (checkResult) {
			Swal.fire({
				title: '提示',
				text: "確定要新增?!",
				icon: 'question',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '確定',
				cancelButtonText: '取消'
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire({
						icon: 'success',
						title: '已新增!',
						timer: 1500
					})
					setTimeout(() => {
						$('#form').submit();
					}, 1500)
				}
			})
		} else {
			Swal.fire({
				icon: 'error',
				title: '格式錯誤',
				html: warningStr
			})
		}
	})

	//圖片上傳同步顯示
	$("#imgInput").change(function () {
		readURL(this);
	})
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function (e) {
				$("#preview_img").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	//一鍵輸入
	$('#correctInput').click(function () {
		$('#courseName').val('面試必勝10招')
		$('#courseCategory').val('求職技巧')
		$('#courseIntroduction').val('畢業季將近，即將踏入社會的準畢業生們開始尋找自己未來的出路，積極查找各種工作資訊，許多公司也紛紛開出職缺，想趁著畢業求職潮廣招人才。面對各種夢幻職缺，你知道企業面試官們最在意哪些地方嗎？')
		$('#lecturer').val('王大陸')
		$('#date').val('2022-08-01')
		$('#coursePic').val('url')
		$('#courseVedio').val('url')
		$('#score').val('9.9')
		$('#price').val('1999')
	})
	$('#wrongInput').click(function () {
		$('#courseName').val('面試必勝100招')
		$('#courseCategory').val('求職技巧')
		$('#courseIntroduction').val('畢業季將近，即將踏入社會的準畢業生們開始尋找自己未來的出路，積極查找各種工作資訊，許多公司也紛紛開出職缺，想趁著畢業求職潮廣招人才。面對各種夢幻職缺，你知道企業面試官們最在意哪些地方嗎？')
		$('#lecturer').val('王大陸')
		$('#date').val('2022-01-01')
		$('#coursePic').val('url')
		$('#courseVedio').val('url')
		$('#score').val('11')
		$('#price').val('1999')
	})

	//score icon test
	// document.getElementById("score").addEventListener("blur", function () {
	// 	let scoreRegex = /^[\d.]+$/;
	// 	let scoreValue = document.getElementById("score").value;
	// 	let checkBoolean = scoreRegex.test(scoreValue);
	// 	if (checkBoolean) {
	// 		document.getElementById("score_check").innerHTML = "<img alt='ok!!' width='30' height='30' src='img/right.jpg'/>"
	// 		return true
	// 	} else {
	// 		document.getElementById("score_check").innerHTML = "<img alt='錯誤' width='30' height='30' src='img/wrong.jpg'/>"
	// 		return false
	// 	}
	// })

	//DataTable
	$(document).ready(function () {
		$('#table_id').DataTable(
			{
				initComplete : function() {
					$("#example_filter").detach().appendTo('#new-search-area');
				},

				columnDefs: [
					{orderable: false, targets: 7},
					{
						"targets":[ 0,1, 2,3,4,5,6 ],
						"className": "text-center",
					},
					{
						"search": "text-center"
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
			}
		)
	});

})