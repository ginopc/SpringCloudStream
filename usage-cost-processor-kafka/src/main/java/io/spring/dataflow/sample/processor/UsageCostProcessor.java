package io.spring.dataflow.sample.processor;

import io.spring.dataflow.sample.model.UsageCostDetail;
import io.spring.dataflow.sample.model.UsageDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * <pre>
 *
 * Title: UsageCostProcessor class
 * Description: Cost Processor Configuration
 *
 * Copyright: Copyright (c) 2020
 * Company:
 * </pre>
 *
 * @author John Doe (john.doe@example.ext)
 * @version 1.0
 */
@Configuration
public class UsageCostProcessor {

    private double ratePerSecond = 0.1;
    private double ratePerMB = 0.05;

    @Bean
    public Function<UsageDetail, UsageCostDetail> processUsageCost(){
        return usageDetail -> {
            UsageCostDetail usageCostDetail = new UsageCostDetail(
                    usageDetail.getUserId(),
                    usageDetail.getDuration(),
                    usageDetail.getData()
            );
            return usageCostDetail;
        };
    }
}
