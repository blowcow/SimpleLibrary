<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>Insert title here</title>
</head>
<body>
<h3>欢迎您${sessionScope.user.name}</h3>
<table>
<tr>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=booklist>所有图书</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=mybook>我的图书</a></td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=managebook>图书管理</a></td>
<td>用户管理</td>
<td><a href=http://localhost:8080/SSH/ShowAction?menuitem=privilege>权限管理</a></td>
</tr>
</table>
<table style="width: 907px; height: 268px; border-top-width: medium; border-right-width: medium; border-bottom-width: medium; background-position: center center; border-left-width: medium; border-top-style: dotted; text-align: center; border-right-style: dotted; border-left-style: dotted; border-bottom-style: dotted">
<tr><td><h2><p align="center">图书管理</p></h2></td></tr>
<tr><td style="width: 252px; ">用户名</td><td>密码</td><td>问题</td><td>答案</td><td>身份</td></tr>
<s:iterator value="pageList" var="user" status="status">
	<tr>
	<td><s:property value="#user.name"/></td>
	<td><s:property value="#user.pass"/></td>
	<td><s:property value="#user.question"/></td>
	<td><s:property value="#user.answer"/></td>
	<td>
	<select id="${user.userid}" name="role" value="${user.role.roleid}">
	<option <s:if test="#user.role.roleid==1">selected="selected"</s:if>>游客</option>
	<option <s:if test="#user.role.roleid==2">selected="selected"</s:if>>注册用户</option>
	<option <s:if test="#user.role.roleid==3">selected="selected"</s:if>>管理员</option>
	<option <s:if test="#user.role.roleid==4">selected="selected"</s:if>>超级管理员</option>
	</select>
	</td>
	<td><button value=${user.userid} onclick="changerole(this.value)">修改</button></td>
	</tr>
</s:iterator>
</table>
</body>
<script type="text/javascript">
function changerole(id) {
		var str = document.getElementById(id).value;
		var loader=new net.AjaxRequest("ChangeRole?userid="+id+"&user.role.name="+str,deal,onerror,"POST");
}
function deal(){
 	alert("修改成功");
}
function onerror() {
	alert("出错了");
}
</script>
</html>