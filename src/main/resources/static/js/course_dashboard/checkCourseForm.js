$(function () {
    //DataTable
    $(document).ready(function () {
        $('#table_id').DataTable(
            {
                columnDefs: [
                    {orderable: false, targets: 7}
                ]
            }
        )
    });

	//前往新增視圖
    $('#btn-toCreateCourse').click(function () {
        location.href = "/toCreatePage";
    })

	//取消回去courselist
    $('#btn-cancel').on('click', function () {
        location.href = "/courses";
    })

    //$('#delete').on('click',function () {
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

	//score icon test
	document.getElementById("score").addEventListener("blur", function () {
		let scoreRegex = /^[\d.]+$/;
		let scoreValue = document.getElementById("score").value;
		let checkBoolean = scoreRegex.test(scoreValue);
		if (checkBoolean) {
			document.getElementById("score_check").innerHTML = "<img alt='ok!!' width='30' height='30' src='img/right.jpg'/>"
			return true
		} else {
			document.getElementById("score_check").innerHTML = "<img alt='錯誤' width='30' height='30' src='img/wrong.jpg'/>"
			return false
		}
	})

})