package com.jialian.platform.controller.modelHouse;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.ModelHouse;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.domain.vo.ModelHouseDetailVo;
import com.jialian.api.service.ModelHouseServiceApi;
import com.jialian.api.service.Address.AddressServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *  后台--样板房管理相关操作控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月18日 下午6:14:01
 *@Version:1.0
 */
@Controller
@RequestMapping("/modelHouse")
public class ModelHouseController {
   
	@Resource
	private ModelHouseServiceApi modelHouseService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	
	@Resource
	private AddressServiceApi addressServiceApi;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	/**
	 * 样板房列表查询，带分页
	 * @Description:
	 * @Param:@param startIndex 起始查询页码
	 * @Param:@param pageSize 需要查询的页数
	 * @Param:@return JsonResult
	 * @Author: shx  408640463@qq.com
	 * @Since:2015年12月19日 下午1:47:47
	 * @Version:1.0
	 */
	@RequestMapping("/selectModelHouseList.do")
	@ResponseBody
	public JsonResult selectModelHouseList(@RequestBean int startIndex,@RequestBean int pageSize){
		JsonResult jsonResult=new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("status",(short)1);
		List<ModelHouse> modelHouseList = modelHouseService.selectByWhere(where);
		int totalItems = modelHouseService.countByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.addData("modelHouseList", modelHouseList);
		jsonResult.addData("totalItems", totalItems);
		return jsonResult;
	}
	
	
	/**
	 * 后台--根据样板房id删除样板房(物理删除，修改状态)
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午2:53:11
	 *@Version:1.0
	 */
	@RequestMapping("/deleteModelHouseById.do")
	@ResponseBody
	public JsonResult deleteModelHouseById(@RequestBean Long id, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			ModelHouse modelHouse = modelHouseService.selectByPrimaryKey(id);
			if(modelHouse == null || modelHouse.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("该样板房已删除或不存在");
			}else{
                modelHouse.setStatus((short)0);
                modelHouse.setUpdateTime(new Date());
                int result = modelHouseService.updateModelHouseByPrimaryKey(modelHouse);
                if(result == 1){
                	jsonResult.setSuccess(true);
                	jsonResult.setMessage("删除成功");
                	jsonResult.setStateCode(Const.SUCCESS_CODE);
                }else{
                	jsonResult.setSuccess(false);
                	jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
                	jsonResult.setMessage("删除失败");
                }
			}
		}
		return jsonResult;
	}
	
	
	/**
	 * 后台--根据id查询样板房详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午3:52:14
	 *@Version:1.0
	 */
	@RequestMapping("/selectModelHouseById.do")
	@ResponseBody
	public JsonResult selectModelHouseById(@RequestBean Long id, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
        if(id == null || id <= 0){
        	jsonResult.setSuccess(false);
        	jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
        	jsonResult.setMessage("参数错误");
        }else{
            ModelHouseDetailVo modelHouse =	modelHouseService.selectModelHouseDetail(id);
            if(modelHouse == null || modelHouse.equals("")){
            	 jsonResult.setSuccess(false);
            	 jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
            	 jsonResult.setMessage("该样板房已被删除或不存在");
            }else{
            	jsonResult.setSuccess(true);
            	jsonResult.setStateCode(Const.SUCCESS_CODE);
            	jsonResult.setMessage("查询成功");
            	jsonResult.addData("modelHouse", modelHouse);
            }
        }       
		return jsonResult;
	}
	
	/**
	 * 编辑样板房，保存
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月14日 下午2:54:57
	 *@Version:1.0
	 */
	@RequestMapping("/editModelHouse.do")
	@ResponseBody
	public JsonResult editModelHouse(@RequestBean ModelHouse modelHouse,@RequestBean Address address,
			@RequestBean ResourceInfoReplacePara resourceInfo, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(modelHouse == null || modelHouse.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else if(address == null || address.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			ServiceResult<ModelHouseDetailVo> result = modelHouseService.editModelHouse(modelHouse,address,resourceInfo);
			if(result.isOk()){//修改成功
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("修改成功");
			}else{//修改失败
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage(result.getComment());
			}
			
		}
		return jsonResult;
	}
	
	
	/**
	 * 后台--样板房添加
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午4:37:16
	 *@Version:1.0
	 */
	@RequestMapping("/addModelHouse.do")
	@ResponseBody
	public JsonResult addModelHouse(@RequestBean ModelHouse modelHouse,@RequestBean Address address,
			 @RequestBean ResourceInfo resourceInfo, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(modelHouse == null || modelHouse.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else if(address == null || address.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else if(resourceInfo == null || resourceInfo.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			if(address.getProvince() != null ){
				if(address.getProvince().equals("上海") || address.getProvince().equals("北京") || address.getProvince().equals("天津") || address.getProvince().equals("重庆")){
					address.setCounty(address.getCity());
					address.setCity(address.getProvince());
					
				}
			}
			address.setAddressType((short)3);
			address.setStatus((short)1);
			address = addressServiceApi.insertAddress(address);
			if(address == null || address.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("添加失败,地址填写有误");
			}else{
				modelHouse.setModelNum("YB" + idWorker.nextId());
				modelHouse.setAddress(address.getId());
				modelHouse = modelHouseService.addModelHouse(modelHouse);
				resourceInfo.setObjectId(modelHouse.getId());
				resourceInfo.setNo(modelHouse.getModelNum());
				int result = resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
				if(result == 1) {
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setMessage("添加成功");
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
					jsonResult.setMessage("添加失败");
				}
					
			}
		}
		return jsonResult;
	}

	
	/**
	* @Description: 样板房信息修改
	* @param @param modelHouse
	* @param @param request
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月30日 上午11:16:38
	 */
	@RequestMapping("/updataModelHouse.do")
	@ResponseBody
	public JsonResult updataModelHouse(@RequestBean ModelHouse modelHouse,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object>  result=modelHouseService.updateModelHouseById(modelHouse);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
}
