
package com.plcs.web.modules.sys.dao;

import com.plcs.web.common.persistence.TreeDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
