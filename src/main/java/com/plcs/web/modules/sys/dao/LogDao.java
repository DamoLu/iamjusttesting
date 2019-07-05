
package com.plcs.web.modules.sys.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
