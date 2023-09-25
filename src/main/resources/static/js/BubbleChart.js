// Create root1 element
// https://www.amcharts.com/docs/v5/getting-started/#root1_element
var root1 = am5.Root.new("chartBubble");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root1.setThemes([
    am5themes_Animated.new(root1)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root1.container.children.push(am5xy.XYChart.new(root1, {
    panX: true,
    panY: true,
    wheelY: "zoomX",
    pinchZoomX:true,
    pinchZoomY:true
}));


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.DateAxis.new(root1, {
    baseInterval: { timeUnit: "minute", count: 5 }, // 시간 단위 설정
    min: startDate,
    max: endDate,
    renderer: am5xy.AxisRendererX.new(root1, {}),
    tooltip: am5.Tooltip.new(root1, {})
}));

xAxis.children.moveValue(am5.Label.new(root1, {
    text: "",
    x: am5.p50,
    centerX: am5.p50
}), xAxis.children.length - 1);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root1, {
    renderer: am5xy.AxisRendererY.new(root1, {
        inversed: false
    }),
    tooltip: am5.Tooltip.new(root1, {})
}));

yAxis.children.moveValue(am5.Label.new(root1, {
    rotation: -90,
    text: "",
    y: am5.p50,
    centerX: am5.p50
}), 0);


// Create series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.LineSeries.new(root1, {
    calculateAggregates: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "y",
    valueXField: "date",
    seriesTooltipTarget: "bullet",
    tooltip: am5.Tooltip.new(root1, {
        pointerOrientation: "horizontal",
        labelText: "[bold]{title}[/]\nValue_1: {valueY.formatNumber('#.0')}\nDate: {valueX.formatDate()}"
    })
}));

series.strokes.template.set("visible", false);


// Add bullet
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/#Bullets
var circleTemplate = am5.Template.new({});
circleTemplate.adapters.add("fill", function(fill, target) {
    var dataItem = target.dataItem;
    if (dataItem) {
        return am5.Color.fromString(dataItem.dataContext.color);
    }
    return fill
});
series.bullets.push(function() {
    var bulletCircle = am5.Circle.new(root1, {
        radius: 5,
        fill: series.get("fill"),
        fillOpacity: 0.8
    }, circleTemplate);
    return am5.Bullet.new(root1, {
        sprite: bulletCircle
    });
});

// Set data
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/#Setting_data
series.data.setAll(chartData);


// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
chart.set("cursor", am5xy.XYCursor.new(root1, {
    xAxis: xAxis,
    yAxis: yAxis,
    snapToSeries: [series]
}));


// Add scrollbars
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
chart.set("scrollbarX", am5.Scrollbar.new(root1, {
    orientation: "horizontal"
}));


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear(1000);
chart.appear(1000, 100);