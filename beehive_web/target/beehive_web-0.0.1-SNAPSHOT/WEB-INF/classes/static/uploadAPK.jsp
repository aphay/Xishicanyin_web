<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安装包上传测试</title>
<style type="text/css">
body{text-align:center}
</style>
</head>
<body>
        <p><a href="<%=request.getContextPath()%>/apk/list">查看所有版本</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/apk/download">下载最新版本</a></p><br/>
        <c:if test="${!empty msg}">${msg}<br/></c:if>
         <form action="<%=request.getContextPath()%>/uploadAPK" method="post" enctype="multipart/form-data">
         <table align="center" valign="middle">
                <tr><td>APK：</td><td><input name="apkFile" id="apkFile" type="file" /></td></tr>
                <tr><td>version：</td><td><input type="text" name="version" />（数字）如：1 </td></tr>
                <tr><td>showVersion：</td><td><input type="text" name="showVersion" />（字符） 如：1.0 </td></tr>
                <tr><td>introduction：</td><td><input type="text" name="introduction" /> 更新内容介绍</td></tr>
                <tr><td colspan="2"><input type="submit" value="提交"></td></tr>
        </table>
        </form>
</body>
</html>