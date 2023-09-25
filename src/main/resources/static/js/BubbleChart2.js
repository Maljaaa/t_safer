// Create root2 element
// https://www.amcharts.com/docs/v5/getting-started/#root2_element
var root2 = am5.Root.new("chartBubble2");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root2.setThemes([
    am5themes_Animated.new(root2)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root2.container.children.push(am5xy.XYChart.new(root2, {
    panX: true,
    panY: true,
    wheelY: "zoomX",
    pinchZoomX:true,
    pinchZoomY:true
}));


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.DateAxis.new(root2, {
    baseInterval: { timeUnit: "minute", count: 5 }, // 시간 단위 설정
    min: startDate,
    max: endDate,
    renderer: am5xy.AxisRendererX.new(root2, {}),
    tooltip: am5.Tooltip.new(root2, {})
}));

xAxis.children.moveValue(am5.Label.new(root2, {
    text: "",
    x: am5.p50,
    centerX: am5.p50
}), xAxis.children.length - 1);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root2, {
    renderer: am5xy.AxisRendererY.new(root2, {
        inversed: false
    }),
    tooltip: am5.Tooltip.new(root2, {})
}));

yAxis.children.moveValue(am5.Label.new(root2, {
    rotation: -90,
    text: "",
    y: am5.p50,
    centerX: am5.p50
}), 0);


// Create series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.LineSeries.new(root2, {
    calculateAggregates: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "y",
    valueXField: "date",
    seriesTooltipTarget: "bullet",
    tooltip: am5.Tooltip.new(root2, {
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
    var bulletCircle = am5.Circle.new(root2, {
        radius: 5,
        fill: series.get("fill"),
        fillOpacity: 0.8
    }, circleTemplate);
    return am5.Bullet.new(root2, {
        sprite: bulletCircle
    });
});

// Set data
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/#Setting_data
series.data.setAll(chartData2);


// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
chart.set("cursor", am5xy.XYCursor.new(root2, {
    xAxis: xAxis,
    yAxis: yAxis,
    snapToSeries: [series]
}));


// Add scrollbars
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
chart.set("scrollbarX", am5.Scrollbar.new(root2, {
    orientation: "horizontal"
}));


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear(1000);
chart.appear(1000, 100);