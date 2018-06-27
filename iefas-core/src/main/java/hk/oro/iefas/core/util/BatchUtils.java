package hk.oro.iefas.core.util;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;

import hk.oro.iefas.core.exceptions.BaseException;

public class BatchUtils {

    public static Range[] createRanges(int ... rangeNums) {
        if (CommonUtils.isEmpty(rangeNums)) {
            throw new BaseException("rangeNums must be not empty");
        }
        
        int rangeLength = rangeNums.length;
        Range[] ranges = new Range[rangeLength];
        int startRange = 1;
        int endRange;
        
        for (int i = 0; i < rangeLength; i++) {
            endRange = startRange + rangeNums[i];
            ranges[i] = new Range(startRange, endRange - 1);
            startRange = endRange;
        }
        
        return ranges;
    }
    
    public static FixedLengthTokenizer createFixedLengthTokenizer(String[] names,int ... rangeNums) {
        if (CommonUtils.isEmpty(names)) {
            throw new BaseException("names must be not empty");
        }
        Range[] ranges = createRanges(rangeNums);
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setNames(names);
        tokenizer.setColumns(ranges);
        return tokenizer;
    }
}
