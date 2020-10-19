package io.spring.dataflow.sample.consumer;

import io.spring.dataflow.sample.model.UsageCostDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * <pre>
 *
 * Title: UsageCostLogger class
 * Description: class description and scope
 *
 * Copyright: Copyright (c) 2020
 * Company:
 * </pre>
 *
 * @author ginopc@tiscali.it
 * @version 1.0
 */
@Configuration
public class UsageCostLogger {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Bean
    public Consumer<UsageCostDetail> consume(){
        return usageCostDetail -> {
            logger.info(usageCostDetail.toString());
        };
    }
}
