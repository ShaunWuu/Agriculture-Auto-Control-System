var airTemp = document.getElementById("airTemp");
var airWater = document.getElementById("airWater");
var earthTemp1 = document.getElementById("earthTemp1");
// var earthWater1 = document.getElementById("earthWater1");
var earthTemp2 = document.getElementById("earthTemp2");
// var earthWater2 = document.getElementById("earthWater2");
var Light = document.getElementById("Light");

var airTempHigh = parseInt(document.getElementById("airTempHigh").value);
var airTempLow = parseInt(document.getElementById("airTempLow").value);
var airTempMax = parseInt(document.getElementById("airTempMax").value);
var airTempMin = parseInt(document.getElementById("airTempMin").value);

var airWaterHigh = parseInt(document.getElementById("airWaterHigh").value);
var airWaterLow = parseInt(document.getElementById("airWaterLow").value);
var airWaterMax = parseInt(document.getElementById("airWaterMax").value);
var airWaterMin = parseInt(document.getElementById("airWaterMin").value);

var earthTemp1High = parseInt(document.getElementById("earthTemp1High").value);
var earthTemp1Low = parseInt(document.getElementById("earthTemp1Low").value);
var earthTemp1Max = parseInt(document.getElementById("earthTemp1Max").value);
var earthTemp1Min = parseInt(document.getElementById("earthTemp1Min").value);

var earthTemp2High = parseInt(document.getElementById("earthTemp2High").value);
var earthTemp2Low = parseInt(document.getElementById("earthTemp2Low").value);
var earthTemp2Max = parseInt(document.getElementById("earthTemp2Max").value);
var earthTemp2Min = parseInt(document.getElementById("earthTemp2Min").value);

// var earthWater1High = parseInt(document.getElementById("earthWater1High").value);
// var earthWater1Low = parseInt(document.getElementById("earthWater1Low").value);
// var earthWater1Max = parseInt(document.getElementById("earthWater1Max").value);
// var earthWater1Min = parseInt(document.getElementById("earthWater1Min").value);

// var earthWater2High = parseInt(document.getElementById("earthWater2High").value);
// var earthWater2Low = parseInt(document.getElementById("earthWater2Low").value);
// var earthWater2Max = parseInt(document.getElementById("earthWater2Max").value);
// var earthWater2Min = parseInt(document.getElementById("earthWater2Min").value);

var LightHigh = parseInt(document.getElementById("LightHigh").value);
var LightLow = parseInt(document.getElementById("LightLow").value);
var LightMax = parseInt(document.getElementById("LightMax").value);
var LightMin = parseInt(document.getElementById("LightMin").value);

var lowAirTemp = document.getElementById('lowAirTemp');
var highAirTemp = document.getElementById('highAirTemp');

var lowAirWater = document.getElementById('lowAirWater');
var highAirWater = document.getElementById('highAirWater');

var lowEarthTemp1 = document.getElementById('lowEarthTemp1');
var highEarthTemp1 = document.getElementById('highEarthTemp1');

// var lowEarthWater1 = document.getElementById('lowEarthWater1');
// var highEarthWater1 = document.getElementById('highEarthWater1');

var lowEarthTemp2 = document.getElementById('lowEarthTemp2');
var highEarthTemp2 = document.getElementById('highEarthTemp2');

var lowEarthWater2 = document.getElementById('lowEarthWater2');
var highEarthWater2 = document.getElementById('highEarthWater2');

var lowLight = document.getElementById('lowLight');
var highLight = document.getElementById('highLight');

createSlider(airTemp, airTempHigh, airTempLow, 1, airTempMax, airTempMin, 0);
createSlider(airWater, airWaterHigh, airWaterLow, 1, airWaterMax, airWaterMin, 0);
createSlider(earthTemp1, earthTemp1High, earthTemp1Low, 1, earthTemp1Max, earthTemp1Min, 0);
// createSlider(earthWater1, earthWater1High, earthWater1Low, 1, earthWater1Max, earthWater1Min, 0);
createSlider(earthTemp2, earthTemp2High, earthTemp2Low, 1, earthTemp2Max, earthTemp2Min, 0);
// createSlider(earthWater2, earthWater2High, earthWater2Low, 1, earthWater2Max, earthWater2Min, 0);
createSlider(Light, LightHigh, LightLow, 100, LightMax, LightMin, 0);

setAllSliderNumber();

function setAllSliderNumber(){

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////

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
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    earthTemp1.noUiSlider.on('update', function (values) {
        document.getElementById('lowEarthTemp1').setAttribute('value', values[0]);
        document.getElementById('lowEarthTemp1').value = values[0];
        document.getElementById('highEarthTemp1').setAttribute('value', values[1]);
        document.getElementById('highEarthTemp1').value = values[1];
    });

    lowEarthTemp1.addEventListener('change', function () {
        var getter = earthTemp1.noUiSlider.get();
        earthTemp1.noUiSlider.set([lowEarthTemp1.value,getter[1]])
    });

    highEarthTemp1.addEventListener('change', function () {
        var getter = earthTemp1.noUiSlider.get();
        earthTemp1.noUiSlider.set([getter[0], highEarthTemp1.value])
    });

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    earthTemp2.noUiSlider.on('update', function (values) {
        document.getElementById('lowEarthTemp2').setAttribute('value', values[0]);
        document.getElementById('lowEarthTemp2').value = values[0];
        document.getElementById('highEarthTemp2').setAttribute('value', values[1]);
        document.getElementById('highEarthTemp2').value = values[1];
    });

    lowEarthTemp2.addEventListener('change', function () {
        var getter = earthTemp2.noUiSlider.get();
        earthTemp2.noUiSlider.set([lowEarthTemp2.value,getter[1]])
    });

    highEarthTemp2.addEventListener('change', function () {
        var getter = earthTemp2.noUiSlider.get();
        earthTemp2.noUiSlider.set([getter[0], highEarthTemp2.value])
    });

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    // earthWater1.noUiSlider.on('update', function (values) {
    //     document.getElementById('lowEarthWater1').setAttribute('value', values[0]);
    //     document.getElementById('lowEarthWater1').value = values[0];
    //     document.getElementById('highEarthWater1').setAttribute('value', values[1]);
    //     document.getElementById('highEarthWater1').value = values[1];
    // });
    //
    // lowEarthWater1.addEventListener('change', function () {
    //     var getter = earthWater1.noUiSlider.get();
    //     earthWater1.noUiSlider.set([lowEarthWater1.value,getter[1]])
    // });
    //
    // highEarthWater1.addEventListener('change', function () {
    //     var getter = earthWater1.noUiSlider.get();
    //     earthWater1.noUiSlider.set([getter[0], highEarthWater1.value])
    // });

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    // earthWater2.noUiSlider.on('update', function (values) {
    //     document.getElementById('lowEarthWater2').setAttribute('value', values[0]);
    //     document.getElementById('lowEarthWater2').value = values[0];
    //     document.getElementById('highEarthWater2').setAttribute('value', values[1]);
    //     document.getElementById('highEarthWater2').value = values[1];
    // });
    //
    // lowEarthWater2.addEventListener('change', function () {
    //     var getter = earthWater2.noUiSlider.get();
    //     earthWater2.noUiSlider.set([lowEarthWater2.value,getter[1]])
    // });
    //
    // highEarthWater2.addEventListener('change', function () {
    //     var getter = earthWater2.noUiSlider.get();
    //     earthWater2.noUiSlider.set([getter[0], highEarthWater2.value])
    // });

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    Light.noUiSlider.on('update', function (values) {
        document.getElementById('lowLight').setAttribute('value', values[0]);
        document.getElementById('lowLight').value = values[0];
        document.getElementById('highLight').setAttribute('value', values[1]);
        document.getElementById('highLight').value = values[1];
    });

    lowLight.addEventListener('change', function () {
        var getter = Light.noUiSlider.get();
        Light.noUiSlider.set([lowLight.value,getter[1]])
    });

    highLight.addEventListener('change', function () {
        var getter = Light.noUiSlider.get();
        Light.noUiSlider.set([getter[0], highLight.value])
    });

    ///////////////////////////////////////////////////////////////////////////////////////////////////

}

function createSlider(element, high, low, stepSet, maxSet, minSet, decimalSet){

    noUiSlider.create(element, {
        start: [low, high],
        connect: true,
        step: stepSet,
        range: {
            'min': minSet,
            'max': maxSet
        },
        ariaFormat: wNumb({
            decimals: decimalSet
        }),
        format: wNumb({
            decimals: decimalSet
        })
    });

}