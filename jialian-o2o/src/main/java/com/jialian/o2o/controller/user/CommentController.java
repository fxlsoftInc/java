package com.jialian.o2o.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.query.CommentQuery;
import com.jialian.api.service.Comment.CommentServiceApi;

/**
 * o2o--评论相关控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2016年1月8日 上午11:23:14
 *@Version:1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentServiceApi commentService;
	
	/**
	 * 首页评论列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月8日 上午11:37:47
	 *@Version:1.0
	 */
	@RequestMapping("/getCommentList.do")
	@ResponseBody
	public JsonResult getCommentList(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<CommentQuery>> result = commentService.getCommentList();
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		jsonResult.addData("commentList", result.getData());
		return jsonResult;
	}
}
