$(function() {
	"use strict";
	
	//職務型態比例
	$.ajax({
		type: 'GET',
		//		url: basePath + '/jobtypejson',
		url: '/dashboard/comments/jobtypejson',
		success: function(result) {

			var ctx1 = $("#jobTypeChart").get(0).getContext("2d");
			var jobTypeChart = new Chart(ctx1, {
				type: "doughnut",
				data: {
					labels: ["正職", "兼職", "工讀", "實習"],
					datasets: [{
						label: '總筆數',
						backgroundColor: [
							'rgb(255, 99, 132)',
							'rgb(54, 162, 235)',
							'rgb(255, 206, 86)',
							'rgb(75, 192, 192)',
							//							'rgb(153, 102, 255)'
						],
						data: result
					}]
				},
				options: {
					responsive: true,
					legend: {
						position: 'top',
					},
					plugins: {
						title: {
							display: true,
							text: '職務型態比例'
						}
					}

				},

			});
		}
	})

});