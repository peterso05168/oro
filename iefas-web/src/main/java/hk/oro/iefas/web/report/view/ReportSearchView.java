package hk.oro.iefas.web.report.view;

import java.io.Serializable;

import javax.inject.Named;

import hk.oro.iefas.domain.report.ReportParamVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ReportSearchView implements Serializable {

    private static final long serialVersionUID = -8730521840336231784L;

    @Getter
    @Setter
    private ReportParamVO reportGenerateVO = new ReportParamVO();

}
