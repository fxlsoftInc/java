package com.jialian.platform.controller.material;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Material.MaterialAttributeServiceApi;
import com.jialian.api.service.Material.MaterialPriceServiceApi;
import com.jialian.api.service.Material.MaterialServiceApi;
import com.jialian.common.custom.RequestBean;

@Controller
@RequestMapping("/material")
public class MaterialController {
	
	@Resource
	private MaterialServiceApi materialService;
	
	@Resource
	private MaterialAttributeServiceApi materialAttributeService;
	
	@Resource
	private MaterialPriceServiceApi materialPriceService;
	
	@RequestMapping("/getMaterialList.do")
	@ResponseBody
	public JsonResult getMaterialList(@RequestBean String materialName, @RequestBean Boolean materialPrice,
			@RequestParam(value = "startIndex", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
		JsonResult result = new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("status", 1);
		if(materialName != null){
			criteria.andLike("material_name", "%" + materialName.toString() + "%");
		}
		if(materialPrice == null){
			where.setOrderByClause("id asc");
		}else{
			if(!materialPrice){
				where.setOrderByClause("material_price asc");
			}else{
				where.setOrderByClause("material_price desc");
			}
		}
		
		List<Material> materialList = materialService.selectByWhere(where);
		int totalItems = materialService.countByWhere(where);
		result.addData("list", materialList);
		result.addData("totalItems", totalItems);
		result.setMessage("查询成功");
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		return result;
	}
	
	@RequestMapping("/getMaterialInfo.do")
	@ResponseBody
	public JsonResult getMaterialInfo(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <=0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("查询失败");
			return result;
		}
		Material material = materialService.selectByPrimaryKey(id);
		if(material != null){
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("查询成功");
			result.addData("material", material);
		}else{
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_NODATA_CODE);
			result.setMessage("没有此数据");
		}
		return result;
	}
	
	@RequestMapping("/updateMaterialSelective.do")
	@ResponseBody
	public JsonResult updateMaterialSelective(@RequestBean Material material){
		JsonResult result = new JsonResult();
		//判断参数
		if(material.getId() == null || material.getId() <=0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误，更新数据失败");
			result.addData("material", material);
			return result;
		}
		int flag = materialService.updateByPrimaryKeySelective(material);
		if(flag > 0){
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("更新成功");
			result.addData("material", materialService.selectByPrimaryKey(material.getId()));
			return result;
		}else{
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE);
			result.setMessage("未知");
			result.addData("material", material);
			return result;
		}
	}
	
	@RequestMapping("/addMaterial.do")
	@ResponseBody
	public JsonResult addMaterialSelective(@RequestBean Material material){
		JsonResult result = new JsonResult();
		//判断参数
		if(material.getId() != null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误，保存失败");
			return result;
		}
		material.setStatus((short)1);
		material = materialService.insertSelective(material);
		if(material == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE);
			result.setMessage("未知错误");
			return result;
		}else{
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("保存成功");
			result.addData("material", material);
			return result;
		}
	}
	
	@RequestMapping("/isExitMaterial.do")
	@ResponseBody
	public JsonResult isExitMateriaNo(@RequestBean String materialNo){
		JsonResult result = new JsonResult();
		if(materialNo == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("material_no", materialNo);
		List<Material> materialList = materialService.selectByWhere(where);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("查询成功");
		result.addData("materialList", materialList);
		return result;
	}
	
	@RequestMapping("/deleteMaterialById.do")
	@ResponseBody
	public JsonResult deleteMaterialById(Long id){
		JsonResult result = new JsonResult();
		if(id == null || id <=0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
			return result;
		}
		Material material = new Material();
		material.setId(id);
		material.setStatus((short)0);
		int flag = materialService.updateByPrimaryKeySelective(material);
		if(flag > 0){
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setMessage("删除成功");
			return result;
		}else{
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE);
			result.setMessage("出现未知错误");
			return result;
		}
	}
}
