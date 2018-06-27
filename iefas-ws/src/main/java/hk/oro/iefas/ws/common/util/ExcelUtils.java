package hk.oro.iefas.ws.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class ExcelUtils {

    public static Workbook copyExcel(InputStream... inputStreams) throws IOException {
        return copyOldVersionExcel(inputStreams);
    }

    public static Workbook copyOldVersionExcel(InputStream... inputStreams) throws IOException {
        if (inputStreams == null) {
            throw new NullPointerException("inputStreams must not be null");
        }
        Workbook wb = new HSSFWorkbook();
        Workbook wbFrom = null;
        for (InputStream inputStream : inputStreams) {
            wbFrom = new HSSFWorkbook(inputStream);
            copyExcel(wbFrom, wb);
        }
        return wb;
    }

    public static Workbook copyNewVersionExcel(InputStream... inputStreams) throws IOException {
        if (inputStreams == null) {
            throw new NullPointerException("inputStreams must not be null");
        }
        Workbook wb = new XSSFWorkbook();
        Workbook wbFrom = null;
        for (InputStream inputStream : inputStreams) {
            wbFrom = new XSSFWorkbook(inputStream);
            copyExcel(wbFrom, wb);
        }
        return wb;
    }

    public static Workbook copyExcel(Workbook wbFrom, Workbook wb) throws IOException {
        if (wb == null) {
            throw new NullPointerException("wb must not be null");
        }
        if (wbFrom == null) {
            return wb;
        }

        for (Sheet sheetFrom : wbFrom) {
            Sheet sheetTo = wb.createSheet(sheetFrom.getSheetName());

            List<CellRangeAddress> mergedRegions = sheetFrom.getMergedRegions();
            for (CellRangeAddress cellRangeAddress : mergedRegions) {
                sheetTo.addMergedRegion(cellRangeAddress);
            }

            int i = 0;
            for (Row row : sheetFrom) {
                Row createRow = sheetTo.createRow(i++);
                createRow.setHeightInPoints(row.getHeightInPoints());

                int j = 0;
                for (Cell cell : row) {
                    // sheetTo.autoSizeColumn(j);
                    sheetTo.setColumnWidth(j, sheetFrom.getColumnWidth(j));
                    Cell createCell = createRow.createCell(j++);

                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.cloneStyleFrom(cell.getCellStyle());
                    createCell.setCellStyle(cellStyle);

                    CellType cellTypeEnum = cell.getCellTypeEnum();
                    switch (cellTypeEnum) {
                        case STRING:
                            createCell.setCellValue(cell.getRichStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date dateCellValue = cell.getDateCellValue();
                                Date date = new Date(0);
                                if (date.compareTo(dateCellValue) < 0) {
                                    createCell.setCellValue(dateCellValue);
                                }

                            } else {
                                createCell.setCellValue(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            createCell.setCellValue(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            createCell.setCellValue(cell.getCellFormula());
                            break;
                        case BLANK:
                            createCell.setCellType(CellType.BLANK);
                            break;
                        default:
                            createCell.setCellType(CellType._NONE);
                    }

                }
            }

        }
        IOUtils.closeQuietly(wbFrom);
        return wb;
    }
}
