package io.spring.dataflow.sample;

import io.spring.dataflow.sample.model.UsageCostDetail;
import io.spring.dataflow.sample.model.UsageDetail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsageCostProcessorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testUsageCostProcessor(){
		ConfigurableApplicationContext 	context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration.getCompleteConfiguration(UsageCostProcessorApplication.class)
		)
				.web(WebApplicationType.NONE)
				.run();

		try {
			// given
			InputDestination source = context.getBean(InputDestination.class);
			UsageDetail usageDetail = new UsageDetail("user1", 30L, 100L);

			final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("contentType", "application/json");
			MessageHeaders messageHeaders = new MessageHeaders(headers);
			final Message<?> message = converter.toMessage(usageDetail, messageHeaders);
			source.send(message);

			// when

			OutputDestination target = context.getBean(OutputDestination.class);
			Message<byte[]> sourceMessage = target.receive(10000);

			final UsageCostDetail usageCostDetail = (UsageCostDetail) converter.fromMessage(sourceMessage, UsageCostDetail.class);

			assertThat(usageCostDetail.getCallCost()).isEqualTo(3.0);
			assertThat(usageCostDetail.getDataCost()).isEqualTo(5.0);
		}
		finally {
			context.close();
		}
	}

}
