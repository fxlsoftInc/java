package com.jialian.api.service;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.ModelHouse;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.domain.query.ModelHouseQuery;
import com.jialian.api.domain.vo.ModelHouseDetailVo;
import com.jialian.api.domain.vo.ModelHouseVo;

public interface ModelHouseServiceApi {

	/**
	 * @Description: 样板房列表
	 * @param: @param query 查询封装类
	 * @param: @return   
	 * @return: ServiceResult<Page<ModelHouse>>   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	ServiceResult<Page<ModelHouseVo>> getModelHouseListByQuery(ModelHouseQuery query);
	
	/**
	* @Description: 样板房信息更改
	* @param @param query
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月30日 上午11:06:06
	 */
	ServiceResult<Object> updateModelHouseById(ModelHouse modelHouse);

	/**
	 * 后台--样板房列表，包括分页
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午1:49:29
	 *@Version:1.0
	 */
	List<ModelHouse> selectByWhere(Where where);

	/**
	 *  后台--样板房列表计数
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午1:50:06
	 *@Version:1.0
	 */
	int countByWhere(Where where);

	/**
	 * 后台--根据id查询样板房
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午3:04:38
	 *@Version:1.0
	 */
	ModelHouse selectByPrimaryKey(Long id);
	
	/**
	 * 后台--根据id查询样板房
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午3:04:38
	 *@Version:1.0
	 */
	ModelHouseDetailVo selectModelHouseDetail(Long id);

	/**
	 * 后台--根据样板房id删除
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午3:07:45
	 *@Version:1.0
	 */
	int updateModelHouseByPrimaryKey(ModelHouse modelHouse);

	/**
	 * 后台--添加样板房
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午5:01:56
	 *@Version:1.0
	 */
	ModelHouse addModelHouse(ModelHouse modelHouse);

	/**
	 * 后台--修改样板房
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月14日 下午3:13:58
	 *@Version:1.0
	 */
	ServiceResult<ModelHouseDetailVo> editModelHouse(ModelHouse modelHouse, Address address,
			ResourceInfoReplacePara resourceInfo);

}
