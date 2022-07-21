//Sweet Alert

$(function() {
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
					location.href = `/CommentDelete?id=${$(this).val()}`;
				}, 1500)
			} else {
				//				location.href = '/comments'
			}

		})

	});

	$('#comment-new').click(function() {
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
					location.href = `/CommentMessageDelete?id=${$(this).val()}`;
				}, 1500)
			} else {
				location.href = '/comments'
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

	$('.listComp').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('.listJob').raty({
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
	$('#OneInput').click(function() {
		//		$('#userId').show()
		//		$('#userId').val('A123456789')
		$('#compName').val('狗來富寵物廣場')
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
	})

	//message
	$('#OneInputMessage').click(function() {
		$('#replyNickName').val('徬徨的畢業生')
		$('#messageContent').val('趕謝大大無私的分享~~')
	});
	
	

});
//anonymous/user show

$(function() {
	$('#confirm-switch').click(function() {
		$('#nickName').toggle();
		if ($('#confirm-switch').prop('checked')) {
			$('#nickName').val('匿名');
		} else {
			$('#nickName').val('');
		}
	});

	$('#messageNickName #confirm-switch').click(function() {
		$('#replyNickName').toggle();
		if ($('#confirm-switch').prop('checked')) {
			$('#replyNickName').val('匿名');
		} else {
			$('#replyNickName').val('');
		}
	});
	
	

})

//function bar controller
$(document).ready(function() {
	var identifier = window.location.pathname;
	switch (identifier) {
		case '/comments':
			$('#overview').removeClass('success-border').addClass('success');
			break;

		case '/comments/search':
			$('#overview').removeClass('success-border').addClass('success');
			break;

		case '/user/comments':
			$('#mycomment').removeClass('success-border').addClass('success');
			break;
		case '/comments/analysis':
			$('#analysis').removeClass('success-border').addClass('success');
			break;
	}

});

//控制修改留言視窗

function editMessage(id) {
	var messageId = '#' + id;
	var editArea = '#messageEditArea' + id;
	var editCancel = '#editCancel' + id;
	var currentMessage = '#currentMessage' + id;

	$(messageId).click(function() {
		$(editArea).removeClass('d-none');
		$(currentMessage).addClass('d-none');
	})
	$(editCancel).click(function() {
		$(editArea).addClass('d-none');
		$(currentMessage).removeClass('d-none');
	})

}

//控制回覆留言視窗

function replyMessage(id) {
	var messageId = '#reply' + id;
	var replyArea = '#replyMessageArea' + id;
	var replyCancel = '#replyCancel' + id;
//	var modifybtn = '#modifybtn' + id;
//	var modifyreply = '#modifyreply' + id;
	
	$(messageId).click(function() {
		$(replyArea).removeClass('d-none');
	})
	$(replyCancel).click(function() {
		$(replyArea).addClass('d-none');
	})
	
	//一鍵輸入
//	$(modifybtn).click(function() {
//		$(modifyreply).val('謝大大無私的分享~~');
//	})

}

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

$(function() {
	var numberOfItems = $(".blog_area .blog_item").length;
	var limitPerPage = 5;
	var totalPages = Math.ceil(numberOfItems / limitPerPage);
	var paginationSize = 5;
	var currentPage;

	function showPage(whichPage) {
		if (whichPage < 1 || whichPage > totalPages) return false;

		currentPage = whichPage;

		$(".blog_area .blog_item").hide().slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage).show();

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

	$(document).on("click", ".pagination li.current-page:not(.active)", function() {
		return showPage(+$(this).text());
	});

	$(".next-page").on("click", function() {
		return showPage(currentPage + 1);
	});

	$(".previous-page").on("click", function() {
		return showPage(currentPage - 1);
	});
});

//Text limit

var text_max = 200;
$('#count_message').html(text_max + '/200字');

$('#messageContent').keyup(function() {
	var text_length = $('#messageContent').val().length;
	var text_remaining = text_max - text_length;

	$('#count_message').html(text_remaining + '/200字');
});

/* 浮動提示 */
$(function() {
	$('[data-toggle="tooltip"]').tooltip();
});


////////////////////////////////////////////////////

//
//$(function() {
//	var data = eval('('+'${listComment}'+')');
//    console.log(data);
//
//});
//













