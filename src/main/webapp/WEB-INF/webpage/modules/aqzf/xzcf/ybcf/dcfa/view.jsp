<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问笔录查看</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>
<form id="inputForm" action="${ctx}/xzcf/ybcf/dcfa/${action}"  method="post" class="form-horizontal" >
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
		<tr>
			<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
			<td class="width-35"><input id="dsperson" name="dsperson" type="text"  class="easyui-textbox" value="${dcfa.dsperson}"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
			<td class="width-15 active"><label class="pull-right">立案编号：</label></td>
			<td class="width-35"><input id="M1" name="M1" type="text" value="${dcfa.m1}"  class="easyui-textbox" data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">调查开始时间：</label></td>
			<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${dcfa.m2 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
			<td class="width-15 active"><label class="pull-right">调查结束时间：</label></td>
			<td class="width-35"><input id="M3" name="M3" class="easyui-datetimebox" value="${dcfa.m3 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">办案部门：</label></td>
			<td class="width-35"><input id="M9" name="M9" type="text"  class="easyui-textbox" value="${dcfa.m9 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
			<td class="width-15 active"><label class="pull-right">调查人员：</label></td>
			<td class="width-35"><input id="M4" name="M4" type="text"  class="easyui-textbox" value="${xwbl.m7 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">部门负责人：</label></td>
			<td class="width-35"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${dcfa.m7 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
			<td class="width-15 active"><label class="pull-right">分管领导：</label></td>
			<td class="width-35"><input id="M8" name="M8" type="text"  class="easyui-textbox" value="${xwbl.m8 }"  data-options="editable:false" style="height: 30px;width: 100%" /></td>
		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">调查步骤：</label></td>
			<td class="width-85" colspan="3"><textarea id="M5" name="M5" style="width:100%;height:300px;visibility:hidden;" readonly="readonly">${dcfa.m5 }</textarea></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">调查报告：</label></td>
			<td class="width-35" colspan="3"><input type="text" id="M6" name="M6" class="easyui-textbox" value="${dcfa.m6 }"  style="height: 80px;width: 100%" /></td>
		</tr>
		</tbody>
	</table>
</form>
<script>
	//初始化
	$(function() {

		var KConfigForFile = {
			uploadJson: ctx + '/kindeditor/uploadFile.shtml',
			fileManagerJson: ctx + '/kindeditor/manager.shtml',
			allowFileManager: false
		};

		var KEditorConfig = {
			uploadJson: ctx + '/kindeditor/uploadFile.shtml',
			fileManagerJson: ctx + '/kindeditor/manager.shtml',
			allowFileManager: false,
			filterMode: true,
			afterBlur: function () {
				this.sync();
			},
			afterChange: function () {

			},
			pasteType: 1,
			afterCreate: function () {
				var self = this;
				KindEditor.ctrl(document, 13, function () {
					self.sync();
					KindEditor('form[name=form1]')[0].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function () {
					self.sync();
					KindEditor('form[name=form1]')[0].submit();
				});
			}
		};

		var upEditor = KindEditor.editor(KConfigForFile);

		//渲染富文本
		window.editor = KindEditor.create(
				'#M5',
				$.extend({}, KEditorConfig, {
					width: '600',
					items: ['source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright', '|',
						'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', '|', 'quickformat', '|', 'image', '|', 'link', 'fontname', 'fullscreen'
					]
				})
		);

	});


</script>
</body>
</html>