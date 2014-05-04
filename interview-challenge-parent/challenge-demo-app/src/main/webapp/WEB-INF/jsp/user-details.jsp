<%@ include file="/WEB-INF/tags/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/layout.jsp">

 <s:layout-component name="body">    		

	<center/>
	<div>
		<table border="0">		
			<tr>
				<td>
					<strong>First Name</strong>
				</td>
				<td>
					${actionBean.user.firstName}
				</td>
			</tr>
			<tr>
				<td>
					<strong>Last Name</strong>
				</td>
				<td>
					${actionBean.user.lastName}
				</td>
			</tr>
			<tr>
				<td>
					<strong>Currency</strong>
				</td>
				<td>
					${actionBean.user.currency}
				</td>
			</tr>
			<tr valign="top">
				<td>
					<strong>FRIENDS</strong>
				</td>
				
				<c:if test="${empty actionBean.user.friends}">
					<td align="top">
						No friends
					</td>
				</c:if>
					
					<c:if test="${!empty actionBean.user.friends}">
						<td>
							<table border="0">		
								<tr><td><strong>First Name</strong></td><td><strong>Last Name</strong></td><td><strong>Currency</strong></td></tr>	
								<tr><td><hr/></td><td><hr/></td><td><hr/></td></tr>		
								<tr>			
									<c:forEach items="${actionBean.user.friends}" var="friend" varStatus = "loopStatus">					
										<tr>
											<td>
												<d:link href="/challenge/users/details/${friend.id}">${friend.firstName}</d:link>
											</td>
											<td>
												<d:link href="/challenge/users/details/${friend.id}">${friend.lastName}</d:link>
											</td>
											<td>${friend.currency}</td>
										</tr>
									</c:forEach>
							</table>				
						<td>
					</c:if>
			</tr>
			<tr valign="top">
				<td>
					<strong>PRODUCTS</strong>
				</td>
				<c:if test="${empty actionBean.user.products}">
					<td>
						No products
					</td>
				</c:if>
				
				<c:if test="${!empty actionBean.user.products}">
					<td>
							<table border="0">		
								<tr><td><strong>Sku</strong></td><td><strong>Price</strong></td><td><strong>Currency</strong></td></tr>
								<tr><td><hr/></td><td><hr/></td><td><hr/></td></tr>
								<c:forEach items="${actionBean.user.products}" var="product" varStatus = "loopStatus">								
									<tr>
										<td>
											${product.sku}
										</td>
										<td>
											${product.price}
										</td>
										<td>
											${product.currency}
										</td>
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

