<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<html>
<head>
   <SCRIPT LANGUAGE="JavaScript" >
   var serchindex=0;
   var serchcounter=0;
   var serchstr = "";
   var serchwordcounter=0;
   var start=0;
   function wordserch(){
	   if(serchstr != document.serch.q.value){
		   serchindex=0;serchwordcounter=0;
		   }
	   serchstr = document.serch.q.value;
	   var bCrumb = new Array();
	   var i;
	   while(serchstr.indexOf('　')>=0){
		   serchstr = serchstr.replace("　"," ");
		   }
	   while(serchstr.indexOf('|')>=0){
		   serchstr = serchstr.replace("|"," ");
		   }
	   while(serchstr.indexOf('&')>=0){
		   serchstr = serchstr.replace("&"," ");
		   }
	   bCrumb=serchstr.split(" ");
	   for(i=serchwordcounter;i<bCrumb.length;i++){
		   serchstring = bCrumb[i];
		   serchcounter++;
		   ref=serch(serchstring);
		   if(!ref){
			   alert('検索単語が見つかりません');
			   }
		   if(ref==true && start>=0){
			   break;
			   }
		   else if(ref==true && start<0){
			   serchwordcounter++;
			   serchindex=0;
			   break;
			   }
		   else{
			   serchwordcounter++;
			   serchindex=0;
			   }
		   }
	   }
   function serch(txt,index){
	   var before,txt,after,objstr;kazu=0;
	   var serchstr=txt;
	   objct = document.getElementById('UR');
	   str = objct.innerHTML;
	   start = str.indexOf(serchstr,serchindex);
	   if(start>0){
		   before=str.substring(0,start);
		   txt="<B><A NAME='serchstr"+serchcounter+"'>"+serchstr+"</A></B>";
		   after=str.substring(start+serchstr.length);
		   objstr = before+txt+after;
		   if(navigator.appName.indexOf("Microsoft")>-1){
			   objct.innerHTML = objstr;
			   location.hash = "#serchstr"+serchcounter;
			   }
		   else{
			   document.clear();
			   document.open();
			   document.write(objstr);
			   document.close();
			   location.hash = "#serchstr";
			   }
		   serchindex=start+txt.length;
		   start = str.indexOf(serchstr,serchindex);
		   return true;
		   }
	   else{
		   return false;
		   }
   }
   </SCRIPT>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>掲示板</title>
 
    
</head>
	<body>
	    <h1>掲示板</h1>
    <h3>ご自由にどうぞ！</h3>
<DIV STYLE="text-align:right;vertical-align:baseline;font-size:10pt;">
	<FORM name=serch action="javascript:wordserch()">文章内から検索：
	<INPUT name=q><INPUT type=button onClick="wordserch()" value="検索">
	</FORM></DIV>

<s:form method="POST">
	
	メッセージ:<html:text property="message" size="50"/>
	<s:submit property="next" value="投稿"/>
	<s:submit property="update" value="更新"/>
	ようこそ、${f:h(name)}さん。
	<s:submit property="logout" value="Sign Out"/>
</s:form>
<html:errors/>
<DIV ID="UR">
<table border="1" align="center" width="90%">
<th>No.</th>
<th>日時</th>
<th>名前</th>
<th>テキスト</th>
<c:forEach var="board" items="${boardList}">
<tr>
	<td width="4%">${board.id}</td>
	<td width="20%">${board.posteddate}</td>
	<td width="16%">${board.name}</td>
<td width="61%">${board.message}</td>
</tr>
</c:forEach>
</table>

</DIV>
<c:if test="${hasPrev}">
	<a align="center" href="?page=${page-1}" >&lt;Previous</a>
</c:if>
<c:if test="${hasNext}">
	<a align="center" href="?page=${page+1}" >Next&gt;</a>
</c:if>
</body>
</html>
