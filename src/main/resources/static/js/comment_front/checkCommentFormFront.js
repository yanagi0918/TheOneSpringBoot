//Form rule
$(function () {
	$('#form').validate({
		rules: {
			user: {
				required: true,
			},
			userId: {
				required: true,
			},
			ref_time: {
				required: true,
			},
			comp_name: {
				required: true,
			},
			job_name: {
				required: true,
			},
			job_description: {
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
			job_description: {
				required: '請選擇類別',
			},
			monthly_salary: {
				digits: '請輸入大於0的整數',
			},
		},
	})

})

//Sweet Alert

$(function () {
	$('.comment-delete').click(function () {
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

	$('#comment-new').click(function () {
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

});




//Star rating js
$.raty.path = '/img';

$(function () {

	$('#compScore').raty({
		targetScore: '#comp_score',
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
$(function () {
	$('#OneInput').click(function () {
		$('#userId').show()
		$('#userId').val('A123456789')
		$('#comp_name').val('狗來富寵物廣場')
		$('#ref_time').val('2000-01-01')
		$('#job_name').val('美容師')
		$('#job_description option[value="全職"]').attr('selected', 'selected')
		$('#user').attr('checked', 'checked')
		$('#std_hour').val('10')
		$('#real_hour').val('12')
		$('#over_freq').val('2')
		$('#seniority').val('5')
		$('#total_seniority').val('7')
		$('#seniority').val('5')
		$('#monthly_salary').val('40000')
		$('#yearly_salary').val('55')
		$('#bonus_count').val('2')
		$('#share').val('老闆親切，加班可報')
	})
});
//anonymous/user show

$(function () {
	$('#anonymous').click(function () {
		$('#userId').val('匿名');
		$('#userId').hide();
	})

	$('#user').click(function () {
		$('#userId').val('');
		$('#userId').show();
	});

})

//function bar controller
$(document).ready(function () {
	var identifier = window.location.pathname;
	switch (identifier) {
		case '/comments':
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


















