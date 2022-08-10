<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
	<tr>
		<td height="10" width="20">&nbsp;</td>
		<td width="530" valign="top">
		<!-- 메뉴 시작 -->
			<c:if test="${principal.getBlog().id == blog.id}">
			<a href="/blog/getBlog/${blog.id}"><b>기본설정</b></a>&nbsp;&nbsp;
				<a href="/category/list/${blog.id}"><b>카테고리</b></a>&nbsp;&nbsp;
				<b>글작성</b>&nbsp;&nbsp;
				<a href="javascript:popup();"><b>블로그삭제</b></a>&nbsp;&nbsp;
			</c:if>
		<!-- 메뉴 끝 -->
		</td>
	</tr>
	<tr><td height="5">&nbsp;</td></tr>
	<tr><td height="10">&nbsp;</td>
		<td>
		
			<!-- 포스트 수정화면 시작 -->
			<form action="/post/update/${post.id}" method="post">
				<table width="720" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td>제목 :</td>
						<td>
							<input type="text" size="50" name="title" value="${post.title} ">
							<select name="categoryName">
								<c:forEach var="category" items="${blog.categoryList}">
									<option <c:if test="${category.name eq post.category.name}">selected</c:if>>${category.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>내용 :</td>
						<td colspan="10"><textarea name="content" rows="10" cols="80">${post.content}</textarea></td>
					</tr>
					<tr><td height="5">&nbsp;</td></tr>
					<tr><td colspan="10" align="center">&nbsp;<input type="submit" value="수정하기"></td></tr>
				</table>
			</form> 
			<!-- 포스트 수정화면 종료 -->
			
		</td>
	</tr>
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>

<%@include file="../layout/footer.jsp" %>