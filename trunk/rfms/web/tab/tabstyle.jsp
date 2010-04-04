<%@ page contentType="text/css" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="userAgent" value="${header['user-agent']}"/>
<c:set var="isMac" value="false"/>
<c:set var="isMacIE" value="false"/>



/******************************************************************************
********************************   wireframe   ********************************
******************************************************************************/

.ditch-tab-skin-wireframe .ditchnet-tab-container {
			margin:10px;
			border-bottom: 1px solid gray;
			border-left:   1px solid gray;
			}
	
.ditch-tab-skin-wireframe .ditch-tab-wrap {
			
			}
	
.ditch-tab-skin-wireframe .ditch-tab {
			float:left;
			padding:2px 10px 2px;
			border-top:  1px solid gray;
			border-right:1px solid gray;
			cursor:pointer;
			}
	
.ditch-tab-skin-wireframe .ditch-tab-wrap .ditch-unfocused {
			color:gray;
			background-color:white;
			}
	
.ditch-tab-skin-wireframe .ditch-tab-wrap .ditch-focused {
			color:black;
			background-color:silver;
			}

.ditch-tab-skin-wireframe .ditch-tab-pane-wrap {
			padding:8px;
			border-top:    1px solid gray;
			border-right:  1px solid gray;
			}

.ditch-tab-skin-wireframe .ditch-tab-pane {
			
			}
	
.ditch-tab-skin-wireframe br.ditch-clear {
			clear:both;
			}
			
			
/******************************************************************************
******************************   INVISIBLE   **********************************
******************************************************************************/

.ditch-tab-skin-invisible .ditchnet-tab-container {
			margin:10px;
			border:0;
			}
	
.ditch-tab-skin-invisible .ditch-tab-wrap {
			display:none;
			border:0;
			}
	
.ditch-tab-skin-invisible .ditch-tab {

			}
	
.ditch-tab-skin-invisible .ditch-tab-wrap .ditch-unfocused {
			border:0;
			}
	
.ditch-tab-skin-invisible .ditch-tab-wrap .ditch-focused {
			border:0;
			}

.ditch-tab-skin-invisible .ditch-tab-pane-wrap {
			border:0;
			padding:8px;
			}

.ditch-tab-skin-invisible .ditch-tab-pane {
			border:0;
			}
	
.ditch-tab-skin-invisible br.ditch-clear {
			clear:both;
			}
			
			
/******************************************************************************
********************************   default   **********************************
******************************************************************************/
.ditch-tab-skin-default {
			padding-top: 10px;
			/* padding-left: 10px; */
	        /* padding-right: 10px; */
	        width: 95%;
			}


.ditch-tab-skin-default .ditchnet-tab-container {
			margin:0px;
			}
	
.ditch-tab-skin-default .ditch-tab-wrap {
			position:relative;
			z-index:10;  
			width:100%;
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}
	
.ditch-tab-skin-default .ditch-tab {
			position:relative;
			float:left;
			padding: 3px 9px;
			height:25px;
			margin:0 ;
			text-align:center;
			cursor:pointer;		
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}

.ditch-tab-skin-default .ditch-tab-bg-left {
			position:absolute;
			right:0; top:0;
			width:10px; height:25px;
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_right.gif) right no-repeat;
			}
html>body .ditch-tab-skin-default .ditch-tab-bg-left {
<c:choose>
	<c:when test="${isMac}">
			height:19px;
	</c:when>
	<c:otherwise>
			height:25px;
	</c:otherwise>
</c:choose>
			}



.ditch-tab-skin-default .ditch-unfocused {
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:#eee;
		</c:when>
		<c:otherwise>
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left.gif) left no-repeat;
		</c:otherwise>
	</c:choose>
			}

.ditch-tab-skin-default .ditch-focused {
	       
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:white;
		</c:when>
		<c:otherwise>
			background-image: url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left_selected.gif) ;
		</c:otherwise>
	</c:choose>
			}

<c:if test="${not isMacIE}">/*
.ditch-tab-skin-default .ditch-unfocused .ditch-tab-bg-left{
    background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left.gif) left  no-repeat;
			}
.ditch-tab-skin-default .ditch-focused .ditch-tab-bg-left {
			}
</c:if>*/


.ditch-tab-skin-default .ditch-tab a:link,
.ditch-tab-skin-default .ditch-tab a:visited {
			color:black;
			text-decoration:none;
			}
.ditch-tab-skin-default .ditch-tab-wrap .ditch-unfocused a:link,
.ditch-tab-skin-default .ditch-tab-wrap .ditch-unfocused a:visited {
			color:black;
			}


.ditch-tab-skin-default .ditch-tab-pane-wrap {
			position:relative;
			z-index:9;
			border:1px solid #65B3F9;
			padding:8px;
			width:100%;
			}

.ditch-tab-skin-default .ditch-tab-pane {
			
			}
	
.ditch-tab-skin-default br.ditch-clear {
			clear:both;
			}

			/******************************************************************************
***********************   newlevel1 --add by yuehong   ************************
******************************************************************************/
.ditch-tab-skin-newlevel1 {
			padding-top: 10px;
			/* padding-left: 10px; */
	        /* padding-right: 10px; */
	        width: 95%;
			}


.ditch-tab-skin-newlevel1 .ditchnet-tab-container {
			margin:0px;
			}
	
.ditch-tab-skin-newlevel1 .ditch-tab-wrap {
			position:relative;
			z-index:10;  
			width:100%;
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}
	
.ditch-tab-skin-newlevel1 .ditch-tab {
			position:relative;
			float:left;
			padding: 3px 9px;
			height:25px;
			margin:0 ;
			text-align:center;
			cursor:pointer;		
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}

.ditch-tab-skin-newlevel1 .ditch-tab-bg-left {
			position:absolute;
			right:0; top:0;
			width:10px; height:25px;
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_right.gif) right no-repeat;
			}
html>body .ditch-tab-skin-newlevel1 .ditch-tab-bg-left {
<c:choose>
	<c:when test="${isMac}">
			height:19px;
	</c:when>
	<c:otherwise>
			height:19px;
	</c:otherwise>
</c:choose>
			}



.ditch-tab-skin-newlevel1 .ditch-unfocused {
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:#eee;
		</c:when>
		<c:otherwise>
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left.gif) left no-repeat;
		</c:otherwise>
	</c:choose>
			}

.ditch-tab-skin-newlevel1 .ditch-focused {
	       
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:white;
		</c:when>
		<c:otherwise>
			background-image: url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left_selected.gif) ;
		</c:otherwise>
	</c:choose>
			}

<c:if test="${not isMacIE}">/*
.ditch-tab-skin-newlevel1 .ditch-unfocused .ditch-tab-bg-left{
    background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left.gif) left  no-repeat;
			}
.ditch-tab-skin-newlevel1 .ditch-focused .ditch-tab-bg-left {
			}
</c:if>*/


.ditch-tab-skin-newlevel1 .ditch-tab a:link,
.ditch-tab-skin-newlevel1 .ditch-tab a:visited {
			color:black;
			text-decoration:none;
			}
.ditch-tab-skin-newlevel1 .ditch-tab-wrap .ditch-unfocused a:link,
.ditch-tab-skin-newlevel1 .ditch-tab-wrap .ditch-unfocused a:visited {
			color:black;
			}


.ditch-tab-skin-newlevel1 .ditch-tab-pane-wrap {
			position:relative;
			z-index:9;
			border:0px solid #65B3F9;
			padding:0px;
			width:100%;
			}

.ditch-tab-skin-newlevel1 .ditch-tab-pane {

			}
	
.ditch-tab-skin-newlevel1 br.ditch-clear {
			clear:both;
			}
			
.ditch-tab-skin-newlevel1 .ditch-tab-div-style {
			position:relative;
			z-index:9;
			border:1px solid #65B3F9;
			padding:8px;
			width:100%;

			}

/******************************************************************************
************************   newlevel2 --add by yuehong   ***********************
******************************************************************************/
.ditch-tab-skin-newlevel2 {
			padding-top: 0px; 
	        /* padding-right: 10px; */
	        width: 100%;
			}


.ditch-tab-skin-newlevel2 .ditchnet-tab-container {
			margin:0px;
			}
	
.ditch-tab-skin-newlevel2 .ditch-tab-wrap {
			position:relative;
			padding-left:2px;
			z-index:10;  
			width:100%;
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}
	
.ditch-tab-skin-newlevel2 .ditch-tab {
			position:relative;
			float:left;
			padding: 0px 6px;
			height:19px;
			margin:0 ;
			text-align:center;
			cursor:pointer;
		/*	font:12px "Lucida Grande",LucidaGrande,Verdana,sans-serif;*/
			}

.ditch-tab-skin-newlevel2 .ditch-tab-bg-left {
			position:absolute;
			right:0; top:0;
			width:3px; height:19px;
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_right1.gif) right no-repeat;
			}
html>body .ditch-tab-skin-newlevel2 .ditch-tab-bg-left {
<c:choose>
	<c:when test="${isMac}">
			height:19px;
	</c:when>
	<c:otherwise>
			height:19px;
	</c:otherwise>
</c:choose>
			}



.ditch-tab-skin-newlevel2 .ditch-unfocused {
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:#eee;
		</c:when>
		<c:otherwise>
			background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left1.gif) left no-repeat;
		</c:otherwise>
	</c:choose>
			}

.ditch-tab-skin-newlevel2 .ditch-focused {
	       
	<c:choose>
		<c:when test="${isMacIE}">
			background-color:white;
		</c:when>
		<c:otherwise>
			background-image: url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left1_selected.gif) ;
		</c:otherwise>
	</c:choose>
			}

<c:if test="${not isMacIE}">/*
.ditch-tab-skin-newlevel2 .ditch-unfocused .ditch-tab-bg-left{
    background:url(<c:out value="${pageContext.request.contextPath}" />/images/tab_left1.gif) left  no-repeat;
			}
.ditch-tab-skin-newlevel2 .ditch-focused .ditch-tab-bg-left {
			}
</c:if>*/


.ditch-tab-skin-newlevel2 .ditch-tab a:link,
.ditch-tab-skin-newlevel2 .ditch-tab a:visited {
			color:black;
			text-decoration:none;
			}
.ditch-tab-skin-newlevel2 .ditch-tab-wrap .ditch-unfocused a:link,
.ditch-tab-skin-newlevel2 .ditch-tab-wrap .ditch-unfocused a:visited {
			color:black;
			}


.ditch-tab-skin-newlevel2 .ditch-tab-pane-wrap {
			position:relative;
			z-index:9;
			border:0px solid #65B3F9;
			padding:0px;
			width:100%;
			}

.ditch-tab-skin-newlevel2 .ditch-tab-pane {
			position:relative;
			z-index:9;
			border:1px solid #65B3F9;
			padding:8px;
			width:100%;
			}
	
.ditch-tab-skin-newlevel2 br.ditch-clear {
			clear:both;
			}