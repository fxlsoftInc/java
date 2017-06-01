package com.jialian.api.service;

import com.jialian.api.domain.basic.Page;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.UserQuery;
import com.jialian.api.domain.vo.CollectVo;

public interface UserServiceApi {
	/**
	 * @Description:o2o预约用户登录
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月8日下午1:48:14
	 * @Version:1.0
	 */
	User userLogin(String mobile, String password);
	
	/**
	 * @Description: 添加用户
	 * @param: @param user
	 * @param: @return      
	 * @return: ServiceResult<User>   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	ServiceResult<User> addUser(User user);
	
	/**
	 * @Description: o2o登录用户
	 * @param: @param telephone
	 * @param: @param password
	 * @param: @return      
	 * @return: ServiceResult<User>   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	ServiceResult<User> loginUser(String telephone,String password);

	/**
	 *@Description: o2o个人中心修改密码
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月15日 上午10:57:13
	 *@Version:1.0
	 */
	ServiceResult<User> updateUserPsd(String telephone,String newPsd);

	/**
	 *@Description: o2o个人中心--联系人地址查询
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月15日 下午2:55:54
	 *@Version:1.0
	 */
	ServiceResult<List<Address>> selectUserAddressByUserNo(User user);

	/**
	 * 
	 *@Description: o2o个人中心--根据电话号码查询用户
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月15日 下午4:35:38
	 *@Version:1.0
	 */
	User selectUserByMobile(String mobile);

	/**
	 * 
	 *@Description: o2o个人中心--添加联系人地址
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月16日 上午11:08:02
	 *@Version:1.0
	 */
	ServiceResult<Address> addAddress(String mobile, Address address);

	/**
	 * 
	 *@Description: o2o个人中心--我的收藏列表
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月17日 下午5:49:28
	 *@Version:1.0
	 */
	ServiceResult<List<CollectVo>> selectCollectList(User user);
   
   
	
	/**
	* @Description: o2o找回密码
	* @param @param telephone 
	* @param @param password
	* @param @return
	* @return ServiceResult<User>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 上午11:58:54
	 */
	ServiceResult<User> getBackPassWord(String telephone,String password);
	
	/**
	* @Description: 用户信息查询(分页)
	* @param @param query
	* @param @return
	* @return ServiceResult<User>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月18日 下午12:02:34
	 */
	ServiceResult<Page<User>> getUserMesList(UserQuery query);
	
	/**
	* @Description: 用户信息查询
	* @param @param id 用户id
	* @param @return
	* @return ServiceResult<User>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月15日 下午5:02:24
	 */
	ServiceResult<User> getUserMesById(Long id);
	
	/**
	* @Description: 修改用户信息
	* @param @param user
	* @param @return
	* @return ServiceResult<User>
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月18日 下午2:26:26
	 */
	ServiceResult<User> updateUser(User user,String oldPassWord);

	/**
	 * 删除联系地址
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 上午11:39:27
	 *@Version:1.0
	 */
	int deleteAddressById(Long id);

	List<Address> selectAddressDetailById(Where where);
   
	int updateByPrimaryKeySelective(User record);

	//列表
	List<User> selectByWhere(Where where);

	int countByWhere(Where where);

	
	User selectByPrimaryKey(Long id);
}
