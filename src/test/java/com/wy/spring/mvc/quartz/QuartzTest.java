package com.wy.spring.mvc.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.spi.MutableTrigger;

import java.util.*;
import java.util.Calendar;

/**
 * Created by yuanwang on 2017/8/27.
 */
public class QuartzTest {

    public static void configScheduler() {
        //创建一个JobDetail对象
        JobDetail jobDetail = JobBuilder.newJob(PrintJob.class).
                withIdentity("print_job", "printJob01").
                build();

        java.util.Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + 5 * 1000L);

        //配置Trigger
        Trigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("print_trigger", "trigger01")
                .startNow()
                .endAt(calendar.getTime())
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(1)
                                .withRepeatCount(2)
                ).build();


        //定时服务工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //具体的定时服务容器
        Scheduler scheduler = null;

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
        } catch (SchedulerException schedEx) {
            schedEx.printStackTrace();
        }


    }


    public static void main(String[] args) {
        configScheduler();
    }

}
