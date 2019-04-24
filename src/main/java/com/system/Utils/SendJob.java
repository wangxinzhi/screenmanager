package com.system.Utils;

import com.system.pojo.Program;
import com.system.service.ProgramService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * Quartz
 * 发送节目任务
 */
@PersistJobDataAfterExecution
public class SendJob extends QuartzJobBean {

    private Program program;

    @Autowired
    private ProgramService programService;

    /**
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // Log4jUtil.loggerInfo("[ Quartz' Job -- SendJob ]");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        for (Map.Entry entry : jobDataMap.entrySet()){
            // Log4jUtil.loggerInfo("***** Key "+entry.getKey());
        }
        if (program == null){
            // Log4jUtil.loggerInfo("***** Program is null *****");
        }
        else {
            // Log4jUtil.loggerInfo("***** Program -- " + program.toString() + " *****");
        }
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
