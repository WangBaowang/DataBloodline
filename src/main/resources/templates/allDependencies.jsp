<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>数据血缘关系展示</title>


    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>数据血缘关系列表</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" th:href="@{'http://localhost:8081/toAddDependency'}">新增数据血缘关系</a>
            <a class="btn btn-primary" th:href="@{'http://localhost:8081/allDependencies'}">显示全部数据血缘关系</a>
        </div>
        <div class="col-md-4 column">
            <form name="form" method="get">
                <span style="color:red;font-weight: bold" th:text="${error}"></span>
                <input type="text" name="queryTableName" class="form-control" placeholder="请输入要查询的数据表名称" th:value="${tableName}" required checked>
                <input type="submit" value="父依赖" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/queryParentDependency'">
                <input type="submit" value="子依赖" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/queryChildDependency'">
                <input type="submit" value="所有父依赖" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/queryAllParentsDependency'">
                <input type="submit" value="所有父依赖树" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/childToParentsTree'">
                <input type="submit" value="所有子依赖" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/queryAllChildrenDependency'">
                <input type="submit" value="所有子依赖树" class="btn btn-primary" onclick="javascript:form.action='http://localhost:8081/parentToChildrenTree'">
            </form>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>数据血缘关系编号</th>
                        <th>父依赖表名</th>
                        <th>子依赖表名</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <tr th:each="dependency : ${dependencies}">
                        <td th:text="${dependencyStat.index+1}"></td>
                        <td>[[${dependency.id}]]</td>
                        <td>[[${dependency.parentName}]]</td>
                        <td>[[${dependency.childName}]]</td>
                        <td>
                            <a th:href="@{'http://localhost:8081/toUpdateDependency?id='+${dependency.id}}">修改</a>
                            &nbsp; | &nbsp;
                            <a th:href="@{'http://localhost:8081/deleteDependency/'+${dependency.id}}">删除</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>
