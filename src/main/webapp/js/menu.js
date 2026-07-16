function plus(button) {

    const count = button.previousElementSibling;

    count.innerText = Number(count.innerText) + 1;

}

function minus(button) {

    const count = button.nextElementSibling;

    let value = Number(count.innerText);

    if (value > 1) {
        count.innerText = value - 1;
    }

}

function addCart(button, menuId) {

    const quantity = button
        .closest("tr")
        .querySelector(".quantity")
        .innerText;

    const form = document.createElement("form");

    form.method = "post";
    form.action = contextPath + "/cart/add.do";

    const menuIdInput = document.createElement("input");
    menuIdInput.type = "hidden";
    menuIdInput.name = "menuId";
    menuIdInput.value = menuId;

    const quantityInput = document.createElement("input");
    quantityInput.type = "hidden";
    quantityInput.name = "quantity";
    quantityInput.value = quantity;

    form.appendChild(menuIdInput);
    form.appendChild(quantityInput);

    document.body.appendChild(form);

    form.submit();
}