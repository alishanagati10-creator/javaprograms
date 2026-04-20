let count = 0;

// Live Clock
function updateClock() {
  const now = new Date();

  let hours = now.getHours();
  let minutes = now.getMinutes();
  let seconds = now.getSeconds();

  let ampm = hours >= 12 ? "PM" : "AM";
  hours = hours % 12 || 12;

  hours = String(hours).padStart(2, "0");
  minutes = String(minutes).padStart(2, "0");
  seconds = String(seconds).padStart(2, "0");

  document.getElementById("clock").innerText =
    `${hours}:${minutes}:${seconds} ${ampm}`;
}

// Run every second
setInterval(updateClock, 1000);

// Counter functions
function increase() {
  count++;
  updateCount();
}

function decrease() {
  count--;
  updateCount();
}

function reset() {
  count = 0;
  updateCount();
}

function updateCount() {
  const countElement = document.getElementById("count");
  countElement.innerText = count;

  // Change color based on value
  if (count > 0) {
    countElement.style.color = "green";
  } else if (count < 0) {
    countElement.style.color = "red";
  } else {
    countElement.style.color = "black";
  }
}
