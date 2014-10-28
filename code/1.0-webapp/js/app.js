//Create a new Client object with your broker's hostname, port and your own clientId

$(document).ready(function () {
    var table= $('#historicaldata').DataTable({
        "ajax": "http://localhost:8080/sensor-data",
        "sAjaxDataProp": "",
        "columns": [
            { "data": "id" },
            { "data": "timestamp" },
            { "data": "deviceId" },
            { "data": "value" }
        ],
        "columnDefs": [ {
            "targets": 1,
            "render": function ( data, type, full, meta ) {
                  var date=new Date(data);

                // correct formating of minutes and hours
                var mins = date.getMinutes();
                var hours = date.getHours();
                mins=((mins < 10) ? "0" + mins : mins);
                hours=((hours < 10) ? "0" + hours : hours);

                //output
                return date.getDate()+"."+(date.getMonth()+1)+"."+date.getFullYear()+" "+hours+":"+mins;
            }

        },
            {
                "targets": 3,
                "render": function ( data, type, full, meta ) {

                    return data+" ℃";
                }

            }]
});

    setInterval( function () {
        table.ajax.reload();
    }, 3000 );

});





var client = new Messaging.Client("localhost", 8000, "monitoring-dashboard");

var gauge1;
var gauge2;
$(document).ready(function () {


    gauge1 = new JustGage({
        id: "gauge1",
        value: 0,
        min: -20,
        max: 60,
        title: "Device 1",
        showMinMax: false,
        levelColors: ["#007CFF", "#a9d70b", "#ff0000"],
        levelColorsGradient: false,
        label: "° Celsius"
    });

    gauge2 = new JustGage({
        id: "gauge2",
        value: 0,
        min: -20,
        max: 60,
        title: "Device 2",
        showMinMax: false,
        levelColors: ["#007CFF", "#a9d70b", "#ff0000"],
        levelColorsGradient: false,
        label: "° Celsius"
    });


    var options = {

        //connection attempt timeout in seconds
        timeout: 3,

        //Gets Called if the connection has successfully been established
        onSuccess: function () {
            client.subscribe("iotcloud/+/temperature");
        },

        //Gets Called if the connection could not be established
        onFailure: function (message) {
            alert("Connection failed: " + message.errorMessage);
        }
    };

    //Gets called whenever you receive a message for your subscriptions
    client.onMessageArrived = function (message) {

        var topic = message.destinationName;

        if (topic === "iotcloud/device1/temperature") {
            setTemperatureDevice1(message.payloadString);
        } else if (topic === "iotcloud/device2/temperature") {
            setTemperatureDevice2(message.payloadString);
        }


    };

//Attempt to connect
    client.connect(options);
});

function setTemperatureDevice1(temp) {
    gauge1.refresh(parseFloat(temp));
}

function setTemperatureDevice2(temp) {
    gauge2.refresh(parseFloat(temp));
}