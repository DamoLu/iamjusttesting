
package com.plcs.web.modules.cms.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.modules.cms.entity.Site;

/**
 * 站点DAO接口
 * @version 2013-8-23
 */
@MyBatisDao
public interface SiteDao extends CrudDao<Site> {

}
