package hk.oro.iefas.web.ledger.chequehandling.outgoingcheque.view;

import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.shroff.vo.OutgoingChequeResultVO;
import hk.oro.iefas.domain.shroff.vo.SearchOutgoingChequeVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.common.util.ApplicationContextUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3156 $ $Date: 2018-06-15 14:02:26 +0800 (週五, 15 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class OutgoingChequeSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Integer chequeId;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private ChequeClientService chequeClientService;

    @Getter
    @Setter
    private SearchOutgoingChequeVO searchOutgoingChequeVO;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private LazyDataModel<OutgoingChequeResultVO> lazyDataModel;

    @Getter
    @Setter
    private List<OutgoingChequeResultVO> selections;

    private Set<Integer> chequeIds;

    @PostConstruct
    public void init() {
        log.info("========== IncomingChequeSearchView init start ================");
        chequeIds = new HashSet<>();
        searchOutgoingChequeVO = new SearchOutgoingChequeVO();
        currencyBasicInfoVOS = currencyClientService.findAll();
        statusVOS = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.CQO.name());
        Iterator<StatusVO> statusVOIterator = statusVOS.iterator();
        while (statusVOIterator.hasNext()) {
            StatusVO statusVO = statusVOIterator.next();
            if (statusVO.getStatusVal().equals(CoreConstant.CHEQUE_STATUS_COMBINED)) {
                statusVOIterator.remove();
                break;
            }
        }
        log.info("========== IncomingChequeSearchView init end ==========");
    }

    public void search() {
        log.info("search() start");
        lazyDataModel = new SimpleSelectableLazyDataModel(
            RequestUriConstant.CLIENT_URI_SEARCH_OUTGOING_CHEQUE_LIST_BY_CRITERIA, searchOutgoingChequeVO);
        log.info("search end");

    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        selections = null;
        chequeIds.clear();
        searchOutgoingChequeVO = new SearchOutgoingChequeVO();
        log.info("reset() end");
    }

    public void remove(int index) {
        log.info("remove start");
        selections.remove(index);
        log.info("remove end");
    }

    public void combine() {
        log.info("combine() start");
        if (selections != null && selections.size() > 0) {
            OutgoingChequeResultVO first = selections.get(0);
            for (OutgoingChequeResultVO current : selections) {
                if (!(current.getChequeDate().equals(first.getChequeDate()))
                    || !(current.getPayee().equals(first.getPayee()))
                    || !(current.getCurrencyName().equals(first.getCurrencyName()))) {
                    chequeIds.clear();
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_COMBINE_CHEQUES_FAIL));
                    break;
                } else {
                    chequeIds.add(current.getChequeId());
                }
            }
            if (chequeIds.size() != 0) {
                Integer pChequeId = chequeClientService.combineOutgoingCheque(chequeIds);
                if (pChequeId == null) {
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_COMBINE_CHEQUES_FAIL));
                } else {
                    Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_COMBINE_CHEQUES_SUCCESS));
                }
            }
        }
        log.info("combine() end");
    }

    public void print() {
        log.info("print start");
        Date currentBusinessDate = appResourceUtils.getBusinessDate();
        ChequeVO chequeVO = chequeClientService.getOutgoingChequeDetail(chequeId);
        if(chequeVO.getChequeDate().before(currentBusinessDate)) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_OUTGOING_CHEQUE_DATE_ERROR));
            log.info("print end");
            return;
        }
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_GENERATED);
        chequeClientService.saveOutgoingCheque(chequeVO);
        log.info("print end");
    }

    public void cancel() {
        log.info("cancel start");
        ChequeVO chequeVO = chequeClientService.getOutgoingChequeDetail(chequeId);
        chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_CANCELLED);
        chequeClientService.saveOutgoingCheque(chequeVO);
        log.info("cancel end");
    }

    class SimpleSelectableLazyDataModel extends LazyDataModel<OutgoingChequeResultVO> {

        private static final long serialVersionUID = 1L;

        private BaseClientService clientService;

        private final SearchOutgoingChequeVO searchCriteriaVO;

        private String url;

        private List<OutgoingChequeResultVO> current;

        private SimpleSelectableLazyDataModel(String url, SearchOutgoingChequeVO searchCriteriaVO) {
            super();
            this.clientService = ApplicationContextUtils.getBaseClientService();
            this.url = url;
            this.searchCriteriaVO = searchCriteriaVO;

            log.info("clientService instant is " + this.clientService + ",and request url is " + this.url
                + ",and searchCriteria is " + this.searchCriteriaVO);
        }

        @Override
        public List<OutgoingChequeResultVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
            List<OutgoingChequeResultVO> content = null;
            if (this.clientService != null) {
                SearchVO<SearchOutgoingChequeVO> criteriaVO = new SearchVO<SearchOutgoingChequeVO>(searchCriteriaVO,
                    new PageVO(first, pageSize, sortField, sortOrder));
                ResponseEntity<ResultPageVO<List<OutgoingChequeResultVO>>> responseEntity = this.clientService.exchange(
                    this.url, HttpMethod.POST, new HttpEntity<SearchVO<SearchOutgoingChequeVO>>(criteriaVO),
                    new ParameterizedTypeReference<ResultPageVO<List<OutgoingChequeResultVO>>>() {});
                ResultPageVO<List<OutgoingChequeResultVO>> resultPageVO = responseEntity.getBody();
                if (resultPageVO != null) {
                    setRowCount(resultPageVO.getTotalElements());
                    content = resultPageVO.getContent();
                }
            }
            current = content;
            log.info("load end - return: " + content);
            return content;
        }

        @Override
        public Object getRowKey(OutgoingChequeResultVO object) {
            return object.getChequeId();
        }

        @Override
        public OutgoingChequeResultVO getRowData(String rowKey) {
            for (OutgoingChequeResultVO chequeResultVO : current) {
                if (chequeResultVO.getChequeId().equals(Integer.valueOf(rowKey))) {
                    return chequeResultVO;
                }
            }
            return null;
        }
    }
}
