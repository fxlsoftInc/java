package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface MaterialReaderMapper {
    int countByWhere(Where where);

    List<Material> selectByWhereWithBLOBs(Where where);

    List<Material> selectByWhere(Where where);

    Material selectByPrimaryKey(Long id);
    
    Material selectByMaterialId(Long id);

	/**
	  * @Title: selectProdListByWhereWithBLOBs
	  * @Description: 查找商品
	  * @param where
	  * @return  
	  * @return 返回类型  List<Material>   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月9日 下午3:15:38
	  */
	List<Material> selectProdListByWhereWithBLOBs(Where where);

	/**
	  * @Title: countProdListByWhere
	  * @Description: 查找商品 计数
	  * @param where
	  * @return  
	  * @return 返回类型  int   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月9日 下午3:18:17
	  */
	int countProdListByWhere(Where where);
}