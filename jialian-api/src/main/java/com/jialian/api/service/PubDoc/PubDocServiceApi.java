package com.jialian.api.service.PubDoc;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.PubDoc;
import com.jialian.api.domain.vo.PubDocVo;

public interface PubDocServiceApi {

	/**
	* @Description: 获取文档 
	* @param @return
	* @return ServiceResult<List<PubDoc>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午2:48:34
	 */
	public ServiceResult<List<PubDocVo>> getPubDoc();
	
	/**
	* @Description: 文档详情
	* @param @param id
	* @param @return
	* @return ServiceResult<PubDocVo>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午4:26:20
	 */
	public ServiceResult<PubDocVo> getPubDocDetailed(Long id);
	
	/**
	* @Description: 编辑文档
	* @param @param pubDoc
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月19日 下午4:54:44
	 */
	public ServiceResult<Object> updatePubDoc(PubDoc pubDoc);
}
