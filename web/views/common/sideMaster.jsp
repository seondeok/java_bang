<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section>
    <div class="container-fluid">
        <div class="row content">
        	<!--사이드바-->
            <div class="col-sm-3 col-md-2 sidenav"> 
                <br>
                <br>
                <h4 id="mypagetitle"><a href="mypage.html">&nbsp;&nbsp;관리 메뉴</a></h4>
                <div id="mypageList">
                    <ul class="nav nav-pills nav-stacked">
                        <br>
                        <li>
                            <a class="nav-link active" href="<%=request.getContextPath()%>/master/pensionList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-home">
                                    <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                                    <polyline points="9 22 9 12 15 12 15 22"></polyline>
                                </svg>
                                펜션관리 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        
                        <li>
                            <a class="nav-link active" href="<%=request.getContextPath()%>/master/waitList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-home">
                                    <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                                    <polyline points="9 22 9 12 15 12 15 22"></polyline>
                                </svg>
                                승인대기-펜션 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        
                        <li>
                            <a class="nav-link active"  href="<%=request.getContextPath()%>/master/ownerList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-file">
                                    <path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
                                    <polyline points="13 2 13 9 20 9"></polyline>
                                </svg>
                                업주관리 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        
                        <li>
                            <a class="nav-link active" href="<%=request.getContextPath()%>/master/waitOwnerList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-file">
                                    <path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
                                    <polyline points="13 2 13 9 20 9"></polyline>
                                </svg>
                                승인대기-업주 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        
                        <li>
                            <a class="nav-link active" href="<%=request.getContextPath()%>/master/clientList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-users">
                                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="9" cy="7" r="4"></circle>
                                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                                    <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                                </svg>
                                회원관리 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="<%=request.getContextPath()%>/report/reportList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="feather feather-alert-circle">
                                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="9" cy="7" r="4"></circle>
                                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                                    <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                                </svg>
                                신고관리 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </ul><br>
                </div>
            </div>