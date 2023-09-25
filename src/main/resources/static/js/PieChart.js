// Create root3 element
// https://www.amcharts.com/docs/v5/getting-started/#root3_element
var root3 = am5.Root.new("chartPie");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root3.setThemes([
    am5themes_Animated.new(root3)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
var chart = root3.container.children.push(
    am5percent.PieChart.new(root3, {
        endAngle: 270
    })
);


// Create series
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
var series = chart.series.push(
    am5percent.PieSeries.new(root3, {
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
    category: "적합",
    value: okCount
}, {
    category: "NG",
    value: ngCount
}]);

series.appear(1000, 100);