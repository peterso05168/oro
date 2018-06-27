package hk.oro.iefas.core.util;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class BusinessUtils {

    private static String format = "^[A-Z0-9]{1,3}\\([A-Z]{1}\\)[0-9]{1}$";

    public static String genNarrative(String caseSeqNo, String caseYear, String postTitle, String narrative) {
        StringBuilder result = new StringBuilder();
        String[] post = splitPostTitle(postTitle);
        if (CommonUtils.isNotEmpty(post)) {
            result.append(caseSeqNo).append(caseYear).append(post[1]).append(narrative).append(post[2]);
        }
        return result.toString();
    }

    public static String[] splitPostTitle(String postTitle) {
        if (postTitle.matches(format)) {
            String rank = postTitle.substring(0, postTitle.indexOf("("));
            String caseTeam = postTitle.substring(postTitle.indexOf("(") + 1, postTitle.indexOf(")"));
            String postNum = postTitle.substring(postTitle.indexOf(")") + 1, postTitle.length());
            String[] result = new String[] {rank, caseTeam, postNum};
            return result;
        } else {
            return null;
        }
    }

    public static String addZeroToCaseNo(Integer caseNo) {
        return String.format("%05d", caseNo);
    }

    public static String genCaseNumber(String caseTypeCode, String caseNo, String caseYear) {
        StringBuilder result = new StringBuilder();
        if (CommonUtils.isNotBlank(caseTypeCode) && CommonUtils.isNotBlank(caseNo)
            && CommonUtils.isNotBlank(caseYear)) {
            result.append(caseTypeCode.toUpperCase()).append("-").append(caseNo).append("/").append(caseYear);
        }
        return result.toString();
    }

    public static String genAccountNumber(String caseTypeCode, String accountType, String caseNo, String caseYear) {
        StringBuilder result = new StringBuilder();
        result.append(caseTypeCode.toUpperCase()).append("-").append(accountType.toUpperCase()).append("-")
            .append(caseNo).append("/").append(caseYear);
        return result.toString();
    }

}
