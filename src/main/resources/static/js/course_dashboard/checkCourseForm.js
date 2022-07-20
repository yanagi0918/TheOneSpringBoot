$(function () {

    $('#btn-toCreateCourse').click(function () {
        location.href = "/dashboard/toCreatePage";
    })

    $('#btn-cancel').on('click', function () {
        location.href = "/dashboard/courses";
    })

    //刪除AJAX
    $('.btn-courseDelete').click(function () {
        let courseNo = this.id;
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
                        url: '/dashboard/courses/' + courseNo,
                        type: 'DELETE',
                        success: function (result) {
                            window.location.href = '/dashboard/courses'
                        }
                    });
                }, 1500)
            }
        })
    })


    //課程資料keyup event
    $('#score').keyup(function () {
        validateScore();
    })
    $('#price').keyup(function () {
        validatePrice();
    })
    $('#date').change(function () {
        validateDate();
    })
    $('#imgInp').change(function () {
        validateImg();
    })
    $('#courseName').keyup(function () {
        validateName();

    })
    $('#courseIntroduction').keyup(function () {
        validateIntroduction();
    })
    $('#lecturer').keyup(function () {
        validatelecturer();
    })
    $('#courseVedio').keyup(function () {
        validateVedio();
    })
    $("#userid").keyup(function () {
        validateId();
    })


    //submit 確認(create & update)
    $('#btn-submit').on('click', function () {

        let checkResult = true;

        checkResult = checkPackage();

        if (checkResult) {
            Swal.fire({
                title: '確認送出!?',
                text: "",
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
                        title: '完成!',
                        showConfirmButton: false,
                        timer: 800
                    })
                    setTimeout(() => {
                        $('#form').submit();
                    }, 800)
                }
            })
        } else {
            Swal.fire({
                icon: 'error',
                title: '表單格式錯誤',
                html: ''
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

    //表單驗證 function (valid class set attribute )
    function validateScore() {
        let scoreRegex = /^[\d.]+$/;  //let scoreRegex = /[0-9]+(.[0-9])/;
        if ($("#score").val() === "") {
            $('#score').attr("class", "form-control is-invalid")
            return false;
        } else if (!scoreRegex.test($("#score").val()) || $("#score").val() >= 10) {
            $('#scoreError').text("評分格式不符，請輸入0~9.9")
            $('#score').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#score').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validatePrice() {
        let priceRegex = /^\d+$/;
        if ($("#price").val() === "") {
            $('#price').attr("class", "form-control is-invalid")
            return false;
        } else if (!priceRegex.test($("#price").val())) {
            $('#priceError').text("價格格式不符，請重新輸入")
            $('#price').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#price').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validateDate() {
        let postDate = new Date($("#date").val());
        let nowDate = new Date();
        if ($('#date').val() == "") {
            $('#dateError').text("請輸入上架日期!!")
            $('#date').attr("class", "form-control is-invalid")
            return false;
        } else if (postDate < nowDate) {
            $('#dateError').text("上架日期不可設定於今日之前!!")
            $('#date').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#date').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validateImg() {
        if ($('#imgInp').val() == "" && $('#btn-submit').val() == "createSubmit") {
            $('#imgInp').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#imgInp').attr("class", "form-control is-valid")
            return true;
        }
    }

    function checkName() {
        var courseName = $('#courseName').val();
        var postData = {"courseName": courseName};
        $.ajax({
            type: "post",
            url: "/dashboard/courses/checkName",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(postData),
            success: function (data) {
                if (data != null) {
                    $('#nameError').text(`提示：與課程編號 ${data.courseNo} 的名稱重複，換個更響亮的名稱吧 !`)
                    $('#courseName').attr("class", "form-control is-invalid")
                    $('#btn-submit').attr("disabled", true);
                } else {
                    $('#btn-submit').attr("disabled", false);
                }
            }
        })
    }
    function checkUserid() {
        var userid = $("#userid").val();
        var postData = {"userid": userid};
        $.ajax({
            type: "post",
            url: "/dashboard/courses/checkUserid",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(postData),
            success: function (data) {
                console.log(data)
                if (data) {
                    $('#userid').attr("class", "form-control is-valid")
                    // $('#btn-submit').attr("disabled", true);
                } else {
                    $('#userid').attr("class", "form-control is-invalid")
                    $('#useridError').text("講師帳號未註冊!!")

                    // $('#btn-submit').attr("disabled", false);
                }
            }
        })
    }

    function validateName() {
        checkName();
        if ($('#courseName').val() == "") {
            $('#courseName').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#courseName').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validateId() {
        checkUserid();
        let IdRegex = /^[a-z,A-Z][1-2,8-9]\d{8}$/;
        if ($("#userid").val() === "") {
            $('#userid').attr("class", "form-control is-invalid")
            return false;
        } else if (!IdRegex.test($("#userid").val())) {
            $('#idError').text("身分證格式不符，請輸入正確身分證字號")
            $('#userid').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#userid').attr("class", "form-control is-valid")
            return true;
        }

    }

    function validateIntroduction() {
        if ($('#courseIntroduction').val() == "") {
            $('#courseIntroduction').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#courseIntroduction').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validatelecturer() {
        if ($('#lecturer').val() == "") {
            $('#lecturer').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#lecturer').attr("class", "form-control is-valid")
            return true;
        }
    }

    function validateVedio() {
        if ($('#courseVedio').val() == "") {
            $('#courseVedio').attr("class", "form-control is-invalid")
            return false;
        } else {
            $('#courseVedio').attr("class", "form-control is-valid")
            return true;
        }
    }

    //驗證包
    function checkPackage() {
        let checkResult = false;
        let v1 = validateName();
        let v2 = validateIntroduction()
        let v3 = validatelecturer()
        let v4 = validateVedio()
        let v5 = validateScore()
        let v6 = validatePrice()
        let v7 = validateDate()
        let v8 = validateId()
        if (v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8) {
            checkResult = true;
            return checkResult
        }
        return checkResult;
    }

    //一鍵輸入
    $('#correctInput').click(function () {
        $("#userid").val("A123456789")
        $('#courseName').val('面試必勝10招')
        $('#courseCategory').val('求職技巧')
        $('#courseIntroduction').val('畢業季將近，即將踏入社會的準畢業生們開始尋找自己未來的出路，積極查找各種工作資訊，許多公司也紛紛開出職缺，想趁著畢業求職潮廣招人才。面對各種夢幻職缺，你知道企業面試官們最在意哪些地方嗎？')
        $('#lecturer').val('王大陸')
        $('#date').val('2022-08-01')
        $('#coursePic').val('url')
        $('#courseVedio').val('url')
        $('#score').val('9.9')
        $('#price').val('1999')
        checkPackage();
    })
    $('#wrongInput').click(function () {
        $("#userid").val("Z228456789")
        $('#courseName').val('面試必勝100招')
        $('#courseCategory').val('求職技巧')
        $('#courseIntroduction').val('畢業季將近，即將踏入社會的準畢業生們開始尋找自己未來的出路，積極查找各種工作資訊，許多公司也紛紛開出職缺，想趁著畢業求職潮廣招人才。面對各種夢幻職缺，你知道企業面試官們最在意哪些地方嗎？')
        $('#lecturer').val('')
        $('#date').val('2022-01-01')
        $('#coursePic').val('url')
        $('#courseVedio').val('url')
        $('#score').val('11')
        $('#price').val('abc')
        checkPackage();
    })

    //DataTable
    $(document).ready(function () {
        $('#table_id').DataTable(
            {
                columnDefs: [

                    {orderable: false, targets: [6]},
                    {
                        "targets": [0, 1, 2, 3, 4, 5],
                        "className": "text-center",
                    },
                    {
                        "search": "text-center"
                    }
                ],
                language: {
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