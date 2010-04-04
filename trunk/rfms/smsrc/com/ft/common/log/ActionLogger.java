package com.ft.common.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ft.busi.sm.model.OperateLogManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OperatorLogDTO;
import com.ft.sm.dto.PerformanceLogDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * ���ڼ�¼������־����.
 * @version 1.0
 */
public class ActionLogger {
    private static final String LOG_MANAGER_BEAN_NAME = "operateLogManager";

    private static final String SYSTEM_PREFIX = "SUB_SYSTEM_";

    private static final String MODEL_PREFIX = "MODEL_";

    private String subSystemCode;

    private String modelCode;

    private OperateLogManager logManager;

    /**
     * ���캯��������className������Ӧ����ϵͳ�����ģ�����
     * 
     * @param className
     */
    public ActionLogger(String className) {
        if (className != null && className.length() > 0) {
            String[] temp = className.split("\\.");
            if (temp.length > 4) {
                subSystemCode = SYSTEM_PREFIX + temp[4].toUpperCase();
            }

            if (temp.length > 5) {
                modelCode = MODEL_PREFIX + temp[5].toUpperCase();
            }
        }

        if (subSystemCode == null) {
            subSystemCode = className;
        }

        if (modelCode == null) {
            modelCode = className;
        }
    }

    /**
     * д�������־
     * 
     * @param request
     *                Http����
     * @param actionCode
     *                ��������
     * @param actionSeq
     *                ������ˮ��
     * @param resultCode
     *                �����������
     */
    public void log(HttpServletRequest request, String actionCode,
            String actionSeq, String resultCode) {
        OperatorLogDTO log = new OperatorLogDTO();
        log.setActionCode(actionCode);

        // ��ȥ�������ַ���
        if (actionSeq != null && actionSeq.length() > 255) {
            actionSeq = actionSeq.substring(0, 255);
        }

        log.setActionSeq(actionSeq);
        log.setModelName(this.modelCode);
        log.setSystemCode(this.subSystemCode);
        log.setRemoteHost(getRemoteAddress(request));
        log.setResultCode(resultCode);
        OperatorDTO user = OperatorSessionHelper.getLoginOp(request);
        if (user != null) {
            log.setOperatorLoginName(user.getLoginName());
            log.setOperatorName(user.getContact().getName());
            log.setOrgName(user.getDepartment().getOrgName());
        }

        OperateLogManager opLogManager = this.getLogManager(request);
        try {
            opLogManager.save(log);
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    /**
     * д��������־
     * 
     * @param request
     *                Http����
     * @param actionCode
     *                ��������
     * @param actionSeq
     *                ������ˮ��
     * @param resultCode
     *                �����������
     * @param startTime
     *                ������ʼʱ��
     * @param endTime
     *                ��������ʱ��
     */
    public void log(HttpServletRequest request, String actionCode,
            String actionSeq, String resultCode, Date startTime, Date endTime) {
        PerformanceLogDTO log = new PerformanceLogDTO();
        log.setActionCode(actionCode);

        // ��ȥ�������ַ���
        if (actionSeq != null && actionSeq.length() > 255) {
            actionSeq = actionSeq.substring(0, 255);
        }

        log.setActionSeq(actionSeq);
        log.setModelName(this.modelCode);
        log.setSystemCode(this.subSystemCode);
        log.setRemoteHost(getRemoteAddress(request));
        log.setResultCode(resultCode);
        log.setStartTime(startTime);
        log.setEndTime(endTime);

        OperatorDTO user = OperatorSessionHelper.getLoginOp(request);
        if (user != null) {
            log.setOperatorLoginName(user.getLoginName());
            log.setOperatorName(user.getContact().getName());
            log.setOrgName(user.getDepartment().getOrgName());
        }

        OperateLogManager opLogManager = this.getLogManager(request);
        try {
            opLogManager.save(log);
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    private String getRemoteAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() <= 0)
            ipAddress = request.getRemoteAddr();

        return ipAddress;
    }

    private OperateLogManager getLogManager(HttpServletRequest request) {
        if (this.logManager == null) {
            this.logManager = (OperateLogManager) SpringWebUtils.getBean(
                    request, LOG_MANAGER_BEAN_NAME);
        }

        return this.logManager;
    }
}
