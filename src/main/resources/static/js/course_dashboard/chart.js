(function($) {
	"use strict";

	$.ajax({
		url: '/dashboard/courses/chartdata',
		type: 'GET',
		success: function(result) {

			// Doughnut Chart
			var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
			var myChart6 = new Chart(ctx6, {
				type: "doughnut",
				data: {
					labels: ["英文證照", "日語證照", "韓語證照",
						"生涯轉換與轉業","自我認知","求職技巧","就業市場現況與趨勢"],
					datasets: [{
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(255, 193, 7, .7)",
							"rgba(25,135,  84, .7)",
							"rgba(220,53,  69, .7)",
							"rgba(83,236,232,0.7)",
							"rgba(144,63,217,0.6)",
							"rgba(183,255,0,0.5)",
						],
						data: result
					}]
				},
				options: {
					responsive: true
				}
			});

			// Single Bar Chart
			var ctx4 = $("#bar-chart").get(0).getContext("2d");
			var myChart4 = new Chart(ctx4, {
				type: "bar",
				data: {
					labels: ["未審核", "審核通過", "已退件", "已撤銷"],
					datasets: [{
						label: '筆數',
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
					responsive: true
				}
			});


		}
	});


})(jQuery);