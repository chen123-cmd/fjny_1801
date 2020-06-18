package org.cyb.controller;

import java.util.List;

import org.cyb.pojo.TbItem;
import org.cyb.service.TbItemCatService;
import org.cyb.service.TbItemService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.EasyUITreeNodeBean;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class TbItemController {
		
	@Autowired
	public TbItemService tbItemService;
	@Autowired
	public TbItemCatService tbItemCatService;
	
	//查询商品
	@RequestMapping("/getItem")
	@ResponseBody
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue = "1")
	Integer page
			,@RequestParam(defaultValue = "10") Integer rows) {
		return tbItemService.getTbItemList(page,rows);
		
	}
	
	//添加商品
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public FjnyResult saveTbItem(TbItem tbItem,String desc){
		System.out.println("========saveTbItem=========");
		tbItemService.saveTbItem(tbItem,desc);
		return FjnyResult.ok();
	}
	
	//类目选择
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUITreeNodeBean> getItemCatList(@RequestParam(value ="id",defaultValue = "0") long parentId) {
		System.out.println("parentId:" + parentId);
		return tbItemCatService.getTbItemCatList(parentId);
		
	}
	
	///删除商品
	@RequestMapping("/deleteItem")
	@ResponseBody
	public FjnyResult deleteitem(TbItem tbItem) {
		System.out.println("controoler：删除商品");
		return tbItemService.deleteTbItem(tbItem);
	}

}
