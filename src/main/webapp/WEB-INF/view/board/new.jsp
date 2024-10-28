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
<c:import url="/WEB-INF/view/fragment/navbar.jsp">
    <c:param name="active" value="new"/>
</c:import>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-9 col-lg-6 ">
            <h2>새 게시물 작성</h2>
            <form method="post">
                <div class="mb-3">
                    <label for="inputTitle1" class="form-label">제목</label>
                    <input type="text" name="title" class="form-control" id="inputTitle1">
                </div>
                <div class="mb-3">
                    <label for="inputContent1" class="form-label">본문</label>
                    <textarea name="content" class="form-control" rows="10" id="inputContent1"></textarea>
                </div>
                <div class="mb-3">
                    <label for="writer" class="form-label">작성자</label>
                    <input type="text" name="writer" class="form-control" id="writer">
                </div>
                <div class="mb-3">
                    <button class="btn btn-primary">
                        저장
                    </button>
                </div>
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
