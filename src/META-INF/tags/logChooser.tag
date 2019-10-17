<%@ taglib uri="http://iliframework.univ-artois.fr/" prefix="ili" %>
<form action="LogController">
<select name="loglevel">
<ili:loglevels var="aLevel">
<option ${aLevel eq applicationLevel?'selected=selected':''}>${aLevel}</option>
</ili:loglevels>
</select>
<input type="submit"/>
</form>