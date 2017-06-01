package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.Where;
import java.util.List;

public interface ItemReaderMapper {
	
    int countByWhere(Where where);

    List<Item> selectByWhere(Where where);

    Item selectByPrimaryKey(Long id);

    List<Item> selectWithHouseStruByWhere(Where where);
}