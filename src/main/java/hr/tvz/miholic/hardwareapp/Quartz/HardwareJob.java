package hr.tvz.miholic.hardwareapp.Quartz;

import hr.tvz.miholic.hardwareapp.Hardware.Repository.HardwareRepository;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class HardwareJob extends QuartzJobBean {

    @Autowired
    private HardwareRepository hardwareRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Ovo su trenutno dostupni hardwari");
        System.out.println("---------------------------------");
        hardwareRepository.findAll().stream().filter(h -> h.getAmount() > 0).forEach(h -> System.out.println(h.dostupnost()));
        System.out.println("---------------------------------");
    }
}
