function $(id){
    return document.getElementById(id);
}


function preRegist(){

    var unameTxt = $("unameTxt");
    //用户名应为6~16位数字和字母组成
    var unameReg=/[0-9a-zA-Z]{6,16}/gim;
    var uname=unameTxt.value;
    var unameSpan=$("unameSpan");
    if (!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false;
    }else{
        unameSpan.style.visibility="hidden";
    }

    //密码的长度至少为8位
    var pwdTxt=$("pwdTxt");
    var pwd=pwdTxt.value;
    var pwdReg=/[\w]{8,}/gim;
    var pwdSpan=$("pwdSpan");
    if (!pwdReg.test(pwd)){
        pwdSpan.style.visibility="visible";
        return false;
    }else{
        pwdSpan.style.visibility="hidden";
    }

    //密码两次输入不一致
    if ($("pwdTxt2").value !=$("pwdTxt").value){
        $("pwdSpan2").style.visibility="visible";
        return false;
    }else{
        $("pwdSpan2").style.visibility="hidden";
    }

    //请输入正确的邮箱格式
    var email=$("emailTxt").value;
    var emailSpan=$("emailSpan");
    var emailReg=/^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/
    if (!emailReg.test(email)){
        emailSpan.style.visibility="visible";
        return false;
    }else{
        emailSpan.style.visibility="hidden";
    }

    return true;
}


var xmlHttpRequest;

function createXMLHttpRequest(){
    if (window.xmlHttpRequest){//DOM2标准的浏览器
        xmlHttpRequest = new XMLHttpRequest();
    }else if (window.ActiveXObject){ //IE浏览器
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxm12.XMLHTTP");
        }

    }
}
function ckUname(uname){
    createXMLHttpRequest();
    var url="user.do?operate=ckUname&uname="+uname;
    xmlHttpRequest.open("GET",url,true);
    //设置回调函数ckUnameCB
    xmlHttpRequest.onreadystatechange=ckUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    //console.log()
    if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        //服务器端响应给客户端的文本内容
        //alert(xmlHttpRequest.responseText);
        var responseText=xmlHttpRequest.responseText;
        if (responseText=="{'uname':'1'}"){
            alert("用户名已被注册");
        }else{
            alert("用户名可以注册");
        }

    }

}