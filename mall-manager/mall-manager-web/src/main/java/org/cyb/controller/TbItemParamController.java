package org.cyb.controller;

import java.util.List;

import org.cyb.pojo.TbItem;
import org.cyb.service.TbItemParamService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
	
	@Autowired
	private TbItemParamService tbItemParamService;
	
	//查询规格
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(@RequestParam(defaultValue = "1") Integer page
			,@RequestParam(defaultValue = "10") Integer rows) {
		return tbItemParamService.getItemParamList(page, rows);
	
	}
	
	//查询类目是否存在规格模板
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FjnyResult checkParam(@PathVariable Long itemCatId) {
		return tbItemParamService.checkParam(itemCatId);
	}
	
	//保存添加的类目规格模板
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public FjnyResult addItemParam(@PathVariable Long cid,String paramData) {
		return tbItemParamService.addItemParam(cid,paramData);
	}
	
	//修改规格信息
	@RequestMapping("/update/{cid}")
	@ResponseBody
	public FjnyResult updateTbItemParam(TbItem tbItem) {
		return tbItemParamService.updateItemParam(tbItem);
	}
	
	//删除规格
	@RequestMapping("/delete/{cid}")
	@ResponseBody
	public FjnyResult deleteItemParam(@RequestParam(value = "ids") List<Long> ids) {
		System.out.println("删除商品ids:"  + ids);
		return tbItemParamService.deleteItemParam(ids);
	}
	
}
