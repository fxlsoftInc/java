package com.jialian.api.service.Combo;

import java.util.List;

import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Where;

public interface ComboInfoServiceApi {

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
    List<ComboInfo> selectByWhere(Where where);

    /**
	  * 3
	  * @Description:
	  * @Param:@param id
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:10:37
	  * @Version:1.0
	  */
    ComboInfo selectByPrimaryKey(Long id);
    
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
    int insert(ComboInfo record);

    /**
	  * 7
	  * @Description:选择性插入数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:12:55
	  * @Version:1.0
	  */
    int insertSelective(ComboInfo record);

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
    int updateByWhereSelective(ComboInfo record, Where where);

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
    int updateByWhere(ComboInfo record, Where where);

    /**
	  * 10
	  * @Description:根据ID选择性进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:10
	  * @Version:1.0
	  */
    int updateByPrimaryKeySelective(ComboInfo record);

    /**
	  * 11
	  * @Description:根据ID进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:49
	  * @Version:1.0
	  */
    int updateByPrimaryKey(ComboInfo record);
    
}
