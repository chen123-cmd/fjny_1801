package org.cyb.controller;

import java.util.List;

import org.cyb.pojo.TbItem;
import org.cyb.service.TbItemCatService;
import org.cyb.service.TbItemDescService;
import org.cyb.service.TbItemParamService;
import org.cyb.service.TbItemService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.EasyUITreeNodeBean;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	public TbItemDescService tbItemDescService;
	@Autowired
	public TbItemParamService tbItemParamService;

	
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
	public FjnyResult saveTbItem(TbItem tbItem,String desc,String itemParams){
		System.out.println("========saveTbItem=========");
		tbItemService.saveTbItem(tbItem,desc,itemParams);
		return FjnyResult.ok();
	}
	
	//类目选择
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUITreeNodeBean> getItemCatList(@RequestParam(value ="id",defaultValue = "0") long parentId) {
		System.out.println("parentId:" + parentId);
		return tbItemCatService.getTbItemCatList(parentId);
		
	}
	
	//修改商品信息
	@RequestMapping("/query/item-desc/{id}")
	@ResponseBody
	public FjnyResult getTbItemDesc(@PathVariable Long id) {
		//System.out.println("getTbItemDesc-id" + id);
		return tbItemDescService.getTbItemDesc(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public FjnyResult updateTbItem(TbItem tbItem,String desc) {
		return tbItemService.updateItem(tbItem, desc);
	}
	
	
	//删除商品
	@RequestMapping("/delete")
	@ResponseBody
	public FjnyResult deleteItem(@RequestParam(value = "ids") List<Long> ids) {
		System.out.println("删除商品ids:"  + ids);
		return tbItemService.deleteTbItem(ids);
	}
		
	//下架商品
	@RequestMapping("/changeGoodsState")
	@ResponseBody
	public FjnyResult changeBannerState(@RequestParam(value = "ids") List<Long> ids,Integer status) {
		System.out.println("下架商品ids:"  + ids);
		return tbItemService.changeGoodsState(ids, status);
	}	
	
	
	//查询商品规格
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FjnyResult checkParam(@PathVariable Long itemCatId) {
		return tbItemParamService.checkParam(itemCatId);
	}	
	
	
}
