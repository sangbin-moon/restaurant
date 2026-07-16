function updateCartSession(menuId, quantity) {

    fetch("cart/update.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body:
            "menuId=" + encodeURIComponent(menuId)
            + "&quantity=" + encodeURIComponent(quantity)
    })
    .then(response => response.text())
    .then(result => {
        if (result !== "success") {
            alert("장바구니 수량 변경에 실패했습니다.");
        }
    })
    .catch(error => {
        console.error(error);
        alert("장바구니 수량 변경 중 오류가 발생했습니다.");
    });
}


function cartPlus(button) {

    const row = button.closest("tr");

    const quantityElement = row.querySelector(".cartQuantity");
    const priceElement = row.querySelector(".price");

    const menuId = row.dataset.menuId;

    let quantity = Number(quantityElement.innerText);

    quantity++;

    quantityElement.innerText = quantity;

    const unitPrice = Number(priceElement.dataset.unitPrice);

    priceElement.innerText = unitPrice * quantity;

    updateCartSession(menuId, quantity);
}


function cartMinus(button) {

    const row = button.closest("tr");

    const quantityElement = row.querySelector(".cartQuantity");
    const priceElement = row.querySelector(".price");

    const menuId = row.dataset.menuId;

    let quantity = Number(quantityElement.innerText);

    if (quantity > 1) {
        quantity--;
    } else {
        return;
    }

    quantityElement.innerText = quantity;

    const unitPrice = Number(priceElement.dataset.unitPrice);

    priceElement.innerText = unitPrice * quantity;

    updateCartSession(menuId, quantity);
}

