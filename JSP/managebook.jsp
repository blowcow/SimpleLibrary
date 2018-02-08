<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<title>Insert title here</title>
</head>
<body>
<h3>欢迎您${sessionScope.user.name}</h3>
<table>
<tr>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=booklist>所有图书</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=mybook>我的图书</a></td>
<td>图书管理</td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=manageuser>用户管理</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=privilege>权限管理</a></td>
</tr>
</table>
<table style="width: 1368px; height: 643px" align="center" frame="box" cellspacing="0" border="1">
<tr><td colspan="6"><h2><p align="center">图书管理</p></h2></td></tr>
<tr style="width: 1306px; " align="right"><td style="width: 431px; " colspan="6" align="right">请输入书的ID<input id="bookid" type="text" /><button onclick="querybook()">搜索</button> </td></tr>
<tr><td style="width: 252px; ">书名</td><td>作者</td><td>价格</td><td>数量</td><td>借阅人</td><td>确认修改</td></tr>
<tr id="booklist"></tr>
</table>
<script type="text/javascript">
function querybook() {
		var str = document.getElementById("bookid").value;
		var loader=new net.AjaxRequest("QueryBook?bookid="+str,deal,onerror,"GET");
}
function deal(){
	result=this.req.responseText;							//获取返回的检测结果
	document.getElementById("booklist").innerHTML=result;
}
function onerror() {
	alert("出错了");
}
function updatebook() {
		var id = document.getElementById("id").value;
		var name = document.getElementById("name").value;
		var author = document.getElementById("author").value;
		var price = document.getElementById("price").value;
		var count = document.getElementById("count").value;
		var loader=new net.AjaxRequest("UpdateBook?bookid="+id+"&name="+name+"&author="+author+"&price="+price+"&count="+count,updatedeal,onerror,"GET");
}
function updatedeal(){
	alert("修改成功");
}
</script>
</html>