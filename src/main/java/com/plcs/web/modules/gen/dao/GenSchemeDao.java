
package com.plcs.web.modules.gen.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {
	
}
