package com.jialian.api.service.Item;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.Where;

public interface ItemServiceApi {

	/**
	* @Description: 修改项目信息
	* @param @param item
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午5:23:09
	 */
	public ServiceResult<Object> updateItem(Item item);
	

	/**
	 * 查询列表，含分页
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 上午10:34:53
	 *@Version:1.0
	 */
	List<Item> selectByWhere(Where where);
	
	/**
	 * 查询列表，含分页
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 上午10:34:53
	 *@Version:1.0
	 */
	List<Item> selectWithHouseStruByWhere(Where where);

	/**
	 * 统计总数
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 上午10:35:07
	 *@Version:1.0
	 */
	int countByWhere(Where where);

	/**
	 * 编辑装修项目
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 上午10:35:17
	 *@Version:1.0
	 */
	int updateByPrimaryKeySelective(Item item);


	/**
	 * 查看装修项目详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午5:44:53
	 *@Version:1.0
	 */
	public Item selectByPrimaryKey(Long id);


	/**
	 * 装修项目添加
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午5:54:41
	 *@Version:1.0
	 */
	public Item addItem(Item item);
}
