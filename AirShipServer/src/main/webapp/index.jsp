
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<title>獲取ip以及犯罪紀錄</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Shadow Login Form template Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login sign up Responsive web template, SmartPhone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />

<script type="text/javascript" language="javascript">
<!--
function GetLocalIPAddress()
{
var obj = null;
var rslt = "";
try
{
obj = new ActiveXObject("rcbdyctl.Setting");
rslt = obj.GetIPAddress;
obj = null;
}
catch(e)
{
//异常发生
}
return rslt;
}
function getMac(){
var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
var service = locator.ConnectServer(".");
var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");
var e = new Enumerator (properties);
{
var p = e.item();
var mac = p.MACAddress;
return mac
}
}
//-->
function init () {
var ip = GetLocalIPAddress();
var mac = getMac();
fm.clientIP.value = ip;
fm.clientMAC.value = mac;
} 
</script>
</head>
<body onload="init();">
<form name = 'fm'>
通过JavaScript获取的IP信息：<input type ='text' name = 'clientIP'><br>
通过JavaScript获取的MAC信息：<input type='text'name = 'clientMAC'> 
通过JSP获取的IP信息：<%= request.getRemoteAddr() %>
</form>
</body>
</html>