/**
 * 
 */package com.jialian.core.persistence.reader;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 上午11:31:28  */

import java.util.List;

import com.jialian.api.domain.entity.Comment;
import com.jialian.api.domain.query.CommentQuery;

/**
 *@Description: 
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 上午11:31:28
 *@Version:1.0
 */
public interface CommentReaderMapper {
	Comment selectByPrimaryKey(Long id);

	//评论列表
	List<Comment> getCommentList();

	//后台--评论列表
	List<CommentQuery> selectCommentList();
}
