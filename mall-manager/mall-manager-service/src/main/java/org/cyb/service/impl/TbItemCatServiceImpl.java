package org.cyb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cyb.mapper.TbItemCatMapper;
import org.cyb.pojo.TbItem;
import org.cyb.pojo.TbItemCat;
import org.cyb.pojo.TbItemCatExample;
import org.cyb.pojo.TbItemCatExample.Criteria;
import org.cyb.service.TbItemCatService;
import org.cyb.service.TbItemService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.EasyUITreeNodeBean;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNodeBean> getTbItemCatList(Long parendId) {
		System.out.println("getTbItemCatList");
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parendId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(example);
		List<EasyUITreeNodeBean> list = new ArrayList<EasyUITreeNodeBean>();
		for(TbItemCat tbItemCat : tbItemCatList) {
			EasyUITreeNodeBean node = new EasyUITreeNodeBean();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			/*
			 * Boolean isParent = tbItemCat.getIsParent(); if(isParent) {
			 * e.setState("closed"); }else { e.setState("open"); }
			 */
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			list.add(node);
		}
		return list;

	}
	


}
