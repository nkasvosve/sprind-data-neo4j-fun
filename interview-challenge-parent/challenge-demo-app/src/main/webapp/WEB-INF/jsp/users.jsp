<%@ include file="/WEB-INF/tags/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/layout.jsp">

 <s:layout-component name="body">    		

	<center/>
	<div>
		<table border="0">		
			<tr><td>First Name</td><td>Last Name</td></tr>			
			<tr><td><hr/></td><td><hr/></td></tr>
			<tr>			
				<c:forEach items="${actionBean.users}" var="user" varStatus = "loopStatus">					
					<tr>
						<td>
							<d:link href="/challenge/users/details/${user.id}">${user.firstName} </d:link>
						</td>
						<td>
							<d:link href="/challenge/users/details/${user.id}">${user.lastName}</d:link>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</div>
	
  </s:layout-component>
</s:layout-render>

