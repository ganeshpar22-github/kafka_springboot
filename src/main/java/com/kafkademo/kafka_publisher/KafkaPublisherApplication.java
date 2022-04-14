package com.kafkademo.kafka_publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topicName = "kafkaspringboot";
	
	/*For string, we doesn't have to configure anything,
	 *  all the things are already configured
	 *  but in case of object we have to configure multiple things
*/
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {

		template.send(topicName, "Hello World, this is demo project for kafka on spring boot, my name is " + name);

		return "Data Published";
	}
	
	
	@GetMapping("/publishjson")
	public String publishMessage() {
		
		User user = new User(48, "Ganesh Parmar", new String[] {"Dewas","Adarsh Nagar","House no : 114/A"});
		template.send(topicName, user);

		return "Json Data Published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
