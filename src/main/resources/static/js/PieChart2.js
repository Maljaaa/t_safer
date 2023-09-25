// Create root4 element
// https://www.amcharts.com/docs/v5/getting-started/#root4_element
var root4 = am5.Root.new("chartPie2");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root4.setThemes([
    am5themes_Animated.new(root4)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
var chart = root4.container.children.push(
    am5percent.PieChart.new(root4, {
        endAngle: 270
    })
);


// Create series
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
var series = chart.series.push(
    am5percent.PieSeries.new(root4, {
        valueField: "value",
        categoryField: "category",
        endAngle: 270,
    })
);

series.states.create("hidden", {
    endAngle: -90
});

series.get("colors").set("colors", [
    am5.color("#5EA639"),
    am5.color("#FF1016")
]);

// Set data
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
series.data.setAll([{
    category: "IN",
    value: inCount
}, {
    category: "OUT",
    value: outCount
}]);

series.appear(1000, 100);