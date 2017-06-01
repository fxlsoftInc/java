package com.jialian.api.service.ResourceInfo;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;

public interface ResourceInfoServiceApi {
	
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
	 List<ResourceInfo> selectByWhere(Where where);

	 /**
	  * 3
	  * @Description:
	  * @Param:@param id
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:10:37
	  * @Version:1.0
	  */
	 ResourceInfo selectByPrimaryKey(Long id);
	 
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
	 ResourceInfo insert(ResourceInfo record);

	 /**
	  * 7
	  * @Description:选择性插入数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:12:55
	  * @Version:1.0
	  */
	 ResourceInfo insertSelective(ResourceInfo record);

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
	 int updateByWhereSelective(ResourceInfo record, Where where);

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
	 int updateByWhere(ResourceInfo record, Where where);

	 /**
	  * 10
	  * @Description:根据ID选择性进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:10
	  * @Version:1.0
	  */
	 int updateByPrimaryKeySelective(ResourceInfo record);

	 /**
	  * 11
	  * @Description:根据ID进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:49
	  * @Version:1.0
	  */
	 int updateByPrimaryKey(ResourceInfo record);

	 /**
	  * 12
	  *@Description: 后台--首页轮播图展示
	  *@Author: shx  408640463@qq.com
	  *@Since:2016年1月12日 下午12:08:46
	  *@Version:1.0
	  */
	ServiceResult<List<ResourceInfo>> selectResourceInfoByHomeType(Short type);

	ServiceResult<List<ResourceInfo>> selectResouceInfoByType(Short type);
}
