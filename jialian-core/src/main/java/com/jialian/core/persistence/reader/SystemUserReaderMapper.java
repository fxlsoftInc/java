package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.SystemUser;

public interface SystemUserReaderMapper {
	
    SystemUser selectByPrimaryKey(Integer userId);
    
    int selectSystemUserCount(@Param("data")SystemUser systemUser);
    
    List<SystemUser> selectSystemUser(@Param("data")SystemUser systemUser,@Param("startIndex")int startIndex,@Param("onePageCount")int onePageCount);
}