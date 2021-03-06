package org.landy.business.domain.file;

import org.landy.business.domain.detail.XHFRequestDetail;
import org.landy.business.enums.WorkflowEnum;
import org.landy.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author landyl
 * @create 9:46 AM 05/08/2018
 */
public class XHFRequestFile extends RequestFile<XHFRequestDetail> {
    public static final String[] xhfDetailHeaders = {
            "ROW_NUMBER", "SYSTEM_SOURCE", "SYSTEM_SOURCE_ID","MEMBER_NUMBER",
            "IS_ACTIVE", "IS_REVERSED","NOT_CANCELLED","GENDER"
    };

    private List<XHFRequestDetail> requestXHFDetails;

    @Override
    public List<XHFRequestDetail> getRequestDetails() {
        return getRequestXHFDetails();
    }

    @Override
    public String[] getDetailHeaders() {
        return xhfDetailHeaders;
    }

    @Override
    public WorkflowEnum getProcessWorkFlow() {
        return WorkflowEnum.XHF;
    }

    private List<XHFRequestDetail> getRequestXHFDetails() {
        if (requestXHFDetails == null) {
            List<String> detailLines = getDetailLines();

            if (detailLines == null) return null;

            XHFRequestDetail detail;
            requestXHFDetails = new ArrayList<>();
            for (String detailLine : detailLines) {
                detail = parseDetailLinesToRequestBOBDetail(detailLine);
                requestXHFDetails.add(detail);
            }
        }

        return requestXHFDetails;
    }

    private XHFRequestDetail parseDetailLinesToRequestBOBDetail(String detailLine) {
        XHFRequestDetail detail = new XHFRequestDetail();
        String[] detailValues = detailLine.split(Constants.DELIMITER_PIPE);
        parseToDetail(detail,detailValues);
        return detail;
    }

}
