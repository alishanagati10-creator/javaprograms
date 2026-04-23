const panels = document.querySelectorAll(".panel");
let currentActive = 0;

// Set active panel function
function setActivePanel() {
  panels.forEach((panel) => panel.classList.remove("active"));
  panels[currentActive].classList.add("active");
}

// Auto slide function
function nextSlide() {
  currentActive++;
  if (currentActive >= panels.length) {
    currentActive = 0;
  }
  setActivePanel();
}

// Start auto sliding
let interval = setInterval(nextSlide, 1000);

// Click functionality (manual override)
panels.forEach((panel, index) => {
  panel.addEventListener("click", () => {
    currentActive = index;
    setActivePanel();

    // restart timer when user clicks
    clearInterval(interval);
    interval = setInterval(nextSlide, 1000);
  });
});
