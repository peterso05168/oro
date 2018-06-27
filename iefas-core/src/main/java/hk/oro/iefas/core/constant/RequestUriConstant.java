package hk.oro.iefas.core.constant;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public class RequestUriConstant {

    /**
     * Common
     */
    public static final String URI_FIND_ALL = "/findAll";
    public static final String URI_FIND_ONE = "/findOne";
    public static final String URI_LIST = "/list";
    public static final String URI_SAVE = "/save";

    public static final String URI_ROOT_COMMON = "/common";
    public static final String URI_STATUS_LIST = "/searchStatusList";
    public static final String URI_CASE_TYPE_LIST = "/searchCaseTypeList";
    public static final String URI_DIVIDEND_CASE_TYPE_LIST = "/searchDividendCaseTypeList";
    public static final String URI_OUTSIDER_TYPE_LIST = "/searchOutsiderTypeList";
    public static final String URI_CALCULATION_METHOD = "/searchCalculationMethod";
    public static final String URI_NATURE_OF_CLAIM = "/searchNatureOfClaim";
    public static final String URI_CASE_ACCOUNT_TYPE_LIST = "/searchCaseAccountTypeList";
    public static final String URI_PAYMENT_TYPE_LIST = "/searchPaymentType";
    public static final String URI_DIVIDEND_SCHEDULE_STATUS_LIST = "/searchDividendScheduleStatus";
    public static final String URI_PLEDGE_TYPE_LIST = "/searchPledgeType";
    public static final String URI_ADDRESS_TYPE_LIST = "/searchAddressType";
    public static final String URI_DIVIDEND_SCHEDULE_TYPE_LIST = "/searchDividendScheduleType";
    public static final String URI_SEARCH_ANALYSIS_CODE = "/searchAnalysisCodeList";
    public static final String URI_SEARCH_ALL_CODE_TABLE = "/searchAllCodeTable";
    public static final String URI_SEARCH_ANALYSIS_CODE_TYPE = "/searchAnalysisCodeTypeList";
    public static final String URI_SEARCH_VOUCHER_TYPE = "/searchVoucherTypeList";
    public static final String URI_SEARCH_PAYMENT_TYPE_LIST = "/searchPaymentTypeList";
    public static final String URI_SEARCH_REJECT_REASON_LIST = "/searchRejectReasonList";

    public static final String CLIENT_URI_STATUS_LIST = URI_ROOT_COMMON + URI_STATUS_LIST;
    public static final String CLIENT_URI_CASE_TYPE_LIST = URI_ROOT_COMMON + URI_CASE_TYPE_LIST;
    public static final String CLIENT_URI_DIVIDEND_CASE_TYPE_LIST = URI_ROOT_COMMON + URI_DIVIDEND_CASE_TYPE_LIST;
    public static final String CLIENT_URI_CALCULATION_METHOD = URI_ROOT_COMMON + URI_CALCULATION_METHOD;
    public static final String CLIENT_URI_OUTSIDER_TYPE_LIST = URI_ROOT_COMMON + URI_OUTSIDER_TYPE_LIST;
    public static final String CLIENT_URI_NATURE_OF_CLAIM = URI_ROOT_COMMON + URI_NATURE_OF_CLAIM;
    public static final String CLIENT_URI_CASE_ACCOUNT_TYPE_LIST = URI_ROOT_COMMON + URI_CASE_ACCOUNT_TYPE_LIST;
    public static final String CLIENT_URI_PAYMENT_TYPE_LIST = URI_ROOT_COMMON + URI_PAYMENT_TYPE_LIST;
    public static final String CLIENT_URI_DIVIDEND_SCHEDULE_STATUS_LIST
        = URI_ROOT_COMMON + URI_DIVIDEND_SCHEDULE_STATUS_LIST;
    public static final String CLIENT_URI_PLEDGE_TYPE_LIST = URI_ROOT_COMMON + URI_PLEDGE_TYPE_LIST;
    public static final String CLIENT_URI_ADDRESS_TYPE_LIST = URI_ROOT_COMMON + URI_ADDRESS_TYPE_LIST;
    public static final String CLIENT_URI_DIVIDEND_SCHEDULE_TYPE_LIST
        = URI_ROOT_COMMON + URI_DIVIDEND_SCHEDULE_TYPE_LIST;
    public static final String CLIENT_URI_SEARCH_ANALYSIS_CODE_LIST = URI_ROOT_COMMON + URI_SEARCH_ANALYSIS_CODE;
    public static final String CLIENT_URI_SEARCH_ALL_CODE_TABLE = URI_ROOT_COMMON + URI_SEARCH_ALL_CODE_TABLE;
    public static final String CLIENT_URI_SEARCH_ANALYSIS_CODE_TYPE_LIST
        = URI_ROOT_COMMON + URI_SEARCH_ANALYSIS_CODE_TYPE;
    public static final String CLIENT_URI_SEARCH_VOUCHER_TYPE = URI_ROOT_COMMON + URI_SEARCH_VOUCHER_TYPE;
    public static final String CLIENT_SEARCH_URI_PAYMENT_TYPE_LIST = URI_ROOT_COMMON + URI_SEARCH_PAYMENT_TYPE_LIST;
    public static final String CLIENT_SEARCH_URI_REJECT_REASON_LIST = URI_ROOT_COMMON + URI_SEARCH_REJECT_REASON_LIST;

    /**
     * Funds Common
     */
    public static final String URI_ROOT_FUNDS_COMMON = "/fundsCommon";
    public static final String URI_INVESTMENT_TYPE_LIST = "/searchInvestmentType";
    public static final String URI_INVESTMENT_STATUS_LIST = "/searchInvestmentStatus";

    public static final String CLIENT_URI_INVESTMENT_TYPE_LIST = URI_ROOT_FUNDS_COMMON + URI_INVESTMENT_TYPE_LIST;
    public static final String CLIENT_URI_INVESTMENT_STATUS_LIST = URI_ROOT_FUNDS_COMMON + URI_INVESTMENT_STATUS_LIST;

    /**
     * system Parameter
     */
    public static final String URI_ROOT_SYS_PARAM = "/sysparam";

    public static final String CLIENT_URI_SYS_PARAM_LIST = URI_ROOT_SYS_PARAM + URI_FIND_ALL;

    /**
     * Funds Parameter
     */
    public static final String URI_FUNDS_OTHER_PARA = "/fundsParameter";
    public static final String URI_LOAD_PARA = "/loadParameter";
    public static final String URI_SAVE_PARA = "/saveParameter";

    public static final String CLIENT_URI_LOAD_PARA = URI_FUNDS_OTHER_PARA + URI_LOAD_PARA;
    public static final String CLIENT_URI_SAVE_PARA = URI_FUNDS_OTHER_PARA + URI_SAVE_PARA;

    /**
     * User
     */
    public static final String URI_ROOT_USER = "/user";
    public static final String URI_USER_FINDBY_LOGINNAME = "/findByLoginName";
    public static final String URI_USER_DETAIL = "/findUserDetail";
    public static final String URI_USER_CLEARLOCK = "/clearLock";
    public static final String URI_USER_FINDBY_CRITERIA = "/searchUserList";
    public static final String URI_USER_INSERT = "/insertUserDetail";
    public static final String URI_USER_UPDATE = "/updateUserDetail";

    public static final String CLIENT_URI_USER_FINDBY_LOGINNAME = URI_ROOT_USER + URI_USER_FINDBY_LOGINNAME;
    public static final String CLIENT_URI_USER_DETAIL = URI_ROOT_USER + URI_USER_DETAIL;
    public static final String CLIENT_URI_USER_CLEARLOCK = URI_ROOT_USER + URI_USER_CLEARLOCK;
    public static final String CLIENT_URI_USER_SAVE = URI_ROOT_USER + URI_SAVE;
    public static final String CLIENT_URI_USER_FINDBY_CRITERIA = URI_ROOT_USER + URI_USER_FINDBY_CRITERIA;
    public static final String CLIENT_URI_USER_INSERT = URI_ROOT_USER + URI_USER_INSERT;
    public static final String CLIENT_URI_USER_UPDATE = URI_ROOT_USER + URI_USER_UPDATE;
    public static final String CLIENT_URI_USER_FINDONE = URI_ROOT_USER + URI_FIND_ONE;

    /**
     * Menu
     */
    public static final String URI_ROOT_MENU = "/menu";
    public static final String URI_TOPMENU_LIST = "/findAllTopMenu";
    public static final String URI_MENU_FINDBY_POSTID = "/findByPostId";

    public static final String CLIENT_URI_TOPMENU_LIST = URI_ROOT_MENU + URI_TOPMENU_LIST;
    public static final String CLIENT_URI_MENU_FINDBY_POSTID = URI_ROOT_MENU + URI_MENU_FINDBY_POSTID;

    /**
     * User Attempt Log
     */
    public static final String URI_ROOT_USER_ATTEMPT_LOG = "/userAttemptLog";

    public static final String CLIENT_URI_USER_ATTEMPT_LOG_SAVE = URI_ROOT_USER_ATTEMPT_LOG + URI_SAVE;

    /**
     * Delegation
     */
    public static final String URI_ROOT_DELEGATION = "/delegation";
    public static final String URI_DELEGATION_FINDBY_DELEGATION_TO = "/findByDelegateTo";
    public static final String URI_DELEGATION_FINDBY_DELEGATION_FROM = "/findByDelegateFrom";
    public static final String URI_DELEGATION_SAVE = "/saveDelegation";

    public static final String CLIENT_URI_DELEGATION_FINDBY_DELEGATION_TO
        = URI_ROOT_DELEGATION + URI_DELEGATION_FINDBY_DELEGATION_TO;
    public static final String CLIENT_URI_DELEGATION_FINDBY_DELEGATION_FROM
        = URI_ROOT_DELEGATION + URI_DELEGATION_FINDBY_DELEGATION_FROM;
    public static final String CLIENT_URI_DELEGATION_SAVE = URI_ROOT_DELEGATION + URI_DELEGATION_SAVE;

    /**
     * Division Admin
     */
    public static final String URI_ROOT_DIVISION_ADMIN = "/divisionAdmin";
    public static final String URI_DIVISION_ADMIN_BY_POST = "/getDivisionListByPost";
    public static final String URI_DIVISION_ADMIN_BY_DIVISION = "/getDivisionListByDivision";
    public static final String URI_DIVISION_ADMIN_BY_DIVISION_AND_POST = "/findByDivisionAndPost";

    public static final String CLIENT_URI_DIVISION_ADMIN_BY_POST = URI_ROOT_DIVISION_ADMIN + URI_DIVISION_ADMIN_BY_POST;
    public static final String CLIENT_URI_DIVISION_ADMIN_BY_DIVISION
        = URI_ROOT_DIVISION_ADMIN + URI_DIVISION_ADMIN_BY_DIVISION;
    public static final String CLIENT_URI_DIVISION_ADMIN_SAVE = URI_ROOT_DIVISION_ADMIN + URI_SAVE;
    public static final String CLIENT_URI_GET_DIVISION_ADMIN_DETAIL = URI_ROOT_DIVISION_ADMIN + URI_FIND_ONE;
    public static final String CLIENT_URI_DIVISION_ADMIN_BY_DIVISION_AND_POST
        = URI_ROOT_DIVISION_ADMIN + URI_DIVISION_ADMIN_BY_DIVISION_AND_POST;

    /**
     * Division
     */
    public static final String URI_ROOT_DIVISION = "/division";
    public static final String URI_DIVISION_BY_CRITERIA = "/findDivisionListByCriteria";
    public static final String URI_DIVISION_DETAIL = "/getDivisionDetail";

    public static final String CLIENT_URI_DIVISION_BY_CRITERIA = URI_ROOT_DIVISION + URI_DIVISION_BY_CRITERIA;
    public static final String CLIENT_URI_DIVISION_DETAIL = URI_ROOT_DIVISION + URI_DIVISION_DETAIL;

    /**
     * Post
     */
    public static final String URI_ROOT_POST = "/post";
    public static final String URI_POST_SAVE = "/savePostDetail";
    public static final String URI_POST_VALIDATE = "/validatePostDetail";
    public static final String URI_POST_DETAIL = "/getPostDetail";
    public static final String URI_POST_LIST = "/searchPostList";
    public static final String URI_POST_FINDBY_DIVISIONID = "/findByDivisionId";
    public static final String URI_POST_FINDBY_DIVISIONADMIN = "/findByDivisionAdmin";
    public static final String URI_POST_GET_FIRST_APPROVER = "/getFirstApprover";
    public static final String URI_POST_GET_SECOND_APPROVER = "/getSecondApprover";
    public static final String URI_POST_GET_A_APPROVER = "/getAApprover";
    public static final String URI_POST_GET_B_APPROVER = "/getBApprover";

    public static final String CLIENT_URI_POST_SAVE = URI_ROOT_POST + URI_POST_SAVE;
    public static final String CLIENT_URI_POST_VALIDATE = URI_ROOT_POST + URI_POST_VALIDATE;
    public static final String CLIENT_URI_POST_DETAIL = URI_ROOT_POST + URI_POST_DETAIL;
    public static final String CLIENT_URI_POST_LIST = URI_ROOT_POST + URI_POST_LIST;
    public static final String CLIENT_URI_POST_FINDBY_DIVISIONID = URI_ROOT_POST + URI_POST_FINDBY_DIVISIONID;
    public static final String CLIENT_URI_POST_FINDBY_DIVISIONADMIN = URI_ROOT_POST + URI_POST_FINDBY_DIVISIONADMIN;
    public static final String CLIENT_URI_POST_GET_FIRST_APPROVER = URI_ROOT_POST + URI_POST_GET_FIRST_APPROVER;
    public static final String CLIENT_URI_POST_GET_SECOND_APPROVER = URI_ROOT_POST + URI_POST_GET_SECOND_APPROVER;
    public static final String CLIENT_URI_POST_GET_A_APPROVER = URI_ROOT_POST + URI_POST_GET_A_APPROVER;
    public static final String CLIENT_URI_POST_GET_B_APPROVER = URI_ROOT_POST + URI_POST_GET_B_APPROVER;

    /**
     * Rank
     */
    public static final String URI_ROOT_RANK = "/rank";
    public static final String URI_RANK_DETAIL = "/getRankDetail";
    public static final String URI_FIND_BY_RANK_NAME = "/findByRankName";

    public static final String CLIENT_URI_RANK_DETAIL = URI_ROOT_RANK + URI_RANK_DETAIL;
    public static final String CLIENT_URI_RANK_LIST = URI_ROOT_RANK + URI_FIND_ALL;
    public static final String CLIENT_FIND_BY_RANK_NAME = URI_ROOT_RANK + URI_FIND_BY_RANK_NAME;

    /**
     * Division Privilege
     */
    public static final String URI_ROOT_DIVISION_PRIVILEGE = "/divisionPrivilege";
    public static final String URI_DIVISION_PRIVILEGE_SAVE = "/saveRolePrivilege";
    public static final String URI_DIVISION_PRIVILEGE_LIST = "/searchDivisionPrivilegeList";
    public static final String URI_DIVISION_PRIVILEGE_FINDBY_DIVISION = "/findByDivisionAndPrivilege";

    public static final String CLIENT_URI_DIVISION_PRIVILEGE_LIST
        = URI_ROOT_DIVISION_PRIVILEGE + URI_DIVISION_PRIVILEGE_LIST;
    public static final String CLIENT_URI_DIVISION_PRIVILEGE_SAVE
        = URI_ROOT_DIVISION_PRIVILEGE + URI_DIVISION_PRIVILEGE_SAVE;
    public static final String CLIENT_URI_DIVISION_PRIVILEGE_FINDBY_DIVISION
        = URI_ROOT_DIVISION_PRIVILEGE + URI_DIVISION_PRIVILEGE_FINDBY_DIVISION;

    /**
     * Privilege
     */
    public static final String URI_ROOT_PRIVILEGE = "/privilege";
    public static final String URI_PRIVILEGE_FINDBY_POSTID = "/findByPostId";
    public static final String URI_PRIVILEGE_LIST = "/findAll";

    public static final String CLIENT_URI_PRIVILEGE_FINDBY_POSTID = URI_ROOT_PRIVILEGE + URI_PRIVILEGE_FINDBY_POSTID;
    public static final String CLIENT_URI_PRIVILEGE_LIST = URI_ROOT_PRIVILEGE + URI_PRIVILEGE_LIST;

    /**
     * Role Privilege
     */
    public static final String URI_ROOT_ROLE_PRIVILEGE = "/rolePrivilege";
    public static final String URI_ROLE_PRIVILEGE_SAVE = "/saveRolePrivilege";
    public static final String URI_ROLE_PRIVILEGE_DETAIL = "/findRolePrivilege";
    public static final String URI_ROLE_PRIVILEGE_LIST = "/searchRolePrivilegeList";
    public static final String URI_ROLE_PRIVILEGE_FINDBY_ROLE = "/findByRoleAndPrivilege";

    public static final String CLIENT_URI_ROLE_PRIVILEGE_SAVE = URI_ROOT_ROLE_PRIVILEGE + URI_ROLE_PRIVILEGE_SAVE;
    public static final String CLIENT_URI_ROLE_PRIVILEGE_DETAIL = URI_ROOT_ROLE_PRIVILEGE + URI_ROLE_PRIVILEGE_DETAIL;
    public static final String CLIENT_URI_ROLE_PRIVILEGE_LIST = URI_ROOT_ROLE_PRIVILEGE + URI_ROLE_PRIVILEGE_LIST;
    public static final String CLIENT_URI_ROLE_PRIVILEGE_FINDBY_ROLE
        = URI_ROOT_ROLE_PRIVILEGE + URI_ROLE_PRIVILEGE_FINDBY_ROLE;

    /**
     * Currency
     */
    public static final String URI_ROOT_CURRENCY = "/currencies";
    public static final String URI_CURRENCY_SAVE = "/saveCurrency";
    public static final String URI_CURRENCY_EXISTS = "/exists";
    public static final String URI_CURRENCY_LIST = "/searchCurrencyRateList";
    public static final String URI_CURRENCY_DETAIL = "/searchCurrencyRate";

    public static final String CLIENT_URI_CURRENCY_SAVE_CURRENCY = URI_ROOT_CURRENCY + URI_CURRENCY_SAVE;
    public static final String CLIENT_URI_CURRENCY_EXISTS = URI_ROOT_CURRENCY + URI_CURRENCY_EXISTS;
    public static final String CLIENT_URI_CURRENCY_LIST = URI_ROOT_CURRENCY + URI_CURRENCY_LIST;
    public static final String CLIENT_URI_CURRENCY_FIND_ALL = URI_ROOT_CURRENCY + URI_FIND_ALL;
    public static final String CLIENT_URI_CURRENCY_FIND_ONE = URI_ROOT_CURRENCY + URI_CURRENCY_DETAIL;

    /**
     * Bank Account
     */
    public static final String URI_ROOT_BANK_ACCOUNT = "/bankAccount";
    public static final String URI_BANK_ACCOUNT_LIST = "/searchBankAccountInfoList";
    public static final String URI_BANK_ACCOUNT_SAVE = "/saveBankAccountInfo";
    public static final String URI_BANK_ACCOUNT_DETAIL = "/searchBankAccountInfo";
    public static final String URI_BANK_ACCOUNT_EXISTSBY_BANKCODE = "/existsByBankCode";
    public static final String URI_BANK_ACCOUNT_EXISTSBY_BANKNAME = "/existsByBankName";
    public static final String URI_BANK_ACCOUNT_EXISTSBY_BANKSHORTNAME = "/existsByBankShortName";
    public static final String URI_BANK_ACCOUNT_FINDBY_BANKNAME = "/findByBankName";

    public static final String CLIENT_URI_BANK_ACCOUNT_LIST = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_LIST;
    public static final String CLIENT_URI_BANK_ACCOUNT_SAVE = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_SAVE;
    public static final String CLIENT_URI_BANK_ACCOUNT_DETAIL = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_DETAIL;
    public static final String CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKCODE
        = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_EXISTSBY_BANKCODE;
    public static final String CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKNAME
        = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_EXISTSBY_BANKNAME;
    public static final String CLIENT_URI_BANK_ACCOUNT_EXISTSBY_BANKSHORTNAME
        = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_EXISTSBY_BANKSHORTNAME;
    public static final String CLIENT_URI_BANK_ACCOUNT_FINDBY_BANKNAME
        = URI_ROOT_BANK_ACCOUNT + URI_BANK_ACCOUNT_FINDBY_BANKNAME;
    public static final String CLIENT_URI_BANK_ACCOUNT_FIND_ALL = URI_ROOT_BANK_ACCOUNT + URI_FIND_ALL;

    /**
     * Bank Rate
     */
    public static final String URI_ROOT_BANK_RATE = "/bankRate";
    public static final String URI_BANK_RATE_LIST = "/searchUploadBankRateList";
    public static final String URI_BANK_RATE_SAVE = "/createUploadBankRate";
    public static final String URI_BANK_RATE_SAVE_VALIDATE = "/createUploadBankRateValidate";
    public static final String URI_BANK_RATE_DETAIL = "/searchUploadBankRate";
    public static final String URI_BANK_RATE_LIST_SAVE = "/saveDailyBankRateList";
    public static final String URI_BANK_DEPOSIT_TYPE_LIST = "/findBankDepositTypeList";
    public static final String URI_BANKRATE_TEMPLATE_DOWNLOAD = "/downloadBankRateTemplate";

    public static final String CLIENT_URI_BANK_RATE_LIST = URI_ROOT_BANK_RATE + URI_BANK_RATE_LIST;
    public static final String CLIENT_URI_BANK_RATE_SAVE = URI_ROOT_BANK_RATE + URI_BANK_RATE_SAVE;
    public static final String CLIENT_URI_BANK_RATE_SAVE_VALIDATE = URI_ROOT_BANK_RATE + URI_BANK_RATE_SAVE_VALIDATE;
    public static final String CLIENT_URI_BANK_RATE_DETAIL = URI_ROOT_BANK_RATE + URI_BANK_RATE_DETAIL;
    public static final String CLIENT_URI_BANK_RATE_LIST_SAVE = URI_ROOT_BANK_RATE + URI_BANK_RATE_LIST_SAVE;
    public static final String CLIENT_URI_BANK_DEPOSIT_TYPE_LIST = URI_ROOT_BANK_RATE + URI_BANK_DEPOSIT_TYPE_LIST;
    public static final String CLIENT_URI_BANKRATE_TEMPLATE_DOWNLOAD
        = URI_ROOT_BANK_RATE + URI_BANKRATE_TEMPLATE_DOWNLOAD;

    /**
     * Cash Requirement
     */
    public static final String URI_ROOT_CASH_REQUIREMENT = "/cashRequirement";
    public static final String URI_CASH_REQUIREMENT_LIST = "/searchCashRequirementList";
    public static final String URI_CASH_REQUIREMENT_DETAIL = "/searchCashRequirement";
    public static final String URI_CASH_REQUIREMENT_SAVE = "/saveCaseRequirement";
    public static final String URI_CASH_REQUIREMENT_SAVE_VALIDATE = "/saveCaseRequirementValidate";
    public static final String URI_DAILY_CASH_REQUIREMENT_DETAIL = "/searchDailyCashRequirement";

    public static final String CLIENT_URI_CASH_REQUIREMENT_LIST = URI_ROOT_CASH_REQUIREMENT + URI_CASH_REQUIREMENT_LIST;
    public static final String CLIENT_URI_CASH_REQUIREMENT_DETAIL
        = URI_ROOT_CASH_REQUIREMENT + URI_CASH_REQUIREMENT_DETAIL;
    public static final String CLIENT_URI_CASH_REQUIREMENT_SAVE = URI_ROOT_CASH_REQUIREMENT + URI_CASH_REQUIREMENT_SAVE;
    public static final String CLIENT_URI_CASH_REQUIREMENT_SAVE_VALIDATE
        = URI_ROOT_CASH_REQUIREMENT + URI_CASH_REQUIREMENT_SAVE_VALIDATE;
    public static final String CLIENT_URI_DAILY_CASH_REQUIREMENT_DETAIL
        = URI_ROOT_CASH_REQUIREMENT + URI_DAILY_CASH_REQUIREMENT_DETAIL;

    /**
     * Investment Instruction
     */
    public static final String URI_ROOT_INVESTMENT_INSTRUCTION = "/investmentInstruction";
    public static final String URI_INVESTMENT_INSTRUCTION_LIST = "/searchInvestmentInstructionList";
    public static final String URI_INVESTMENT_INSTRUCTION_DETAIL = "/searchInvestmentInstruction";

    public static final String CLIENT_URI_INVESTMENT_INSTRUCTION_LIST
        = URI_ROOT_INVESTMENT_INSTRUCTION + URI_INVESTMENT_INSTRUCTION_LIST;
    public static final String CLIENT_URI_INVESTMENT_INSTRUCTION_DETAIL
        = URI_ROOT_INVESTMENT_INSTRUCTION + URI_INVESTMENT_INSTRUCTION_DETAIL;

    /**
     * User Profile
     */
    public static final String URI_ROOT_USER_PROFILE = "/userProfile";
    public static final String URI_USER_PROFILE_SAVE = "/saveUserProfile";
    public static final String URI_USER_NAME_BY_POST = "/getUserNameByPostId";
    public static final String URI_USER_EXIST_EMAIL = "/existsByEmailAddress";

    public static final String CLIENT_URI_USER_PROFILE_SAVE = URI_ROOT_USER_PROFILE + URI_USER_PROFILE_SAVE;
    public static final String CLIENT_URI_USER_NAME_BY_POST = URI_ROOT_USER_PROFILE + URI_USER_NAME_BY_POST;
    public static final String CLIENT_URI_USER_EXIST_EMAIL = URI_ROOT_USER_PROFILE + URI_USER_EXIST_EMAIL;

    /**
     * Role
     */
    public static final String URI_ROOT_ROLE = "/role";
    public static final String URI_ROLE_SUMMARY_LIST = "/findRoleSummaryList";
    public static final String URI_ROLE_SAVE = "/saveRoleDetail";
    public static final String URI_ROLE_VALIDATE = "/validateRoleDetail";
    public static final String URI_ROLE_DETAIL = "/getRoleDetail";
    public static final String URI_ROLE_LIST = "/searchRoleList";
    public static final String URI_ROLE_LIST_BY_DIVISION = "/findRoleListByDivision";
    public static final String URI_VALIDATE_ROLE_DETAIL = "/validateRoleDetail";

    public static final String CLIENT_URI_ROLE_SUMMARY_LIST = URI_ROOT_ROLE + URI_ROLE_SUMMARY_LIST;
    public static final String CLIENT_URI_ROLE_SAVE = URI_ROOT_ROLE + URI_ROLE_SAVE;
    public static final String CLIENT_URI_ROLE_VALIDATE = URI_ROOT_ROLE + URI_ROLE_VALIDATE;
    public static final String CLIENT_URI_ROLE_DETAIL = URI_ROOT_ROLE + URI_ROLE_DETAIL;
    public static final String CLIENT_URI_ROLE_LIST = URI_ROOT_ROLE + URI_ROLE_LIST;
    public static final String CLIENT_URI_ROLE_LIST_BY_DIVISION = URI_ROOT_ROLE + URI_ROLE_LIST_BY_DIVISION;
    public static final String CLIENT_URI_VALIDATE_ROLE_DETAIL = URI_ROOT_ROLE + URI_VALIDATE_ROLE_DETAIL;

    /**
     * PostRole
     */
    public static final String URI_ROOT_POST_ROLE = "/postRole";
    public static final String URI_POST_ROLE_SAVE = "/savePostRole";
    public static final String URI_POST_ROLE_LIST_BY_POST = "/searchPostRoleList";
    public static final String URI_POST_ROLE_BY_POST_AND_ROLE = "/findByPostAndRole";
    public static final String URI_POST_ROLE_BY_ROLE = "/findByRole";

    public static final String CLIENT_URI_POST_ROLE_SAVE = URI_ROOT_POST_ROLE + URI_POST_ROLE_SAVE;
    public static final String CLIENT_URI_POST_ROLE_LIST_BY_POST = URI_ROOT_POST_ROLE + URI_POST_ROLE_LIST_BY_POST;
    public static final String CLIENT_URI_POST_ROLE_BY_POST_AND_ROLE
        = URI_ROOT_POST_ROLE + URI_POST_ROLE_BY_POST_AND_ROLE;
    public static final String CLIENT_URI_POST_ROLE_BY_ROLE = URI_ROOT_POST_ROLE + URI_POST_ROLE_BY_ROLE;

    /**
     * Holiday
     */
    public static final String URI_ROOT_HOLIDAY = "/holiday";
    public static final String URI_HOLIDAY_SAVE = "/saveHoliday";
    public static final String URI_HOLIDAY_CREATE = "/createHoliday";
    public static final String URI_HOLIDAY_LOAD = "/loadHoliday";
    public static final String URI_HOLIDAY_LIST = "/searchHoliday";
    public static final String URI_HOLIDAY_DELETE = "/deleteHoliday";
    public static final String URI_HOLIDAY_IMPORT_TEMPLATE = "/importListTemplate";
    public static final String URI_HOLIDAY_DOWNLOAD = "/downloadHolidayTemplate";
    public static final String URI_HOLIDAY_CONFIRM_UPLOAD = "/confirmUploadRecord";

    public static final String CLIENT_URI_HOLIDAY_SAVE = URI_ROOT_HOLIDAY + URI_HOLIDAY_SAVE;
    public static final String CLIENT_URI_HOLIDAY_CREATE = URI_ROOT_HOLIDAY + URI_HOLIDAY_CREATE;
    public static final String CLIENT_URI_HOLIDAY_LOAD = URI_ROOT_HOLIDAY + URI_HOLIDAY_LOAD;
    public static final String CLIENT_URI_HOLIDAY_LIST = URI_ROOT_HOLIDAY + URI_HOLIDAY_LIST;
    public static final String CLIENT_URI_HOLIDAY_DELETE = URI_ROOT_HOLIDAY + URI_HOLIDAY_DELETE;
    public static final String CLIENT_URI_HOLIDAY_DOWNLOAD = URI_ROOT_HOLIDAY + URI_HOLIDAY_DOWNLOAD;
    public static final String CLIENT_URI_HOLIDAY_IMPORT_TEMPLATE = URI_ROOT_HOLIDAY + URI_HOLIDAY_IMPORT_TEMPLATE;
    public static final String CLIENT_URI_HOLIDAY_CONFIRM_UPLOAD = URI_ROOT_HOLIDAY + URI_HOLIDAY_CONFIRM_UPLOAD;
    /**
     * Placing Deposits
     */
    public static final String URI_ROOT_PLACING_DEPOSITS = "/placingDeposits";
    public static final String URI_PLACING_DEPOSITS_LIST = "/searchPlacingDepositsList";
    public static final String URI_PLACING_DEPOSITS_DETAIL = "/searchPlacingDeposits";
    public static final String URI_PLACING_DEPOSITS_SAVE = "/savePlacingDeposits";
    public static final String URI_PLACING_DEPOSITS_EXISTS = "/existsByInvestmentDate";

    public static final String CLIENT_URI_PLACING_DEPOSITS_LIST = URI_ROOT_PLACING_DEPOSITS + URI_PLACING_DEPOSITS_LIST;
    public static final String CLIENT_URI_PLACING_DEPOSITS_DETAIL
        = URI_ROOT_PLACING_DEPOSITS + URI_PLACING_DEPOSITS_DETAIL;
    public static final String CLIENT_URI_PLACING_DEPOSITS_SAVE = URI_ROOT_PLACING_DEPOSITS + URI_PLACING_DEPOSITS_SAVE;
    public static final String CLIENT_URI_PLACING_DEPOSITS_EXISTS
        = URI_ROOT_PLACING_DEPOSITS + URI_PLACING_DEPOSITS_EXISTS;

    /**
     * Dividend
     */
    public static final String URI_ROOT_COMMON_DIVIDEND = "/commonDividend";
    public static final String URI_COMMON_DIVIDEND_ORFEEITEM_LIST = "/searchORFeeItemList";
    public static final String URI_ROOT_ORFEE = "/ORFee";
    public static final String URI_ORFEE_SEARCH_ORFEEITEM = "/searchORFeeItemWithCalculationMethod";
    public static final String URI_ORFEE_VALIDATE_SAVE_ORFEEITEM = "/validateSaveORFeeItemWithCalculationMethod";
    public static final String URI_ORFEE_SAVE_ORFEE_ITEM = "/saveORFeeItemWithCalculationMethod";
    public static final String URI_ORFEE_SEARCH_ORFEE_ITEM_WITH_ANALYSIS = "/searchORFeeItemWithAnalysis";
    public static final String URI_ORFEE_SAVE_ORFEE_ITEM_WITH_ANALYSIS = "/saveORFeeItemWithAnalysis";
    public static final String URI_CREDITOR_TYPE_LIST = "/searchCreditorType";
    public static final String URI_AdJTYPE_LIST = "/searchAdjTypeList";

    public static final String URI_GAZETTES_DESCRIPTION = "/gazettesDescription";

    public static final String URI_LOAD_GAZETTE_DESCRIPTION = "/searchGazettesDescriptionList";
    public static final String URI_SAVE_GAZETTE_DESCRIPTION = "/saveGazettesDescription";
    public static final String URI_SEARCH_GAZETTE_DESCRIPTION = "/searchGazettesDescription";

    public static final String CLIENT_LOAD_URI_GAZETTE_DESCRIPTION
        = URI_GAZETTES_DESCRIPTION + URI_LOAD_GAZETTE_DESCRIPTION;
    public static final String CLIENT_SAVE_URI_GAZETTE_DESCRIPTION
        = URI_GAZETTES_DESCRIPTION + URI_SAVE_GAZETTE_DESCRIPTION;
    public static final String CLIENT_SEARCH_URI_GAZETTE_DESCRIPTION
        = URI_GAZETTES_DESCRIPTION + URI_SEARCH_GAZETTE_DESCRIPTION;
    public static final String CLIENT_GAZETTES_DESCRIPTION_LIST = URI_GAZETTES_DESCRIPTION + URI_FIND_ALL;

    public static final String CLIENT_URI_COMMON_DIVIDEND_ORFEEITEM_LIST
        = URI_ROOT_COMMON_DIVIDEND + URI_COMMON_DIVIDEND_ORFEEITEM_LIST;
    public static final String CLIENT_URI_ORFEE_SEARCH_ORFEEITEM = URI_ROOT_ORFEE + URI_ORFEE_SEARCH_ORFEEITEM;
    public static final String CLIENT_URI_ORFEE_VALIDATE_SAVE_ORFEEITEM
        = URI_ROOT_ORFEE + URI_ORFEE_VALIDATE_SAVE_ORFEEITEM;
    public static final String CLIENT_URI_ORFEE_SAVE_ORFEE_ITEM = URI_ROOT_ORFEE + URI_ORFEE_SAVE_ORFEE_ITEM;
    public static final String CLIENT_URI_CREDITOR_TYPE_LIST = URI_ROOT_COMMON_DIVIDEND + URI_CREDITOR_TYPE_LIST;
    public static final String CLIENT_URI_GROUND_TYPE_LIST = URI_ROOT_COMMON_DIVIDEND + URI_AdJTYPE_LIST;
    public static final String CLIENT_URI_ORFEE_SEARCH_ORFEEITEM_WITH_ANALYSIS
        = URI_ROOT_ORFEE + URI_ORFEE_SEARCH_ORFEE_ITEM_WITH_ANALYSIS;
    public static final String CLIENT_URI_ORFEE_SAVE_ORFEEITEM_WITH_ANALYSIS
        = URI_ROOT_ORFEE + URI_ORFEE_SAVE_ORFEE_ITEM_WITH_ANALYSIS;

    /**
     * Common Creditor
     */
    public static final String URI_ROOT_DIVIDEND_COMMON_CREDITOR = "/dividendCommonCreditor";
    public static final String URI_COMMON_CREDITOR_LIST = "/searchCommonCreditorList";
    public static final String URI_COMMON_CREDITOR_DETAIL = "/searchCommonCreditor";
    public static final String URI_COMMON_CREDITOR_SAVE = "/saveCommonCreditor";
    public static final String URI_COMMON_CREDITOR_EXISTS = "/existsByCommonCreditorName";
    public static final String URI_COMMON_CREDITOR_ALL = "/searchAllActCommonCreditors";
    public static final String URI_COMMON_CREDITOR_SEARCH_BY_NAME = "/searchCommonCreditorByName";
    public static final String URI_COMMON_CREDITOR_SEARCH_BY_SEQ_NO = "/searchCommonCreditorBySeqNo";

    public static final String CLIENT_URI_COMMON_CREDITOR_LIST
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_LIST;
    public static final String CLIENT_URI_COMMON_CREDITOR_DETAIL
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_DETAIL;
    public static final String CLIENT_URI_COMMON_CREDITOR_SAVE
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_SAVE;
    public static final String CLIENT_URI_COMMON_CREDITOR_EXISTS
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_EXISTS;
    public static final String CLIENT_URI_COMMON_CREDITOR_ALL
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_ALL;
    public static final String CLIENT_URI_COMMON_CREDITOR_DETAIL_BY_NAME
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_SEARCH_BY_NAME;
    public static final String CLIENT_URI_COMMON_CREDITOR_DETAIL_BY_SEQ_NO
        = URI_ROOT_DIVIDEND_COMMON_CREDITOR + URI_COMMON_CREDITOR_SEARCH_BY_SEQ_NO;

    /**
     * Common Creditor Section
     */
    public static final String URI_ROOT_COMMON_CREDITOR_SECTION = "/commonCreditorSection";
    public static final String URI_COMMON_CREDITOR_SECTION_SEARCH_BY_NAME = "/searchCommonCreditorSectionByName";
    public static final String URI_COMMON_CREDITOR_SECTION_SAVE = "/saveCommonCreditor";
    public static final String URI_COMMON_CREDITOR_SECTION_SEARCH_BY_SEQ_NO = "/searchCommonCreditorSectionBySeqNo";

    public static final String CLIENT_URI_COMMON_CREDITOR_SECTION_SEARCH_BY_NAME
        = URI_ROOT_COMMON_CREDITOR_SECTION + URI_COMMON_CREDITOR_SECTION_SEARCH_BY_NAME;
    public static final String CLIENT_URI_COMMON_CREDITOR_SECTION_SAVE
        = URI_ROOT_COMMON_CREDITOR_SECTION + URI_COMMON_CREDITOR_SECTION_SAVE;
    public static final String CLIENT_URI_COMMON_CREDITOR_SECTION_SEARCH_BY_SEQ_NO
        = URI_ROOT_COMMON_CREDITOR_SECTION + URI_COMMON_CREDITOR_SECTION_SEARCH_BY_SEQ_NO;

    /**
     * Ground Code
     */
    public static final String URI_ROOT_GROUND_CODE_CREDITOR = "/groundcode";

    public static final String URI_GROUND_CODE_LIST = "/searchGroundCodeList";
    public static final String URI_GROUND_CODE = "/searchGroundCode";
    public static final String URI_DELETE_GROUND_CODE = "/deleteGroundCode";
    public static final String URI_SAVE_GROUND_CODE = "/saveGroundCode";

    public static final String CLIENT_URI_GROUND_CODE_LIST = URI_ROOT_GROUND_CODE_CREDITOR + URI_GROUND_CODE_LIST;
    public static final String CLIENT_URI_SAVE_GROUND_CODE = URI_ROOT_GROUND_CODE_CREDITOR + URI_SAVE_GROUND_CODE;
    public static final String CLIENT_URI_GROUND_CODE = URI_ROOT_GROUND_CODE_CREDITOR + URI_GROUND_CODE;
    public static final String CLIENT_URI_DELETE_GROUND_CODE = URI_ROOT_GROUND_CODE_CREDITOR + URI_DELETE_GROUND_CODE;
    public static final String CLIENT_URI_GROUND_CODE_ALL = URI_ROOT_GROUND_CODE_CREDITOR + URI_FIND_ALL;

    /**
     * Withheld Reason
     */
    public static final String URI_ROOT_WITHHELD_REASON = "/withheldReason";

    public static final String URI_SEARCH_WITHHELD_REASON = "/searchWithheldReason";
    public static final String URI_SAVE_WITHHELD_REASON = "/saveWithheldReason";
    public static final String URI_SEARCH_WITHHELD_REASON_BY_ID = "/searchWithheldReasonByWRId";
    public static final String URI_SEARCH_WITHHELD_REASON_LIST = "/searchWithheldReasonList";

    public static final String CLIENT_URI_SAVE_WITHHELD_REASON = URI_ROOT_WITHHELD_REASON + URI_SAVE_WITHHELD_REASON;
    public static final String CLIENT_URI_SEARCH_WITHHELD_REASON
        = URI_ROOT_WITHHELD_REASON + URI_SEARCH_WITHHELD_REASON;
    public static final String CLIENT_URI_SEARCH_WITHHELD_REASON_BY_ID
        = URI_ROOT_WITHHELD_REASON + URI_SEARCH_WITHHELD_REASON_BY_ID;
    public static final String CLIENT_URI_SEARCH_WITHHELD_REASON_LIST
        = URI_ROOT_WITHHELD_REASON + URI_SEARCH_WITHHELD_REASON_LIST;

    /**
     * PercentagesAdjustment
     */
    public static final String URI_ROOT_PERCENTAGES_ADJUSTMENT = "/percentagesAdjustment";

    public static final String URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT_LIST = "/searchPercentagesAdjustmentList";
    public static final String URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT = "/searchPercentagesAdjustment";
    public static final String URI_ROOT_SAVE_PERCENTAGES_ADJUSTMENT = "/savePercentagesAdjustment";
    public static final String URI_ROOT_SEARCH_BY_APPADJRESULTITEMID = "/searchDivScheduleDistByAppAdjResultItemId";

    public static final String CLIENT_URI_SEARCH_PERCENTAGES_ADJUSTMENT_LIST
        = URI_ROOT_PERCENTAGES_ADJUSTMENT + URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT_LIST;
    public static final String CLIENT_URI_SEARCH_PERCENTAGES_ADJUSTMENT
        = URI_ROOT_PERCENTAGES_ADJUSTMENT + URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT;
    public static final String CLIENT_URI_SAVE_PERCENTAGES_ADJUSTMENT
        = URI_ROOT_PERCENTAGES_ADJUSTMENT + URI_ROOT_SAVE_PERCENTAGES_ADJUSTMENT;
    public static final String CLIENT_URI_SEARCH_BY_APPADJRESULTITEMID
        = URI_ROOT_PERCENTAGES_ADJUSTMENT + URI_ROOT_SEARCH_BY_APPADJRESULTITEMID;

    /**
     * Dividend Parameter
     */
    public static final String URI_ROOT_DIVIDEND_PARAMETER = "/dividendParameter";
    public static final String URI_DIVIDEND_PARAMETER_SEARCH = "/searchDividendParameter";
    public static final String URI_DIVIDEND_PARAMETER_SAVE = "/saveDividendParameter";

    public static final String CLIENT_URI_DIVIDEND_PARAMETER_SEARCH
        = URI_ROOT_DIVIDEND_PARAMETER + URI_DIVIDEND_PARAMETER_SEARCH;
    public static final String CLIENT_URI_DIVIDEND_PARAMETER_SAVE
        = URI_ROOT_DIVIDEND_PARAMETER + URI_DIVIDEND_PARAMETER_SAVE;

    /**
     * WILON and Severance Pay
     */
    public static final String URI_ROOT_WILON_AND_SEVERANCE_PAY = "/wilon";
    public static final String URI_SEARCH_WILON_AND_SEVERANCE_PAY_LIST = "/searchWilonAndSeverancePayList";
    public static final String URI_SAVE_WILON_AND_SEVERANCE_PAY = "/saveWILONAndSeverancePay";
    public static final String URI_SEARCH_CREDITOR_BY_CASENUMBER = "/searchCreditorsByCaseNumber";
    public static final String URI_SEARCH_BY_WILON_AND_SEVERANCE_ID = "/searchWilonAndSeveranceById";
    public static final String URI_CREATE_WILON_AND_SEVERANCE_VALIDATE = "/createWILONAndSeveranceValidate";

    public static final String CLIENT_URI_SEARCH_WILON_AND_SEVERANCE_PAY_LIST
        = URI_ROOT_WILON_AND_SEVERANCE_PAY + URI_SEARCH_WILON_AND_SEVERANCE_PAY_LIST;
    public static final String CLIENT_URI_SAVE_WILON_AND_SEVERANCE_PAY
        = URI_ROOT_WILON_AND_SEVERANCE_PAY + URI_SAVE_WILON_AND_SEVERANCE_PAY;
    public static final String CLIENT_URI_SEARCH_CREDITOR_BY_CASENUMBER
        = URI_ROOT_WILON_AND_SEVERANCE_PAY + URI_SEARCH_CREDITOR_BY_CASENUMBER;
    public static final String CLIENT_URI_SEARCH_BY_WILON_AND_SEVERANCE_ID
        = URI_ROOT_WILON_AND_SEVERANCE_PAY + URI_SEARCH_BY_WILON_AND_SEVERANCE_ID;
    public static final String CLIENT_URI_CREATE_WILON_AND_SEVERANCE_VALIDATE
        = URI_ROOT_WILON_AND_SEVERANCE_PAY + URI_CREATE_WILON_AND_SEVERANCE_VALIDATE;

    /**
     * Adjudication
     */
    public static final String URI_ROOT_ADJUDICATION = "/adjudication";
    public static final String URI_PRE_ADJUDICATION_LIST = "/searchPreAdjudicationList";
    public static final String URI_ORD_ADJUDICATION_LIST = "/searchOrdAdjudicationList";
    public static final String URI_ADJUDICATION_DETAIL = "/searchAdjudication";
    public static final String URI_ADJUDICATION_SAVE = "/saveAdjudication";

    public static final String CLIENT_URI_PRE_ADJUDICATION_LIST = URI_ROOT_ADJUDICATION + URI_PRE_ADJUDICATION_LIST;
    public static final String CLIENT_URI_ORD_ADJUDICATION_LIST = URI_ROOT_ADJUDICATION + URI_ORD_ADJUDICATION_LIST;
    public static final String CLIENT_URI_ADJUDICATION_DETAIL = URI_ROOT_ADJUDICATION + URI_ADJUDICATION_DETAIL;
    public static final String CLIENT_URI_ADJUDICATION_SAVE = URI_ROOT_ADJUDICATION + URI_ADJUDICATION_SAVE;

    /**
     * Interest Trial Adjudication
     */
    public static final String URI_ROOT_INTERESTTRIAL = "/interestTrial";
    public static final String URI_SEARCH_INTERESTTRIALLIST = "/searchInterestTrialList";
    public static final String URI_SEARCH_INTERESTTRIAL_BY_ID = "/searchInterestTrial";
    public static final String URI_SAVE_INTERESTTRIAL = "/saveInterestTrial";
    public static final String URI_CREATE_INTERESTTRIAL = "/createInterestTrial";
    public static final String URI_SEARCH_DIVSHCEDULEITEM_BY_ADJRESULTID = "/searchDivScheduleItemByAdjResultId";

    public static final String CLIENT_URI_SEARCH_INTERESTTRIALLIST
        = URI_ROOT_INTERESTTRIAL + URI_SEARCH_INTERESTTRIALLIST;
    public static final String CLIENT_URI_SEARCH_INTERESTTRIAL_BY_ID
        = URI_ROOT_INTERESTTRIAL + URI_SEARCH_INTERESTTRIAL_BY_ID;
    public static final String CLIENT_URI_SAVE_INTERESTTRIAL = URI_ROOT_INTERESTTRIAL + URI_SAVE_INTERESTTRIAL;
    public static final String CLIENT_URI_CREATE_INTERESTTRIAL = URI_ROOT_INTERESTTRIAL + URI_CREATE_INTERESTTRIAL;
    public static final String CLIENT_URI_SEARCH_DIVSHCEDULEITEM_BY_ADJRESULTID
        = URI_ROOT_INTERESTTRIAL + URI_SEARCH_DIVSHCEDULEITEM_BY_ADJRESULTID;

    /**
     * UserPwdHist
     */

    public static final String URI_ROOT_USER_PWD_HIST = "/userPwdHist";
    public static final String URI_USER_PWD_HIST_SAVE = "/save";
    public static final String URI_USER_PWD_HIST_FINDBY_USER_AC_ID = "/findByUserAcId";

    public static final String CLIENT_URI_USER_PWD_HIST_SAVE = URI_ROOT_USER_PWD_HIST + URI_USER_PWD_HIST_SAVE;
    public static final String CLIENT_URI_USER_PWD_HIST_FINDBY_USER_AC_ID
        = URI_ROOT_USER_PWD_HIST + URI_USER_PWD_HIST_FINDBY_USER_AC_ID;

    /**
     * Error Message
     */
    public static final String URI_ROOT_ERROR_MESSAGE = "/errorMessage";

    public static final String CLIENT_URI_ERROR_MESSAGE_FINDALL = URI_ROOT_ERROR_MESSAGE + URI_FIND_ALL;

    /**
     * Error Message Param
     */
    public static final String URI_ROOT_ERROR_MESSAGE_PARAM = "/errorMessageParam";

    public static final String CLIENT_URI_ERROR_MESSAGE_PARAM_FINDALL = URI_ROOT_ERROR_MESSAGE_PARAM + URI_FIND_ALL;

    /**
     * System Setting
     */
    public static final String URI_ROOT_SYSTEM_SETTING = "/SystemSetting";
    public static final String URI_SYSTEM_SETTING_PAGE_LOAD = "/loadSystemSettingPage";
    public static final String URI_SYSTEM_SETTING_SYSPAR_LIST = "/loadSystemSettingParameter";
    public static final String URI_SYSTEM_SETTING_SYSPAR_SAVE = "/saveParameters";
    public static final String URI_SYSTEM_SETTING_ORO_INFO_LOAD = "/loadOROInformation";
    public static final String URI_SYSTEM_SETTING_ORO_INFO_SAVE = "/saveOROInfomation";
    public static final String URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_LOAD = "/loadSystemNotification";
    public static final String URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_SAVE = "/saveSystemNotification";
    public static final String URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_POP_UP = "/popUpSystemNotification";

    public static final String CLIENT_URI_SYSTEM_SETTING_PAGE_LOAD
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_PAGE_LOAD;
    public static final String CLIENT_URI_SYSTEM_SETTING_SYSPAR_LIST
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_SYSPAR_LIST;
    public static final String CLIENT_URI_SYSTEM_SETTING_SYSPAR_SAVE
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_SYSPAR_SAVE;
    public static final String CLIENT_URI_SYSTEM_SETTING_ORO_INFO_LOAD
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_ORO_INFO_LOAD;
    public static final String CLIENT_URI_SYSTEM_SETTING_ORO_INFO_SAVE
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_ORO_INFO_SAVE;
    public static final String CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_LOAD
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_LOAD;
    public static final String CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_SAVE
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_SAVE;
    public static final String CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_POP_UP
        = URI_ROOT_SYSTEM_SETTING + URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_POP_UP;

    /**
     * Case Detail
     */
    public static final String URI_ROOT_CASE = "/case";
    public static final String URI_SEARCH_CASE_BY_CRITERIA = "/findByCriteria";
    public static final String URI_EXISTS_BY_CASENO = "/validateExistsByCaseNo";
    public static final String URI_CASE_SAVE = "/saveCaseDetail";
    public static final String URI_CASE_DETAIL = "/getCaseDetail";
    public static final String URI_CASE_FINDBYCASENO = "/findByCaseNumber";
    public static final String URI_CASE_FIND_BY_CASENO = "/findByCaseNo";

    public static final String CLIENT_URI_EXISTS_BY_CASENO = URI_ROOT_CASE + URI_EXISTS_BY_CASENO;
    public static final String CLIENT_URI_SEARCH_CASE_BY_CRITERIA = URI_ROOT_CASE + URI_SEARCH_CASE_BY_CRITERIA;
    public static final String CLIENT_URI_CASE_SAVE = URI_ROOT_CASE + URI_CASE_SAVE;
    public static final String CLIENT_URI_CASE_DETAIL = URI_ROOT_CASE + URI_CASE_DETAIL;
    public static final String CLIENT_URI_CASE_FINDBYCASENO = URI_ROOT_CASE + URI_CASE_FINDBYCASENO;
    public static final String CLIENT_URI_CASE_FIND_BY_CASENO = URI_ROOT_CASE + URI_CASE_FIND_BY_CASENO;

    /**
     * Forgot Password Log
     */
    public static final String URI_ROOT_FORGOT_PWD_LOG = "/forgotPwdLog";
    public static final String URI_FORGOT_PWD_LOG_CREATE = "/createForgotPwdLog";
    public static final String URI_FORGOT_PWD_LOG_FINDBY_TOKEN = "/findByToken";

    public static final String CLIENT_URI_FORGOT_PWD_LOG_SAVE = URI_ROOT_FORGOT_PWD_LOG + URI_SAVE;
    public static final String CLIENT_URI_FORGOT_PWD_LOG_FINDONE = URI_ROOT_FORGOT_PWD_LOG + URI_FIND_ONE;
    public static final String CLIENT_URI_FORGOT_PWD_LOG_CREATE = URI_ROOT_FORGOT_PWD_LOG + URI_FORGOT_PWD_LOG_CREATE;
    public static final String CLIENT_URI_FORGOT_PWD_LOG_FINDBY_TOKEN
        = URI_ROOT_FORGOT_PWD_LOG + URI_FORGOT_PWD_LOG_FINDBY_TOKEN;

    /**
     * Case Deposit Card
     */
    public static final String URI_ROOT_DEPOSIT_CARD = "/depositCard";
    public static final String URI_FIND_DEPOSIT_CARD_BY_CASE = "/findDepositCardByCase";
    public static final String URI_DEPOSIT_CARD_SAVE = "/saveDepositCardDetail";
    public static final String URI_DEPOSIT_CARD_DETAIL = "/getDepositCardDetail";

    public static final String CLIENT_URI_FIND_DEPOSIT_CARD_BY_CASE
        = URI_ROOT_DEPOSIT_CARD + URI_FIND_DEPOSIT_CARD_BY_CASE;
    public static final String CLIENT_URI_DEPOSIT_CARD_SAVE = URI_ROOT_DEPOSIT_CARD + URI_DEPOSIT_CARD_SAVE;
    public static final String CLIENT_URI_DEPOSIT_CARD_DETAIL = URI_ROOT_DEPOSIT_CARD + URI_DEPOSIT_CARD_DETAIL;

    /**
     * Case Account
     */
    public static final String URI_ROOT_CASE_ACCOUNT = "/caseAccount";
    public static final String URI_FIND_CASE_ACCOUNT_BY_CASE_ID = "/findCaseAccountByCaseId";
    public static final String URI_CASE_ACCOUNT_SAVE = "/saveCaseAccountDetail";
    public static final String URI_CASE_ACCOUNT_DETAIL = "/getCaseAccountDetail";
    public static final String URI_FIND_CASE_ACCOUNT_BY_AC_NO = "/findByAccountNumber";
    public static final String URI_FIND_OLD_CASE_ACCOUNT_BY_ACCOUNT_TYPE = "/findOldCaseAccountByAccountType";

    public static final String CLIENT_URI_FIND_CASE_ACCOUNT_BY_CASE_ID
        = URI_ROOT_CASE_ACCOUNT + URI_FIND_CASE_ACCOUNT_BY_CASE_ID;
    public static final String CLIENT_URI_CASE_ACCOUNT_SAVE = URI_ROOT_CASE_ACCOUNT + URI_CASE_ACCOUNT_SAVE;
    public static final String CLIENT_URI_CASE_ACCOUNT_DETAIL = URI_ROOT_CASE_ACCOUNT + URI_CASE_ACCOUNT_DETAIL;
    public static final String CLIENT_URI_FIND_CASE_ACCOUNT_BY_AC_NO
        = URI_ROOT_CASE_ACCOUNT + URI_FIND_CASE_ACCOUNT_BY_AC_NO;
    public static final String CLIENT_URI_FIND_OLD_CASE_ACCOUNT_BY_ACCOUNT_TYPE
        = URI_ROOT_CASE_ACCOUNT + URI_FIND_OLD_CASE_ACCOUNT_BY_ACCOUNT_TYPE;

    /**
     * Email
     */
    public static final String URI_ROOT_EMAIL = "/email";
    public static final String URI_EMAIL_SEND_MODULE_EMAIL = "/sendModuleMail";

    public static final String CLIENT_URI_EMAIL_SEND_MODULE_EMAIL = URI_ROOT_EMAIL + URI_EMAIL_SEND_MODULE_EMAIL;

    /**
     * Dividend Schedule
     */
    public static final String ROOT_DIVIDEND_SCHEDULE = "/dividendSchedule";
    public static final String VALIDATE_CREATE_DIVIDEND_SCHEDULE = "/validateCreateDividendSchedule";
    public static final String SEARCH_DIVIDEND_SCHEDULE_LIST = "/searchDividendScheduleList";
    public static final String SEARCH_DIVIDEND_SCHEDULE = "/searchDividendSchedule";
    public static final String FIND_BY_CREDITOR_ID = "/findByCreditorId";
    public static final String SEARCH_CREDITOR_BY_CASEID = "/searchCreditorByCaseId";
    public static final String SEARCH_TOTALINTERESTAMOUNT = "/searchTotalInterestAmount";
    public static final String SEARCH_DIVSCHEDULEITEM_BY_ID = "/searchDividendScheduleItemById";
    public static final String SAVE_DIVIDENDSCHEDULE = "/saveDividendSchedule";
    public static final String SEARCH_BY_CREDTYPEID = "/findCredTypePercentageByCredTypeId";

    public static final String CLIENT_VALIDATE_CREATE_DIVIDEND_SCHEDULE
        = ROOT_DIVIDEND_SCHEDULE + VALIDATE_CREATE_DIVIDEND_SCHEDULE;
    public static final String CLIENT_SEARCH_DIVIDEND_SCHEDULE_LIST
        = ROOT_DIVIDEND_SCHEDULE + SEARCH_DIVIDEND_SCHEDULE_LIST;
    public static final String CLIENT_SEARCH_DIVIDEND_SCHEDULE = ROOT_DIVIDEND_SCHEDULE + SEARCH_DIVIDEND_SCHEDULE;
    public static final String CLIENT_FIND_BY_CREDITOR_ID = ROOT_DIVIDEND_SCHEDULE + FIND_BY_CREDITOR_ID;
    public static final String CLIENT_SEARCH_CREDITOR_BY_CASEID
        = ROOT_DIVIDEND_SCHEDULE + SEARCH_CREDITOR_BY_CASEID;
    public static final String CLIENT_SEARCH_TOTALINTERESTAMOUNT = ROOT_DIVIDEND_SCHEDULE + SEARCH_TOTALINTERESTAMOUNT;
    public static final String CLIENT_SEARCH_DIVSCHEDULEITEM_BY_ID
        = ROOT_DIVIDEND_SCHEDULE + SEARCH_DIVSCHEDULEITEM_BY_ID;
    public static final String CLIENT_SAVE_DIVIDENDSCHEDULE = ROOT_DIVIDEND_SCHEDULE + SAVE_DIVIDENDSCHEDULE;
    public static final String CLIENT_SEARCH_BY_CREDTYPEID = ROOT_DIVIDEND_SCHEDULE + SEARCH_BY_CREDTYPEID;

    /**
     * Dividend Cheque
     */
    public static final String ROOT_DIVIDEND_CHEQUE = "/dividendCheque";
    public static final String URI_DIVIDEND_CHEQUE_LIST = "/searchDividendChequeList";
    public static final String URI_DIVIDEND_CHEQUE_CREATE = "/saveDividendCheque";
    public static final String URI_DIVIDEND_CHEQUE_DETAIL = "/searchDividendCheque";
    public static final String SAVE_DIVIDEND_CHEQUELIST = "/saveDividendScheduleList";
    public static final String URI_DIVIDEND_CHEQUE_LIST_BY_DIVSCHEID = "/searchDividendChequeListBydivScheId";

    public static final String CLIENT_DIVIDEND_CHEQUE_LIST = ROOT_DIVIDEND_CHEQUE + URI_DIVIDEND_CHEQUE_LIST;
    public static final String CLIENT_DIVIDEND_CHEQUE_CREATE = ROOT_DIVIDEND_CHEQUE + URI_DIVIDEND_CHEQUE_CREATE;
    public static final String CLIENT_DIVIDEND_CHEQUE_DETAIL = ROOT_DIVIDEND_CHEQUE + URI_DIVIDEND_CHEQUE_DETAIL;
    public static final String CLIENT_SAVE_DIVIDEND_CHEQUELIST = ROOT_DIVIDEND_CHEQUE + SAVE_DIVIDEND_CHEQUELIST;
    public static final String CLIENT_DIVIDEND_CHEQUE_LIST_BY_DIVSCHEID
        = ROOT_DIVIDEND_CHEQUE + URI_DIVIDEND_CHEQUE_LIST_BY_DIVSCHEID;

    /**
     * SYS Sequence
     */
    public static final String ROOT_SYS_SEQ = "/sysSequence";
    public static final String URI_SYS_SEQ_GEN = "/generateIncrSeq";
    public static final String URI_SYS_WITHHELD_REASON_SEQ = "/generateWithheldReasonNo";
    public static final String URI_SYS_COMMON_CREDITOR_SEQ = "/generateVariableSeqNo";

    public static final String CLIENT_URI_SYS_SEQ_GEN = ROOT_SYS_SEQ + URI_SYS_SEQ_GEN;
    public static final String CLIENT_URI_WITHHELD_REASON_SEQ = ROOT_SYS_SEQ + URI_SYS_WITHHELD_REASON_SEQ;
    public static final String CLIENT_URI_SYS_COMMON_CREDITOR_SEQ = ROOT_SYS_SEQ + URI_SYS_COMMON_CREDITOR_SEQ;

    /**
     * Dashboard Info
     */
    public static final String ROOT_DASHBOARD_INFO = "/dashboardInfo";
    public static final String URI_DASHBOARD_INFO_FINDBY_POSTID = "/findByPostId";

    public static final String CLIENT_URI_DASHBOARD_INFO_FINDBY_POSTID
        = ROOT_DASHBOARD_INFO + URI_DASHBOARD_INFO_FINDBY_POSTID;

    /**
     * Creditor
     */
    public static final String URI_ROOT_CREDITOR = "/creditor";
    public static final String URI_FIND_CREDITOR_BY_CASE = "/findCreditorByCaseNumber";
    public static final String URI_FIND_CREDITOR_BY_ID = "/findCreditorById";

    public static final String CLIENT_URI_FIND_CREDITOR_BY_CASE = URI_ROOT_CREDITOR + URI_FIND_CREDITOR_BY_CASE;
    public static final String CLIENT_URI_FIND_CREDITOR_BY_ID = URI_ROOT_CREDITOR + URI_FIND_CREDITOR_BY_ID;

    /**
     * SysWfInitialStatus
     */
    public static final String URI_ROOT_SYS_WF_INITIAL_STATUS = "/sysWfInitialStatus";
    public static final String URI_SYS_WF_INITIAL_STATUS_FINDBY_PRIVILEGE_CODE = "/findByPrivilegeCode";

    public static final String CLIENT_URI_SYS_WF_INITIAL_STATUS_FINDBY_PRIVILEGE_CODE
        = URI_ROOT_SYS_WF_INITIAL_STATUS + URI_SYS_WF_INITIAL_STATUS_FINDBY_PRIVILEGE_CODE;

    /**
     * SysWorkFlowRule
     */
    public static final String URI_ROOT_SYS_WORKFLOW_RULE = "/sysWorkFlowRule";
    public static final String URI_SYS_WORKFLOW_RULE_FINDBY_PRIVILEGE_CODE = "/findByPrivilegeCode";
    public static final String URI_FIND_SYS_WORKFLOW_RULE = "/findSysWorkFlow";
    public static final String URI_SYS_WORKFLOW_RULE_FIND_INIT_ACTION = "/findIntialAction";

    public static final String CLIENT_URI_SYS_WORKFLOW_RULE_FINDBY_PRIVILEGE_CODE
        = URI_ROOT_SYS_WORKFLOW_RULE + URI_SYS_WORKFLOW_RULE_FINDBY_PRIVILEGE_CODE;
    public static final String CLIENT_URI_FIND_SYS_WORKFLOW_RULE
        = URI_ROOT_SYS_WORKFLOW_RULE + URI_FIND_SYS_WORKFLOW_RULE;
    public static final String CLIENT_URI_SYS_WORKFLOW_RULE_FIND_INIT_ACTION
        = URI_ROOT_SYS_WORKFLOW_RULE + URI_SYS_WORKFLOW_RULE_FIND_INIT_ACTION;

    /**
     * Proof of Debt
     */
    public static final String URI_ROOT_PROOF_OF_DEBT = "/proofOfDebt";
    public static final String URI_FIND_PROOF_OF_DEBT_BY_CRITERIA = "/findByCriteria";

    public static final String CLIENT_URI_FIND_PROOF_OF_DEBT_BY_CRITERIA
        = URI_ROOT_PROOF_OF_DEBT + URI_FIND_PROOF_OF_DEBT_BY_CRITERIA;
    public static final String CLIENT_URI_SAVE_PROOF_OF_DEBT = URI_ROOT_PROOF_OF_DEBT + URI_SAVE;
    public static final String CLIENT_URI_GET_PROOF_OF_DEBT_DETAIL = URI_ROOT_PROOF_OF_DEBT + URI_FIND_ONE;

    /**
     * Proof of Debt Item
     */
    public static final String URI_ROOT_PROOF_OF_DEBT_ITEM = "/proofOfDebtItem";

    public static final String CLIENT_URI_SAVE_PROOF_OF_DEBT_ITEM = URI_ROOT_PROOF_OF_DEBT_ITEM + URI_SAVE;
    public static final String CLIENT_URI_GET_PROOF_OF_DEBT_ITEM_DETAIL = URI_ROOT_PROOF_OF_DEBT_ITEM + URI_FIND_ONE;

    /**
     * Outsider
     */
    public static final String URI_ROOT_OUTSIDER = "/outsider";
    public static final String URI_SEARCH_OUTSIDER_BY_CRITERIA = "/searchByCriteria";
    public static final String URI_SAVE_OUTSIDER = "/saveOutsider";
    public static final String URI_GET_OUTSIDER_DETAIL = "/getOutsiderDetail";
    public static final String URI_VALIDATE_OUTSIDER_NAME = "/validateOutsiderName";
    public static final String CLIENT_URI_SEARCH_OUTSIDER = URI_ROOT_OUTSIDER + URI_SEARCH_OUTSIDER_BY_CRITERIA;
    public static final String CLIENT_URI_GET_OUTSIDER_DETAIL = URI_ROOT_OUTSIDER + URI_GET_OUTSIDER_DETAIL;
    public static final String CLIENT_URI_SAVE_OUTSIDER = URI_ROOT_OUTSIDER + URI_SAVE_OUTSIDER;
    public static final String CLIENT_URI_VALIDATE_OUTSIDER = URI_ROOT_OUTSIDER + URI_VALIDATE_OUTSIDER_NAME;

    /**
     * JournalType
     */
    public static final String URI_ROOT_JOURNAL_TYPE = "/journalType";

    public static final String CLIENT_URI_JOURNAL_TYPE_FINDALL = URI_ROOT_JOURNAL_TYPE + URI_FIND_ALL;

    /**
     * Roster
     */
    public static final String URI_ROOT_ROSTER = "/roster";
    public static final String URI_SEARCH_ROSTER_LIST_BY_CRITERIA = "/searchRosterList";
    public static final String URI_GET_ROSTER_DETAIL = "/getRosterDetail";
    public static final String URI_SAVE_ROSTER = "/saveRoster";
    public static final String URI_DELETE_ROSTER = "/deleteRoster";
    public static final String URI_EXISTS_BY_ON_DUTY_DATE = "/existByOnDutyDate";
    public static final String CLIENT_URI_SEARCH_ROSTER_LIST_BY_CRITERIA
        = URI_ROOT_ROSTER + URI_SEARCH_ROSTER_LIST_BY_CRITERIA;
    public static final String CLIENT_URI_GET_ROSTER_DETAIL = URI_ROOT_ROSTER + URI_GET_ROSTER_DETAIL;
    public static final String CLIENT_URI_SAVE_ROSTER = URI_ROOT_ROSTER + URI_SAVE_ROSTER;
    public static final String CLIENT_URI_DELETE_ROSTER = URI_ROOT_ROSTER + URI_DELETE_ROSTER;
    public static final String CLIENT_URI_EXISTS_BY_ON_DUTY_DATE = URI_ROOT_ROSTER + URI_EXISTS_BY_ON_DUTY_DATE;

    /**
     * Cheque
     */
    public static final String URI_ROOT_CHEQUE = "/cheque";
    public static final String URI_SEARCH_INCOMING_CHEQUE_LIST_BY_CRITERIA = "/searchIncomingCheque";
    public static final String URI_SEARCH_OUTGOING_CHEQUE_LIST_BY_CRITERIA = "/searchOutgoingCheque";
    public static final String URI_GET_INCOMING_CHEQUE_DETAIL_BY_ID = "/getIncomingChequeDetail";
    public static final String URI_GET_OUTGOING_CHEQUE_DETAIL = "/getOutgoingChequeDetail";
    public static final String URI_SAVE_INCOMING_CHEQUE = "/saveIncomingCheque";
    public static final String URI_SAVE_OUTGOING_CHEQUE = "/saveOutgoingCheque";
    public static final String URI_CHEQUE_EXISTS_BY_CHEQUE_NUMBER = "/existsByChequeNumber";
    public static final String URI_CHEQUE_GET_CHEQUE_DETAIL_BY_CHEQUE_NO = "/getChequeDetailByChequeNo";
    public static final String URI_CHEQUE_COMBINE_OUTGOING_CHEQUE = "/combineOutgoingCheque";
    public static final String URI_CHEQUE_GET_OUTGOING_CHEQUE_LIST_BY_PARENT_ID = "/getOutgoingChequeListByParentId";
    public static final String URI_CHEQUE_SEARCH_CHEQUE_FOR_CHEQUE_FILE = "/searchChequeForChequeFile";
    public static final String CLIENT_URI_SEARCH_INCOMING_CHEQUE_LIST_BY_CRITERIA
        = URI_ROOT_CHEQUE + URI_SEARCH_INCOMING_CHEQUE_LIST_BY_CRITERIA;
    public static final String CLIENT_URI_SEARCH_OUTGOING_CHEQUE_LIST_BY_CRITERIA
        = URI_ROOT_CHEQUE + URI_SEARCH_OUTGOING_CHEQUE_LIST_BY_CRITERIA;
    public static final String CLIENT_URI_GET_INCOMING_CHEQUE_DETAIL_BY_ID
        = URI_ROOT_CHEQUE + URI_GET_INCOMING_CHEQUE_DETAIL_BY_ID;
    public static final String CLIENT_URI_SAVE_INCOMING_CHEQUE = URI_ROOT_CHEQUE + URI_SAVE_INCOMING_CHEQUE;
    public static final String CLIENT_URI_SAVE_OUTGOING_CHEQUE = URI_ROOT_CHEQUE + URI_SAVE_OUTGOING_CHEQUE;
    public static final String CLIENT_URI_CHEQUE_EXISTS_BY_CHEQUE_NUMBER
        = URI_ROOT_CHEQUE + URI_CHEQUE_EXISTS_BY_CHEQUE_NUMBER;
    public static final String CLIENT_URI_CHEQUE_GET_CHEQUE_DETAIL_BY_CHEQUE_NO
        = URI_ROOT_CHEQUE + URI_CHEQUE_GET_CHEQUE_DETAIL_BY_CHEQUE_NO;
    public static final String CLIENT_URI_GET_OUTGOING_CHEQUE_DETAIL = URI_ROOT_CHEQUE + URI_GET_OUTGOING_CHEQUE_DETAIL;
    public static final String CLIENT_URI_CHEQUE_COMBINE_OUTGOING_CHEQUE
        = URI_ROOT_CHEQUE + URI_CHEQUE_COMBINE_OUTGOING_CHEQUE;
    public static final String CLIENT_URI_CHEQUE_GET_OUTGOING_CHEQUE_LIST_BY_PARENT_ID
        = URI_ROOT_CHEQUE + URI_CHEQUE_GET_OUTGOING_CHEQUE_LIST_BY_PARENT_ID;
    public static final String CLIENT_URI_CHEQUE_SEARCH_CHEQUE_FOR_CHEQUE_FILE
        = URI_ROOT_CHEQUE + URI_CHEQUE_SEARCH_CHEQUE_FOR_CHEQUE_FILE;

    /**
     * Analysis Code
     */
    public static final String URI_ROOT_ANALYSIS_CODE = "/analysisCode";
    public static final String URI_FIND_ANALYSIS_CODE_BY_ANALYSIS_CODE = "/findByAnalysisCode";
    public static final String URI_FIND_ANALYSIS_CODE_BY_CRITERIA = "/findByCriteria";
    public static final String URI_EXISTS_BY_ANALYSIS_CODE = "/existsByAnalysisCode";
    public static final String URI_IS_ANALYSIS_CODE_EXISTED_BY_CRITERIA = "/isExistedAnalysisCodeByCriteria";

    public static final String CLIENT_URI_IS_ANALYSIS_CODE_EXISTS_BY_CRITERIA
        = URI_ROOT_ANALYSIS_CODE + URI_IS_ANALYSIS_CODE_EXISTED_BY_CRITERIA;
    public static final String CLIENT_URI_FIND_ANALYSIS_CODE_BY_ANALYSIS_CODE
        = URI_ROOT_ANALYSIS_CODE + URI_FIND_ANALYSIS_CODE_BY_ANALYSIS_CODE;
    public static final String CLIENT_FIND_ANALYSIS_CODE_BY_CRITERIA
        = URI_ROOT_ANALYSIS_CODE + URI_FIND_ANALYSIS_CODE_BY_CRITERIA;
    public static final String CLIENT_URI_GET_ANALYSIS_CODE_DETAIL = URI_ROOT_ANALYSIS_CODE + URI_FIND_ONE;
    public static final String CLIENT_URI_SAVE_ANALYSIS_CODE_DETAIL = URI_ROOT_ANALYSIS_CODE + URI_SAVE;
    public static final String CLIENT_URI_EXISTS_BY_ANALYSIS_CODE
        = URI_ROOT_ANALYSIS_CODE + URI_EXISTS_BY_ANALYSIS_CODE;

    /**
     * Voucher
     */
    public static final String URI_ROOT_VOUCHER = "/voucher";
    public static final String URI_VOUCHER_UPDATE_RECEIPT_VOUCHER = "/updateReceiptVoucher";
    public static final String URI_VOUCHER_SAVE_JOURNAL_VOUCHER = "/saveJournalVoucher";
    public static final String URI_VOUCHER_FIND_JOURNAL_VOUCHER = "/findJournalVoucher";
    public static final String URI_VOUCHER_SAVE_PAYMENT_VOUCHER = "/savePaymentVoucher";
    public static final String URI_VOUCHER_FIND_PAYMENT_VOUCHER = "/findPaymentVoucher";
    public static final String URI_VOUCHER_SEARCH_JOURNAL_VOUCHER = "/searchJournalVoucher";
    public static final String URI_VOUCHER_FIND_PAYMENT_VOUCHER_BY_CRITERIA = "/findPaymentVoucherByCriteria";
    public static final String URI_VOUCHER_SAVE_RECEIPT_VOUCHER = "/saveReceiptVoucher";
    public static final String URI_VOUCHER_FIND_RECEIPT_VOUCHER = "/findReceiptVoucher";
    public static final String URI_VOUCHER_FIND_RECEIPT_VOUCHER_BY_CRITERIA = "/findReceiptVoucherByCriteria";
    public static final String URI_VOUCHER_DELETE_RECEIPT_VOUCHER = "/deleteReceiptVoucher";
    public static final String URI_VOUCHER_DELETE_VOUCHER = "/deleteVoucher";
    public static final String URI_VOUCHER_DOWNLOAD_TEMPLATE = "/downloadImportTemplate";
    public static final String URI_VOUCHER_SUBMIT_JOURNAL_VOUCHER = "/submitJournalVoucher";
    public static final String URI_VOUCHER_APPROVE_JOURNAL_VOUCHER = "/approveJournalVoucher";
    public static final String URI_VOUCHER_REJECT_JOURNAL_VOUCHER = "/rejectJournalVoucher";
    public static final String URI_VOUCHER_VERIFY_JOURNAL_VOUCHER = "/verifyJournalVoucher";
    public static final String URI_VOUCHER_REVERSE_JOURNAL_VOUCHER = "/reverseJournalVoucher";
    public static final String URI_VOUCHER_DELETE_JOURNAL_VOUCHER = "/deleteJournalVoucher";
    public static final String URI_VOUCHER_SUBMIT_VOUCHER = "/submitVoucher";
    public static final String URI_VOUCHER_APPROVE_VOUCHER = "/approveVoucher";
    public static final String URI_VOUCHER_VERIFY_VOUCHER = "/verifyVoucher";
    public static final String URI_VOUCHER_REJECT_VOUCHER = "/rejectVoucher";
    public static final String URI_VOUCHER_REVERSE_VOUCHER = "/reverseVoucher";
    public static final String URI_VOUCHER_SUBMIT_PAYMENT_VOUCHER = "/submitPaymentVoucher";
    public static final String URI_VOUCHER_APPROVE_PAYMENT_VOUCHER = "/approvePaymentVoucher";
    public static final String URI_VOUCHER_REJECT_PAYMENT_VOUCHER = "/rejectPaymentVoucher";
    public static final String URI_VOUCHER_VERIFY_PAYMENT_VOUCHER = "/verifyPaymentVoucher";
    public static final String URI_VOUCHER_REVERSE_PAYMENT_VOUCHER = "/reversePaymentVoucher";
    public static final String URI_VOUCHER_DELETE_PAYMENT_VOUCHER = "/deletePaymentVoucher";
    public static final String URI_VOUCHER_APPROVE_RECEIPT_VOUCHER = "/approverReceiptVoucher";
    public static final String URI_VOUCHER_VERIFY_RECEIPT_VOUCHER = "/verifyReceiptVoucher";
    public static final String URI_VOUCHER_REVERSE_RECEIPT_VOUCHER = "/reverseReceiptVoucher";
    public static final String URI_VOUCHER_REJECT_RECEIPT_VOUCHER = "/rejectReceiptVoucher";

    public static final String CLIENT_URI_VOUCHER_REJECT_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REJECT_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REVERSE_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REVERSE_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_VERIFY_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_VERIFY_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_APPROVE_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_APPROVE_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REVERSE_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_REVERSE_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REJECT_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_REJECT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_VERIFY_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_VERIFY_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_APPROVE_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_APPROVE_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_SUBMIT_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_SUBMIT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_UPDATE_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_UPDATE_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_SAVE_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SAVE_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_FIND_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_FIND_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_FIND_PAYMENT_VOUCHER_BY_CRITERIA
        = URI_ROOT_VOUCHER + URI_VOUCHER_FIND_PAYMENT_VOUCHER_BY_CRITERIA;
    public static final String CLIENT_URI_VOUCHER_SAVE_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SAVE_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_FIND_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_FIND_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_SAVE_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SAVE_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_FIND_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_FIND_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_FIND_RECEIPT_VOUCHER_BY_CRITERIA
        = URI_ROOT_VOUCHER + URI_VOUCHER_FIND_RECEIPT_VOUCHER_BY_CRITERIA;
    public static final String CLIENT_URI_VOUCHER_DELETE_RECEIPT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_DELETE_RECEIPT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_SEARCH_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SEARCH_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_DELETE_VOUCHER = URI_ROOT_VOUCHER + URI_VOUCHER_DELETE_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_DOWNLOAD_TEMPLATE = URI_ROOT_VOUCHER + URI_VOUCHER_DOWNLOAD_TEMPLATE;
    public static final String CLIENT_URI_VOUCHER_SUBMIT_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SUBMIT_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_APPROVE_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_APPROVE_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REJECT_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REJECT_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_VERIFY_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_VERIFY_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REVERSE_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REVERSE_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_DELETE_JOURNAL_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_DELETE_JOURNAL_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_SUBMIT_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_SUBMIT_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_APPROVE_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_APPROVE_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REJECT_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REJECT_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_VERIFY_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_VERIFY_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_REVERSE_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_REVERSE_PAYMENT_VOUCHER;
    public static final String CLIENT_URI_VOUCHER_DELETE_PAYMENT_VOUCHER
        = URI_ROOT_VOUCHER + URI_VOUCHER_DELETE_PAYMENT_VOUCHER;

    /**
     * ORFeeComputation
     */
    public static final String URI_ROOT_ORFEECOMPUTATION = "/orFeeComputation";
    public static final String URI_ORFEECOMPUTATION_LIST = "/searchORFeeComputationList";
    public static final String URI_ORFEECOMPUTATION_SAVE = "/saveORFeeComputation";
    public static final String URI_CASE_CREATABLE_VALIDATION = "/validateCaseCreatable";
    public static final String URI_ORFEECOMPUTATION_DETAIL = "/searchORFeeComputation";
    public static final String URI_ORFEECOMPUTATION_INIT = "/initORFeeComputation";
    public static final String URI_CASE_FEE_MAIN_LIST = "/findCaseFeeMains";
    public static final String URI_DIV_CAL_CRED_DISTS_LIST = "/getDivCalCredDists";

    public static final String CLIENT_URI_ORFEECOMPUTATION_LIST = URI_ROOT_ORFEECOMPUTATION + URI_ORFEECOMPUTATION_LIST;
    public static final String CLIENT_URI_ORFEECOMPUTATION_SAVE = URI_ROOT_ORFEECOMPUTATION + URI_ORFEECOMPUTATION_SAVE;
    public static final String CLIENT_URI_CASE_CREATABLE_VALIDATION
        = URI_ROOT_ORFEECOMPUTATION + URI_CASE_CREATABLE_VALIDATION;
    public static final String CLIENT_URI_ORFEECOMPUTATION_DETAIL
        = URI_ROOT_ORFEECOMPUTATION + URI_ORFEECOMPUTATION_DETAIL;
    public static final String CLIENT_URI_ORFEECOMPUTATION_INIT = URI_ROOT_ORFEECOMPUTATION + URI_ORFEECOMPUTATION_INIT;
    public static final String CLIENT_URI_CASE_FEE_MAIN_LIST = URI_ROOT_ORFEECOMPUTATION + URI_CASE_FEE_MAIN_LIST;
    public static final String CLIENT_URI_DIV_CAL_CRED_DISTS_LIST
        = URI_ROOT_ORFEECOMPUTATION + URI_DIV_CAL_CRED_DISTS_LIST;

    /**
     * Control Account
     */
    public static final String URI_ROOT_CONTROL_ACCOUNT = "/controlAccount";
    public static final String URI_CONTROL_ACCOUNT_SAVE_CONTROL_ACCOUNT = "/saveControlAccount";
    public static final String URI_CONTROL_ACCOUNT_SEARCH_CONTROL_ACCOUNT = "/searchControlAccount";
    public static final String URI_CONTROL_ACCOUNT_GET_CONTROL_ACCOUNT_DETAIL = "/getControlAccountDetail";
    public static final String URI_CONTROL_ACCOUNT_FIND_ALL_CONTROL_ACCOUNT = "/findAllControlAccount";
    public static final String URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_CODE = "/existsByControlAccountCode";
    public static final String URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_NAME = "/existsByControlAccountName";

    public static final String CLIENT_URI_CONTROL_ACCOUNT_SEARCH_CONTROL_ACCOUNT
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_SEARCH_CONTROL_ACCOUNT;
    public static final String CLIENT_URI_CONTROL_ACCOUNT_GET_CONTROL_ACCOUNT_DETAIL
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_GET_CONTROL_ACCOUNT_DETAIL;
    public static final String CLIENT_URI_CONTROL_ACCOUNT_SAVE_CONTROL_ACCOUNT
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_SAVE_CONTROL_ACCOUNT;
    public static final String CLIENT_URI_CONTROL_ACCOUNT_FIND_ALL_CONTROL_ACCOUNT
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_FIND_ALL_CONTROL_ACCOUNT;
    public static final String CLIENT_URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_NAME
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_NAME;
    public static final String CLIENT_URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_CODE
        = URI_ROOT_CONTROL_ACCOUNT + URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_CODE;

    /**
     * Control Account Type
     */
    public static final String URI_ROOT_CONTROL_ACCOUNT_TYPE = "/controlAccountType";

    public static final String CLIENT_URI_CONTROL_ACCOUNT_TYPE_FIND_ALL = URI_ROOT_CONTROL_ACCOUNT_TYPE + URI_FIND_ALL;

    /**
     * Receive Deposit
     */
    public static final String URI_ROOT_RECEIVE_DEPOSIT = "/receiveDeposit";
    public static final String URI_RECEIVE_DEPOSIT_SEARCH_RECEIVE_DEPOSIT = "/searchReceiveDeposit";
    public static final String URI_RECEIVE_DEPOSIT_GET_DEPOSIT_DETAIL = "/getDepositDetail";
    public static final String URI_RECEIVE_DEPOSIT_SAVE_DEPOSIT = "/saveDeposit";
    public static final String URI_RECEIPT_SAVE_RECEIPT = "/saveReceipt";
    public static final String URI_RECEIPT_GENERATE_RECEIPT_NUMBER = "/generateReceiptNumber";
    public static final String URI_RECEIVE_DEPOSIT_GENERATE_DEPOSIT_NUMBER = "/generateDepositNumber";
    public static final String CLIENT_URI_RECEIPT_SAVE_RECEIPT = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIPT_SAVE_RECEIPT;
    public static final String CLIENT_URI_RECEIPT_DEPOSIT_SEARCH_RECEIPT_DEPOSIT
        = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIVE_DEPOSIT_SEARCH_RECEIVE_DEPOSIT;
    public static final String CLIENT_URI_RECEIPT_DEPOSIT_GET_DEPOSIT_DETAIL
        = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIVE_DEPOSIT_GET_DEPOSIT_DETAIL;
    public static final String CLIENT_URI_RECEIPT_DEPOSIT_SAVE_DEPOSIT
        = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIVE_DEPOSIT_SAVE_DEPOSIT;
    public static final String CLIENT_URI_RECEIPT_GENERATE_RECEIPT_NUMBER
        = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIPT_GENERATE_RECEIPT_NUMBER;
    public static final String CLIENT_URI_RECEIVE_DEPOSIT_GENERATE_DEPOSIT_NUMBER
        = URI_ROOT_RECEIVE_DEPOSIT + URI_RECEIVE_DEPOSIT_GENERATE_DEPOSIT_NUMBER;
    /**
     * Payment File
     */
    public static final String URI_ROOT_PAYMENT_FILE = "/paymentFile";
    public static final String URI_PAYMENT_FILE_SEARCH_PAYMENT_FILE = "/searchPaymentFile";
    public static final String CLIENT_URI_PAYMENT_FILE_SEARCH_PAYMENT_FILE
        = URI_ROOT_PAYMENT_FILE + URI_PAYMENT_FILE_SEARCH_PAYMENT_FILE;

    /**
     * Payment File Type
     */
    public static final String URI_ROOT_PAYMENT_FILE_TYPE = "/paymentFileType";
    public static final String CLIENT_URI_FIND_ALL_PAYMENT_FILE_TYPE = URI_ROOT_PAYMENT_FILE_TYPE + URI_FIND_ALL;

    /**
     * General Ledger
     */
    public static final String URI_ROOT_GENERAL_LEDGER = "/generalLedger";
    public static final String URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER = "/findGeneralLedger";
    public static final String URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER_BY_CONTROL_ACCOUNT_ID
        = "/findGeneralLedgerByControlAcId";

    public static final String CLIENT_URI_FIND_GENERAL_LEDGER_BY_CONTROL_ACCOUNT_ID
        = URI_ROOT_GENERAL_LEDGER + URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER_BY_CONTROL_ACCOUNT_ID;
    public static final String CLIENT_URI_FIND_GENERAL_LEDGER
        = URI_ROOT_GENERAL_LEDGER + URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER;
    public static final String CLIENT_URI_SAVE_GENERAL_LEDGER = URI_ROOT_GENERAL_LEDGER + URI_SAVE;

    /**
     * Voucher Attachment
     */
    public static final String URI_ROOT_VOUCHER_ATTACHMENT = "/voucherAttachment";

    public static final String URI_FIND_VOUCHER_ATTACHMENT_BY_VOUCHER = "/findVoucherAttachmentByVoucher";
    public static final String URI_DELETE_VOUCHER_ATTACHMENT = "/deleteVoucherAttachment";

    public static final String CLIENT_URI_SAVE_VOUCHER_ATTACHMENT = URI_ROOT_VOUCHER_ATTACHMENT + URI_SAVE;
    public static final String CLIENT_URI_DELETE_VOUCHER_ATTACHMENT
        = URI_ROOT_VOUCHER_ATTACHMENT + URI_DELETE_VOUCHER_ATTACHMENT;
    public static final String CLIENT_URI_FIND_VOUCHER_ATTACHMENT = URI_ROOT_VOUCHER_ATTACHMENT + URI_FIND_ONE;
    public static final String CLIENT_URI_FIND_VOUCHER_ATTACHMENT_BY_VOUCHER
        = URI_ROOT_VOUCHER_ATTACHMENT + URI_FIND_VOUCHER_ATTACHMENT_BY_VOUCHER;

    /**
     * System Attachment
     */
    public static final String URI_ROOT_SYS_ATTACHMENT = "/sysAttachment";

    public static final String CLIENT_URI_SAVE_SYS_ATTACHMENT = URI_ROOT_SYS_ATTACHMENT + URI_SAVE;
    public static final String CLIENT_URI_FIND_SYS_ATTACHMENT = URI_ROOT_SYS_ATTACHMENT + URI_FIND_ONE;

    /**
     * Historical Release Case List
     */
    public static final String URI_ROOT_HISTORICAL_CASE_LIST = "/historicalCaseList";
    public static final String URI_SEARCH_HISTORICAL_CASE_LIST = "/searchCaseList";
    public static final String URI_FIND_HISTORICAL_CASE_LIST_ITEM = "/findCaseListItem";
    public static final String URI_SAVE_HISTORICAL_CASE_LIST_ITEM = "/saveCaseListItem";

    public static final String CLIENT_SEARCH_HISTORICAL_CASE_LIST
        = URI_ROOT_HISTORICAL_CASE_LIST + URI_SEARCH_HISTORICAL_CASE_LIST;
    public static final String CLIENT_FIND_HISTORICAL_CASE_LIST_ITEM
        = URI_ROOT_HISTORICAL_CASE_LIST + URI_FIND_HISTORICAL_CASE_LIST_ITEM;
    public static final String CLIENT_SAVE_HISTORICAL_CASE_LIST_ITEM
        = URI_ROOT_HISTORICAL_CASE_LIST + URI_SAVE_HISTORICAL_CASE_LIST_ITEM;

    /**
     * Bulk Reversal
     */
    public static final String URI_ROOT_BULK_REVESAL = "/bulkReversal";
    public static final String URI_BULK_REVESAL_SEARCH = "/searchBulkReversal";
    public static final String URI_BULK_REVESAL_GENERATE = "/generateBulkReversal";
    public static final String URI_BULK_REVESAL_LOAD = "/loadBulkReversalDetail";
    public static final String URI_BULK_REVESAL_CONFIRM = "/confirmBulkReversal";
    public static final String URI_BULK_REVESAL_VALIDATE = "/validateBulkReversal";
    public static final String URI_BULK_REVESAL_DO = "/doBulkReversal";
    public static final String URI_BULK_REVESAL_REVERSE_CHEQUE_LIST = "/reverseChequeList";
    public static final String URI_BULK_REVESAL_INSERT = "/insertBulkReversal";

    public static final String CLIENT_URI_BULK_REVESAL_SEARCH = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_SEARCH;
    public static final String CLIENT_URI_BULK_REVESAL_GENERATE = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_GENERATE;
    public static final String CLIENT_URI_BULK_REVESAL_LOAD = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_LOAD;
    public static final String CLIENT_URI_BULK_REVESAL_CONFIRM = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_CONFIRM;
    public static final String CLIENT_URI_BULK_REVESAL_VALIDATE = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_VALIDATE;
    public static final String CLIENT_URI_BULK_REVESAL_DO = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_CONFIRM;
    public static final String CLIENT_URI_BULK_REVESAL_REVERSE_CHEQUE_LIST
        = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_REVERSE_CHEQUE_LIST;
    public static final String CLIENT_URI_BULK_REVESAL_INSERT = URI_ROOT_BULK_REVESAL + URI_BULK_REVESAL_INSERT;

    /**
     * Transfer Amount to BEA / General Revenue
     */
    public static final String URI_ROOT_TRANSFER_TO_BEA_OR_GR = "/transferToGrOrBea";

    public static final String URI_FIND_TRANSFER_BY_CRITERIA = "/findTransferByCriteria";

    public static final String CLIENT_URI_SAVE_TRANSFER_TO_BEA_OR_GR_DETAIL = URI_ROOT_TRANSFER_TO_BEA_OR_GR + URI_SAVE;
    public static final String CLIENT_URI_GET_TRANSFER_TO_BEA_OR_GR_DETAIL
        = URI_ROOT_TRANSFER_TO_BEA_OR_GR + URI_FIND_ONE;
    public static final String CLIENT_URI_FIND_TRANSFER_BY_CRITERIA
        = URI_ROOT_TRANSFER_TO_BEA_OR_GR + URI_FIND_TRANSFER_BY_CRITERIA;

    /**
     * Transfer Amount to BEA / General Revenue Item
     */
    public static final String URI_ROOT_TRANSFER_TO_BEA_OR_GR_ITEM = "/transferToGrOrBeaItem";

    public static final String URI_FIND_TRANSFER_ITEM_BY_TRANSFER = "/findTransferItemByTransfer";

    public static final String CLIENT_URI_SAVE_TRANSFER_TO_BEA_OR_GR_ITEM_DETAIL
        = URI_ROOT_TRANSFER_TO_BEA_OR_GR_ITEM + URI_SAVE;
    public static final String CLIENT_URI_FIND_TRANSFER_ITEM_BY_TRANSFER
        = URI_ROOT_TRANSFER_TO_BEA_OR_GR_ITEM + URI_FIND_TRANSFER_ITEM_BY_TRANSFER;

    /**
     * Transfer Amount Type
     */
    public static final String URI_SEARCH_TRANSFER_AMOUNT_TYPE_LIST = "/searchTransferAmountTypeList";
    public static final String CLIENT_URI_SEARCH_TRANSFER_AMOUNT_TYPE_LIST
        = URI_ROOT_COMMON + URI_SEARCH_TRANSFER_AMOUNT_TYPE_LIST;

    /**
     * Shroff Bank Txf Item
     */
    public static final String URI_ROOT_BANK_TXF_ITEM = "/shrBankTxfItem";
    public static final String URI_BANK_TXF_ITEM_SEARCH = "/searchBankTransferItem";

    public static final String CLIENT_URI_BANK_TXF_ITEM_SEARCH = URI_ROOT_BANK_TXF_ITEM + URI_BANK_TXF_ITEM_SEARCH;
}
