package com.ingbank.assesment;
dapter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Autowired
    private Environment env;
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}