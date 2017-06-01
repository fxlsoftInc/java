package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.AdminRule;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface AdminRuleReaderMapper {
    int countByWhere(Where where);

    List<AdminRule> selectByWhere(Where where);

    AdminRule selectByPrimaryKey(Long id);
}