/**
 * 
 */package com.jialian.core.service.impl.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 下午1:46:17  */import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.ShopCart;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Shop.ShopCartServiceApi;
import com.jialian.core.persistence.reader.MaterialAttributeReaderMapper;
import com.jialian.core.persistence.reader.ShopCartReaderMapper;
import com.jialian.core.persistence.writer.ShopCartWriterMapper;

/**
 *@Description: 
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 下午1:46:17
 *@Version:1.0
 */
@Service("shopCartService")
public class ShopCartServiceImpl implements ShopCartServiceApi {

	@Resource
	private ShopCartReaderMapper shopCartReaderMapper;
	@Resource
	private ShopCartWriterMapper shopCartWriterMapper;
	@Resource
	private MaterialAttributeReaderMapper materialAttributeReaderMapper;
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopCartServiceApi#selectShopCartByWhere(com.jialian.api.domain.entity.Where)
	 */
	@Override
	public List<ShopCart> selectByUserId(Long userId) {
		return shopCartReaderMapper.selectByUserId(userId);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopCartServiceApi#addShopCart(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer, java.util.Date)
	 */
	@Override
	public int addShopCart(Long userId, Long proId, Long proAttrId,
			Integer count, Date createTime) {
		int flag=0;
		Where where2 = new Where();
		Criteria criteria2 = where2.createCriteria();
		criteria2.andEqualTo("user_id", userId);
		criteria2.andEqualTo("pro_attr_id", proAttrId);
		MaterialAttribute matattr=materialAttributeReaderMapper.selectByPrimaryKey(proAttrId);
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("user_id", userId);
		criteria.andEqualTo("pro_attr_id", proAttrId);
		//查找数据库里是否有该记录
		ShopCart sc=shopCartReaderMapper.selectByUserIdProAttrId(where);
		if(sc!=null){//有
			Integer sum=sc.getCount()+count;
			//判断购物车里的是否大于当前库存
			if( sum <= matattr.getCurrentInventory()){//小于等于
				sc.setCount(sum);
				flag=shopCartWriterMapper.updateByPrimaryKeySelective(sc);
			}else{//大于
				flag=2;
			}
			
		}else{//无
			//判断当前库存是否大于0
			if(matattr.getCurrentInventory()>0){//大于
				ShopCart shopCart=new ShopCart();
				shopCart.setCount(count);
				shopCart.setCreateTime(createTime);
				shopCart.setProAttrId(proAttrId);
				shopCart.setProId(proId);
				shopCart.setRemark("");
				shopCart.setUserId(userId);
				
				flag=shopCartWriterMapper.insertSelective(shopCart);
			}else{//小于
				flag=2;
			}
			
		}
		
		return flag;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopCartServiceApi#deleteShopCart(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int deleteShopCart(Long userId, Long proAttrId) {
		int flag=0;
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("user_id", userId);
		criteria.andEqualTo("pro_attr_id", proAttrId);
		ShopCart sc=shopCartReaderMapper.selectByUserIdProAttrId(where);
		if(sc!=null){
			flag=shopCartWriterMapper.deleteByPrimaryKey(sc.getId());
		}else{
			flag=1;
		}
		return flag;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopCartServiceApi#updateShopCart(java.lang.Long, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public int updateShopCart(Long userId, Long proAttrId, Integer count) {
		int flag=0;
		Where where2 = new Where();
		Criteria criteria2 = where2.createCriteria();
		criteria2.andEqualTo("user_id", userId);
		criteria2.andEqualTo("pro_attr_id", proAttrId);
		MaterialAttribute matattr=materialAttributeReaderMapper.selectByPrimaryKey(proAttrId);
		
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("user_id", userId);
		criteria.andEqualTo("pro_attr_id", proAttrId);
		//查询数据库里有没有记录
		ShopCart sc=shopCartReaderMapper.selectByUserIdProAttrId(where);
		if(sc!=null){//有
			//判断购物车里的是否大于当前库存
			if(count <= matattr.getCurrentInventory()){//小于等于
				sc.setCount(count);
				flag=shopCartWriterMapper.updateByPrimaryKeySelective(sc);
			}else{//大于
				flag=2;
			}
			
		}else{//无
			flag=3;
		}
		return flag;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopCartServiceApi#selectById(long)
	 */
	public ShopCart selectById(Long scid) {
		return shopCartReaderMapper.selectById(scid);
	}
	@Override
	public List<ShopCart> selectByUserId_ids(Map<String, Object> map) {
		return shopCartReaderMapper.selectByUserId_ids(map);
	}

}
