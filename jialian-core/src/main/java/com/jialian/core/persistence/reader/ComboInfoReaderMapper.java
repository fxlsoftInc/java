package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface ComboInfoReaderMapper {
    int countByWhere(Where where);

    List<ComboInfo> selectByWhere(Where where);

    ComboInfo selectByPrimaryKey(Long id);
    
    List<ComboInfo> selectByComboId(Long id);
}