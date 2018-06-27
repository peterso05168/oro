package hk.oro.iefas.ws.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;

import hk.oro.iefas.core.batch.BaseTasklet;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class SampleTasklet extends BaseTasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("SampleTasklet executing");

        return RepeatStatus.FINISHED;
    }

}
