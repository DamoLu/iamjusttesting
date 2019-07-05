package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactPhone;

/**
 * 联系电话
 */
@MyBatisDao
public interface WsxdContactPhoneDao extends CrudDao<WsxdContactPhone> {

    int insert(@Param("pojo") WsxdContactPhone pojo);

    int insertList(@Param("pojos") List< WsxdContactPhone> pojo);

    List<WsxdContactPhone> select(@Param("pojo") WsxdContactPhone pojo);

    int update(@Param("pojo") WsxdContactPhone pojo);

    List<WsxdContactPhone> findContactPhones(WsxdCase wsxdCase);
}
