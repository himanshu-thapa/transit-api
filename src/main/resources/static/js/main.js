var latLog = [];
$(document).ready(function () {
    Swal.fire({
        title: "Loading...",
        text: "Please wait",
        allowOutsideClick: false
    });
    swal.showLoading();

    var mapFlag = sessionStorage.getItem('mapFlag');
    // ########FIRST##########
    if (mapFlag == null) {
        sessionStorage.setItem('mapFlag', 101);
        var maps = L.map('mapid').setView([34.0658699, -118.44796], 8);
        mapLink =
            '<a href="http://openstreetmap.org">OpenStreetMap</a>';
        L.tileLayer(
            'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; ' + mapLink + ' Contributors',
                maxZoom: 18,
            }).addTo(maps);

        $('#agencyList').empty();
        $.ajax({
            url: "/api/agency/all",
            type: "get",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                var result = response.response.agency;
                sessionStorage.setItem('agency', JSON.stringify(result));
                $('#agencyList').append('<option value="" selected hidden>Please Select Agency</option>');
                for (var i = 0; i < result.length; i++) {
                    var option = document.createElement("option");
                    option.text = result[i].title + '(' + result[i].regionTitle + ')';
                    option.value = result[i].tag;
                    $('#agencyList').append(option);
                }
                swal.close();
            }
        });

    }
    // ########AFTER FIRST##########
    else {
        latLog = sessionStorage.getItem('latLog');
        latLog = JSON.parse(latLog);
        var map = L.map('mapid').setView([34.0658699, -118.44796], 8);
        mapLink = '<a href="http://openstreetmap.org">OpenStreetMap</a>';
        L.tileLayer(
            'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; ' + mapLink + ' Contributors',
                maxZoom: 18,
            }).addTo(map);

        $('#stopList').empty();
        $('#stopList').append('<option value="" selected hidden>Please Select Stop</option>');
        for (var i = 0; i < latLog.length; i++) {
            var option2 = document.createElement("option");
            option2.text = latLog[i].title;
            option2.value = latLog[i].tag;
            $('#stopList').append(option2);

            marker = new L.marker([latLog[i].lat, latLog[i].lon])
                .bindPopup(latLog[i].title)
                .addTo(map);
        }

        var latlngs = [];
        for (var j = 0; j < latLog.length; j++) {
            latlngs.push([latLog[j].lat, latLog[j].lon]);
        }

        var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
        map.fitBounds(polyline.getBounds());

        var agencyNew = sessionStorage.getItem('agency');
        agencyNew = JSON.parse(agencyNew);
        $('#agencyList').empty();
        $('#agencyList').append('<option value="' + sessionStorage.getItem('agencyTag') + '" selected>' + sessionStorage.getItem('agencyTitle') + '</option>');
        for (var i = 0; i < agencyNew.length; i++) {
            var option = document.createElement("option");
            option.text = agencyNew[i].title + '(' + agencyNew[i].regionTitle + ')';
            option.value = agencyNew[i].tag;
            $('#agencyList').append(option);
        }

        var routeNew = sessionStorage.getItem('route');
        routeNew = JSON.parse(routeNew);
        $('#routeList').empty();
        $('#routeList').append('<option value="' + sessionStorage.getItem('routeTag') + '" selected>' + sessionStorage.getItem('routeTitle') + '</option>');
        for (var y = 0; y < routeNew.length; y++) {
            var option1 = document.createElement("option");
            option1.text = routeNew[y].title;
            option1.value = routeNew[y].tag;
            $('#routeList').append(option1);
        }

        swal.close();
    }
});

var agencyTag;
$('#agencyList').on('change', function () {
    Swal.fire({
        title: "Loading...",
        text: "Please wait",
        allowOutsideClick: false
    });
    swal.showLoading();
    agencyTag = this.value;
    sessionStorage.setItem('agencyTag', agencyTag);
    var optionsText = this.options[this.selectedIndex].text;
    sessionStorage.setItem('agencyTitle', optionsText);
    $('#routeList').empty();
    $.ajax({
        url: "/api/agency/routes",
        type: "get",
        data: {
            agencyTag: agencyTag
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            var result = response.response.route;
            sessionStorage.setItem('route', JSON.stringify(result));
            $('#routeList').append('<option value="" selected hidden>Please Select Route</option>');
            for (var i = 0; i < result.length; i++) {
                var option = document.createElement("option");
                option.text = result[i].title;
                option.value = result[i].tag;
                $('#routeList').append(option);
            }
            swal.close();
        }
    });
});

var routeTag;
var latMax;
var lonMax;
var latLog = [];
$('#routeList').on('change', function () {
    Swal.fire({
        title: "Loading...",
        text: "Please wait",
        allowOutsideClick: false
    });
    swal.showLoading();
    routeTag = this.value;
    var optionsText = this.options[this.selectedIndex].text;
    sessionStorage.setItem('routeTag', routeTag);
    sessionStorage.setItem('routeTitle', optionsText);

    $('#stopList').empty();

    var data;
    if (sessionStorage.getItem('agencyTag') == null) {
        data = {
            agencyTag: agencyTag,
            routeTag: routeTag
        }
    } else {
        data = {
            agencyTag: sessionStorage.getItem('agencyTag'),
            routeTag: routeTag
        }
    }
    $.ajax({
        url: "/api/agency/route-details",
        type: "get",
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            var result = response.response.route.stop;
            latMax = response.response.route.latMax;
            lonMax = response.response.route.lonMax;

            sessionStorage.setItem('latLog', JSON.stringify(result));
            sessionStorage.setItem('latMax', latMax);
            sessionStorage.setItem('lonMax', lonMax);

            $('#stopList').empty()
            $('#stopList').append('<option value="" selected hidden>Please Select Stop</option>');
            for (var i = 0; i < result.length; i++) {
                var option = document.createElement("option");
                option.text = result[i].title;
                option.value = result[i].tag;
                $('#stopList').append(option);
            }
            swal.close();
            window.location.reload();
        }
    });
});

$('#stopList').on('change', function (e) {
    e.preventDefault();
    Swal.fire({
        title: "Loading...",
        text: "Please wait",
        allowOutsideClick: false
    });
    swal.showLoading();
    var stopTag = this.value;
    $('#stopList').empty();
    $.ajax({
        url: "/api/agency/stop/predictions",
        type: "get",
        data: {
            agencyTag: sessionStorage.getItem('agencyTag'),
            routeTag: sessionStorage.getItem('routeTag'),
            stopTag: stopTag
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            console.log(response);
            $('#stopList').empty();
            $('#stopList').append('<option value="" selected hidden>Please Select Stop</option>');
            for (var i = 0; i < latLog.length; i++) {
                var option2 = document.createElement("option");
                option2.text = latLog[i].title;
                option2.value = latLog[i].tag;
                $('#stopList').append(option2);
            }

            if (response.statusCode === 400) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'There are no predictions for this stop!'
                })
            }else {
                if (response.response.predictions == null ||response.response.predictions.direction == null) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'There is no prediction for this stop!'
                    })
                } else {
                    var html = "";

                    $.each(response.response.predictions.direction.prediction, function(key, value)
                    {
                        html += "Vehicle " + value.vehicle +
                            " will arrive here after about " + value.minutes + " minutes.<br>";
                    });

                    Swal.fire({
                        icon: 'success',
                        title: 'Prediction for: '+response.response.predictions.direction.title,
                        text: html
                    })
                }
            }
        }
    });
});