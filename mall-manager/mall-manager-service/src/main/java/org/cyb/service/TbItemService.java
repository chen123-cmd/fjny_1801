package org.cyb.service;

import org.cyb.utils.EasyUIDataGridResult;

public interface TbItemService {
	//獲取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows); 
}
