package org.cyb.service;

import java.util.List;

import org.cyb.utils.EasyUITreeNodeBean;

public interface TbItemCatService {
	List<EasyUITreeNodeBean> getTbItemCatList(Long parendId);
}
