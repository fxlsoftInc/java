package com.jialian.api.service.Admin;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.AdminRoleQuery;
import com.jialian.api.domain.query.AdminUserQuery;
import com.jialian.api.domain.vo.AdminUserVo;

public interface AdminUserServiceApi {
	
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
    List<AdminUser> selectByWhere(Where where);
    
    /**
    * @Description: 用户列表查询
    * @param @param query 查询条件封装类
    * @param @return
    * @return ServiceResult<Page<AdminUser>>
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2016年1月13日 下午2:30:29
     */
    ServiceResult<Page<AdminUserVo>> getAdminUserListByQuery(AdminUserQuery query);

    /**
	  * 3
	  * @Description:
	  * @Param:@param id
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:10:37
	  * @Version:1.0
	  */
    AdminUser selectByPrimaryKey(Long id);
    
    /**
    * @Description: 获取管理员信息
    * @param @param id
    * @param @return
    * @return ServiceResult<AdminUser>
    * @throws     
    * @author 卢光磊  lgl1012cto@foxmail.com 
    * @date: 2016年1月13日 下午5:36:40
     */
    ServiceResult<AdminUserVo> getAdminUserById(Long id);
    
    
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
    int insert(AdminUser record);

    /**
	  * 7
	  * @Description:选择性插入数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:12:55
	  * @Version:1.0
	  */
    int insertSelective(AdminUser record);

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
    int updateByWhereSelective(AdminUser record, Where where);

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
    int updateByWhere(AdminUser record, Where where);

    /**
	  * 10
	  * @Description:根据ID选择性进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:10
	  * @Version:1.0
	  */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
	  * 11
	  * @Description:根据ID进行修改数据
	  * @Param:@param record
	  * @Param:@return
	  * @Author: FxLsoft fxlsoft@163.com
	  * @Since:2015年12月9日下午3:14:49
	  * @Version:1.0
	  */
    int updateByPrimaryKey(AdminUser record);
    
    /**
     * 12
     * @Description:根据UserID获取相应的接口/模块的权限
     * @Param:@param userId
     * @Param:@return
     * @Author: FxLsoft fxlsoft@163.com
     * @Since:2015年12月10日下午2:38:30
     * @Version:1.0
     */
    List<AdminPermission> selectPermissionByUserId(Long userId);

    /**
     * 后台--删除管理员
     *@Description:
     *@Author: shx  408640463@qq.com
     *@Since:2015年12月21日 下午5:48:12
     *@Version:1.0
     */
	int deleteAdminUserById(Long id);

	/**
	 * 添加账户
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月4日 上午11:30:44
	 *@Version:1.0
	 */
	int addAdminUser(AdminUser adminUser, AdminRole adminRole);
	
	/**
	* @Description: 添加账户(针对页面新增的接口)
	* @param @param adminUser
	* @param @return
	* @return ServiceResult<Object>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月12日 下午6:44:28
	 */
	ServiceResult<Object> addAdminUser_new(AdminUser adminUser);

	/**
	 * 系统设置--管理账户列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月4日 下午3:19:57
	 *@Version:1.0
	 */
	ServiceResult<List<AdminUserVo>> selectAdminRoleList(AdminRoleQuery query);

	/**
	 * 查询角色名称
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月18日 上午11:27:47
	 *@Version:1.0
	 */
	List<AdminUserVo> selectRoleNameByRoleType(String roleType);
}
