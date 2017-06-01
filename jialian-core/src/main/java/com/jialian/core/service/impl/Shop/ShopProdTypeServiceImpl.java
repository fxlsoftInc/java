/**
 * 
 */package com.jialian.core.service.impl.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月20日 下午1:49:05  */import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.ShopProdType;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Shop.ShopProdTypeServiceApi;
import com.jialian.core.persistence.reader.ShopProdTypeReaderMapper;
import com.jialian.core.persistence.writer.ShopProdTypeWriterMapper;

/**
 *@Description:
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月20日 下午1:49:05
 *@Version:1.0
 */
@Service("shopProdTypeService")
public class ShopProdTypeServiceImpl implements ShopProdTypeServiceApi {

	@Resource
	private ShopProdTypeReaderMapper shopProdTypeReaderMapper;
	@Resource
	private ShopProdTypeWriterMapper shopProdTypeWriterMapper;
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#selectByWhere(com.jialian.api.domain.entity.Where)
	 */
	@Override
	public List<ShopProdType> selectByWhere(Where where) {
		// 自动生成的方法存根
		return shopProdTypeReaderMapper.selectByWhere(where);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// 自动生成的方法存根
		return shopProdTypeWriterMapper.deleteByPrimaryKey(id);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#insertSelective(com.jialian.api.domain.entity.ShopProdType)
	 */
	@Override
	public int insertSelective(ShopProdType record) {
		// 自动生成的方法存根
		return shopProdTypeWriterMapper.insertSelective(record);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#updateByPrimaryKeySelective(com.jialian.api.domain.entity.ShopProdType)
	 */
	@Override
	public int updateByPrimaryKeySelective(ShopProdType record) {
		// 自动生成的方法存根
		return shopProdTypeWriterMapper.updateByPrimaryKeySelective(record);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#countByWhere(com.jialian.api.domain.entity.Where)
	 */
	@Override
	public int countByWhere(Where where) {
		// 自动生成的方法存根
		return shopProdTypeReaderMapper.countByWhere(where);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopProdTypeServiceApi#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ShopProdType selectByPrimaryKey(Long id) {
		// 自动生成的方法存根
		return shopProdTypeReaderMapper.selectByPrimaryKey(id);
	}

}
