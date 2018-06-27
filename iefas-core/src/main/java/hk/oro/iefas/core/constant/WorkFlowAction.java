package hk.oro.iefas.core.constant;

import java.util.Arrays;
import java.util.Optional;

import lombok.Getter;

/**
 * @version $Revision: 3170 $ $Date: 2018-06-15 19:34:19 +0800 (週五, 15 六月 2018) $
 * @author $Author: noah.liang $
 */
public enum WorkFlowAction {

    /**
     * Save Action
     */
    Save("SAV", "Save"),

    /**
     * Submit Action
     */
    Submit("SUB", "Submit"),

    /**
     * Confirm Action
     */
    Confirm("COM", "Confirm"),

    /**
     * Approve Action
     */
    Approve("APP", "Approve"),

    /**
     * Reject Action
     */
    Reject("REJ", "Reject"),

    /**
     * Delete Action
     */
    Delete("DEL", "Delete"),

    /**
     * Submit for 2nd Approve
     */
    SubmitFor2ndApprove("SSA", "Submit for 2nd Approve"),

    /**
     * Verify
     */
    Verify("VER", "Verify"),

    /**
     * Reverse
     */
    Reverse("REV", "Reverse"),

    /**
     * Print
     */
    Print("PRC", "Print Cheque"),

    /**
     * Re issues
     */
    Reissues("REI", "Re issues"),

    /**
     * Pending for payment
     */
    PendingForPayment("PP", "Pending for payment"),

    /**
     * Payment Received
     */
    PaymentReceived("PR", "Payment Received");

    @Getter
    private String code;

    @Getter
    private String desc;

    private WorkFlowAction(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WorkFlowAction getByCode(String code) {
        Optional<WorkFlowAction> actionOptional
            = Arrays.stream(WorkFlowAction.values()).filter(action -> action.getCode().equals(code)).findFirst();
        return actionOptional.isPresent() ? actionOptional.get() : null;
    }
}
