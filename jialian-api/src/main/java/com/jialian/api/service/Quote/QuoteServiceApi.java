package com.jialian.api.service.Quote;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.LaborCost;
import com.jialian.api.domain.entity.SemiDecorationOrderInfo;
import com.jialian.api.domain.query.QuoteOnlineQuery;
import com.jialian.api.domain.vo.QuoteOnlineVO;

public interface QuoteServiceApi {
	ServiceResult<QuoteOnlineVO> quoteOnline(QuoteOnlineQuery qQuote);
	
	LaborCost insertSelective(LaborCost record);
	
	int updateByPrimaryKeySelective(LaborCost record);
	
	SemiDecorationOrderInfo insertSelective(SemiDecorationOrderInfo record);
	
	int updateByPrimaryKeySelective(SemiDecorationOrderInfo record);
}
