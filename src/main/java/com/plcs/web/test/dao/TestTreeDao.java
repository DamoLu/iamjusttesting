
package com.plcs.web.test.dao;

import com.plcs.web.test.entity.TestTree;
import com.plcs.web.common.persistence.TreeDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;

/**
 * 树结构生成DAO接口
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}