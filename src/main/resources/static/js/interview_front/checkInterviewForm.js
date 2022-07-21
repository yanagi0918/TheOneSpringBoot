
//錯誤輸入
$('#InterviewwrongInput').click(function () {
    $('#intTime').val('2022-12-30')
   
})
//正確輸入
$('#InterviewcorrectInput').click(function () {
    $('#userId').val('A123456789')
    $('#intTime').val('2022-05-20')
    $('#compName').val('多拉A夢共和國')
    $('#jobName').val('竹蜻蜓修復員')
    $('#offer').val('2')
    $('#test').val('1')
    $('#qA').val('出了一張A4紙上面是個綜合試題國文/英文/數學/邏輯'); 
    $('#share').val('現場 一入廠先去警衛室進行換證 被小夫帶至會議室進行人事資料表填寫考智力測驗，不說我還以為在寫什麼綜合試題 國文/英文/數學/邏輯 邏輯爆幹難 我嚴重懷疑是我智商不足 接著就面主管，看起來好像胖虎,之後就自我介紹完後進行簡報分享' 
 )
})

		
//Star rating js 星星產生器
$.raty.path = '/img';

$(function() {

	$('#compScore').raty({
		targetScore: '#compScore',
	});

	$('#oneInput').raty({
		score: 5
	});

	$('.listComp').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('#confirmComp').raty({
		readOnly: true
	});
})





	

$(function() {

	$('#userId').keyup(function() {
		validateuserId();
		
	})
	$('#intTime').keyup(function() {
		validateintTime();
		
	})
	

	$('#compName').keyup(function() {
		validatecompName();
	})

	$('#jobName').keyup(function() {
		validatejobName();
	})

	$('#qA').change(function() {
		validateqA();
	})


	$('#share').keyup(function() {
		validateshare();
	})

	


	function validateuserId() {
		if ($('#userId').val() == "") {
			$('#userIdError').text("請輸入您的帳號吧")
			$('#userId').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#userId').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validateintTime() {
		let postDate = new Date($("#intTime").val());
        let nowDate = new Date();
		if ($('#intTime').val() == "") {
			$('#intTime').attr("class", "form-control is-invalid")
			return false;
		} else if (postDate > nowDate) {
            $('#intTimeError').text("面試日期不可設定於今日之後!!")
            $('#intTime').attr("class", "form-control is-invalid")
            return false;
        } else {
			$('#intTime').attr("class", "form-control is-valid")
			return true;
		}
	}

	function validatecompName() {
		if ($('#compName').val() == "") {
			$('#compNameError').text("請輸入公司名稱")
			$('#compName').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#compName').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validatejobName() {
		if ($('#jobName').val() == "") {
			$('#jobNameError').text("請輸入工作內容")
			$('#jobName').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#jobName').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validateqA() {
		if ($('#qA').val() == "") {
			$('#qAError').text("寫些東西吧")
			$('#qA').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#qA').attr("class", "form-control is-valid")
			return true;
		}
	}
	function validateshare() {
		if ($('#share').val() == "") {
			$('#shareError').text("分享些資訊吧")
			$('#share').attr("class", "form-control is-invalid")
			return false;
		} else {
			$('#share').attr("class", "form-control is-valid")
			return true;
		}
	}

	//表單送出檢查
	$('#intv_submit').click(function() {
		let checkintvForm = false;
		checkintvForm = checkPackage();
		
		if (checkintvForm) {
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
		}
	});




	//驗證包
	function checkPackage() {
		let checkintvForm = false;
		let v1 = validateuserId();
		let v2 = validateintTime()
		let v3 = validatecompName()
		let v4 = validatejobName()
		let v5 = validateqA()
		let v6 = validateshare()
		console.log(v2)
		if (v1 && v2 && v3 && v4 && v5 && v6 ) {
			checkintvForm = true;
		}
		return checkintvForm;
	}


});

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