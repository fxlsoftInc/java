package com.jialian.core.service.impl.Quote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.HouseStructureInfo;
import com.jialian.api.domain.entity.LaborCost;
import com.jialian.api.domain.entity.SemiDecorationOrderInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.QuoteOnlineQuery;
import com.jialian.api.domain.vo.QuoteOnlineVO;
import com.jialian.api.domain.vo.SemDecOrderInfoVO;
import com.jialian.api.domain.vo.SemDecorationOrderVO;
import com.jialian.api.service.Quote.QuoteServiceApi;
import com.jialian.core.persistence.reader.HouseStructureInfoReaderMapper;
import com.jialian.core.persistence.reader.SemiDecorationOrderInfoReaderMapper;
import com.jialian.core.persistence.reader.SemiDecorationOrderReaderMapper;
import com.jialian.core.persistence.writer.LaborCostWriterMapper;
import com.jialian.core.persistence.writer.SemiDecorationOrderInfoWriterMapper;

@Service("quoteService")
public class QuoteServiceImpl implements QuoteServiceApi{

	@Resource
	private HouseStructureInfoReaderMapper hStruInfoReaderMapper;
	
	@Resource
	private SemiDecorationOrderInfoReaderMapper semDecOrderInfoReaderMapper;
	
	@Resource
	private SemiDecorationOrderReaderMapper semDecOrderReaderMapper;
	
	@Resource
	private LaborCostWriterMapper laborCostWriterMapper;
	
	@Resource
	private SemiDecorationOrderInfoWriterMapper semiDecorationOrderInfoWriterMapper;
	
	@Override
	public ServiceResult<QuoteOnlineVO> quoteOnline(QuoteOnlineQuery qQuote) {
		ServiceResult<QuoteOnlineVO> result = new ServiceResult<QuoteOnlineVO>();
		//创建查询条件
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		List<Object> houseSType = new ArrayList<>();
		//判断参数
		QuoteOnlineVO qVO = new QuoteOnlineVO();
		Double totalWeight = 0d;
		//卧室-主卧和次卧
		if(qQuote.getWo() >= 1){
			houseSType.add(1);
			qVO.setZhuCount(1);
			qVO.setCiwoCount(qQuote.getWo() - 1);
			if(qVO.getCiwoCount() >= 0)houseSType.add(7);
		}
		//客厅
		if(qQuote.getTing() >= 1){
			houseSType.add(2);
			qVO.setKetingCount(qQuote.getTing());
		}
		//厨房
		if(qQuote.getChu() >= 1){
			houseSType.add(3);
			qVO.setChuCount(qQuote.getChu());
		}
		//卫生间
		if(qQuote.getWei() >= 1){
			houseSType.add(4);
			qVO.setWeiCount(qQuote.getWei());
		}
		//阳台
		if(qQuote.getYang() >= 1){
			houseSType.add(5);
			qVO.setYangCount(qQuote.getYang());
		}
		//其他
		criteria.andIn("type", houseSType);
		List<HouseStructureInfo> houseStruInfoList = hStruInfoReaderMapper.selectByWhere(where);
		Map<String, Double> hashWeight = new HashMap<String, Double>();
		for(HouseStructureInfo hsInfo:houseStruInfoList){
			switch(hsInfo.getType()){
				case 1:{
					hashWeight.put("zhuwo", hsInfo.getConversionRate() * qVO.getZhuCount());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getZhuCount();
					break;
				}
				case 2:{
					hashWeight.put("keting", hsInfo.getConversionRate() * qVO.getKetingCount());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getKetingCount();
					break;
				}
				case 3:{
					hashWeight.put("chufang", hsInfo.getConversionRate() * qVO.getChuCount());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getChuCount();
					break;
				}
				case 4:{
					hashWeight.put("weishengjian", hsInfo.getConversionRate());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getWeiCount();
					break;
				}
				case 5:{
					hashWeight.put("yangtai", hsInfo.getConversionRate() * qVO.getYangCount());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getYangCount();
					break;
				}
				case 6:{
					hashWeight.put("qita", hsInfo.getConversionRate());
					break;
				}
				case 7:{
					hashWeight.put("ciwo", hsInfo.getConversionRate() * qVO.getCiwoCount());
					totalWeight = totalWeight + hsInfo.getConversionRate() * qVO.getCiwoCount();
					break;
				}
				default:break;
			}
		}
		//半包清单
		SemDecorationOrderVO semDecOrderVO = semDecOrderReaderMapper.selectSemDecOrderByPrimaryKey((long)qQuote.getType() + 1);
		Double totalPrice = 0d;
		Double semPrice = 0d;
		for(int i = 0; i< semDecOrderVO.getSemDecOrderInfo().size(); i++){
			SemDecOrderInfoVO sVO = semDecOrderVO.getSemDecOrderInfo().get(i);
			//适合用面积计算的 设置工程量
			if(sVO.getItemType() == (short)1){
				Double proSize = 0d;
				switch(sVO.getHouStrType()){
					case 1:{
						if(hashWeight.get("zhuwo") == null){
							break;
						}else{
							proSize = qQuote.getZhuwoArea() <= 0? (hashWeight.get("zhuwo")/totalWeight) * qQuote.getArea() : qQuote.getZhuwoArea();
						}
						qVO.setZhuArea(proSize);
						break;
					}
					case 2:{
						if(hashWeight.get("keting") == null){
							proSize = 0d;
						}else{
							proSize = qQuote.getTingArea() <= 0? (hashWeight.get("keting")/totalWeight) * qQuote.getArea() : qQuote.getTingArea();
						}
						qVO.setKetingArea(proSize);
						break;
					}
					case 3:{
						if(hashWeight.get("chufang") == null){
							proSize = 0d;
						}else{
							proSize = qQuote.getChuArea() <= 0? (hashWeight.get("chufang")/totalWeight) * qQuote.getArea() : qQuote.getChuArea();
						}
						qVO.setChuArea(proSize);
						break;
					}
					case 4:{
						if(hashWeight.get("weishengjian") == null){
							proSize = 0d;
						}else{
							proSize = qQuote.getWeiArea() <= 0? (hashWeight.get("weishengjian")/totalWeight) * qQuote.getArea() : qQuote.getWeiArea();
						}
						qVO.setWeiArea(proSize);
						break;
					}
					case 5:{
						if(hashWeight.get("yangtai") == null){
							proSize = 0d;
						}else{
							proSize = qQuote.getYangArea() <= 0? (hashWeight.get("yangtai")/totalWeight) * qQuote.getArea() : qQuote.getYangArea();
						}
						qVO.setYangArea(proSize);
						break;
					}
					case 6:{
						if(hashWeight.get("qita") == null){
							proSize = 0d;
						}else{
							proSize = hashWeight.get("qita") * qQuote.getArea();
						}
						break;
					}
					case 7:{
						if(hashWeight.get("ciwo") == null){
							proSize = 0d;
						}else{
							proSize = qQuote.getCiWoArea() <=0? (hashWeight.get("ciwo")/totalWeight) * qQuote.getArea() : qQuote.getCiWoArea();
						}
						qVO.setCiwoArea(proSize);
						break;
					}
					default:break;
				}
				sVO.setProjectSize(proSize);
			}else{
				switch(sVO.getHouStrType()){
					case 1:{
						if(hashWeight.get("zhuwo") == null || hashWeight.get("zhuwo") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					case 2:{
						if(hashWeight.get("keting") == null || hashWeight.get("keting") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					case 3:{
						if(hashWeight.get("chufang") == null || hashWeight.get("chufang") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					case 4:{
						if(hashWeight.get("weishengjian") == null || hashWeight.get("weishengjian") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					case 5:{
						if(hashWeight.get("yangtai") == null || hashWeight.get("yangtai") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					case 7:{
						if(hashWeight.get("ciwo") == null || hashWeight.get("ciwo") <= 0){
							sVO.setProjectSize(0d);
						}
						break;
					}
					default:break;
				}
			}
			//计算
			sVO.setTotalMainMaterialPrice(sVO.getMainMaterialPrice() * sVO.getProjectSize());
			sVO.setTotalAuxiMaterialPrice(sVO.getAuxiMaterialPrice() * sVO.getProjectSize());
			sVO.setTotalLaborCost(sVO.getLaborCost() * sVO.getProjectSize());
			sVO.setItemPrice(sVO.getTotalMainMaterialPrice() + sVO.getTotalAuxiMaterialPrice() + sVO.getTotalLaborCost());
			semPrice = semPrice + sVO.getTotalMainMaterialPrice() + sVO.getTotalAuxiMaterialPrice();
			totalPrice = totalPrice + sVO.getItemPrice();
		}
		semDecOrderVO.setOrderTotalPrice(totalPrice);
		semDecOrderVO.setOrderSemPrice(semPrice);
		qVO.setSemDecOrderVO(semDecOrderVO);
		//进行对模板进行计算输出
		result.setOk(true);
		result.setData(qVO);
		return result;
	}

	@Override
	public LaborCost insertSelective(LaborCost record) {
		laborCostWriterMapper.insertSelective(record);
		return record;
	}

	@Override
	public int updateByPrimaryKeySelective(LaborCost record) {
		return laborCostWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SemiDecorationOrderInfo insertSelective(SemiDecorationOrderInfo record) {
		semiDecorationOrderInfoWriterMapper.insertSelective(record);
		return record;
	}

	@Override
	public int updateByPrimaryKeySelective(SemiDecorationOrderInfo record) {
		return semiDecorationOrderInfoWriterMapper.updateByPrimaryKeySelective(record);
	}

}
