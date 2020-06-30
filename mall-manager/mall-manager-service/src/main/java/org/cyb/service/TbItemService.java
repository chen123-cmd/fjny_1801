package org.cyb.service;

import java.util.List;

import org.cyb.pojo.TbItem;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.FjnyResult;

public interface TbItemService {
	//獲取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows);
	
	//添加商品
	public FjnyResult saveTbItem(TbItem tbItem,String desc, String itemParams);
	
	//更改商品信息
	public FjnyResult updateItem(TbItem tbItem,String desc);
	
	//删除商品
	public FjnyResult deleteTbItem(List<Long> ids);
	
	//商品上下架
	public FjnyResult changeGoodsState(List<Long> ids, Integer status);
	
	
}
