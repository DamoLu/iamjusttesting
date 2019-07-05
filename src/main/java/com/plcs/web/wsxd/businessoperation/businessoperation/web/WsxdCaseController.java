package com.plcs.web.wsxd.businessoperation.businessoperation.web;

import com.alibaba.fastjson.JSONObject;
import com.plcs.web.common.config.Global;
import com.plcs.web.common.persistence.Page;
import com.plcs.web.common.utils.DateUtils;
import com.plcs.web.common.utils.Encodes;
import com.plcs.web.common.utils.StringUtils;
import com.plcs.web.common.web.BaseController;
import com.plcs.web.wsxd.businessoperation.businessoperation.dto.WsxdCaseExportDTO;
import com.plcs.web.wsxd.businessoperation.businessoperation.entity.WsxdCase;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdAllocateHstService;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdCaseService;
import com.plcs.web.wsxd.businessoperation.businessoperation.service.WsxdRemindRecordService;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvGroupVO;
import com.plcs.web.wsxd.businessoperation.businessoperation.vo.OdvOV;
import com.plcs.web.wsxd.sysmgt.allocatecase.service.WsxdAllocateGroupService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.util.PoiElUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务操作Controller
 * @author tanweijian
 * @version 2019-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/businessoperation/wsxdCase")
public class WsxdCaseController extends BaseController {

	@Autowired
	private WsxdCaseService wsxdCaseService;
	@Autowired
	private WsxdRemindRecordService wsxdRemindRecordService;
	@Autowired
	private WsxdAllocateHstService wsxdAllocateHstService;
	@Autowired
	private WsxdAllocateGroupService wsxdAllocateGroupService;

    /**
     *  将下拉列表数据返回给搜索栏
     * @param id
     * @return
     */
	@ModelAttribute
	public WsxdCase get(@RequestParam(required=false) String id) {
		WsxdCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsxdCaseService.get(id);
		}
		if (entity == null){
			entity = new WsxdCase();
			// 合作机构在页面配置了，如有需要可在数据库中查询
			// 事业部
			entity.setDepartmentList(wsxdCaseService.findDepartmentList());
			// 放款机构，只有名字
			entity.setLoanOrginList(wsxdCaseService.findLoanOrginList());
            // 案件状态，只有名字
            entity.setCaseStatusList(wsxdRemindRecordService.findCaseStatusList());
            // 客户经理
            entity.setManagerList(wsxdCaseService.findManagerList());
            // 产品名称
            entity.setAppNameList(wsxdCaseService.findAppNameList());
		}
		return entity;
	}

    /**
     * 获取处理组列表
     */
    @ResponseBody
    @RequestMapping(value = "getOdvGroupList", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OdvGroupVO> getOdvGroupList(HttpServletRequest request, @RequestBody OdvGroupVO odvGroupVO) {
    	List<OdvGroupVO> odvGroupVOList = wsxdAllocateGroupService.findOdvGroupList();
    	if (odvGroupVOList != null && odvGroupVOList.size() > 0) {
    		for (OdvGroupVO vo : odvGroupVOList) {
				List<OdvOV> odvOVList = wsxdAllocateGroupService.findOdvList(vo.getOdvGroup());
				vo.setOdvOVList(odvOVList);
			}
		}
        return odvGroupVOList;
    }

    /**
     * 获取处理人员列表
     */
    @ResponseBody
    @RequestMapping(value = "getOdvList", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OdvOV> getOdvList(HttpServletRequest request, @RequestBody OdvOV odvOV) {
        return wsxdAllocateGroupService.findOdvList(odvOV.getId());
    }

	@RequiresPermissions("businessoperation:wsxdCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsxdCase wsxdCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsxdCase> page = wsxdCaseService.findPage(new Page<WsxdCase>(request, response), wsxdCase); 
		model.addAttribute("page", page);
		return "businessoperation/businessoperation/wsxdCaseList";
	}

	@RequiresPermissions("businessoperation:wsxdCase:view")
	@RequestMapping(value = "form")
	public String form(WsxdCase wsxdCase, Model model) {
		model.addAttribute("wsxdCase", wsxdCase);
		return "businessoperation/businessoperation/wsxdCaseForm";
	}

	@RequiresPermissions("businessoperation:wsxdCase:edit")
	@RequestMapping(value = "save")
	public String save(WsxdCase wsxdCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsxdCase)){
			return form(wsxdCase, model);
		}
		wsxdCaseService.save(wsxdCase);
		addMessage(redirectAttributes, "保存业务操作成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdCase/?repage";
	}
	
	@RequiresPermissions("businessoperation:wsxdCase:edit")
	@RequestMapping(value = "delete")
	public String delete(WsxdCase wsxdCase, RedirectAttributes redirectAttributes) {
		wsxdCaseService.delete(wsxdCase);
		addMessage(redirectAttributes, "删除业务操作成功");
		return "redirect:"+Global.getAdminPath()+"/businessoperation/wsxdCase/?repage";
	}

	/**
	 * 导出Excel
	 * @param wsxdCase 搜索条件
	 * @param isAll 是否全选
	 * @param checkIDArr 复选框Id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportFile")
	public void exportFile(WsxdCase wsxdCase,String isAll,String checkIDArr, HttpServletResponse response) throws IOException {
		logger.info("开始导出催记案件数据表...");
		List<WsxdCase> caseList=new ArrayList<WsxdCase>();
		List<WsxdCaseExportDTO> wsxdCaseExportDTOList = new ArrayList<>();
		if(Boolean.valueOf(isAll)){
			// 如果是全选的话，直接根据搜索条件导出所有的即可
			caseList= wsxdCaseService.findList(wsxdCase);
		}else {
			// 根据复选框Id进行导出
			String[] idArr=checkIDArr.split(",");
			if (idArr != null && idArr.length > 0) {
				for (int i = 0; i <idArr.length ; i++) {
					caseList.add(wsxdCaseService.get(idArr[i]));
				}
			}
		}
		for (WsxdCase sourceCase:caseList){
			WsxdCaseExportDTO exportCase=new WsxdCaseExportDTO();
			BeanUtils.copyProperties(sourceCase,exportCase);
			if (sourceCase.getCreateDate() != null) {
				exportCase.setCreateDate(DateUtils.formatDateTime(sourceCase.getCreateDate()));
			}
			wsxdCaseExportDTOList.add(exportCase);
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			TemplateExportParams params = new TemplateExportParams("/template/催记案件数据表.xlsx","催记案件数据表");

			Map<String, Object> mapExcel = new HashMap<String, Object>();
			List<WsxdCaseExportDTO> viewlist = wsxdCaseExportDTOList;
			Map<String, Object> mapDate = new HashMap<String, Object>();
			mapExcel.put("date", PoiElUtil.eval("fd:(date;yyyyMMdd)", mapDate));
			mapExcel.put("viewlist", viewlist);

			String fileName = "催记案件数据表"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			// 告诉浏览器用什么软件可以打开此文件
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(fileName));
			Workbook workbook = ExcelExportUtil.exportExcel(params, mapExcel);
			workbook.write(response.getOutputStream());
			logger.info("结束导出催记案件数据表...");
		} catch (Exception e) {
			logger.error("催记案件数据表导出失败信息："+e.getMessage());
		}
	}

	/**
	 * 更新分配到人
	 */
	@ResponseBody
	@RequestMapping(value = "distributeCSY")
	public JSONObject distributeCSY(HttpServletRequest request, WsxdCase wsxdCase) throws IOException {
		JSONObject object = new JSONObject();
		// 将分案信息保存到分案历史表中
		int count = wsxdAllocateHstService.insertAllocateHst(wsxdCase);
		if (count > 0) {
			object.put("success", true);
		} else {
			object.put("success", false);
			object.put("message", "操作失败！");
		}
		return object;
	}
}