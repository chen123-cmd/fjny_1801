package org.cyb.service;

import org.cyb.pojo.TbItem;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.FjnyResult;

public interface TbItemService {
	//獲取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows);
	//添加商品
	public FjnyResult saveTbItem(TbItem tbItem);
	
}
