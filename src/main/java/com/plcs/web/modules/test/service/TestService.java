
package com.plcs.web.modules.test.service;

import com.plcs.web.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.modules.test.entity.Test;
import com.plcs.web.modules.test.dao.TestDao;

/**
 * 测试Service
 * @version 2013-10-17
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
