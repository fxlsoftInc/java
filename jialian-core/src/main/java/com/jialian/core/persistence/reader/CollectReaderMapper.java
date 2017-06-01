package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.Collect;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.CollectVo;

public interface CollectReaderMapper {
    int countByWhere(Where where);

    List<Collect> selectByWhere(Where where);

    Collect selectByPrimaryKey(Long id);

    /**
     * 收藏列表
     *@Description:
     *@Author: shx  408640463@qq.com
     *@Since:2015年12月17日 下午6:55:45
     *@Version:1.0
     */
	List<CollectVo> selectCollectByUid(long id);
	
	/**
	* @Description: 查询收藏
	* @param @param userId 
	* @param @param parentId 
	* @param @param collectType
	* @param @return
	* @return Collect
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月28日 下午5:42:01
	 */
	Collect selectCollectByUidAndParidAndTypeid(@Param("userId")Long userId,@Param("parentId")Long parentId,
			@Param("collectType")short collectType);
}