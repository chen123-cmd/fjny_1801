<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CheckBox select on DataGrid - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
	<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>CheckBox select on DataGrid</h2>
	<div style="margin-bottom:10px">
		<p>Click on header checkbox to select or unselect all selections.</p>
	</div>
	
	<table id="tt" title="Checkbox Select" class="easyui-datagrid" style="width:700px;height:250px"
			url="datagrid2_getdata.php"
			idField="itemid" pagination="true"
			iconCls="icon-save">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th field="itemid" width="80">Item ID</th>
				<th field="productid" width="120">Product ID</th>
				<th field="listprice" width="80" align="right">List Price</th>
				<th field="unitcost" width="80" align="right">Unit Cost</th>
				<th field="attr1" width="200">Attribute</th>
				<th field="status" width="60" align="center">Status</th>
			</tr>
		</thead>
	</table>
</body>
</html>