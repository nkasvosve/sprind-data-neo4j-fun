

<%@ include file="/WEB-INF/tags/taglibs.jsp"%>

<s:layout-definition>

<!DOCTYPE html> 
<html>  
	<head> 
		<title>Dr Dobbs Interview Challenge - Just for fun</title>
		
		<s:layout-component name="head">
		</s:layout-component>
    </head> 
<body>
	<div class="bodyBackground">			
		<div class="container">

	 		<s:layout-component name="body">
			</s:layout-component>
	
	 		<s:layout-component name="footer">
	 			<jsp:include page="footer.jsp"/>
			</s:layout-component>
				
		<div class="clearBoth"></div>				
	</div>		
</div>		
</body>
</html>
</script> 
</s:layout-definition>

