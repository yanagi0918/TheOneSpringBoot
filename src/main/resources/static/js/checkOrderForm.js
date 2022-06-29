$(function () {
    
    $('#OrderwrongInput').click(function () {
    $('#userId').val('12A345675')
    $('#orderDate').val('2022-05-20')
    $("#state").val(2);
    $('#productId').val('1,2,3,4')
    $('#totalPrice').val('6666K')
})

$('#OrdercorrectInput').click(function () {
    $('#userId').val('J123456789')
    $('#orderDate').val('2022-05-23')
    $("#state").val(1); 
    $('#productId').val('1,2,3,4,5')
    $('#totalPrice').val('86000')
})

    $('.btn-orderUpdate').click(function() {
		location.href = `./OrdersManager?UpdateId=${$(this).val()}`;
	})
	
	$('#btn-toCreate').click(function() {
		location.href = "/Order/order";
	})
	
    $('#btn-goBack').click(function() {
		location.href = "/Order/orders";
	})
	

	$('#btn-submit').click(function() {
		let warningStr = "";
		let checkOrderForm = true;

		 let IdRegex = /^[a-z,A-Z]{1}[1-2,8-9]{1}\d{8}$/; 
    if (!IdRegex.test($("#userId").val())) {
			Swal.fire(
  				'格式錯誤',
  				'請輸入正確身分證字號',
  				'error'
			)
        checkOrderForm = false;
        return checkOrderForm;
    }
    if ($('#orderDate').val() == "") {
			warningStr += "請輸入日期" + "<br>";
			checkOrderForm = false;
		}

    let priceRegex = /^\d*$/;
    if (!priceRegex.test($("#totalPrice").val())) {
		Swal.fire(
  				'格式錯誤',
  				'總價只能輸入有效數字',
  				'error'
			)
        checkOrderForm = false;
        return checkOrderForm;
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
		} else {
			Swal.fire({
				icon: 'error',
				title: '格式錯誤',
				html: warningStr,
			})
		}
	})

});




