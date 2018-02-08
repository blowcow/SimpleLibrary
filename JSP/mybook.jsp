<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="pragma" CONTENT="no-cache">

<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<meta HTTP-EQUIV="expires" CONTENT="0">
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<title>Insert title here</title>
</head>
<body>
<h3>欢迎您${sessionScope.user.name}</h3>
<table>
<tr>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=booklist>所有图书</a></td>
<td>我的图书</td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=managebook>图书管理</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=manageuser>用户管理</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=privilege>权限管理</a></td>
</tr>
</table>
<table style="width: 907px; height: 268px; border-top-width: medium; border-right-width: medium; border-bottom-width: medium; background-position: center center; border-left-width: medium; border-top-style: dotted; text-align: center; border-right-style: dotted; border-left-style: dotted; border-bottom-style: dotted">
<tr><td><h2><p align="center">我的图书</p></h2></td></tr>
<tr><td style="width: 252px; ">书名</td><td>作者</td><td>价格</td><td></td></tr>
<s:iterator value="pageList" var="book" status="status">
	<tr>
	<td><s:property value="#book.name"/></td>
	<td><s:property value="#book.author"/></td>
	<td><s:property value="#book.price"/></td>
	<td><button value=${book.bookid} onclick="backbook(this.value)">归还</button></td>
	<td id=${book.bookid}></td>
	</tr>
</s:iterator>
</table>
</body>
<script type="text/javascript">
function backbook(str) {
		var loader=new net.AjaxRequest("BackBook?bookid="+str,deal,onerror,"GET");
}
function deal(){
	result=this.req.responseText;							//获取返回的检测结果
	result=result.replace(/\s/g,"");								//去除Unicode空白符
	alert(result);
}
function onerror() {
	alert("出错了");
}
</script>
</html>