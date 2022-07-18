(function ($) {
    "use strict";

    $.ajax({
        url: '/dashboard/courses/chartdata',
        type: 'GET',
        success: function (result) {

            // pie Chart
            var ctx6 = $("#pie-chart")[0].getContext("2d");
            var myChart6 = new Chart(ctx6, {
                type: "pie",
                data: {
                    labels: ["英文證照", "日語證照",
						"韓語證照", "求職技巧",
                        "自我認知", "生涯轉換轉業",
                        "就業市場現況趨勢"],
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
                        data: result.dataCategory
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
                    labels: ["待審核", "已審核", "駁回"],
                    datasets: [{
                        label: "筆數",
                        backgroundColor: [
                            "rgba(0,196,255,0.7)",
                            "rgba(0,196,255,0.7)",
                            "rgba(0,196,255,0.7)",
                        ],
                        data: result.dataStatus
                    }]
                },
                options: {
                    responsive: true
                }
            });


        }
    });


})(jQuery);