<html>
<head>
    <title>修改数据血缘关系</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改数据血缘关系</small>
                    <div class="col-md-4 column">
                        <a class="btn btn-primary" th:href="@{'http://localhost:8081/allDependencies'}">回到首页</a>
                    </div>
                </h1>
            </div>
        </div>
    </div>

    <form th:action="@{http://localhost:8081/updateDependency}" th:object="${QDependency}" method="get">

        <input type="hidden" name="dependencyId" th:field="*{id}">

        <div class="form-group">
            <label>父依赖表名：</label>
            <span style="color:red;font-weight: bold" th:text="${parentError}"></span>
            <input type="text" name="parentName" class="form-control" th:field="*{parentName}" required>
        </div>
        <div class="form-group">
            <label>子依赖表名：</label>
            <span style="color:red;font-weight: bold" th:text="${childError}"></span>
            <input type="text" name="childName" class="form-control" th:field="*{childName}" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>
    </form>


</div>

</body>
</html>
