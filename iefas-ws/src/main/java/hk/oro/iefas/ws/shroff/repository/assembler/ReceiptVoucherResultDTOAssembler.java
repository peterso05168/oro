package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherResultDTO;
import hk.oro.iefas.domain.shroff.entity.QVoucher;
import hk.oro.iefas.domain.shroff.entity.Voucher;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
public class ReceiptVoucherResultDTOAssembler {
    public static Page<ReceiptVoucherResultDTO> toListResultDTO(Page<Voucher> page) {
        List<Voucher> content = page.getContent();
        List<ReceiptVoucherResultDTO> dtoList = new ArrayList<ReceiptVoucherResultDTO>();
        if (CommonUtils.isNotEmpty(content)) {
            content.stream().forEach(voucher -> {
                ReceiptVoucherResultDTO receiptVoucherResultDTO
                    = DataUtils.copyProperties(voucher, ReceiptVoucherResultDTO.class);
                dtoList.add(receiptVoucherResultDTO);
            });
        }

        return new PageImpl<ReceiptVoucherResultDTO>(dtoList,
            new PageRequest(page.getNumber(), page.getSize(), page.getSort()), page.getTotalElements());
    }

    public static QBean<ReceiptVoucherResultDTO> toResultDTO() {
        QVoucher voucher = QVoucher.voucher;
        return Projections.bean(ReceiptVoucherResultDTO.class, voucher.voucherId, voucher.voucherNo,
            voucher.voucherDate, voucher.groupCode, voucher.voucherTotalAmount, voucher.status);
    }

    public QBean<ReceiptVoucherResultDTO> getQBean() {
        QVoucher voucher = QVoucher.voucher;
        Map<String, Expression<?>> bindings = new HashMap<String, Expression<?>>();
        bindings.put("voucherId", voucher.voucherId);
        bindings.put("voucherNumber", voucher.voucherNo);
        bindings.put("voucherDate", voucher.voucherDate);
        bindings.put("groupCode", voucher.groupCode);
        bindings.put("voucherTotalAmount", voucher.voucherTotalAmount);
        bindings.put("status", voucher.status);

        return Projections.bean(ReceiptVoucherResultDTO.class, bindings);
    }
}
