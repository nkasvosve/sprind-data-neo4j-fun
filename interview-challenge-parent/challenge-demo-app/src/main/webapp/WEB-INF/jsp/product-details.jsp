<%@ include file="/WEB-INF/tags/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/layout.jsp">

 <s:layout-component name="body">    		

	<center/>
	<div>
		<table border="0">		
			<tr><td>Name</td><td>Price</td><td>Currency</td></tr>		
			<tr><td><hr/></td><td><hr/></td><td><hr/></td></tr>						
			<tr>
				<td>
					${actionBean.product.sku}
				</td>
				<td>
					${actionBean.product.price}
				</td>
				<td>
					${actionBean.product.currency}
				</td>
			</tr>
			<tr valign="top">
				<td>
				<br/>
					<strong>BOUGHT BY</strong>
				</td>
				
				<c:if test="${empty actionBean.product.users}">
					<td>
						Not yet bought
					</td>
				</c:if>
					
					<c:if test="${!empty actionBean.product.users}">
						<td>
							<table border="0">		
								<tr><td><strong>First Name</strong></td><td><strong>Last Name</strong></td><td><strong>Currency</strong></td></tr>	
								<tr><td><hr/></td><td><hr/></td><td><hr/></td></tr>		
								<tr>			
									<c:forEach items="${actionBean.product.users}" var="user" varStatus = "loopStatus">					
										<tr>
											<td>
												<d:link href="/challenge/users/details/${user.id}">${user.firstName} </d:link>
											</td>
											<td>
												<d:link href="/challenge/users/details/${user.id}">${user.lastName}</d:link>
											</td>
											<td>${user.currency}</td>
										</tr>
									</c:forEach>
							</table>				
						<td>
					</c:if>
			</tr>
			
		</table>
	</div>
	
  </s:layout-component>
</s:layout-render>

