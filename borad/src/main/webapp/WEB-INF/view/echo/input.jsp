<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>掲示板</title>
</head>
	<body>
	    <h1>掲示板</h1>
    <h2>ご自由にどうぞ！</h2>
    <div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="false"></div>


<s:form method="POST">
	名前:<html:text property="name" size="10"/>
	メッセージ:<html:text property="message" size="50"/>
	<s:submit property="next" value="投稿"/>
	<s:submit property="update" value="更新"/>
	
</s:form>
<html:errors/>

<table border="1" align="center" width="80%">
<th>日時</th>
<th>名前</th>
<th>テキスト</th>
<c:forEach var="echoForm" items="${echoList}">
<tr>
	<td width="20%"></td>
	<td width="10%">${echoForm.name}</td>
<td width="50%"></td>
</tr>
</c:forEach>

</table>

</body>
</html>
