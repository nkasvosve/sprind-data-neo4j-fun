<%@ include file="/WEB-INF/tags/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/layout.jsp">

 <s:layout-component name="body">    		

	<center/>
	<div>
		<table border="0">		
			<tr><td><strong>Name</strong></td><td><strong>Price</strong></td><td><strong>Currency</strong></td></tr>	
			<tr><td><hr/></td><td><hr/></td><td><hr/></td></tr>		
			<tr>			
				<c:forEach items="${actionBean.products}" var="product" varStatus = "loopStatus">					
					<tr>
						<td>
							<d:link href="/challenge/products/details/${product.id}">${product.sku} </d:link>
						</td>
						<td>
							${product.price}
						</td>
						<td>
							${product.currency}
						</td>
					</tr>
				</c:forEach>
			</tr>
		</table>
	</div>
	
  </s:layout-component>
</s:layout-render>

