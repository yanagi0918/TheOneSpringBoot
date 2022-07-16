(function($) {
	"use strict";

	$.ajax({
		url: '/dashboard/order/Orderchartdata',
		type: 'GET',
		success: function(result) {

			// Single Bar Chart
			var ctx4 = $("#bar-chart").get(0).getContext("2d");
			var myChart4 = new Chart(ctx4, {
				type: "bar",
				data: {
					labels: ["已付款", "待審核", "已退款", "已駁回"],
					datasets: [{
						label: '筆數',
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(255, 193, 7, .7)",
							"rgba(25,135,  84, .7)",
							"rgba(220,53,  69, .7)"
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
					labels: ["已付款", "待審核", "已退款", "已駁回"],
					datasets: [{
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(255, 193, 7, .7)",
							"rgba(25,135,  84, .7)",
							"rgba(220,53,  69, .7)"
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