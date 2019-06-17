var airTemp = document.getElementById("airTemp");
var airWater = document.getElementById("airWater");
var earthTemp = document.getElementById("earthTemp");
var earthWater = document.getElementById("earthWater");
var co2 = document.getElementById("co2");
var ec = document.getElementById("ec");
var ph = document.getElementById("ph");
var light = document.getElementById("light");
// var waterLevel = document.getElementById("waterLevel");
// var monitorInterval = document.getElementById("monitorInterval");

noUiSlider.create(airTemp, {
    start: [16, 25],
    connect: true,
    step: 1,
    range: {
        'min': 0,
        'max': 50
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

noUiSlider.create(airWater, {
    start: [60, 85],
    connect: true,
    step: 1,
    range: {
        'min': 0,
        'max': 100
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

noUiSlider.create(earthTemp, {
    start: [23, 30],
    connect: true,
    step: 1,
    range: {
        'min': 0,
        'max': 50
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

noUiSlider.create(earthWater, {
    start: [40, 60],
    connect: true,
    step: 1,
    range: {
        'min': 0,
        'max': 100
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

noUiSlider.create(co2, {
    start: [300, 500],
    connect: true,
    step: 5,
    range: {
        'min': 0,
        'max': 1000
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

noUiSlider.create(ec, {
    start: [1, 4],
    connect: true,
    step: 0.01,
    range: {
        'min': 0,
        'max': 5
    },
    ariaFormat: wNumb({
        decimals: 1
    })
    ,
    format: wNumb({
        decimals: 1
    })
});

noUiSlider.create(ph, {
    start: [5.5, 7],
    connect: true,
    step: 0.1,
    range: {
        'min': 0,
        'max': 14
    },
    ariaFormat: wNumb({
        decimals: 1
    })
    ,
    format: wNumb({
        decimals: 1
    })
});

noUiSlider.create(light, {
    start: [1000, 1300],
    connect: true,
    step: 100,
    range: {
        'min': 0,
        'max': 2000
    },
    ariaFormat: wNumb({
        decimals: 0
    })
    ,
    format: wNumb({
        decimals: 0
    })
});

// noUiSlider.create(waterLevel, {
//     start: [40, 60],
//     connect: true,
//     step: 0.01,
//     range: {
//         'min': 0,
//         'max': 100
//     },
//     ariaFormat: wNumb({
//         decimals: 0
//     })
//     ,
//     format: wNumb({
//         decimals: 0
//     })
// });

// noUiSlider.create(monitorInterval, {
//     start: [3],
//     step: 1,
//     range: {
//         'min': 3,
//         'max': 300
//     },
//     ariaFormat: wNumb({
//         decimals: 0
//     })
//     ,
//     format: wNumb({
//         decimals: 0
//     })
// });

var lowAirTemp = document.getElementById('lowAirTemp');
var highAirTemp = document.getElementById('highAirTemp');

var lowAirWater = document.getElementById('lowAirWater');
var highAirWater = document.getElementById('highAirWater');

var lowEarthWater = document.getElementById('lowEarthWater');
var highEarthWater = document.getElementById('highEarthWater');

var lowEarthTemp = document.getElementById('lowEarthTemp');
var highEarthTemp = document.getElementById('highEarthTemp');

var lowCO2 = document.getElementById('lowCO2');
var highCO2 = document.getElementById('highCO2');

var lowEC = document.getElementById('lowEC');
var highEC = document.getElementById('highEC');

var lowPH = document.getElementById('lowPH');
var highPH = document.getElementById('highPH');

var lowLight = document.getElementById('lowLight');
var highLight = document.getElementById('highLight');

// var lowWaterLevel = document.getElementById('lowWaterLevel');
// var highWaterLevel = document.getElementById('highWaterLevel');

// var monitorIntervalVal = document.getElementById("monitorIntervalVal");

airTemp.noUiSlider.on('update', function (values) {
    document.getElementById('lowAirTemp').setAttribute('value', values[0]);
    document.getElementById('lowAirTemp').value = values[0];
    document.getElementById('highAirTemp').setAttribute('value', values[1]);
    document.getElementById('highAirTemp').value = values[1];
});

lowAirTemp.addEventListener('change', function () {
    var getter = airTemp.noUiSlider.get();
    airTemp.noUiSlider.set([lowAirTemp.value,getter[1]])
});

highAirTemp.addEventListener('change', function () {
    var getter = airTemp.noUiSlider.get();
    airTemp.noUiSlider.set([getter[0], highAirTemp.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

airWater.noUiSlider.on('update', function (values) {
    document.getElementById('lowAirWater').setAttribute('value', values[0]);
    document.getElementById('lowAirWater').value = values[0];
    document.getElementById('highAirWater').setAttribute('value', values[1]);
    document.getElementById('highAirWater').value = values[1];
});

lowAirWater.addEventListener('change', function () {
    var getter = airWater.noUiSlider.get();
    airWater.noUiSlider.set([lowAirWater.value,getter[1]])
});

highAirWater.addEventListener('change', function () {
    var getter = airWater.noUiSlider.get();
    airWater.noUiSlider.set([getter[0], highAirWater.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

earthTemp.noUiSlider.on('update', function (values) {
    document.getElementById('lowEarthTemp').setAttribute('value', values[0]);
    document.getElementById('lowEarthTemp').value = values[0];
    document.getElementById('highEarthTemp').setAttribute('value', values[1]);
    document.getElementById('highEarthTemp').value = values[1];
});

lowEarthTemp.addEventListener('change', function () {
    var getter = earthTemp.noUiSlider.get();
    earthTemp.noUiSlider.set([lowEarthTemp.value,getter[1]])
});

highEarthTemp.addEventListener('change', function () {
    var getter = earthTemp.noUiSlider.get();
    earthTemp.noUiSlider.set([getter[0], highEarthTemp.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

earthWater.noUiSlider.on('update', function (values) {
    document.getElementById('lowEarthWater').setAttribute('value', values[0]);
    document.getElementById('lowEarthWater').value = values[0];
    document.getElementById('highEarthWater').setAttribute('value', values[1]);
    document.getElementById('highEarthWater').value = values[1];
});

lowEarthWater.addEventListener('change', function () {
    var getter = earthWater.noUiSlider.get();
    earthWater.noUiSlider.set([lowEarthWater.value,getter[1]])
});

highEarthWater.addEventListener('change', function () {
    var getter = earthWater.noUiSlider.get();
    earthWater.noUiSlider.set([getter[0], highEarthWater.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

co2.noUiSlider.on('update', function (values) {
    document.getElementById('lowCO2').setAttribute('value', values[0]);
    document.getElementById('lowCO2').value = values[0];
    document.getElementById('highCO2').setAttribute('value', values[1]);
    document.getElementById('highCO2').value = values[1];
});

lowCO2.addEventListener('change', function () {
    var getter = co2.noUiSlider.get();
    co2.noUiSlider.set([lowCO2.value,getter[1]])
});

highCO2.addEventListener('change', function () {
    var getter = co2.noUiSlider.get();
    co2.noUiSlider.set([getter[0], highCO2.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

ec.noUiSlider.on('update', function (values) {
    document.getElementById('lowEC').setAttribute('value', values[0]);
    document.getElementById('lowEC').value = values[0];
    document.getElementById('highEC').setAttribute('value', values[1]);
    document.getElementById('highEC').value = values[1];
});

lowEC.addEventListener('change', function () {
    var getter = ec.noUiSlider.get();
    ec.noUiSlider.set([lowEC.value,getter[1]])
});

highEC.addEventListener('change', function () {
    var getter = ec.noUiSlider.get();
    ec.noUiSlider.set([getter[0], highEC.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

ph.noUiSlider.on('update', function (values) {
    document.getElementById('lowPH').setAttribute('value', values[0]);
    document.getElementById('lowPH').value = values[0];
    document.getElementById('highPH').setAttribute('value', values[1]);
    document.getElementById('highPH').value = values[1];
});

lowPH.addEventListener('change', function () {
    var getter = ph.noUiSlider.get();
    ph.noUiSlider.set([lowPH.value,getter[1]])
});

highPH.addEventListener('change', function () {
    var getter = ph.noUiSlider.get();
    ph.noUiSlider.set([getter[0], highPH.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

light.noUiSlider.on('update', function (values) {
    document.getElementById('lowLight').setAttribute('value', values[0]);
    document.getElementById('lowLight').value = values[0];
    document.getElementById('highLight').setAttribute('value', values[1]);
    document.getElementById('highLight').value = values[1];
});

lowLight.addEventListener('change', function () {
    var getter = light.noUiSlider.get();
    light.noUiSlider.set([lowLight.value,getter[1]])
});

highLight.addEventListener('change', function () {
    var getter = light.noUiSlider.get();
    light.noUiSlider.set([getter[0], highLight.value])
});

////////////////////////////////////////////////////////////////////////////////////////////////////

// waterLevel.noUiSlider.on('update', function (values) {
//     document.getElementById('lowWaterLevel').setAttribute('value', values[0]);
//     document.getElementById('lowWaterLevel').value = values[0];
//     document.getElementById('highWaterLevel').setAttribute('value', values[1]);
//     document.getElementById('highWaterLevel').value = values[1];
// });
//
// lowWaterLevel.addEventListener('change', function () {
//     var getter = waterLevel.noUiSlider.get();
//     waterLevel.noUiSlider.set([lowWaterLevel.value,getter[1]])
// });
//
// highWaterLevel.addEventListener('change', function () {
//     var getter = waterLevel.noUiSlider.get();
//     waterLevel.noUiSlider.set([getter[0], highWaterLevel.value])
// });

////////////////////////////////////////////////////////////////////////////////////////////////////

// monitorInterval.noUiSlider.on('update', function (values) {
//     document.getElementById('monitorIntervalVal').setAttribute('value', values[0]);
//     document.getElementById("monitorIntervalVal").value = values[0];
// });
//
// monitorIntervalVal.addEventListener('change', function () {
//     monitorInterval.noUiSlider.set(monitorIntervalVal.value)
// });