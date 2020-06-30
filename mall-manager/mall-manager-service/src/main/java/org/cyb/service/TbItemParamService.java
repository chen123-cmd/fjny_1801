package org.cyb.service;

import java.util.List;

import org.cyb.pojo.TbItem;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.FjnyResult;

public interface TbItemParamService {
	//获取规格参数列表
	EasyUIDataGridResult getItemParamList(Integer page,Integer rows);
	
	//查询类目是否存在规格模板
	public FjnyResult checkParam(Long itemCatId);

	//保存添加的类目规格模板
	public FjnyResult addItemParam(Long cid, String paramData);
	
	//更改规格信息
	public FjnyResult updateItemParam(TbItem tbItem);
	
	//删除规格信息
	public FjnyResult deleteItemParam(List<Long> ids);
}
