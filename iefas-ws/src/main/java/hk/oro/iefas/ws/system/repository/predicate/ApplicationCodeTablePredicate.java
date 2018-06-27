package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.domain.system.entity.QApplicationCodeTable;

public class ApplicationCodeTablePredicate {

    private static final QApplicationCodeTable APPLICATION_CODE_TABLE = QApplicationCodeTable.applicationCodeTable;

    public static BooleanExpression findByGroupingCode(String groupingCode) {
        return APPLICATION_CODE_TABLE.groupingCode.eq(groupingCode);
    }

    public static OrderSpecifier<String> order() {
        return APPLICATION_CODE_TABLE.codeDesc.asc();
    }

    public static OrderSpecifier<Integer> orderById() {
        return APPLICATION_CODE_TABLE.applicationCodeTableId.asc();
    }
}
