package com.jialian.core.persistence.reader;

import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.Where;

import java.util.List;

public interface AddressReaderMapper {
    int countByWhere(Where where);

    List<Address> selectByWhere(Where where);

    Address selectByPrimaryKey(Long id);

    /*
     *查询收货地址 
     */
	List<Address> selectUserAddressByUserNo(String userNo);
}