package com.plcs.web.wsxd.businessoperation.businessoperation.dao;

import com.plcs.web.common.persistence.CrudDao;
import com.plcs.web.common.persistence.annotation.MyBatisDao;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdContactAddr;

/**
 * 联系地址
 */
@MyBatisDao
public interface WsxdContactAddrDao extends CrudDao<WsxdContactAddr> {

    int insert(@Param("pojo") WsxdContactAddr pojo);

    int insertList(@Param("pojos") List< WsxdContactAddr> pojo);

    List<WsxdContactAddr> select(@Param("pojo") WsxdContactAddr pojo);

    int update(@Param("pojo") WsxdContactAddr pojo);

    List<WsxdContactAddr> findContactAddrs(WsxdCase wsxdCase);
}
