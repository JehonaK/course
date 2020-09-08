package com.course;

import com.blueveery.springrest2ts.Rest2tsGenerator;
import com.blueveery.springrest2ts.converters.JacksonObjectMapper;
import com.blueveery.springrest2ts.converters.ModelClassesToTsInterfacesConverter;
import com.blueveery.springrest2ts.converters.SpringRestToTsConverter;
import com.blueveery.springrest2ts.filters.ContainsSubStringJavaTypeFilter;
import com.blueveery.springrest2ts.filters.HasAnnotationJavaTypeFilter;
import com.blueveery.springrest2ts.implgens.Angular4ImplementationGenerator;
import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

//@EnableRabbit
@EnableEurekaClient
@SpringBootApplication
public class CourseApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CourseApplication.class);
	}

	public static void main(String[] args) {
		generateApiTsFile();
		SpringApplication.run(CourseApplication.class, args);
	}

	private static void generateApiTsFile() {
		Rest2tsGenerator tsGenerator = new Rest2tsGenerator();

		// Java Classes filtering
		tsGenerator.setModelClassesCondition(new HasAnnotationJavaTypeFilter(ApiEntity.class));
		tsGenerator.setRestClassesCondition(new ContainsSubStringJavaTypeFilter("Controller"));

//		 Java model classes converter setup
		JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
		jacksonObjectMapper.setFieldsVisibility(JsonAutoDetect.Visibility.ANY);
		ModelClassesToTsInterfacesConverter modelClassesConverter = new ModelClassesToTsInterfacesConverter(jacksonObjectMapper);
		tsGenerator.setModelClassesConverter(modelClassesConverter);

		// Spring REST controllers converter
		SpringRestToTsConverter restClassesConverter = new SpringRestToTsConverter(new Angular4ImplementationGenerator());
		tsGenerator.setRestClassesConverter(restClassesConverter);

		// set of java root packages for class scanning
		Set<String> javaPackageSet = Collections.singleton("com.course");
		try {
			tsGenerator.generate(javaPackageSet, Paths.get("./target/generated-sources/api-ts"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
