//电子围栏显示
function getPolygonMarker(polygonMarkerId) {
	//polygonMarkerId?
	
    $.ajax({
        type:'post',
        url:"${ctx}/lydw/dzwl/dzwllist",
        success: function(data){
            //cleanFence();
            var data = $.parseJSON(data);
            for (var i = 0; i < data.length; i++) {
                groupId = data[i].floor;
                var newFenceData = [];
                var ponits = data[i].mappoint.split('||');
                for (var j = 0; j < ponits.length; j++) {
                    var p = ponits[j].split(',');
                    //移动到坐标位置
                    /*if(j == 0) {
                        map.moveTo({
                            x: p[0],
                            y: p[1],
                            time: 1,
                            callback: function() {
                                // console.log('moveTo Complete!');
                            }
                        });
                    }*/

                    var coord = {
                        x: p[0],
                        y: p[1],
                        z: map.getFMGroup(p[2]).groupHeight + map.layerLocalHeight
                    }
                    newFenceData.push(coord);
                }
                //createFence();//生产电子围栏
                addPmMarker(newFenceData,groupId);
            }
        }
    });
    console.log("getPolygonMarker");
}

// 真正想地图中添加电子围栏的方法
function addPmMarker(fenceData,floor) {
    var group = map.getFMGroup(map.groupIDs[floor-1]);

    //返回当前层中第一个polygonMarker,如果没有，则自动创建
    fenceLayer = group.getOrCreateLayer('polygonMarker');

    //createRectangleMaker();
    //layer.addMarker(rectangleMarker);

    //createCircleMaker();
    //layer.addMarker(circleMaker);

    createPolygonMaker(fenceData);
    fenceLayer.addMarker(polygonMarker);
    console.log("addPmMarker");
}

//创建自定义形状标注
function createPolygonMaker(fenceData) {
    polygonMarker = new fengmap.FMPolygonMarker({
        //设置透明度
        alpha: .5,
        //设置边框线的宽度
        lineWidth: 1,
        //设置高度
        height: 2,
        //设置多边形坐标点
        points: fenceData
    });
    console.log("createPolygonMaker");
}
