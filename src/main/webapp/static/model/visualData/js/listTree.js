var setting = {
    view: {
        dblClickExpand: true
    },
    check: {
        enable: false
    },
    callback: {
        onClick: onNodeClick
    }


};
var videoList = [], tankList = [], dangerList = [];
loadData(ctx + "/zxjkyj/spjk/qymaplist", videoList, 1);
loadData(ctx + "/zxjkyj/cgssjc/qyjson", tankList, 2);
loadData(ctx + "/zxjkyj/gwgyjc/qyjson", dangerList, 3);
var zNodes = [
    {
        "id": 0, "name": "设备列表", "open": true, children: [
            {
                "id": 1, "pid": 0, "name": "视频监控", "open": true,
                children: videoList
            },
            {
                "id": 2, "pid": 0, "name": "储罐实时监控",
                children: tankList
            },
            {
                "id": 3, "pid": 0, "name": "高危工艺",
                children: dangerList
            }


        ]
    }

];


var zTree;
$(document).ready(function () {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    zTree = $.fn.zTree.getZTreeObj("treeDemo");

});

/*
function init() {
    $(".dataTabUl li").click(function () {
        var ins = $(this).index();
        $(this).find("a").addClass("dataActive").end().siblings().find("a").removeClass("dataActive");
        $(".dataConBox .dataBoxSub").eq(ins).show().siblings().hide();
    })
}*/

function onNodeClick(e,treeId, treeNode) {
    console.log(e)
    console.log(treeId)
    console.log(treeNode);
    $('#content').attr('src',treeNode.url1);
}

function loadData(url, pdata, pid) {
    var tmp ={1:"/zxjkyj/spjk/showqysp/",2:"/zxjkyj/cgssjc/view/",3:"/zxjkyj/gwgyjc/view/"};
    $.ajax({
        async: false,
        url: url,
        success: function (data) {
            data = eval(data);
            $.each(data, function (index, item) {
                pdata.push({url1:ctx+tmp[pid]+item.id,id: item.id, pid: pid, name: (item.title?item.title:item.m1)});
            });
        }
    });
}


