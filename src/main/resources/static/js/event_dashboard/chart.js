(function($) {
	"use strict";

	$.ajax({
		url: '/dashboard/event/chartdata',
		type: 'GET',
		success: function(result) {

			// Single Bar Chart
			var ctx4 = $("#bar-chart").get(0).getContext("2d");
			var myChart4 = new Chart(ctx4, {
				type: "bar",
				data: {
					labels: ["未審核", "審核通過", "已退件", "已撤銷"],
					datasets: [{
						label: '筆數',
						backgroundColor: [
							"rgba(0, 156, 255, .5)",
							"rgba(0, 156, 255, .5)",
							"rgba(0, 156, 255, .5)",
							"rgba(0, 156, 255, .5)"
						],
						data: result
					}]
				},
				options: {
					responsive: true
				}
			});

			// Doughnut Chart
			var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
			var myChart6 = new Chart(ctx6, {
				type: "doughnut",
				data: {
					labels: ["未審核", "審核通過", "已退件", "已撤銷"],
					datasets: [{
						backgroundColor: [
							"rgb(255, 99, 132)",
							"rgb(54, 162, 235)",
							"rgb(255, 206, 86)",
							"rgb(75, 192, 192)"
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