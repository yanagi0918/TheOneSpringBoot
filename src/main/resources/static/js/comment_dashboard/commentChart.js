$(function() {
	"use strict";

	$.ajax({
		type: 'GET',
//		url: basePath + '/jobtypejson',
		url: '/dashboard/comments/jobtypejson',
		success: function(result) {
			
			var ctx1 = $("#jobTypeChart").get(0).getContext("2d");
			var jobTypeChart = new Chart(ctx1, {
				type: "bar",
				data: {
					labels: ["正職", "兼職", "工讀", "實習"],
					datasets: [{
						label: '總筆數',
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
	})

});