const balance = document.getElementById("balance");
const incomeEl = document.getElementById("income");
const expenseEl = document.getElementById("expense");
const list = document.getElementById("list");
const form = document.getElementById("form");
const text = document.getElementById("text");
const amount = document.getElementById("amount");

// Get data from localStorage
let transactions = JSON.parse(localStorage.getItem("transactions")) || [];

// Add transaction
function addTransaction(e) {
  e.preventDefault();

  const transaction = {
    id: Date.now(),
    text: text.value,
    amount: +amount.value
  };

  transactions.push(transaction);
  updateLocalStorage();
  render();

  text.value = "";
  amount.value = "";
}

// Render UI
function render() {
  list.innerHTML = "";

  let income = 0, expense = 0;

  transactions.forEach(t => {
    const li = document.createElement("li");
    li.classList.add(t.amount > 0 ? "plus" : "minus");

    li.innerHTML = `
      ${t.text} <span>₹${t.amount}</span>
      <button class="delete-btn" onclick="removeTransaction(${t.id})">x</button>
    `;

    list.appendChild(li);

    if (t.amount > 0) income += t.amount;
    else expense += t.amount;
  });

  const total = income + expense;

  balance.textContent = `₹${total}`;
  incomeEl.textContent = `₹${income}`;
  expenseEl.textContent = `₹${Math.abs(expense)}`;
}

// Remove transaction
function removeTransaction(id) {
  transactions = transactions.filter(t => t.id !== id);
  updateLocalStorage();
  render();
}

// Save to localStorage
function updateLocalStorage() {
  localStorage.setItem("transactions", JSON.stringify(transactions));
}

form.addEventListener("submit", addTransaction);

// Initial render
render();