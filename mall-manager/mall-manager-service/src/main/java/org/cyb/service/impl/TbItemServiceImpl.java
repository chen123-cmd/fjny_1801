package org.cyb.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.cyb.mapper.TbItemDescMapper;
import org.cyb.mapper.TbItemMapper;
import org.cyb.pojo.TbItem;
import org.cyb.pojo.TbItemDesc;
import org.cyb.pojo.TbItemExample;
import org.cyb.service.TbItemService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.FjnyResult;
import org.cyb.utils.IDUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemServiceImpl implements TbItemService {

	@Resource
	public TbItemMapper tbItemMapper;
	@Resource
	public TbItemDescMapper tbItemDescMapper;

	//获取商品列表
	@Override
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows) {
		// 分页插件
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(total, list);
		return easyUIDataGridResult;
	}

	//添加商品
	@Override
	public FjnyResult saveTbItem(TbItem tbItem,String desc) {
		long genItemId = IDUtils.genItemId();
		tbItem.setId(genItemId);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbItem.setCid(0l);
		tbItem.setStatus((byte)1);
		int insertSelective = tbItemMapper.insertSelective(tbItem);
		if (insertSelective < 0) {
			return FjnyResult.build(500, "添加商品失敗");
		}
		
		//商品描述添加
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insertSelective(tbItemDesc);

		return FjnyResult.ok(tbItem);
	}

	//删除商品
	@Override
	public FjnyResult deleteTbItem(TbItem tbItem) {
		TbItemExample example =new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(tbItem.getId());
		tbItemMapper.deleteByExample(example);
		return FjnyResult.ok();
	}

	
	
	

}
