package org.cyb.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.cyb.mapper.TbItemDescMapper;
import org.cyb.mapper.TbItemMapper;
import org.cyb.mapper.TbItemParamItemMapper;
import org.cyb.mapper.TbItemParamMapper;
import org.cyb.pojo.TbItem;
import org.cyb.pojo.TbItemDesc;
import org.cyb.pojo.TbItemExample;
import org.cyb.pojo.TbItemParam;
import org.cyb.pojo.TbItemParamExample;
import org.cyb.pojo.TbItemParamExample.Criteria;
import org.cyb.pojo.TbItemParamItem;
import org.cyb.service.TbItemService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.ExceptionUtil;
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
	@Resource
	public TbItemParamItemMapper tbItemParamItemMapper;
	@Resource
	public TbItemParamMapper tbItemParamMapper;
	

	// 获取商品列表
	@Override
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows) {
		// 分页插件
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		//根据业务需求 决定是否显示 这里不显示
		//example.createCriteria().andStatusNotEqualTo((byte)3);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(total, list);
		return easyUIDataGridResult;
	}

	// 添加商品
	@Override
	public FjnyResult saveTbItem(TbItem tbItem, String desc,String itemParams) {
		try {
			Date date = new Date();
			long genItemId = IDUtils.genItemId();
			tbItem.setId(genItemId);
			tbItem.setCreated(date);
			tbItem.setUpdated(date);
			tbItem.setCid(0l);
			tbItem.setStatus((byte) 1);
			tbItemMapper.insertSelective(tbItem);
//			int insertSelective = tbItemMapper.insertSelective(tbItem);
//			if (insertSelective < 0) {
//				return FjnyResult.build(500, "添加商品失敗");
//			}

			// 商品描述添加
			TbItemDesc tbItemDesc = new TbItemDesc();
			tbItemDesc.setItemId(tbItem.getId());
			tbItemDesc.setItemDesc(desc);
			tbItemDesc.setCreated(date);
			tbItemDesc.setUpdated(date);
			tbItemDescMapper.insertSelective(tbItemDesc);

			//商品规格添加
			TbItemParamItem record = new TbItemParamItem();
			record.setItemId(genItemId);
			record.setParamData(itemParams);
			record.setCreated(date);
			record.setUpdated(date);
			tbItemParamItemMapper.insert(record);
		} catch (Exception e) {
			return FjnyResult.build(500,ExceptionUtil.getStackTrace(e));
		}
		return FjnyResult.ok();
	}
	

	// 更改商品信息
	@Override
	public FjnyResult updateItem(TbItem tbItem, String desc) {
		// 更新商品信息
		tbItem.setUpdated(new Date());// 更新時間
		tbItemMapper.updateByPrimaryKeySelective(tbItem);
		// 更新商品描述信息
		TbItemDesc record = new TbItemDesc();
		record.setItemId(tbItem.getId());
		record.setItemDesc(desc);
		record.setUpdated(new Date());
		tbItemDescMapper.updateByPrimaryKeySelective(record);
		return FjnyResult.ok();

	}

	// 删除商品
	@Override
	public FjnyResult deleteTbItem(List<Long> ids) {
		try {
			TbItem record = new TbItem();
			record.setStatus((byte)3);
			TbItemExample example = new TbItemExample();
			example.createCriteria().andIdIn(ids);
			tbItemMapper.updateByExampleSelective(record, example);
		} catch (Exception e) {
			return FjnyResult.build(500, "删除失败");
		}
		return FjnyResult.ok();
	}


	//商品上下架
	@Override
	public FjnyResult changeGoodsState(List<Long> ids, Integer status) {
		try {
			TbItem record = new TbItem();
			if(status==1) {
				record.setStatus((byte)1);
				TbItemExample example = new TbItemExample();
				example.createCriteria().andIdIn(ids);
				tbItemMapper.updateByExampleSelective(record, example);
			}else if(status == 2){
				record.setStatus((byte)2);
				TbItemExample example = new TbItemExample();
				example.createCriteria().andIdIn(ids);
				tbItemMapper.updateByExampleSelective(record, example);
			}
		} catch (Exception e) {
			return FjnyResult.build(500, "改变状态失败");
		}

		return FjnyResult.ok();
	}

	


	

}
