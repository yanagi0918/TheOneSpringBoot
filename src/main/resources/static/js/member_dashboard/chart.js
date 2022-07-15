(function($) {
	"use strict";

	$.ajax({
		url: '/dashboard/member/chartdata1',
		type: 'GET',
		success: function(result) {

			// Doughnut Chart
			var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
			var myChart6 = new Chart(ctx6, {
				type: "doughnut",
				data: {
					labels: ["男", "女"],
					datasets: [{
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(255, 0, 0, .6)",

						],
						data: result
					}]
				},
				options: {
					responsive: true
				}
			});
		}
	});


	$.ajax({
		url: '/dashboard/member/chartdata2',
		type: 'GET',
		success: function(result) {
			// Single Bar Chart
			var ctx4 = $("#bar-chart").get(0).getContext("2d");
			var myChart4 = new Chart(ctx4, {
				type: "bar",
				data: {
					labels: ["18歲以下", "19~40歲", "41~65歲", "65歲以上"],
					datasets: [{
						label: '人數',
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(0, 156, 255, .6)",
							"rgba(0, 156, 255, .5)",
							"rgba(0, 156, 255, .4)"
						],
						data: result
					}]
				},
				options: {
					responsive: true,
					scales: {
						yAxes: [{
							ticks: {
								stepSize: 1
							}
						}]
					}
				}
			})
		}
	})





})(jQuery);