package org.cyb.service.impl;

import java.util.Date;
import java.util.List;

import org.cyb.mapper.TbItemCatMapper;
import org.cyb.mapper.TbItemParamMapper;
import org.cyb.pojo.TbItem;
import org.cyb.pojo.TbItemCat;
import org.cyb.pojo.TbItemDesc;
import org.cyb.pojo.TbItemExample;
import org.cyb.pojo.TbItemParam;
import org.cyb.pojo.TbItemParamExample;
import org.cyb.pojo.TbItemParamExample.Criteria;
import org.cyb.service.TbItemParamService;
import org.cyb.utils.EasyUIDataGridResult;
import org.cyb.utils.ExceptionUtil;
import org.cyb.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	//获取规格参数列表
	@Override
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		//添加商品项目名称
		for (int i = 0 ; i < list.size(); i++) {
			TbItemParam tbItemParam = list.get(i);
			if(null != tbItemParam.getItemCatId()) {
				String itemCatName = getItemCatName(tbItemParam.getItemCatId());
				tbItemParam.setItemCatName(itemCatName);
			}
		}
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIDataGridResult dataGrid = new EasyUIDataGridResult(total,list);
		return dataGrid;
	}

	private String getItemCatName(Long cid) {
		TbItemCat key = tbItemCatMapper.selectByPrimaryKey(cid);
		System.out.println(key);
		return key.getName();
	}





	//查询类目是否存在规格模板
	@Override
	public FjnyResult checkParam(Long itemCatId) {
		try {
			TbItemParamExample example = new TbItemParamExample();
			Criteria ctiteriaCriteria = example.createCriteria();
			ctiteriaCriteria.andItemCatIdEqualTo(itemCatId);
			List<TbItemParam> withBLOBs = tbItemParamMapper.selectByExampleWithBLOBs(example);
			if(null == withBLOBs || withBLOBs.isEmpty()) {
				return FjnyResult.ok();
			}
			return FjnyResult.ok(withBLOBs.get(0));
		} catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	

	//保存添加的类目规格模板
	@Override
	public FjnyResult addItemParam(Long cid, String paramData) {
		try {
			TbItemParam record = new TbItemParam();
			record.setItemCatId(cid);
			record.setParamData(paramData);
			record.setCreated(new Date());
			record.setUpdated(new Date());
			tbItemParamMapper.insert(record);
			return FjnyResult.ok();
		} catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}



	//更改规格信息
	@Override
	public FjnyResult updateItemParam(TbItem tbItem) {

		TbItemParam record = new TbItemParam();
		record.setCreated(new Date());
		record.setUpdated(new Date());
		tbItemParamMapper.updateByPrimaryKey(record);
		return FjnyResult.ok();
	}

	//删除规格信息
	@Override
	public FjnyResult deleteItemParam(List<Long> ids) {
//		TbItemParamExample example = new TbItemParamExample();
//		Criteria ctiteriaCriteria = example.createCriteria();
//		ctiteriaCriteria.andItemCatIdEqualTo(itemCatId);
		
		
		try {
			TbItemParamExample example = new TbItemParamExample();
			example.createCriteria().andIdIn(ids);
			tbItemParamMapper.deleteByExample(example);
		} catch (Exception e) {
			return FjnyResult.build(500, "删除失败");
		}
		return FjnyResult.ok();
	}

}
