package hk.oro.iefas.web.dividend.creditorreg.interesttrial.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.CaseInterestTrialVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.InterestTrialAdjudicationVO;
import hk.oro.iefas.domain.dividend.vo.SearchInterestTrialCriteriaVO;

public interface InterestTrialClientService {

    public CaseInterestTrialVO searchInterestTrialList(SearchInterestTrialCriteriaVO criteria);

    public InterestTrialAdjudicationVO searchInterestTrialById(Integer interestTrialAdjudicationId);

    public Integer saveInterestTrial(InterestTrialAdjudicationVO interestTrialAdjudication);

    public InterestTrialAdjudicationVO createInterestTrial(Integer creditorId);

    public List<CreditorVO> searchCreditorByCaseNumber(CaseNumberVO caseNumber);

    public List<DividendScheduleItemVO> searchDivScheduleItemByAdjResultId(Integer adjResultId);
}
