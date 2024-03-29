<%-- Stripes TLD --%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="d" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>


<%-- JSTL TLDs --%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- This is so that you can conveniently refer to the context path with ${contextPath} --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="oddRowColor" value="#F5F5F5"/>
<c:set var="evenRowColor" value="#F0F8FF"/>


