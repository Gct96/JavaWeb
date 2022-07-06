window.onload=function (){
    updateZJ();
    var fruitTb1=document.getElementById("tbl_fruit");
    //获取表中所有的行
    var rows=fruitTb1.rows;
    for(var i=1;i<rows.length-1;i++){
        var tr=rows[i];
        tr.onmouseover=showBGColor;
        tr.onmouseout=clearBGColor;
        var cells=tr.cells;
        var priceTD=cells[1];
        priceTD.onmouseover=showHand;
        //绑定鼠标点击单价单元格的事件
        priceTD.onclick=editPrice;
        var img=cells[4].firstChild;
        if(img&&img.tagName=="IMG"){
            img.onclick=delFruit;
        }
    }
}

function delFruit(){
    if(event&&event.srcElement&&event.srcElement.tagName=="IMG"){
        if(window.confirm("是否确认删除当前库存记录")) {
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTbl = document.getElementById("tbl_fruit");
            fruitTbl.deleteRow(tr.rowIndex);

            updateZJ();
        }
    }
}

function editPrice(){
    if(event&&event.srcElement&&event.srcElement.tagName=="TD"){
        var priceTD=event.srcElement;
        if(priceTD.firstChild&&priceTD.firstChild.nodeType==3){
            var oldPrice=priceTD.innerText;
            priceTD.innerHTML="<input type='text' size='4'/>";
            var input=priceTD.firstChild;
            if(input.tagName=="INPUT"){
                input.value=oldPrice;
                input.select();
                input.onblur=updatePrice;
            }
        }
    }
}

function updatePrice(){
    if(event&&event.srcElement&&event.srcElement.tagName=="INPUT"){
        var input=event.srcElement;
        var newPrice=input.value;
        var priceTD=input.parentElement;
        priceTD.innerText=newPrice;

        //更新当前行的小计这一个格子的值
        updateXJ(priceTD.parentElement);
    }
}

//更新小计
function updateXJ(tr){
    if(tr&&tr.tagName=="TR"){
        var tds=tr.cells;
        var price=tds[1].innerText;
        var count=tds[2].innerText;
        var xj=parseInt(price)*parseInt(count);
        tds[3].innerText=xj;

        updateZJ();
    }
}
//更新总计
function updateZJ(){
    var fruitTbl=document.getElementById("tbl_fruit");
    var rows=fruitTbl.rows;
    var sum=0;
    for(var i=1;i<rows.length-1;i++){
        var tr=rows[i];
        var xj=parseInt(tr.cells[3].innerText);
        sum=sum+xj;
    }
    rows[rows.length-1].cells[1].innerText=sum;
}




//当鼠标悬浮时，显示背景颜色
function showBGColor(){
    //event : 当前发生的事件
    //event.srcElement : 事件源
    //alert(event.srcElement);
    //alert(event.srcElement.tagName);	--> TD
    if(event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement ;
        //td.parentElement 表示获取td的父元素 -> TR
        var tr = td.parentElement ;
        //如果想要通过js代码设置某节点的样式，则需要加上 .style
        tr.style.backgroundColor = "navy" ;

        //tr.cells表示获取这个tr中的所有的单元格
        var tds = tr.cells;
        for(var i = 0 ; i<tds.length ; i++){
            tds[i].style.color="white";
        }
    }
}

//当鼠标离开时，恢复原始样式
function clearBGColor(){
    if(event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement ;
        var tr = td.parentElement ;
        tr.style.backgroundColor="transparent";
        var tds = tr.cells;
        for(var i = 0 ; i<tds.length ; i++){
            tds[i].style.color="threeddarkshadow";
        }
    }
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(){
    if(event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement ;
        //cursor : 光标
        td.style.cursor="hand";
    }

}