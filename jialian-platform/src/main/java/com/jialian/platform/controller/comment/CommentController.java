package com.jialian.platform.controller.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Comment;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.domain.query.CommentQuery;
import com.jialian.api.service.Comment.CommentServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.platform.controller.BaseController;

/**
 * 后台--评论相关控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2016年1月8日 下午3:33:19
 *@Version:1.0
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController{

	@Resource
	private CommentServiceApi commentService;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private LogServiceApi logService;
	
	@RequestMapping("/addComment.do")
	@ResponseBody
	public JsonResult addComment(@RequestBean Comment comment, @RequestBean ResourceInfoReplacePara resourceInfo ){
		JsonResult jsonResult = new JsonResult();
		if(comment == null || comment.getId() == null){
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		if(resourceInfo != null){
			if(resourceInfo.getFromId() == null || resourceInfo.getFromNo() == null ||
					resourceInfo.getTargetId() == null || resourceInfo.getTargetNo() == null){
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setSuccess(false);
				jsonResult.setMessage("参数错误");
				return jsonResult;
			}
			ResourceInfo resInf = new ResourceInfo();
			resInf.setId(resourceInfo.getFromId());
			resInf.setObjectId(0l);
			resourceInfoService.updateByPrimaryKeySelective(resInf);
			resInf.setId(resourceInfo.getTargetId());
			resInf.setObjectId(comment.getId());
			resourceInfoService.updateByPrimaryKeySelective(resInf);
		}
		int result = commentService.updateByPrimaryKeySelective(comment);
		if(result == 1){
			jsonResult.setSuccess(true);
			jsonResult.setMessage("修改成功");
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			
			//记入日志数据里
			AdminUser user = getAdminUser(getRequest());
			logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》评论管理", "添加了评论信息");
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setMessage("修改失败");
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
		}
		return jsonResult;
	}
	
	/**
	 * 查询评论列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月11日 下午4:31:53
	 *@Version:1.0
	 */
	@RequestMapping("/selectCommentList.do")
	@ResponseBody
	public JsonResult selectCommentList(){
		JsonResult jsonResult = new JsonResult();
		List<CommentQuery> commentList = commentService.selectCommentList();
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		jsonResult.setDataObj(commentList.size());
		jsonResult.addData("commentList", commentList);
		return jsonResult;
	}
	
	/**
	 * 编辑保存评论
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月11日 下午6:23:27
	 *@Version:1.0
	 */
	@RequestMapping("/editComment.do")
	@ResponseBody
	public JsonResult editComment(@RequestBean Comment comment){
		JsonResult jsonResult = new JsonResult();
		if(comment == null || comment.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("该评论不存在");
		}else{
		    int result = commentService.updateComment(comment);
		    if(result < 0){
		    	jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("修改失败");
		    }else{
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("修改成功");
				jsonResult.addData("comment", comment);
				
				//记入日志数据里
				AdminUser user = getAdminUser(getRequest());
				logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》评论管理", "修改评论信息");
		    }
		}
		return jsonResult;
	}
}
