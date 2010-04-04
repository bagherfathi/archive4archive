<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<marquee scrollAmount="1" direction="up" height="300" onmouseover="stop()" onmouseout="start()">
	<c:set var="id" value="1"/>
	<c:forEach items="${affiches}" var="affiche">
		<a href="#" class="link-red" onClick="showDialog('<c:out value='${affiche.title}'/>',<c:out value='${affiche.afficheId}'/>,'500','400');" title="<c:out value='${affiche.title}'/>">
		<c:out value="${id}"/>¡¢[<webui:lookup code="affiche_level@SM_AFFICHE" value="${affiche.level}"/>]
		<c:if test="${affiche.level == 0}">
		    <sm:afficheTitle title="${affiche.title}" />
		</c:if>
		<c:if test="${affiche.level == 1}">
		    <b>
		    <sm:afficheTitle title="${affiche.title}" />
		    </b>
		</c:if>
		<c:if test="${affiche.level == 2}">
		    <font color="red">
		    <b>
		    <sm:afficheTitle title="${affiche.title}" />
		    </b>
		    </font>
		    <script language="javascript">
		       if(getCookie('<c:out value='${affiche.title}'/>_<c:out value='${opId}'/>')==null){
		           setCookie('<c:out value='${affiche.title}'/>_<c:out value='${opId}'/>','true');
		           showDialog('<c:out value='${affiche.title}'/>_<c:out value='${opId}'/>',<c:out value='${affiche.afficheId}'/>,'500','400');
		       }
		    </script>
		</c:if>
		</a>
		<br/>
		<c:set var="id" value="${id+1}"/>
	</c:forEach>
</marquee>

