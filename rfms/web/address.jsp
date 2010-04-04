<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<script src="c:out value='${pageContext.request.contextPath}'/js/sm/address.jsp"></script>
<form name="form">
<sm:regions/>
<sm:address formName="form" province="depository.address.state" 
	city="depository.address.city"
	district="depository.address.district" street="depository.address.regineKey"
	zone="depository.address.street3" community="depository.address.street4" 
	detail="depository.address.street5"/>
</form>