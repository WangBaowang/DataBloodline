<html>
<head>
    <title>添加数据血缘关系</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>添加数据血缘关系</small>
                    <div class="col-md-4 column">
                        <a class="btn btn-primary" th:href="@{'http://localhost:8081/allDependencies'}">回到首页</a>
                    </div>
                </h1>
            </div>
        </div>
    </div>
    <form action="http://localhost:8081/addDependency" method="get">
        <div class="form-group">
            <label>父依赖表名：</label>
            <span style="color:red;font-weight: bold" th:text="${parentError}"></span>
            <input type="text" name="parentName" class="form-control" required>
        </div>
        <div class="form-group">
            <label>子依赖表名：</label>
            <span style="color:red;font-weight: bold" th:text="${childError}"></span>
            <input type="text" name="childName" class="form-control" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="添加">
        </div>
    </form>



</div>

</body>
</html>
