# Microservices playground
Created two Services i.e. Department & User
Then made then communicate with each other using http

To facilitate this http communication between the Services I used the 
RestTamplate class, first creating a method to initialize it 
, and then annotating it with @Bean so that it can be managed by the Spring IoC

```
@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){

		return  new RestTemplate();
	}
  
```
## The Communication
To use the RestTemplate object in the Service Layer, First I "field-injected"
the RestTemplate object, like this 
```
    @Autowired
    private RestTemplate restTemplate;
```
Then called,  getForObject on the restTemplate Object
```
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findUserByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" +
                        user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
```
I also implemented Eureka Service Discovery using Eureka Server, 
and went ahead and configured my services as clients to my Eureka Server
Configurations are available in the application.yml files of the individual services
