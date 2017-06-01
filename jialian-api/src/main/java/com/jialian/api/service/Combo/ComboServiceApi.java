package com.jialian.api.service.Combo;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.ComboQuery;
import com.jialian.api.domain.vo.ComboComplexVO;
import com.jialian.api.domain.vo.ComboVo;

public interface ComboServiceApi {

	/**
	 * 1
	 * @Description:根据条件进行统计
	 * @Param:@param where 条件类
	 * @Param:@return int
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月9日下午3:09:05
	 * @Version:1.0
	 */
	int countByWhere(Where where);

	/**
	  * 2
	  * @Description:根据条件进行查询
	  * @Param:@param where 条件类
	  * @Param:@return List<ResourceInfo>
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:09:47
	  * @Version:1.0
	  */
    List<Combo> selectByWhere(Where where);

    /**
	  * 3
	  * @Description:
	  * @Param:@param id
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:10:37
	  * @Version:1.0
	  */
    Combo selectByPrimaryKey(Long id);
    
    /**
	  * 4
	  * @Description:根据条件进行删除
	  * @Param:@param where
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:10:59
	  * @Version:1.0
	  */
    int deleteByWhere(Where where);

    /**
	  * 5
	  * @Description:根据ID进行删除
	  * @Param:@param id
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:11:31
	  * @Version:1.0
	  */
    int deleteByPrimaryKey(Long id);

    /**
	  * 6
	  * @Description:
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:11:51
	  * @Version:1.0
	  */
    Combo insert(Combo record);

    /**
	  * 7
	  * @Description:选择性插入数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:12:55
	  * @Version:1.0
	  */
    Combo insertSelective(Combo record);

    /**
	  * 8
	  * @Description:根据条件进行选择性修改数据
	  * @Param:@param record
	  * @Param:@param where
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:13:15
	  * @Version:1.0
	  */
    int updateByWhereSelective(Combo record, Where where);

    /**
	  * 9
	  * @Description:根据条件进行修改数据
	  * @Param:@param record
	  * @Param:@param where
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:13:35
	  * @Version:1.0
	  */
    int updateByWhere(Combo record, Where where);

    /**
	  * 10
	  * @Description:根据ID选择性进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:10
	  * @Version:1.0
	  */
    int updateByPrimaryKeySelective(Combo record);

    /**
	  * 11
	  * @Description:根据ID进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:49
	  * @Version:1.0
	  */
    int updateByPrimaryKey(Combo record);
    
	/**
	* @Description: o2o套餐组合信息
	* @param @param id 套餐ID
	* @param @return
	* @return ServiceResult<Combo>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 下午5:56:29
	 */
	public ServiceResult<List<ComboVo>> getComboList(Long id);
	
    /**
    * @Description: 套餐信息修改
    * @param @param combo
    * @param @param comboInfo
    * @param @return
    * @return ServiceResult<Object>
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2015年12月21日 下午4:24:18
     */
    ServiceResult<Object> updateCombo(Combo combo,ComboInfo comboInfo);
    
    /**
    * @Description: 套餐信息返回(分页)
    * @param @param query
    * @param @return
    * @return ServiceResult<Page<Combo>>
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2016年1月4日 下午3:49:35
     */
    ServiceResult<Page<Combo>> getComboListByQuery(ComboQuery query);
    
    /**
    * @Description: 套餐添加
    * @param @param combo
    * @param @return
    * @return ServiceResult<Object>
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2016年1月4日 下午4:16:24
     */
    ServiceResult<Object> addCombo(Combo combo);
    
    /**
     * 查询复杂的套餐结构
     * @Description:
     * @Param:@param id
     * @Param:@return
     * @Author: FxLsoft fxlsoft@163.com
     * @Since:2016年1月7日下午2:40:24
     * @Version:1.0
     */
    ComboComplexVO selectComplexComboByPrimaryKey(Long id);

    /**
     * 查询套餐名称列表
     *@Description:
     *@Author: shx  408640463@qq.com
     *@Since:2016年1月18日 上午10:54:28
     *@Version:1.0
     */
	List<Combo> selectComboList();
	
	/**
	* @Description: 套餐列表(所有)
	* @param @return
	* @return ServiceResult<List<Combo>>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月21日 下午4:54:03
	 */
	ServiceResult<List<Combo>> getComboAll();
}
