package org.cyb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.cyb.mapper.TbItemParamItemMapper;
import org.cyb.pojo.TbItemParamItem;
import org.cyb.pojo.TbItemParamItemExample;
import org.cyb.utils.JsonUtils;
import org.springframework.stereotype.Service;

@Service
public class TbItemParamItemServiceImpl implements org.cyb.service.TbItemParamItemService {
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public String getTbItemParamByItemId(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		String paramData = list.get(0).getParamData();
		if (list == null || list.isEmpty()) {
			return "";
		}
		List<Map> groups = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<ul>");
		for (int i = 0; i < groups.size(); i++) {
			Map map = groups.get(i);
			String group = (String) map.get("group");
			//显示组别
			sb.append("<li><table border=\"1\"><tbody><tr><td colspan=\"2\" class=\"group\">");
			sb.append(group);
			sb.append("</td></tr>");
			List<Map> params = (List<Map>) map.get("params");
			//遍历
			for (int j = 0; j < params.size(); j++) {
				Map param = params.get(j);
				String key = (String) param.get("k");
				sb.append("<tr><td class=\"param\"><span>");
				sb.append(key);
				sb.append("</span>:</td>");
				String value = (String) param.get("v");
				sb.append("<td>");
				sb.append(value);
				sb.append("</td>");
			}
			sb.append("</tbody></table></li>");
		}
		return sb.toString();
	}
}
