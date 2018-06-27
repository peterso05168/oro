/**
 * 
 */
package hk.oro.iefas.web.common.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.ledger.voucherhandling.paymentvoucher.view.UploadSupportingDocView;
import hk.oro.iefas.web.system.divisionadministration.post.view.PrivilegeDialogView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2897 $ $Date $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@RequestScoped
public class DialogBean {

    private static final String PRIVILEGE_DIALOG_URI = "privilege-dialog";
    private static final String SWITCH_USER_DIALOG_URI = "switch-user";
    private static final String UPLOAD_SUPPORTING_DOC_URI = "upload-support-doc-dialog";

    @PostConstruct
    private void init() {
        log.info("======DialogBean init======");
    }

    public void openPrivilegeDialog(Integer roleId) {
        log.info("openDialog() start");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 1000);
        options.put("minHeight", 100);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        Map<String, List<String>> params = new HashMap<>();
        params.put(PrivilegeDialogView.REQUEST_PARAM_ROLE_ID, Arrays.asList(roleId.toString()));
        RequestContext.getCurrentInstance().openDialog(PRIVILEGE_DIALOG_URI, options, params);
        log.info("openDialog() end");
    }

    public void openSwitchUserDialog() {
        log.info("openSwitchUserDialog() start");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("minHeight", 50);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        RequestContext.getCurrentInstance().openDialog(SWITCH_USER_DIALOG_URI, options, null);
        log.info("openSwitchUserDialog() end");
    }

    public void openUploadDocDialog(Integer voucherId, Integer voucherAttachmentId) {
        log.info("openUploadDocDialog() start");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 950);
        options.put("height", 320);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        Map<String, List<String>> params = new HashMap<>();
        if (voucherId != null) {
            params.put(UploadSupportingDocView.REQUEST_PARAM_VOUCHER_ID, Arrays.asList(voucherId.toString()));
        }
        if (voucherAttachmentId != null) {
            params.put(UploadSupportingDocView.REQUEST_PARAM_VOUCHER_ATTACHMENT_ID,
                Arrays.asList(voucherAttachmentId.toString()));
        }
        RequestContext.getCurrentInstance().openDialog(UPLOAD_SUPPORTING_DOC_URI, options, params);
        log.info("openUploadDocDialog() end");
    }

    public void closeDialog() {
        log.info("closeDialog() start");
        RequestContext.getCurrentInstance().closeDialog(null);
        log.info("closeDialog() end");
    }
}
