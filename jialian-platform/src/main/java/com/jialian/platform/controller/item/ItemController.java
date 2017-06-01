package com.jialian.platform.controller.item;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Item.ItemServiceApi;

/**
 * 项目相关操作控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月21日 下午6:22:03
 *@Version:1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {
     
	@Resource
	private ItemServiceApi itemService;
	
	/**
	 * 后台--查询装修项目列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午4:04:42
	 *@Version:1.0
	 */
	@RequestMapping("/selectItemList.do")
	@ResponseBody
	public JsonResult selectItemList(HttpServletRequest request,
			@RequestParam(value = "startIndex", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
		JsonResult jsonResult = new JsonResult();
		Where where = new Where();
		where.setLimit(startIndex);
		where.setOffset(pageSize);
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("status", (short)1);
		List<Item> itemList = itemService.selectWithHouseStruByWhere(where);
		int tatalItems = itemService.countByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		jsonResult.addData("itemList", itemList);
		jsonResult.addData("totalItems", tatalItems);
		return jsonResult;
	}
	
	
	/**
	 * 后台--编辑装修项目
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午4:05:12
	 *@Version:1.0
	 */
	@RequestMapping("/editItem.do")
	@ResponseBody
	public JsonResult editItem(Item item, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(item == null || item.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			int result = itemService.updateByPrimaryKeySelective(item);
			if(result == 1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("修改成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("修改失败");
			}
		}
		return jsonResult;
	}
	
	/**
	 * 后台--装修项目详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午5:48:14
	 *@Version:1.0
	 */
	@RequestMapping("/selectItemById.do")
	@ResponseBody
	public JsonResult selectItemById(Long id,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			Item item = itemService.selectByPrimaryKey(id);
			if(item == null || item.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("该项目已不存在或被删除");
			}else{
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.addData("item", item);
			}
		}
		return jsonResult;
	}
	
	
	/**
	 * 后台--装修项目添加
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月22日 下午5:58:13yt
	 *@Version:1.0
	 */
	@RequestMapping("/addItem.do")
	@ResponseBody
	public JsonResult addItem(Item item, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(item == null || item.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			item = itemService.addItem(item);
			if(item.getId() != null){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("添加成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("添加失败，请重新添加");
			}
		}
		return jsonResult;
	}
}
