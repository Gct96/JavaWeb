function editCart(cartItemId,buyCount){ //更新完后的buyCount
    window.location.href='cart.do?operate=editCart&cartItemId='+cartItemId+'&buyCount='+buyCount;
}