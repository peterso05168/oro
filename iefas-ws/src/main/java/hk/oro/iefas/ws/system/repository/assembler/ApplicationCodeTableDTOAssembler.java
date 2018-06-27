package hk.oro.iefas.ws.system.repository.assembler;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.system.entity.ApplicationCodeTable;
import hk.oro.iefas.domain.system.entity.QApplicationCodeTable;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class ApplicationCodeTableDTOAssembler extends AssemblerSupport<ApplicationCodeTableDTO, ApplicationCodeTable> {

    private static final QApplicationCodeTable APPLICATION_CODE_TABLE = QApplicationCodeTable.applicationCodeTable;

    public static QBean<ApplicationCodeTableDTO> getQBean() {
        return Projections.bean(ApplicationCodeTableDTO.class, APPLICATION_CODE_TABLE.applicationCodeTableId,
            APPLICATION_CODE_TABLE.groupingCode, APPLICATION_CODE_TABLE.codeValue, APPLICATION_CODE_TABLE.codeDesc);
    }

    @Override
    public ApplicationCodeTableDTO toDTO(ApplicationCodeTable entity) {
        ApplicationCodeTableDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, ApplicationCodeTableDTO.class);
        }
        return dto;
    }
}
