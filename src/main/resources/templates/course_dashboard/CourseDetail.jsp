<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="tw.vincent.Bean.CourseBean"%>
<%@include file="DashBoardHeader.jspf"%>


<BODY>
	<div class="container-fluid pt-4 px-4">
		<div class="col-sm-12 col-xl-10">
			<div class="bg-light text-center rounded p-4">
				<div class="d-flex align-items-center justify-content-between mb-4">
					<h6 class="mb-0">更新課程資訊</h6>
				</div>

							<% CourseBean course = (CourseBean) request.getAttribute("CourseBean");%>
						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">課程編號</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="courseNo"
									value="<%=course.getCourseNo()%>" readonly="readonly">
							</div>
						</div>


						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">課程分類</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="courseCategory" value="<%=course.getCourseCategory()%>" readonly="readonly">

							</div>
						</div>

	
						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">課程名稱</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="courseName"
									value="<%=course.getCourseName()%>" readonly="readonly">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">課程介紹</label>
							<div class="col-sm-8">
							<textarea name="courseIntroduction" class="form-control" placeholder="課程介紹..." cols="62" rows="5" readonly="readonly"><%=course.getCourseIntroduction()%></textarea>

							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">講師</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="lecturer"
									value="<%=course.getLecturer()%>" readonly="readonly">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">上架時間</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="date"
									value="<%=course.getDate()%>" readonly="readonly">
							</div>
						</div>


						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">課程圖</label>
							<div class="col-sm-8">
								<img class="img-fluid mx-auto mb-4" id="preview_img" src="${contextPath}/${CourseBean.coursePicUrl}"
<%-- 								<img class="img-fluid mx-auto mb-4" id="preview_img" src="<c:url value='${CourseBean.coursePicUrl}'/> " --%>
								alt="請選擇課程圖" style="width: 320px; height: 200px;"> 
							
<!-- 								<input type="text" class="form-control" name="coursePic" -->
<%-- 									value="<%=course.getCoursePicUrl()%>" readonly="readonly"> --%>
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">影片位置</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="courseVedio"
									value="<%=course.getCourseVedioUrl()%>" readonly="readonly">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">評分</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="score"
									value="<%=course.getScore()%>" readonly="readonly">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">價錢</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="price"
									value="<%=course.getPrice()%>" readonly="readonly">
							</div>
						</div>

						<div>
<!-- 							<button type="submit" class="btn btn-primary" name="updateConfirm" value="確認更改" onclick="javascript:return upd();">確認更改</button> -->
							<button type="button" class="btn btn-outline-primary m-0"
										onclick="location.href='<c:url value="/toUpdatePage/${CourseBean.courseNo}"/>'"> 更新</button>
										

							<button type="button" class="btn btn-primary"
								onclick="location.href='<c:url value="/courses"/>'">取消</button>
						</div>
<!-- 					</FORM> -->
				</div>
			</div>
		</div>
</BODY>

<%@include file="DashBoardFooter.jspf"%>