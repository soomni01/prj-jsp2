<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<c:import url="/WEB-INF/view/fragment/navbar.jsp"></c:import>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-9 col-lg-6 ">
            <h2>회원정보</h2>

            <div class="mb-3">
                <label for="inputId" class="form-label">아이디</label>
                <input id="inputId" class="form-control" value="${member.id}" readonly>
            </div>
            <div class="mb-3">
                <label for="inputPassword" class="form-label">비밀번호</label>
                <input id="inputPassword" class="form-control" value="${member.password}" readonly>
            </div>
            <div class="mb-3">
                <label for="inputNickName" class="form-label">별명</label>
                <input id="inputNickName" class="form-control" value="${member.nickName}" readonly>
            </div>
            <div class="mb-3">
                <label for="inputDescription" class="form-label">자기소개</label>
                <textarea name="description" id="inputDescription" class="form-control" rows="10"
                          readonly>${member.description}</textarea>
            </div>
            <div class="mb-3">
                <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                    <i class="fa-solid fa-user-minus"></i>
                    탈퇴
                </button>
                <a class="btn btn-outline-primary" href="/member/edit?id=${member.id}">
                    <i class="fa-solid fa-user-pen"></i>
                    수정
                </a>
            </div>
        </div>
    </div>
</div>

<form action="/member/delete" method="post" id="deleteMember">
    <input type="hidden" name="id" value="${member.id}">
</form>

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">탈퇴 확인</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/member/delete" method="post" id="deleteMember1">
                    <input type="hidden" name="id" value="${member.id}">
                    <label for="confirmPassword" class="form-label">암호</label>
                    <input id="confirmPassword" type="text" class="form-control" name="password">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button form="deleteMember1" class="btn btn-primary">탈퇴하기</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
</body>
</html>
