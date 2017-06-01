package com.jialian.core.service.impl.Comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Comment;
import com.jialian.api.domain.query.CommentQuery;
import com.jialian.api.service.Comment.CommentServiceApi;
import com.jialian.core.persistence.reader.CommentReaderMapper;
import com.jialian.core.persistence.writer.CommentWriterMapper;

@Service("commentService")
public class CommentServiceImpl implements CommentServiceApi{
	
	@Resource
	private CommentReaderMapper commentReaderMapper;

	@Resource
	private CommentWriterMapper commentWriterMapper;
	/**
	 * 获取评论列表
	 */
	@Override
	public ServiceResult<List<CommentQuery>> getCommentList() {
		ServiceResult<List<CommentQuery>> result = new ServiceResult<List<CommentQuery>>();
		List<CommentQuery> commentList = commentReaderMapper.selectCommentList();
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		if(commentList != null && commentList.size() > 0){
			result.setData(commentList);
		}
		return result;
	}

	/**
	 * 添加评论
	 */
	@Override
	public Comment addComment(Comment comment) {
		int result = commentWriterMapper.insertSelective(comment);
		return result > 0? comment:null;
	}

	/**
	 * 评论列表
	 */
	@Override
	public List<CommentQuery> selectCommentList() {
		return commentReaderMapper.selectCommentList();
	}

	/**
	 * 修改评论
	 */
	@Override
	public int updateComment(Comment comment) {
		return commentWriterMapper.updateByPrimaryKey(comment);
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		return commentWriterMapper.updateByPrimaryKeySelective(record);
	}

}
