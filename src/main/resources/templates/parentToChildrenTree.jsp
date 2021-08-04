<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>所有子依赖树</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!--引入Echarts依赖-->
    <script src="https://cdn.bootcss.com/echarts/4.6.0/echarts.js"></script>
    <!--引入jQuery依赖-->
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>所有子依赖树</small>
                    <div class="col-md-4 column">
                        <a class="btn btn-primary" th:href="@{'http://localhost:8081/allDependencies'}">回到首页</a>
                    </div>
                </h1>
            </div>
        </div>
    </div>
    <div id="main" style="width:100%;height:100%;"></div>
</div>
<script type="text/javascript" th:inline="javascript">
    // 绑定DOM
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    // 存放图节点
    var datas = []

    // 存放图的边
    var links = [];

    // json数据
    var treeJson = [[${treeJson}]];
    treeObj = JSON.parse(treeJson);

    // 存放已访问的节点，防止重复push
    let isVisited = new Set();

    // 递归将treeObj数据放到datas和links
    function recurLinks(tempObj, x, y) {
        let parentName = tempObj.parentName;
        if (!isVisited.has(parentName)) {
            datas.push({
                name: parentName,
                x: x,
                y: y
            });
            isVisited.add(parentName);
            if (tempObj.childNames != null) {
                let length = tempObj.childNames.length;
                for (var i = 0; i < length; i++) {
                    links.push({
                        source: parentName,
                        target: tempObj.childNames[i].parentName
                    });
                    // step 用于调整最后展示的节点之间的格式
                    let step = 0;
                    if ((length % 2 == 1 && length != 1) ||(length % 2 == 0 && i < length / 2)) {
                        step = i - length / 2;
                    } else if (length % 2 == 0 && i >= length / 2){
                        step = i - length / 2 + 1;
                    }
                    recurLinks(tempObj.childNames[i], x + 1, y + step);
                }
            }
        }
    }

    // 执行递归
    recurLinks(treeObj, 0, 0);

    option = {
        title: {
            text: '表' + treeObj.parentName + '的所有子依赖树'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
            {
                type: 'graph',
                layout: 'none',
                symbolSize: 50,
                roam: true,
                label: {
                    show: true
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    fontSize: 20
                },
                data: datas,
                // links: [],
                links: links,
                lineStyle: {
                    opacity: 0.9,
                    width: 2,
                    curveness: 0
                }
            }
        ]
    };

    option && myChart.setOption(option);
</script>
</body>
</html>