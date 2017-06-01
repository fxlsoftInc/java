package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.UserQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserReaderMapper {
    int countByWhere(Where where);

    List<User> selectByWhere(Where where);

    User selectByPrimaryKey(Long id);
    
    User selectUserByTelephone(String telephone);
    
    int selectUserCountByQuery(@Param("query")UserQuery query);
    
    List<User> selectUserListByQuery(@Param("query")UserQuery query);

}