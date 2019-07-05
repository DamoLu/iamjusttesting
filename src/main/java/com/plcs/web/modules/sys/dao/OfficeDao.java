
package com.plcs.web.modules.sys.dao;

import com.plcs.web.common.persistence.TreeDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
