package hk.oro.iefas.ws.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import hk.oro.iefas.core.security.UserInfo;

public class RequestContextUtils {

    public static final String CURRENT_USER_INFO = "CURRENT_USER_INFO";
    public static final String TXN_KEY_REF = "TXN_KEY_REF";

    public static void setTxnKeyRef(Long txnKeyRef) {
        set(TXN_KEY_REF, txnKeyRef);
    }

    public static Long getTxnKeyRef() {
        Object object = get(TXN_KEY_REF);
        Long txnKeyRef = null;
        if (object != null && object instanceof Long) {
            txnKeyRef = (Long)object;
        }
        return txnKeyRef;
    }

    public static void setCurrentUserInfo(UserInfo userInfo) {
        set(CURRENT_USER_INFO, userInfo);
    }

    public static UserInfo getCurrentUserInfo() {
        Object object = get(CURRENT_USER_INFO);
        UserInfo userInfo = null;
        if (object != null && object instanceof UserInfo) {
            userInfo = (UserInfo)object;
        }
        return userInfo;
    }

    public static void set(String key, Object value) {
        set(key, value, RequestAttributes.SCOPE_REQUEST);
    }

    public static Object get(String key) {
        return get(key, RequestAttributes.SCOPE_REQUEST);
    }

    public static void set(String key, Object value, int scope) {
        if (RequestContextHolder.getRequestAttributes() != null) {
            RequestContextHolder.getRequestAttributes().setAttribute(key, value, scope);
        }

    }

    public static Object get(String key, int scope) {
        if (RequestContextHolder.getRequestAttributes() != null) {
            return RequestContextHolder.getRequestAttributes().getAttribute(key, scope);
        }
        return null;
    }

}
