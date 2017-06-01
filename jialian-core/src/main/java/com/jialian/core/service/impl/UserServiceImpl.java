package com.jialian.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.UserQuery;
import com.jialian.api.domain.vo.CollectVo;
import com.jialian.api.service.UserServiceApi;
import com.jialian.common.util.MD5;
import com.jialian.common.util.StringUtils;
import com.jialian.core.persistence.reader.AddressReaderMapper;
import com.jialian.core.persistence.reader.CollectReaderMapper;
import com.jialian.core.persistence.reader.UserReaderMapper;
import com.jialian.core.persistence.writer.AddressWriterMapper;
import com.jialian.core.persistence.writer.UserWriterMapper;

@Service("userService")
public class UserServiceImpl implements UserServiceApi{

	@Resource
	private UserReaderMapper userReaderMapper;
	
	@Resource
	private UserWriterMapper userWriterMapper;
	
	@Resource
	private AddressReaderMapper addressReaderMapper;
	
	@Resource
	private AddressWriterMapper addressWriterMapper;
	
	@Resource
	private CollectReaderMapper collectReaderMapper;
	
	@Override
	public User userLogin(String mobile, String password) {
		//手机号和密码都不能为空
		if(mobile == null || password == null)return null;
		//根据mobile和password查询预约用户
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		//添加条件
		criteria.andEqualTo("telephone", mobile).andEqualTo("password", MD5.md5(password));
		//查询数据
		List<User> userList = userReaderMapper.selectByWhere(where);
		//返回结果
		return userList == null || userList.size() == 0? null : userList.get(0);
	}

	@Override
	public ServiceResult<User> addUser(User user) {
		ServiceResult<User> serviceResult=new ServiceResult<User>();
		User u=userReaderMapper.selectUserByTelephone(user.getTelephone());
		if(u!=null){
			serviceResult.setMsgCode(Const.ERROR_USER_HAVE);
			serviceResult.setComment("用户已经存在");
			return serviceResult;
		}
		user.setUserNo("Y"+user.getTelephone());
		//MD5加密
		user.setUserName(user.getTelephone());
		user.setNickName(user.getTelephone());
		user.setPassword(MD5.md5(user.getPassword()));
		user.setRemark("注册用户添加");
		user.setStatus((short)1);
		int i=userWriterMapper.insertSelective(user);
		if(i==1){
			serviceResult.setOk(true);
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("添加成功");
		}else{
			serviceResult.setMsgCode(Const.ERROR_UNKNOWN_CODE);
			serviceResult.setComment("添加失败");
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<User> loginUser(String telephone, String password) {
		ServiceResult<User> serviceResult=new ServiceResult<User>();
		User u=userReaderMapper.selectUserByTelephone(telephone);
		if(u==null){
			serviceResult.setMsgCode(Const.ERROR_USER_NULL);
			serviceResult.setComment("用户不存在");
			return serviceResult;
		}
		if(u.getPassword().equals(MD5.md5(password))){
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("登录成功");
			serviceResult.setOk(true);
			//缓存信息
			u.setPassword(null);
			serviceResult.setData(u);
			//返回所需信息
			Map<String, Object> data=new HashMap<String, Object>();
			data.put("telephone", u.getTelephone());
			serviceResult.setDataMap(data);
		}else{
			serviceResult.setMsgCode(Const.ERROR_TEL_PWD);
			serviceResult.setComment("账户名或密码错误");
		}
		return serviceResult;
	}

	/**
	 * o2o个人中心修改密码
	 */
	@Override
	public ServiceResult<User> updateUserPsd(String telephone,String newPsd) {
		ServiceResult<User> serviceResult = new ServiceResult<User>();
		User user = userReaderMapper.selectUserByTelephone(telephone);
		if(user == null || user.equals("")){
			serviceResult.setMsgCode(Const.ERROR_CODE);
			serviceResult.setOk(false);
			serviceResult.setComment("该用户不存在");
			return serviceResult;
		}else{
			user.setPassword(MD5.md5(newPsd));
			int res = userWriterMapper.updateByNewPsd(user);
			if(res == 1){
				//保存返回数据
				Map<String, Object> data=new HashMap<String, Object>();
				data.put("userName", user.getUserName());
				data.put("mobile", user.getTelephone());
				serviceResult.setDataMap(data);
				serviceResult.setMsgCode(Const.SUCCESS_CODE);
				serviceResult.setOk(true);
				serviceResult.setComment("修改成功");
			}else{
				serviceResult.setMsgCode(Const.ERROR_CODE);
				serviceResult.setOk(false);
				serviceResult.setComment("修改失败");
			}
			return serviceResult;
		}
		
	}

	/**
	 * 个人中心--查询联系人地址
	 */
	@Override
	public ServiceResult<List<Address>> selectUserAddressByUserNo(User user) {
		ServiceResult<List<Address>> serviceResult = new ServiceResult<List<Address>>();
		if(user == null || user.equals("")){
			serviceResult.setOk(false);
			serviceResult.setMsgCode(Const.ERROR_NODATA_CODE);
			serviceResult.setComment("用户不存在");
		}else{
			String userNo = user.getUserNo();
			List<Address> addressList= addressReaderMapper.selectUserAddressByUserNo(userNo);
		    if(addressList != null && addressList.size() > 0){
		    	serviceResult.setData(addressList);
		    	serviceResult.setOk(true);
		    	serviceResult.setMsgCode(Const.SUCCESS_CODE);
		    }else{
		    	serviceResult.setOk(false);
		    	serviceResult.setMsgCode(Const.ERROR_CODE);
		    	serviceResult.setComment("查询失败");
		    }
		}
		return serviceResult;
	}

	/**
	 * 根据电话号码查询用户
	 */
	@Override
	public User selectUserByMobile(String mobile) {
		if(StringUtils.isStrNull(mobile)){
			return null;
		}else{
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("telephone", mobile);
			List<User> userList = userReaderMapper.selectByWhere(where);
			return userList == null || userList.size() == 0? null : userList.get(0);
		}
	}

	/**
	 * 添加联系人地址
	 */
	@Override
	public ServiceResult<Address> addAddress(String mobile, Address address) {
		ServiceResult<Address> result = new ServiceResult<Address>();
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("telephone", mobile);
		System.out.println(address.getId());
		if(address.getId() != null && address.getId() > 0){//修改
		    int res = addressWriterMapper.updateByPrimaryKeySelective(address);
		    if(res == 1){//修改成功
		    	result.setOk(true);
		    	result.setMsgCode(Const.SUCCESS_CODE);
		    	result.setComment("修改成功");
		    }else{//修改失败
		    	result.setOk(false);
		    	result.setMsgCode(Const.ERROR_NODATA_CODE);
		    	result.setComment("修改失败");
		    }
		}else{//添加
			List<User> userList = userReaderMapper.selectByWhere(where);
		    if(userList == null || userList.equals("")){
		    	result.setOk(false);
		    	result.setMsgCode(Const.ERROR_RECORD_DELETED);
		    	result.setComment("该用户不存在或已删除");
		    }else{
		    	User user = userList.get(0);
				String userNo = user.getUserNo();
				address.setAddressNo(userNo);
				address.setAddressType((short)1);
				address.setIsDefault((short)0);
				address.setRemark("添加收货地址");
				address.setStatus((short)1);		
				address.setCreateTime(new Date());		
				if(address == null || address.equals("")){//添加失败
					result.setOk(false);
					result.setMsgCode(Const.ERROR_CODE);
					result.setComment("添加失败");
				}else{//开始添加
					int res = addressWriterMapper.insertLinkAddress(address);
					if(res == 1){//添加成功
						result.setOk(true);
						result.setMsgCode(Const.SUCCESS_CODE);
						result.setComment("添加成功");
					}else{//添加失败
						result.setOk(false);
						result.setMsgCode(Const.ERROR_CODE);
						result.setComment("添加失败");
					}
				}
		    }
		}
		
		return result;
	}

	/**
	 * o2o个人中心--我的收藏列表
	 */
	@Override
	public ServiceResult<List<CollectVo>> selectCollectList(User user) {
		
		ServiceResult<List<CollectVo>> serviceResult = new ServiceResult<List<CollectVo>>();
		List<CollectVo> collectVoList = collectReaderMapper.selectCollectByUid(user.getId());
		serviceResult.setOk(true);
		serviceResult.setMsgCode(Const.SUCCESS_CODE);
		if(collectVoList != null && collectVoList.size() > 0){
			for (int i = 0; i < collectVoList.size(); i++) {
				CollectVo collectVo = collectVoList.get(i);
				Where where = new Where();
				Criteria criteria = where.createCriteria();
				criteria.andEqualTo("parent_id", collectVo.getParentId());
				Integer collectCount = collectReaderMapper.countByWhere(where);
				collectVo.setCollectCount(collectCount);
			}
			serviceResult.setData(collectVoList);
		}
		return serviceResult;
	}

   @Override
	public ServiceResult<User> getBackPassWord(String telephone, String password) {
		ServiceResult<User> serviceResult=new ServiceResult<User>();
		User u=userReaderMapper.selectUserByTelephone(telephone);
		if(u==null){
			serviceResult.setMsgCode(Const.ERROR_USER_NULL);
			serviceResult.setComment("用户不存在");
			return serviceResult;
		}
		User user=new User();
		user.setId(u.getId());
		user.setPassword(MD5.md5(password));
		int i=userWriterMapper.updateByPrimaryKeySelective(user);
		if(i==1){
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("修改成功");
		}
		return serviceResult;
	}
	@Override
	public ServiceResult<Page<User>> getUserMesList(UserQuery query) {
		ServiceResult<Page<User>> result=new ServiceResult<Page<User>>();
		int countRecord=userReaderMapper.selectUserCountByQuery(query);
		Page<User> page=new Page<User>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<User> list=userReaderMapper.selectUserListByQuery(query);
			page.setList(list);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setData(page);
		return result;
	}
	
	@Override
	public ServiceResult<User> getUserMesById(Long id) {
		ServiceResult<User> result=new ServiceResult<User>();
		User user=userReaderMapper.selectByPrimaryKey(id);
		if(user==null){
			result.setMsgCode(Const.ERROR_USER_NULL);
			result.setComment("用户不存在");
			return result;
		}
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		user.setPassword("******");
		result.setData(user);
		return result;
	}

	@Override
	public ServiceResult<User> updateUser(User user,String oldPassWord) {
		ServiceResult<User> result=new ServiceResult<User>();
		User u=userReaderMapper.selectByPrimaryKey(user.getId());
		if(u==null){
			result.setMsgCode(Const.ERROR_USER_NULL);
			result.setComment("用户不存在");
			return result;
		}
		if(!StringUtils.isStrNull(oldPassWord)){
			if(!MD5.md5(oldPassWord).equals(u.getPassword())){
				result.setMsgCode(Const.ERROR_PASSWORD);
				result.setComment("原密码错误");
				return result;
			}
		}
		int i=userWriterMapper.updateByPrimaryKeySelective(user);
		if(i>0){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
			user.setPassword(null);
			result.setData(user);
		}
		return result;
	}

	/**
	 * 删除联系地址
	 */
	@Override
	public int deleteAddressById(Long id) {
		int result = 0;
		Address address = addressReaderMapper.selectByPrimaryKey(id);
		if(address == null || address.equals("")){
			return result;
		}else{
			address.setStatus((short)0);
			address.setCreateTime(new Date());
			result = addressWriterMapper.deleteByPrimaryKey(id);
			return result;
		}
	}

	/**
	 * 查看地址详情
	 */
	@Override
	public List<Address> selectAddressDetailById(Where where) {
		return addressReaderMapper.selectByWhere(where);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<User> selectByWhere(Where where) {
		return userReaderMapper.selectByWhere(where);
	}

	@Override
	public int countByWhere(Where where) {
		return userReaderMapper.countByWhere(where);
	}


	public User selectByPrimaryKey(Long id){
		return userReaderMapper.selectByPrimaryKey(id);
	}
}
