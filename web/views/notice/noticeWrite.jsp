<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<div class="container-fluid">
	<div class="row content">
		<div class="col-sm-3 sidenav">
			<br> <br>
			<h4 id="mypagetitle">
				<a href="mypage.html">게시판</a>
			</h4>
			<div id="boardList">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="mypage.html">공지사항</a></li>
					<li><a href="jjim.html">커뮤니티</a></li>
					<li><a href="updateUserInfo.html">FAQ</a></li>
				</ul>
				<br>
			</div>

		</div>
		<section id="notice-container" class="container">
			<div class="col-sm-9">
				<h2 class="text-center">공지사항 작성</h2>
				<form action="<%=request.getContextPath() %>/notice/noticeFormEnd"
					method="post" enctype="multipart/form-data">

					<table id="tbl-notice" class="table table-bordered">
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" required></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><input type="file" name="up_file"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="20" name="content"></textarea></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center"><input
								type="submit" value="등록" class="btn my-btn"> <input
								type="reset" value="취소" class="btn my-btn"></td>
						</tr>
					</table>
				</form>
		</section>
	</div>
	<%@ include file="/views/common/footer.jsp"%>