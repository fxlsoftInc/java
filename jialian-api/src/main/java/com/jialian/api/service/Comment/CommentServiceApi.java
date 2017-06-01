package com.jialian.api.service.Comment;

import java.util.List;

import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Comment;
import com.jialian.api.domain.query.CommentQuery;

public interface CommentServiceApi {

	ServiceResult<List<CommentQuery>> getCommentList();

	//添加评论
	Comment addComment(Comment comment);

	//后台--评论列表
	List<CommentQuery> selectCommentList();

	//后台--修改评论
	int updateComment(Comment comment);
	
	int updateByPrimaryKeySelective(Comment record);

}
