package io.spring.dataflow.sample.sender;

import io.spring.dataflow.sample.model.UsageDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.function.Supplier;

/**
 * <pre>
 *
 * Title: UsageDetailSender class
 * Description: Send UsageDetail
 *
 * Copyright: Copyright (c) 2020
 * Company:
 * </pre>
 *
 * @author Maurizio Aru (ginopc@tiscali.it)
 * @version 1.0
 */
 @Configuration
public class UsageDetailSender {

    private String[] users = { "user1", "user2", "user3", "user4", "user5"};

    @Bean
    public Supplier<UsageDetail> sendEvents(){
        return () -> {
            UsageDetail usageDetail = new UsageDetail();
            usageDetail.setUserId(this.users[new Random().nextInt(5)]);
            usageDetail.setDuration(new Random().nextInt(300));
            usageDetail.setData(new Random().nextInt(700));
            return usageDetail;
        };
    }
}
