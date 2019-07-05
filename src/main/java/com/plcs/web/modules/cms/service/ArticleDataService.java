
package com.plcs.web.modules.cms.service;

import com.plcs.web.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plcs.web.modules.cms.dao.ArticleDataDao;
import com.plcs.web.modules.cms.entity.ArticleData;

/**
 * 站点Service
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
