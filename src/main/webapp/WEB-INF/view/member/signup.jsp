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
<c:import url="/WEB-INF/fragment/navbar.jsp">
    <c:param name="active" value="signup"/>
</c:import>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-9 col-lg-6 ">
            <h2>회원가입</h2>

            <form method="post">
                <div class="mb-3">
                    <label for="inputId" class="form-label">아이디</label>
                    <input name="id" type="text" id="inputId" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="inputPassword" class="form-label">비밀번호</label>
                    <input name="password" type="text" id="inputPassword" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="inputNickName" class="form-label">별명</label>
                    <input name="nickName" type="text" id="inputNickName" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="inputDescription" class="form-label">자기소개</label>
                    <textarea name="description" id="inputDescription" class="form-control" rows="10"></textarea>
                </div>
                <button class="btn btn-primary">
                    <i class="fa-solid fa-user-plus"></i>
                    가입
                </button>
            </form>
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
