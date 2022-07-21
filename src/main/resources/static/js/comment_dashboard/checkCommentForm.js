//Sweet Alert

$(function() {
	//評論刪除
	$('.comment-delete').click(function() {
		Swal.fire({
			title: '確定刪除嗎？',
			text: '資料將永久刪除！',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '刪除',
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
					location.href = `/dashboard/CommentDelete?id=${$(this).val()}`;
				}, 1500)
			} else {
				location.href = './comments'
			}

		})

	});

	//評論新增
	$('.comment-new').click(function() {
		Swal.fire({
			title: '提示',
			text: '確定要新增?',
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
					showConfirmButton: false,
					timer: 1500
				})
				setTimeout(() => {
					$('#form').submit();
				}, 1500)
			} else {
			}

		})

	});

	//留言刪除
	$('.commentMessage-delete').click(function() {
		Swal.fire({
			title: '確定刪除嗎？',
			text: '資料將永久刪除！',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '刪除',
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
					location.href = `/dashboard/CommentMessageDelete?id=${$(this).val()}`;
				}, 1500)
			} else {
				location.href = './comments'
			}

		})

	});

});


//Form rule
$(function() {

	$('#form').validate({
		rules: {
			user: {
				required: true,
			},
			userId: {
				required: true,
			},
			refTime: {
				required: true,
			},
			compName: {
				required: true,
			},
			jobName: {
				required: true,
			},
			jobDescription: {
				required: true,
			},
			std_hour: {
				required: true,
				number: true,
				range: [0, 24],
			},
			real_hour: {
				required: true,
				number: true,
				range: [0, 24],
			},
			over_freq: {
				required: true,
				digits: true,
				range: [0, 7],
			},
			seniority: {
				required: true,
				number: true,
				range: [0, 100],
			},
			total_seniority: {
				required: true,
				number: true,
				range: [0, 100],
			},
			monthly_salary: {
				required: true,
				digits: true,
				min: 0,
			},
			yearly_salary: {
				required: true,
				number: true,
				min: 0,
			},
			bonus_count: {
				required: true,
				digits: true,
				min: 0,
			},

		},
		messages: {
			jobDescription: {
				required: '請選擇類別',
			},
			monthly_salary: {
				digits: '請輸入大於0的整數',
			},
		},
	})

})

//Star rating js
$.raty.path = '/img';

$(function() {

	$('#comp_Score').raty({
		targetScore: '#compScore',
	});

	$('#jobScore').raty({
		targetScore: '#job_score',
	});

	$('#oneInput').raty({
		score: 5
	});

	$('td.listComp').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('td.listJob').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('#confirmJob').raty({
		readOnly: true
	});

	$('#confirmComp').raty({
		readOnly: true
	});
})

//One key input js
$(function() {
	//comment
	$('#OneInput').click(function() {
		$('#compName').val('狗來富寵物廣場')
		$('#user').val('A123456789')
		$('#refTime').val('2000-01-01')
		$('#jobName').val('美容師')
		$('#jobDescription option[value="全職"]').attr('selected', 'selected')
		$('#nickName').val('愛德華')
		$('#std_hour').val('10')
		$('#real_hour').val('12')
		$('#over_freq').val('2')
		$('#seniority').val('5')
		$('#total_seniority').val('7')
		$('#seniority').val('5')
		$('#monthly_salary').val('40000')
		$('#yearly_salary').val('55')
		$('#bonus_count').val('2')
		$('#share').val('我們一天的工時幾乎都10小時，休假很少超過6天也沒有年終，當爸爸媽媽把重要的毛孩子交到我手上時，所有的責任就已經落在我身上，所以我們得時時刻刻觀察牠的身體狀況，這時很考驗個人經驗跟敏銳度，幫寶貝洗澡不單只是洗澡那麼表面的意義，很多時候毛孩子來洗澡很容易可以知道狗狗是不是有其它疾病，連主人都不知道呢，因為在過程中我們會從頭到尾摸透透，這也是我們必須要有的觀察力。')
	});

	//message
	$('#OneInputMessage').click(function() {
		$('#replyNickName').val('徬徨的畢業生')
		$('#messageContent').val('感謝大大無私的分享~~')
	});

});
//anonymous/user show

$(function() {
	$('#anonymous').click(function() {
		$('#nickName').toggle();
		if ($('#anonymous').prop('checked')) {
			$('#nickName').val('匿名');
		} else {
			$('#nickName').val('');
		}
	});

})

//message like
$(function() {
	$('#mlike').click(function() {
		$('#messageLike').val('true');
	})

	$('#munlike').click(function() {
		$('#messageLike').val('false');
	});

})



//DashBoard Search
$(function() {
	(function(document) {

		// 建立 LightTableFilter
		var LightTableFilter = (function(Arr) {

			var _input;

			// 資料輸入事件處理函數
			function _onInputEvent(e) {
				_input = e.target;
				var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
				Arr.forEach.call(tables, function(table) {
					Arr.forEach.call(table.tBodies, function(tbody) {
						Arr.forEach.call(tbody.rows, _filter);
					});
				});
			}

			// 資料篩選函數，顯示包含關鍵字的列，其餘隱藏
			function _filter(row) {
				var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
				row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
			}

			return {
				// 初始化函數
				init: function() {
					var inputs = document.getElementsByClassName('searchBar');
					Arr.forEach.call(inputs, function(input) {
						input.oninput = _onInputEvent;
					});
				}
			};
		})(Array.prototype);


		// 網頁載入完成後，啟動 LightTableFilter
		document.addEventListener('readystatechange', function() {
			if (document.readyState === 'complete') {
				LightTableFilter.init();
			}
		});

	})(document);
})

//留言管理
function format(commentMessage) {
	return (
		'<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<thead>' + '<tr>' +
		'<th>留言時間</th>' + '<th>留言編號</th>' + '<th>帳號</th>' + '<th>內容</th>' + '<th>回覆</th>' + '<th>喜歡</th>' + '<th>動作</th>' +
		'</tr>' + '</thead>' +
		'<tbody>' + '<tr>' +
		'<td>' + commentMessage.messageTime + '</td>' +
		'<td>' + commentMessage.messageOrder + '</td>' +
		'<td>' + commentMessage.userId + '</td>' +
		'<td>' + commentMessage.messageContent + '</td>' +
		'<td>' + commentMessage.messageReply + '</td>' +
		'<td>' + commentMessage.messageLike + '</td>' +
		'<td>' + '' + '</td>' +
		'</tr>' + '</tbody>' + '</table>'
	);
}
//Data Table
$(document).ready(function() {


	var table1 = $('#commentTable').DataTable({


		//don't display search bar
		searching: false,

		//x 方向卷軸
		scrollX: true,

		//remove column 3,5,7 sorter
		//hide column
		columnDefs: [{
			orderable: false,
			targets: [5, 7]
		}, {
			visible: false,
			targets: [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19]
		}, {
			targets: 18,
			render: $.fn.dataTable.render.ellipsis(20)

		}
		],

		//position of entire
		dom: '<"bottom"i>rt<"bottom"flp><"clear">',

		//Menu
		lengthMenu: [
			[10, 25, 50, -1],
			[10, 25, 50, 'All'],
		],



	});

	var table2 = $('#commentMessageTable').DataTable({


		//don't display search bar
		searching: false,

		//x 方向卷軸
		scrollX: true,
		
		//hide column
		columnDefs: [{
			visible: false,
			targets: [3, 5, 7, 8, 9]
		}, {
			targets: 6,
			render: $.fn.dataTable.render.ellipsis(15)

		}
		],

		//position of entire
		dom: '<"bottom"i>rt<"bottom"flp><"clear">',

		//Menu
		lengthMenu: [
			[10, 25, 50, -1],
			[10, 25, 50, 'All'],
		],

	});

	//dynamicall column
	//comment
	$('#commentSelect input.toggle-vis').on('change', function(e) {
		e.preventDefault();

		// Get the column API object
		var column = table1.column($(this).attr('data-column'));

		// Toggle the visibility
		if ($(this).is(":checked")) {
			column.visible(true);
		} else {
			column.visible(false);

		}
	});
	//message
	$('#messageSelect input.toggle-vis').on('change', function(e) {
		e.preventDefault();

		// Get the column API object
		var column = table2.column($(this).attr('data-column'));

		// Toggle the visibility
		if ($(this).is(":checked")) {
			column.visible(true);
		} else {
			column.visible(false);

		}
	});
});



//menu tab
$(document).ready(function() {
	//  $('div#txt_cont div:gt(0)').css('display', 'none');
	$('div.txt_tab#graph:visible').hide();
	$('div.txt_tab#message:visible').hide();
	$('#menu ul li a').click(function(event) {
		event.preventDefault();
		var id_tab = $(this).attr('href');
		$('#menu ul li a').removeClass('hover_tab');
		$(this).addClass('hover_tab');
		$('div.txt_tab:visible').hide();
		$(id_tab).show('slide');
	});
});

//欄位選擇
var expanded = false;

function showCheckboxes() {
	var checkboxes = document.getElementById("checkboxes");
	if (!expanded) {
		checkboxes.style.display = "block";
		expanded = true;
	} else {
		checkboxes.style.display = "none";
		expanded = false;
	}
}

function showCheckboxesM() {
	var checkboxes = document.getElementById("checkboxesM");
	if (!expanded) {
		checkboxes.style.display = "block";
		expanded = true;
	} else {
		checkboxes.style.display = "none";
		expanded = false;
	}
}