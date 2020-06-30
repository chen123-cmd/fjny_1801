<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="super-theme-example">
			<div style="height: 350px;">
				<table id="itemParamList"></table>
			</div>
			<br /><br/>
		</div>
<script>
$('#itemParamList').datagrid({
	url: '/item/param/list',
	fit: true,
	pagination: true,
	fitColumns: true,
	toolbar: [{
		text: '添加',
		iconCls: 'fa fa-plus',
		handler: function() {
			TT.createWindow({
        		url : "/item-param-add",
        	});
		}
	}, {
		text: '编辑',
		iconCls: 'fa fa-edit',
		handler: function() {
			var ids = getSelectionsIds();
			//判斷如果未選定 不執行 提示
			if (ids.length == 0) {
				$.messager.alert('提示', '必须选择一个商品才能编辑!');
				return;
			}
			//判斷如果多行數據 提示：只能選擇一個商品
			if (ids.indexOf(',') > 0) {
				$.messager.alert('提示', '只能选择一个商品!');
				return;
			}
			//进行数据回显
			$('#itemEditWindow').window({
				onLoad : function() {
					var data = $("#dgTbItem").datagrid("getSelections")[0];
					$('#itemEditForm').form('load',data);
					//将商品描述进行显示
					$.getJSON("item/query/item-desc/" + data.id,function(result){
						if(result.status == 200){
							itemEditEditor.html(result.data.itemDesc);
						}
					});
				 	TT.init({
						"pics" : data.image,
						"cid" : data.cid,
						fun : function(node) {
						}
					}); 
				}
			}).window('open');
		}
	},{
		text: '删除',
		iconCls: 'fa fa-remove',
		handler: function() {
			var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品规格!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品规格吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/param/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品规格成功!',undefined,function(){
            					$("#itemParamList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
		}
	}],

	height: 400,
	columns: [
		[{
			field: 'id',
			title:'ID',
			width: 60
		}, {
			field: 'itemCatId',
			title: '商品类目ID',
			width: 100
		}, {
			field: 'itemCatName',
			title: '商品类目',
			width: 100
		},{
			field: 'paramData',
			title: '规格(只显示分组名称)',
			width: 300,
			formatter:formatItemParamData
		},{
			field: 'created',
			title: '创建日期',
			width: 130,
			formatter:TT.formatDateTime
		},{
			field: 'updated',
			title: '更新日期',
			width: 130,
			formatter:TT.formatDateTime
		}
		]
	]
});
	function formatItemParamData(value , index){
		var json = JSON.parse(value);
		var array = [];
		$.each(json,function(i,e){
			array.push(e.group);
		});
		return array.join(",");
	}

    function getSelectionsIds(){
    	var itemList = $("#itemParamList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var itemParamListToolbar = [{
        text:'新增',
        iconCls:'fa fa-plus',
        handler:function(){
        	TT.createWindow({
        		url : "/item-param-add",
        	});
        }
    },{
        text:'编辑',
        iconCls:'fa fa-edit',
        handler:function(){
        	$.messager.alert('提示','该功能未实现!');
        }
    },{
        text:'删除',
        iconCls:'fa fa-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品规格!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品规格吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/param/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品规格成功!',undefined,function(){
            					$("#itemParamList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>