/**
 * 
 */package com.jialian.core.persistence.writer;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月7日 上午11:32:49  */

import com.jialian.api.domain.entity.Comment;

/**
 *@Description:
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月7日 上午11:32:49
 *@Version:1.0
 */
public interface CommentWriterMapper {
		int deleteByPrimaryKey(Long id);

	    int insert(Comment record);

	    int insertSelective(Comment record);

	    int updateByPrimaryKeySelective(Comment record);

	    int updateByPrimaryKey(Comment record);
}
