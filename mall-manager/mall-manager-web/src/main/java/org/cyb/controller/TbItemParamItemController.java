package org.cyb.controller;

import org.cyb.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TbItemParamItemController {
	@Autowired
	private TbItemParamItemService tbItemParamItemService;
	
	@RequestMapping("/showParam/{itemId}")
	public String showparam(@PathVariable Long itemId,Model model) {
		String html = tbItemParamItemService.getTbItemParamByItemId(itemId);
		model.addAttribute("html",html);
		return "item-param";
	}
}
