package hr.tvz.miholic.hardwareapp.Quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String HARDWARE_PRINT_JOB_IDENTITY = "hardwarePrintJob";
    private static final String HARDWARE_PRINT_TRIGGER = "hardwarePrintTrigger";

    @Bean
    public JobDetail studentPrintJobDetail() {
        return JobBuilder.newJob(HardwareJob.class).withIdentity(HARDWARE_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger studentPrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(studentPrintJobDetail())
                .withIdentity(HARDWARE_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
