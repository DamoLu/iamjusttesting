package com.plcs.web.wsxd.sysmgt.allocatecase.service;

import com.plcs.web.common.constant.AllocateGroupConstants;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.service.CrudService;
import com.plcs.web.common.utils.ResultVO;
import com.plcs.web.modules.sys.dao.RoleDao;
import com.plcs.web.modules.sys.entity.Role;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvGroupVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import com.plcs.web.wsxd.sysmgt.allocatecase.bo.WsxdCaseScopeBo;
import com.plcs.web.wsxd.sysmgt.allocatecase.dao.WsxdAllocateGroupDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.dao.WsxdAllocateGroupHstDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.dao.WsxdAllocateGroupScopeDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.dao.WsxdAllocateGroupScopeHstDao;
import com.plcs.web.wsxd.sysmgt.allocatecase.dto.WsxdAllocateGroupDTO;
import com.plcs.web.wsxd.sysmgt.allocatecase.dto.WsxdAllocateGroupScopeDTO;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroup;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupHst;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupScope;
import com.plcs.web.wsxd.sysmgt.allocatecase.entity.WsxdAllocateGroupScopeHst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 分案处理组Service
 * @author tanweijian
 * @version 2019-06-12
 */
@Service
@Transactional(readOnly = true)
public class WsxdAllocateGroupService extends CrudService<WsxdAllocateGroupDao, WsxdAllocateGroup> {
	private static final Logger logger = LoggerFactory.getLogger(WsxdAllocateGroupService.class);

	@Autowired
	private WsxdAllocateGroupDao wsxdAllocateGroupDao;
	@Autowired
	private WsxdAllocateGroupScopeDao wsxdAllocateGroupScopeDao;
	@Autowired
	private WsxdAllocateGroupHstDao wsxdAllocateGroupHstDao;
	@Autowired
	private WsxdAllocateGroupScopeHstDao wsxdAllocateGroupScopeHstDao;
	@Autowired
	private RoleDao roleDao;

	public WsxdAllocateGroup get(String id) {
		return super.get(id);
	}

	public List<WsxdAllocateGroup> findList(WsxdAllocateGroup wsxdAllocateGroup) {
		return super.findList(wsxdAllocateGroup);
	}

	public Page<WsxdAllocateGroup> findPage(Page<WsxdAllocateGroup> page, WsxdAllocateGroup wsxdAllocateGroup) {
		return super.findPage(page, wsxdAllocateGroup);
	}

	@Transactional(readOnly = false)
	public void save(WsxdAllocateGroup wsxdAllocateGroup) {
		super.save(wsxdAllocateGroup);
	}

	@Transactional(readOnly = false)
	public void delete(WsxdAllocateGroup wsxdAllocateGroup) {
		super.delete(wsxdAllocateGroup);
	}

	public List<OdvGroupVO> findOdvGroupList() {
		return wsxdAllocateGroupDao.selectOdvGroupList();
	}

	/**
	 * 获取处理人员列表
	 * @param odvGroup
	 * @return
	 */
	public List<OdvOV> findOdvList(String odvGroup) {
		List<OdvOV> odvOVList = new ArrayList<OdvOV>();

		try {
			List<OdvOV> odvOVS = wsxdAllocateGroupDao.selectOdvList(odvGroup);
			if (odvOVS != null ) {
				for (OdvOV odvOV : odvOVS) {
					String odvs = odvOV.getOdv();
					String odvsNames = odvOV.getOdvName();

					if (odvs != null && odvs.length() != 0 && odvsNames != null && odvsNames.length() != 0) {
						String[] odvsArray = odvs.split("\\|");
						String[] odvsNamesArray = odvsNames.split("\\|");

						if (odvsArray != null && odvsArray.length > 0) {
							for (int i = 0; i < odvsArray.length; i++) {
								OdvOV newOdvOV = new OdvOV();
								newOdvOV.setId(odvOV.getId());
								newOdvOV.setOdv(odvsArray[i]);
								newOdvOV.setOdvName(odvsNamesArray[i]);
								odvOVList.add(newOdvOV);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(WsxdAllocateGroupService.class.getName(), "findOdvList", e.getMessage(), e);
			logger.error("获取处理人员列表出现异常");
			return odvOVList;
		}

		return odvOVList;
	}

	public ResultVO<WsxdAllocateGroup> getGroupAndScope(String id){
		ResultVO<WsxdAllocateGroup> resultVO = new ResultVO<>();
		WsxdAllocateGroup wsxdAllocateGroup = new WsxdAllocateGroup();
		wsxdAllocateGroup.setId(id);
		try{
		    resultVO.setResult(wsxdAllocateGroupDao.selectAllocateGroupListAndScope(wsxdAllocateGroup).get(0));
		    resultVO.setSuccess(true);
		    return resultVO;
		}catch (Exception e){
		    logger.error("获取分案处理组信息异常: ",e);
		    resultVO.setError(AllocateGroupConstants.GET_GROUP_FAILED,"获取分案处理组信息失败");
		}
		return resultVO;
	}

	public ResultVO<List<Role>> getRoleList(){
		ResultVO<List<Role>> resultVO = new ResultVO<>();
		try {
			resultVO.setResult(roleDao.selectRoleList());
			resultVO.setSuccess(true);
		} catch (Exception e){
			logger.error("获取角色列表信息异常: ",e);
			resultVO.setError(AllocateGroupConstants.GET_ROLE_FAILED,"获取角色列表信息失败");
		}
		return resultVO;
	}

	public ResultVO<List<WsxdCaseScopeBo>> getCaseScopeList(){

		ResultVO<List<WsxdCaseScopeBo>> resultVO = new ResultVO<>();
		try{
			resultVO.setResult(wsxdAllocateGroupDao.selectCaseScopeList());
			resultVO.setSuccess(true);
		} catch (Exception e){
			logger.error("获取案件范围列表异常: " , e);
			resultVO.setError(AllocateGroupConstants.GET_CASESCOPE_FAILED,"获取案件范围列表失败");
		}
		return resultVO;
	}

	/**
	 * 新增案件处理组
	 * @param group
	 * @return
	 */
	@Transactional
	public ResultVO<String> insertGroup(WsxdAllocateGroupDTO group){
		ResultVO<String> resultVO = new ResultVO<>();
		//处理组启用
		if (group.getStatus().equals("1")) {
			resultVO = check(group);
			if(!resultVO.getSuccess())
				return  resultVO;
		}
		try {
			//新增处理组
			WsxdAllocateGroup wsxdAllocateGroup = new WsxdAllocateGroup();
			wsxdAllocateGroup.setWsxdAllocateGroupScopeList(new ArrayList<>());
			BeanUtils.copyProperties(group,wsxdAllocateGroup,"wsxdAllocateGroupScopeList");
			wsxdAllocateGroup.preInsert();
			int row = wsxdAllocateGroupDao.insertGroup(wsxdAllocateGroup);
			if(row < 1){
				throw new RuntimeException("新增处理组失败");
			}

			//新增处理组案件范围
			group.getWsxdAllocateGroupScopeList().forEach(scopeDto -> {
				WsxdAllocateGroupScope scope = new WsxdAllocateGroupScope();
				BeanUtils.copyProperties(scopeDto,scope);
				scope.setGroupId(wsxdAllocateGroup.getId());
				scope.setGroupName(wsxdAllocateGroup.getGroupName());
				scope.preInsert();
				scope.setCreateDate(wsxdAllocateGroup.getCreateDate());
				scope.setUpdateDate(wsxdAllocateGroup.getUpdateDate());
				wsxdAllocateGroup.getWsxdAllocateGroupScopeList().add(scope);
			});

			row = wsxdAllocateGroupScopeDao.batchInsertAllocateGroupScope(wsxdAllocateGroup.getWsxdAllocateGroupScopeList());
			if(row < 1 ){
				throw new RuntimeException("批量新增处理组案件范围失败");
			}

		} catch (Exception e) {
			logger.error("新增案件处理组发生异常: ", e);
			throw new RuntimeException(e);
		}
		resultVO.setResult(group.getId());
		resultVO.setSuccess(true);
		return resultVO;
	}

	/**
	 * 更新案件处理组信息
	 * @param groupDto
	 * @return
	 */
	@Transactional
	public ResultVO<String> updateGroup(WsxdAllocateGroupDTO groupDto) throws RuntimeException{

		ResultVO<String> resultVO = new ResultVO<>();
		//处理组启用
		if (groupDto.getStatus().equals("1")) {
			resultVO = check(groupDto);
			if(!resultVO.getSuccess()) {
				return resultVO;
			}
		}
		//新增处理组信息历史

		WsxdAllocateGroupHst wsxdAllocateGroupHst = new WsxdAllocateGroupHst();
		WsxdAllocateGroup originGroup = new WsxdAllocateGroup();
		originGroup.setId(groupDto.getId());
		try {
			int effectedRow;
			originGroup = wsxdAllocateGroupDao.selectAllocateGroupListAndScope(originGroup).get(0);
			Date originGroupCreateDate = originGroup.getCreateDate();
			BeanUtils.copyProperties(originGroup,wsxdAllocateGroupHst,"wsxdAllocateGroupScopeList");
			wsxdAllocateGroupHst.setGroupId(originGroup.getId());
			wsxdAllocateGroupHst.preInsert();
			wsxdAllocateGroupHst.setCreateDate(originGroup.getCreateDate());
			wsxdAllocateGroupHst.setUpdateDate(originGroup.getUpdateDate());
			effectedRow = wsxdAllocateGroupHstDao.insert(wsxdAllocateGroupHst);
			if(effectedRow < 1){
				throw new RuntimeException("新增处理组信息历史失败");
			}

			WsxdAllocateGroup newGroup = new WsxdAllocateGroup();
			BeanUtils.copyProperties(groupDto,newGroup,"wsxdAllocateGroupScopeList");
			//更新处理组
			newGroup.preUpdate();
			effectedRow = wsxdAllocateGroupDao.update(newGroup);
			if(effectedRow != 1){
				throw new RuntimeException("保存处理组信息失败");
			}


			//新增处理组案件范围历史
			List<WsxdAllocateGroupScopeHst> groupScopeHstList = new ArrayList<>();
			originGroup.getWsxdAllocateGroupScopeList().forEach(originScope -> {
				WsxdAllocateGroupScopeHst wsxdAllocateGroupScopeHst = new WsxdAllocateGroupScopeHst();
				BeanUtils.copyProperties(originScope, wsxdAllocateGroupScopeHst);
				wsxdAllocateGroupScopeHst.preInsert();
				wsxdAllocateGroupScopeHst.setCreateDate(originScope.getCreateDate());
				wsxdAllocateGroupScopeHst.setUpdateDate(originScope.getUpdateDate());
				groupScopeHstList.add(wsxdAllocateGroupScopeHst);

			});
			effectedRow = wsxdAllocateGroupScopeHstDao.batchInsertGroupScopeHst(groupScopeHstList);
			if (effectedRow < 1) {
				throw new RuntimeException("批量新增处理组案件范围历史失败");
			}


			//删除处理组案件范围
			effectedRow = wsxdAllocateGroupScopeDao.deleteByGroupId(groupDto.getId());
			if (effectedRow < 1) {
				throw new RuntimeException("删除处理组案件范围失败");
			}

			newGroup.setWsxdAllocateGroupScopeList(new ArrayList<>());
			groupDto.getWsxdAllocateGroupScopeList().forEach(scopeDto -> {
				WsxdAllocateGroupScope scope = new WsxdAllocateGroupScope();
				BeanUtils.copyProperties(scopeDto, scope);
				scope.setGroupId(newGroup.getId());
				scope.setGroupName(newGroup.getGroupName());
				scope.preInsert();
				scope.setCreateDate(originGroupCreateDate);
				scope.setUpdateDate(newGroup.getUpdateDate());
				newGroup.getWsxdAllocateGroupScopeList().add(scope);
			});

			//批量新增处理组案件范围
			effectedRow = wsxdAllocateGroupScopeDao.batchInsertAllocateGroupScope(newGroup.getWsxdAllocateGroupScopeList());
			if (effectedRow < 1) {
				throw new RuntimeException("批量新增处理组案件范围失败");
			}
		} catch (Exception e) {
			logger.error("更新案件处理组信息发生异常: ", e);
			throw new RuntimeException(e);
		}
		resultVO.setSuccess(true);
		return resultVO;
	}

	/**
	 * 更新案件处理组状态
	 * @param groupDto
	 * @return
	 */
	@Transactional
	public ResultVO<String> updateGroupStatus(WsxdAllocateGroupDTO groupDto) throws RuntimeException {

		ResultVO<String> resultVO = new ResultVO<>();
		WsxdAllocateGroup newGroup = new WsxdAllocateGroup();
		BeanUtils.copyProperties(groupDto, newGroup, "wsxdAllocateGroupScopeList");
		//查询原处理组记录
		WsxdAllocateGroup originGroup = new WsxdAllocateGroup();
		originGroup.setId(groupDto.getId());
		try {
			originGroup = wsxdAllocateGroupDao.selectAllocateGroupListAndScope(originGroup).get(0);
			if (originGroup.getWsxdAllocateGroupScopeList() == null || originGroup.getWsxdAllocateGroupScopeList().size() == 0){
				resultVO.setError(AllocateGroupConstants.GET_GROUP_FAILED, "获取分案处理组信息失败");
				return resultVO;
			}
		} catch (Exception e) {
			logger.error("获取分案处理组信息异常: ", e);
			resultVO.setError(AllocateGroupConstants.GET_GROUP_FAILED, "获取分案处理组信息失败");
			return resultVO;
		}

		//处理组启用
		if (groupDto.getStatus().equals("1")) {
			List<WsxdAllocateGroupScopeDTO> scopeDtoList = new ArrayList<>();
			originGroup.getWsxdAllocateGroupScopeList().forEach( originScope -> {
				WsxdAllocateGroupScopeDTO scopeDTO = new WsxdAllocateGroupScopeDTO();
				BeanUtils.copyProperties(originScope , scopeDTO);
				scopeDtoList.add(scopeDTO);
			});
			groupDto.setWsxdAllocateGroupScopeList(scopeDtoList);
			groupDto.setGroupName(originGroup.getGroupName());
			groupDto.setOdvs(originGroup.getOdvs());
			groupDto.setMaxOverdueDay(originGroup.getMaxOverdueDay());
			groupDto.setMinOverdueDay(originGroup.getMinOverdueDay());

			resultVO = check(groupDto);
			if(!resultVO.getSuccess()) {
				return resultVO;
			}
		}

		try {
			//新增处理组信息历史
			int effectedRow;
			WsxdAllocateGroupHst wsxdAllocateGroupHst = new WsxdAllocateGroupHst();
			BeanUtils.copyProperties(originGroup,wsxdAllocateGroupHst,"wsxdAllocateGroupScopeList");
			wsxdAllocateGroupHst.setGroupId(originGroup.getId());
			wsxdAllocateGroupHst.preInsert();
			wsxdAllocateGroupHst.setCreateDate(originGroup.getCreateDate());
			wsxdAllocateGroupHst.setUpdateDate(originGroup.getUpdateDate());
			effectedRow = wsxdAllocateGroupHstDao.insert(wsxdAllocateGroupHst);
			if(effectedRow < 1){
				throw new RuntimeException("新增处理组信息历史失败");
			}

			//更新处理组状态
			newGroup.preUpdate();
			effectedRow = wsxdAllocateGroupDao.update(newGroup);
			if(effectedRow != 1){
				throw new RuntimeException("保存处理组信息失败");
			}

		} catch (Exception e) {
			logger.error("更新案件处理组状态发生异常: ", e);
			throw new RuntimeException(e);
		}
		resultVO.setSuccess(true);
		return resultVO;
	}

	private ResultVO<String> check(WsxdAllocateGroupDTO group){
		ResultVO<String> resultVO = new ResultVO<>();
		try {
			List<WsxdAllocateGroup> enabledGroupList = wsxdAllocateGroupDao.selectEnabledGroupListAndScope(group.getId());
			resultVO = validateDuplicate(group, enabledGroupList);
			if (!resultVO.getSuccess()) {
				return resultVO;
			}
		} catch (Exception e){
			logger.error("获取已启用案件处理组列表发生异常: ", e);
			resultVO.setError(AllocateGroupConstants.GET_ENABLEDGROUP_FAILED,"获取已启用处理组列表失败");
			return resultVO;
		}
		resultVO.setSuccess(true);
		return resultVO;
	}
    /**
     * 检查逾期范围是否交集(案件范围精确到公共池)
     * 检查当前处理组的处理人员是否已经出现在其它已启用的处理组
     * 逾期范围校验规则:
     * 1.机构-事业部 完全一致
     * 2.公共池(满足以下任意一种情况进行校验):
     *        当前处理组        已启用的处理组
     *   i.      全部               任意
     *   ii.     任意               全部
     *   iii.            相同
     * @param currentAllocateGroup 当前处理组
     * @param enabledAllocateGroupList 已启用的处理组列表
     */
    private ResultVO<String> validateDuplicate(WsxdAllocateGroupDTO currentAllocateGroup, List<WsxdAllocateGroup> enabledAllocateGroupList) {
        Set<String> existedGroupNameSet = new HashSet<>();
        ResultVO<String> result = new ResultVO<>();

        //获取当前处理组的处理人员
        List<String> odvList = Arrays.asList(currentAllocateGroup.getOdvs().split("\\|"));

        if (enabledAllocateGroupList != null && enabledAllocateGroupList.size() > 0) {
            boolean firstFlag = true;
            logger.info("开始对处理组 {} 进行逾期范围校验..." , currentAllocateGroup.getGroupName());
            //遍历当前处理组的案件范围
            for (WsxdAllocateGroupScopeDTO currentScope : currentAllocateGroup.getWsxdAllocateGroupScopeList()) {
                //遍历所有已启动处理组的案件范围
                for (WsxdAllocateGroup enabledGroup : enabledAllocateGroupList) {
                    for (WsxdAllocateGroupScope enabledScope : enabledGroup.getWsxdAllocateGroupScopeList()) {
                        if (currentScope.getDepartmentId().equals(enabledScope.getDepartmentId())) {
                            logger.info("当前处理组 {} : {}--{}--{} ",
                                    currentAllocateGroup.getGroupName(),currentScope.getAppOrgName(),currentScope.getDepartmentName(),currentScope.getHasCommonPool());
                            logger.info("已启动处理组 {} : {}--{}--{}",
                                    enabledGroup.getGroupName(),enabledScope.getAppOrgName(),enabledScope.getDepartmentName(),enabledScope.getHasCommonPool());

                            //if( (currentScope.equals("0") && enabledScope.equals("1")) || (currentScope.equals("1") && enabledScope.equals("0")))
                            if ("2".equals(currentScope.getHasCommonPool()) || "2".equals(enabledScope.getHasCommonPool())
                                    || currentScope.getHasCommonPool().equals(enabledScope.getHasCommonPool())) {
                                logger.info("当前处理组逾期范围 {}-{}", currentAllocateGroup.getMinOverdueDay(),currentAllocateGroup.getMaxOverdueDay());
                                logger.info("已启动处理组逾期范围 {}-{} ", enabledGroup.getMinOverdueDay(),enabledGroup.getMaxOverdueDay());
                                int overdueMin = currentAllocateGroup.getMinOverdueDay();
                                int overdueMax = currentAllocateGroup.getMaxOverdueDay();
                                //检查逾期范围是否重叠
                                if (overdueMin > enabledGroup.getMaxOverdueDay() || overdueMax < enabledGroup.getMinOverdueDay()) {
                                    break;
                                } else {
                                    logger.info("逾期范围重叠...");
//                                    result.setSuccess(false);
//                                    result.setCode("2");
                                    result.setError(AllocateGroupConstants.OVERDUE_DUPLICATE,"逾期范围重叠");
                                    return result;
                                }
                            }
                            break;
                        }
                    }
                    //添加已启动的处理组的处理人员(只在最外层循环第一次时添加)
                    if (firstFlag) {
                        List<String> existedOdvs = Arrays.asList(enabledGroup.getOdvs().split("\\|"));
                        existedGroupNameSet.addAll(existedOdvs);
                    }
                }
                firstFlag = false;
            }
            //排查当前处理组的处理人员是否已经出现在其它已启用的处理组
            int totalSize = existedGroupNameSet.size() + odvList.size();
            existedGroupNameSet.addAll(odvList);
            if (totalSize != existedGroupNameSet.size()) {
//                result.setSuccess(false);
//                result.setCode("3");
                result.setError(AllocateGroupConstants.ODVS_IS_EXISTED,"处理人员出现在其它的启动处理组");
                return result;
            }
        }
        result.setSuccess(true);
        return result;
    }
}