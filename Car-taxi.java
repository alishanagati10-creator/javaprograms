<!DOCTYPE html>
<html>
<head>
    <title>Call Taxi Booking</title>
    <style>
        body {
            font-family: Arial;
            background: #eef2f3;
            padding: 20px;
        }
        h1 {
            text-align: center;
        }
        .box {
            background: white;
            padding: 15px;
            margin: 15px auto;
            width: 900px;
            border-radius: 6px;
        }
        label {
            width: 130px;
            display: inline-block;
        }
        input, select {
            padding: 5px;
            margin: 5px;
        }
        button {
            padding: 6px 12px;
            background: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 6px;
            text-align: center;
        }
        th {
            background: #ddd;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>

<body>

<h1>🚕 Call Taxi Booking Application</h1>

<div class="box">
    <h3>Book Taxi</h3>

    <label>Customer ID:</label>
    <input type="number" id="cid"><br>

    <label>Pickup Point:</label>
    <select id="pickup">
        <option>A</option><option>B</option><option>C</option>
        <option>D</option><option>E</option><option>F</option>
    </select><br>

    <label>Drop Point:</label>
    <select id="drop">
        <option>A</option><option>B</option><option>C</option>
        <option>D</option><option>E</option><option>F</option>
    </select><br>

    <label>Pickup Time:</label>
    <input type="number" id="time"><br>

    <button onclick="bookTaxi()">Book Taxi</button>

    <p id="result"></p>
</div>

<div class="box">
    <h3>Taxi Details</h3>
    <div id="details"></div>
</div>

<script>

const DISTANCE_PER_POINT = 15;
let bookingId = 1;


let taxis = [];
for (let i = 1; i <= 4; i++) {
    taxis.push({
        taxiId: i,
        currentPoint: 'A',
        freeTime: 0,
        totalEarnings: 0,
        bookings: []
    });
}


function distance(from, to) {
    return Math.abs(from.charCodeAt(0) - to.charCodeAt(0)) * DISTANCE_PER_POINT;
}

function travelTime(from, to) {
    return Math.abs(from.charCodeAt(0) - to.charCodeAt(0));
}

function calculateFare(dist) {
    return 100 + (dist - 5) * 10;
}


function bookTaxi() {
    try {
        let cid = document.getElementById("cid").value;
        let pickup = document.getElementById("pickup").value;
        let drop = document.getElementById("drop").value;
        let time = parseInt(document.getElementById("time").value);
        let result = document.getElementById("result");

        
        if (cid === "" || isNaN(time)) {
            throw new Error("Customer ID and Pickup Time are mandatory");
        }

        let chosenTaxi = null;
        let minDistance = Infinity;

        taxis.forEach(taxi => {
            if (taxi.freeTime <= time) {
                let d = Math.abs(
                    taxi.currentPoint.charCodeAt(0) - pickup.charCodeAt(0)
                );

                if (
                    d < minDistance ||
                    (d === minDistance &&
                     chosenTaxi &&
                     taxi.totalEarnings < chosenTaxi.totalEarnings)
                ) {
                    minDistance = d;
                    chosenTaxi = taxi;
                }
            }
        });

        
        if (!chosenTaxi) {
            throw new Error("No Taxi Available at this time");
        }

        let dist = distance(pickup, drop);
        let tripTime = travelTime(pickup, drop);
        let fare = calculateFare(dist);
        let dropTime = time + tripTime;

        chosenTaxi.bookings.push({
            bookingId,
            cid,
            pickup,
            drop,
            time,
            dropTime,
            fare
        });

        chosenTaxi.totalEarnings += fare;
        chosenTaxi.currentPoint = drop;
        chosenTaxi.freeTime = dropTime;

        result.innerHTML = `✅ Taxi-${chosenTaxi.taxiId} is allotted`;
        result.className = "success";

        bookingId++;
        showTaxiDetails();

    } catch (e) {
        let result = document.getElementById("result");
        result.innerHTML = "❌ " + e.message;
        result.className = "error";
    }
}

function showTaxiDetails() {
    let html = "";

    taxis.forEach(taxi => {
        if (taxi.bookings.length === 0) return;

        html += `<h4>Taxi-${taxi.taxiId} | Total Earnings: Rs. ${taxi.totalEarnings}</h4>`;
        html += `
        <table>
            <tr>
                <th>BookingID</th>
                <th>CustomerID</th>
                <th>From</th>
                <th>To</th>
                <th>Pickup</th>
                <th>Drop</th>
                <th>Amount</th>
            </tr>`;

        taxi.bookings.forEach(b => {
            html += `
            <tr>
                <td>${b.bookingId}</td>
                <td>${b.cid}</td>
                <td>${b.pickup}</td>
                <td>${b.drop}</td>
                <td>${b.time}</td>
                <td>${b.dropTime}</td>
                <td>${b.fare}</td>
            </tr>`;
        });

        html += `</table><br>`;
    });

    document.getElementById("details").innerHTML = html;
}
</script>

</body>
</html>
