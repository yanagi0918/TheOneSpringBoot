(function($) {
	"use strict";

	$.ajax({
		url: '/dashboard/resume/chartdata1',
		type: 'GET',
		success: function(result) {

			// Doughnut Chart
			var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
			var myChart6 = new Chart(ctx6, {
				type: "doughnut",
				data: {
					labels: ["大學", "科技大學", "研究所", "博士","高中職","五專"],
					datasets: [{
						backgroundColor: [
							"rgba(0, 156, 255, .7)",
							"rgba(255, 0, 0, .6)",
							"rgba(255, 156, 0, .7)",
							"rgba(0, 0, 255, .6)",
							"rgba(0, 256, 0, .7)",
							"rgba(155, 156, 0, .6)",

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


//	$.ajax({
//		url: '/dashboard/member/chartdata2',
//		type: 'GET',
//		success: function(result) {
//			// Single Bar Chart
//			var ctx4 = $("#bar-chart").get(0).getContext("2d");
//			var myChart4 = new Chart(ctx4, {
//				type: "doughnut",
//				data: {
//					labels: ["男", "女"],
//					datasets: [{
//						backgroundColor: [
//							"rgba(0, 156, 255, .7)",
//							"rgba(255, 0, 0, .6)",
//
//						],
//						data: result
//					}]
//				},
//				options: {
//					responsive: true
//				}
//			});
//		}
//	});





})(jQuery);