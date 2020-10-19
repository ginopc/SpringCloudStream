package io.spring.dataflow.sample;

import io.spring.dataflow.sample.model.UsageCostDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.awaitility.Awaitility;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(OutputCaptureExtension.class)
class UsageCostLoggerKafkaTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testUsageCostLogger(CapturedOutput output){
		// create Spring Boot Application
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration.getCompleteConfiguration(UsageCostLoggerKafkaApplication.class))
				.web(WebApplicationType.NONE)
				.run();

		try {
			InputDestination source = context.getBean(InputDestination.class);

			UsageCostDetail usageCostDetail = new UsageCostDetail( "user1", 3.0, 5.0);
			final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
			Map<String, Object> headers = new HashMap<>();
			headers.put("contentType", "application/json");
			MessageHeaders messageHeaders = new MessageHeaders(headers);
			final Message<?> message = converter.toMessage(usageCostDetail, messageHeaders);

			source.send(message);

			Awaitility.await().until(output::getOut, value -> value.contains("{\"userId\": \"user1\", \"callCost\": \"3.0\", \"dataCost\": \"5.0\" }"));
		}
		finally {
			context.close();
		}
	}

}
