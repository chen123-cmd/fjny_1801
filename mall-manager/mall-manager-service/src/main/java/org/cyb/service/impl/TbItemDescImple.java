package org.cyb.service.impl;

import org.cyb.mapper.TbItemDescMapper;
import org.cyb.pojo.TbItemDesc;
import org.cyb.service.TbItemDescService;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbItemDescImple implements TbItemDescService {
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public FjnyResult getTbItemDesc(Long itemId) {
		TbItemDesc itemDesc  = tbItemDescMapper.selectByPrimaryKey(itemId);
		return FjnyResult.ok(itemDesc);
	}

}
