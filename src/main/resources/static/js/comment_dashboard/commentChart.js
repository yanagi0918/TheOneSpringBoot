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
	
	//評論發布月份
	$.ajax({
		type: 'GET',
		//		url: basePath + '/jobtypejson',
		url: '/dashboard/comments/commenttimejson',
		success: function(result) {

			var ctx4 = $("#commentTimeChart").get(0).getContext("2d");
			var commentTimeChart = new Chart(ctx4, {
				type: "bar",
				data: {
					labels: ["Mon", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Agr", "Sep", "Oct", "Nov", "Dec"],
					datasets: [{
						label: '總筆數',
						backgroundColor: [
							'rgb(54, 162, 235)'
							//							'rgb(153, 102, 255)'
						],
						data: result,
						yAxisID: 'yAxis'
					}]
				},
				options: {  
    				scales: {
        				yAxis: {
                			ticks:{stepSize:1}
        				}
    				}
				}

			});
		}
	})

});