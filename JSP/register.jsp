<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="pragma" CONTENT="no-cache">

<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<meta HTTP-EQUIV="expires" CONTENT="0">
<title>Insert title here</title>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
</head>
<body style="background-color: Silver; ">
<form action="register" method="POST" style="width: auto; clear: both; height: auto">

<table style="width: 290px; "><tr id="tip"></tr></table><br>
<div id="box" style="margin-left: auto; width: auto; clear: both; margin-top: auto; margin-bottom: auto; height: auto; margin-right: auto">
名字<input name="name" type="text" onblur="testname(this.value)" style="width: 189px; ">
<br><br>密码<input name="pass" type="text" style="width: 188px; "><br>
<br>问题<textarea name="question" rows="2" cols="30" style="width: 484px; height: 27px"></textarea><br><br>
答案<textarea name="answer" rows="2" cols="30" style="width: 484px; height: 28px"></textarea><br><br>
<a href="index.html">登录</a>
<input type="submit" value="注册">
</form>
</div>
</body>
<script type="text/javascript">
function testname(str) {
	if(str!=null&&str!=""){
		var loader=new net.AjaxRequest("testname?name="+str,deal,onerror,"GET");
	}
}
function deal(){
	result=this.req.responseText;							//获取返回的检测结果
	result=result.replace(/\s/g,"");								//去除Unicode空白符
	document.getElementById("tip").innerHTML=result;		//设置提示文字
	document.getElementById("tip").style.display='block';
}
function onerror() {
	alert("出错了");
}
</script>
</html>