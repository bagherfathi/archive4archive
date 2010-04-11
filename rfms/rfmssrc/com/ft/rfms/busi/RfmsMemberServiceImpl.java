package com.ft.rfms.busi;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.RfmsMember;
import com.ft.rfms.entity.dao.RfmsMemberDAO;
import com.ft.rfms.model.RfmsMemberService;
import com.ft.utils.ExcelUtil;

public class RfmsMemberServiceImpl extends BaseServiceImpl implements
		RfmsMemberService {

	private RfmsMemberDAO rfmsMemberDAO;

	public List<RfmsMember> importMember(String fileName, Long operatorId) {
		List<RfmsMember> memberList = new ArrayList();
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fileName));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getLastRowNum();

			for (int i = 1; i <= rows; i++) {
				HSSFRow row = sheet.getRow(i);
				String name = ExcelUtil.getCellStringValue(row, 0);
				if (StringUtils.isEmpty(name)) {
					continue;
				}
				String mobile = ExcelUtil.getCellStringValue(row, 1);
				if (!StringUtils.isEmpty(mobile)) {
					// 判断手机是否在用户表存在
					RfmsMember rfmsMember = (RfmsMember) this.baseDao
							.getEntityByIdentityAttribute(RfmsMember.class,
									"mobile", mobile);

					RfmsMember member = new RfmsMember();
					member.setName(ExcelUtil.getCellStringValue(row, 0));
					member.setPwd("123456");
					member.setMobile(mobile);
					if ("男".equals(ExcelUtil.getCellStringValue(row, 2))) {
						member.setSex("1");
					} else if ("女".equals(ExcelUtil.getCellStringValue(row, 2))) {
						member.setSex("2");
					}
					member.setAddress(ExcelUtil.getCellStringValue(row, 3));
					member.setOperatorId(operatorId);
					if (rfmsMember == null) {
						rfmsMemberDAO.save(member);
					}
					memberList.add(member);
				}
			}
			return memberList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<RfmsMember> getRfmsMemberByStatus(String status) {
		return rfmsMemberDAO.getRfmsMemberByStatus(status);
	}

	public RfmsMemberDAO getRfmsMemberDAO() {
		return rfmsMemberDAO;
	}

	public void setRfmsMemberDAO(RfmsMemberDAO rfmsMemberDAO) {
		this.rfmsMemberDAO = rfmsMemberDAO;
	}

}
