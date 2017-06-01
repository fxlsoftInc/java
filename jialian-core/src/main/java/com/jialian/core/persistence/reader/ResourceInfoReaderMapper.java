package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResourceInfoReaderMapper {
    int countByWhere(Where where);

    List<ResourceInfo> selectByWhere(Where where);

    ResourceInfo selectByPrimaryKey(Long id);
    
    List<ResourceInfo> selectByNo(String no);
    
    List<ResourceInfo> selectImgByObjectTypeAndObjectId(@Param("objectType")Short objectType,@Param("objectId")Long objectId,@Param("no")String no);

	List<ResourceInfo> selectResourceInfoByHomeType(@Param("objectType")Short type);
	
  
}