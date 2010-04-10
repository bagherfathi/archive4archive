package com.ft.rfms.web.merchant;

import hidden.org.codehaus.plexus.util.StringUtils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.rfms.model.RfmsMemberService;
import com.ft.singleTable.web.BaseSimpleAction;
import com.ft.singleTable.web.WebHelper;
import com.ft.utils.DateUtil;

public class MemberImport extends BaseSimpleAction {
	private static final long serialVersionUID = 3680027327640826434L;
	private int memberCount;
	private RfmsMemberService rfmsMemberService;

	public ActionForward save(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		MemberForm aform = (MemberForm) arg1;

		FormFile file = aform.getStrFile();// 取得上传的文件
		String fName = file.getFileName().toLowerCase();
		if (!fName.endsWith(".xls")) {
			return unspecified(arg0, arg1, arg2, arg3);
		} else {
			try {
				Date now = new Date();
				String seq = DateUtil.date2MysqlDate(now) + "";
				String fileName = seq + ".xls";

				WebHelper.writeUploadFile2Server(fileName, file);
				String strMerchantId = aform.getCurrentUser().getMerchantCode();
				Long merchantId = new Long(0);
				if (!StringUtils.isEmpty(strMerchantId)) {
					merchantId = new Long(strMerchantId);
				}
				memberCount = rfmsMemberService.importMember(fileName,
						merchantId);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return unspecified(arg0, arg1, arg2, arg3);
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public RfmsMemberService getRfmsMemberService() {
		return rfmsMemberService;
	}

	public void setRfmsMemberService(RfmsMemberService rfmsMemberService) {
		this.rfmsMemberService = rfmsMemberService;
	}

}
