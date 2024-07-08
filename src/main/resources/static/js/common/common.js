function goBack() {
    window.history.back();
}

function goOrderList() {
    if (auth == null) {
        location.href = "/login/loginForm";
    } else {
        location.href = "/purchase/orderList";
    }
};

function goCartList() {
    if (auth == null) {
        location.href = "/login/loginForm";
    } else {
        location.href = "/corner/cartList";
    }
};