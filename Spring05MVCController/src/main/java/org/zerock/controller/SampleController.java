/*
	[어노테이션]
	#. @Controller
	: 스프링 MVC 에서 사용하는 어노테이션으로 자동으로 스프링 객체(Bean) 로 등록
	  Controller 역할임을 알려줌
	  servlet-context.xml에 <context:component-scan base-package="org.zerock.controller"/>
	  => 설정 경로의 패키지를 스캔하도록 설정됨
	  => 해당 패키지에 선언된 클래스들을 조사하면서,
	  => 스프링에서 객체설정에 사용되는 어노테이션들이 가진 클래스들을 파악. 필요시 객체로 생성해서 관리함
	
	#. @RequestMapping
	: 현재 클래스의 모든 메서드(핸들러)들의 기본적인 URL 경로가 됨(예) url/sample/aaa
		= 클래스 선언(/sample)과 메서드 선언(/aaa)에 사용 가능
		
	#. @RequestMapping (value = "/basic" , method = {RequestMethod.GET, RequestMethod.POST})
	: GET, POST 방식 모두 처리해야 하는 경우에는 배열로 지정 가능
	
	#. @GetMapping / @PostMapping
	: 오직 Get 방식, Post방식에만 사용 = 스프링 4.3 이후 버전부터 사용가능, 간편하지만 기능에 제한이 있음
	
	#. @RequestParam()
	: 파라미터 수집함. 파라미터 타입에 따라 (자동) 변환하는 방식 이용
	
	#. @ModelAttribute
	: 강제로 전달받은 파라미터를 Model에 담아서 전달하도록 할 때 사용
	  @ModelAttribute에 걸린 파라미터는 타입에 상관없이 무조건 Model에 담아서 전달
	  => 파라미터로 전달된 데이터를 다시 화면에서 사용해야 할 경우 유용하게 사용됨
	
	
	
	# Model 객체 = 데이터 전달자
		= Controller 의 메서드 작성시에 Model 타입을 파라미터로 지정 가능
			=> (하는 역할) JSP 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재
			=> 이를 이용하면 JSP 같은 View로 전달해야 하는 데이터를 담아서 보낼 수 있음
			=> 스프링은 메서드의 파라미터에 Model 타입이 지정된 경우에
				=> 특별히 Model 타입의 객체를 만들어서 메서드에 주입하게 됨
	
	(예시)
		a. Servlet Model2 데이터 전달방식
			request.setAttribute("key","value");
			RequestDispatcher dis = request.getRequestDispatcher("/path");
			dis.Forward(request,response);
		b. 스프링 Model 이용
			public String home(Model model){
				model.addAttribute("prop","value");
				return "home";
			}
		# Model 사용방법
			= 메서드 파라미터로 Model 타입으로 선언 => 자동으로 스프링 MVC에서 Model 타입의 객체로 만들어줌
			=> (개발자) 필요한 데이터를 담아주는 작업만으로 모든 작업 완료
			= (Model을 사용하는 경우) => 주로 Controller 에 전달된 데이터를 이용하거나 추가적인 데이터를 가져와야 하는 상황
			
	
 */
package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	   // http://localhost:8880/sample/*
	   @RequestMapping("") 
	   public void basic() {
	      log.info("basic.................");
	   }
	   
	   // http://localhost:8880/sample/basic
	   @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	   public void basicGet() {
	      log.info("basic get....GET,POST 모두");
	   }
	   
	   // http://localhost:8880/sample/basicOnlyGet
	   @GetMapping("basicOnlyGet")
	   public void basicGet2() {
	      log.info("basic get only get ....");
	   }
	   
	   // http://localhost:8880/sample/ex01?name=aaa&age=111
	   @GetMapping("/ex01")
	   public String ex01(SampleDTO dto) {
		   log.info("" + dto);
		   return "ex01";
	   }	   
	   
	   // http://localhost:8880/sample/ex02?name=aaa&age=111
	   @GetMapping("/ex02")
	   public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		   log.info("name: " + name);
		   log.info("age : " + age);
		   return "ex02";
	   }
	
/* 배열 객체 처리 = 동일한 이름의 파라미터 여러개를 전달하는 경우 => ArrayList<> 등으로 처리 가능 
 				=> 스프링은 파라미터 타입을 보고 객체를 생성하므로 List<>와 같은 인터페이스로 지정하면 안됨 */
	   // http://localhost:8880/sample/ex02List?ids=111&ids=222&ids=333
	   @GetMapping("/ex02List")
	   public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		   log.info("ids : " + ids);
		   return "ex02List";
	   }	   
	   
/* 배열 */
	   // http://localhost:8880/sample/ex02Array?ids=111&ids=222&ids=333
	   @GetMapping("/ex02Array")
	   public String ex02Array(@RequestParam("ids") String[] ids ) {
			   log.info("array ids :"+ Arrays.toString(ids)); // 오버라이딩 안해주면 주솟값이 나옴
		   return "ex02Array";
	   }
	   
/* 객체 리스트 타입 파라미터 */
	   // http://localhost:8880/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	   @GetMapping("/ex02Bean")
	   public String ex02Bean(SampleDTOList list) {
		   log.info("list dtos : "+list);
		   return "ex02Bean";
	   }
	   
/* Model = 데이터 전달자 = 위의 예시 b. 스프링 Model 이용 참조 */
// 화면으로 전달되는 객체는 기본적으로 Java Bean 규칙에 맞는 객체만 가능 //
//  = 전달 시 클래스명의 앞글자는 소문자로 처리됨 => 자바빈 생성 규칙 때문 //
//	   (반면) 기본 자료형의 경우 파라미터로 선언되더라도 기본적으로 화면까지 전달되지 않음 /
	   //    -> 이를 해결하고자 @ModelAttribute 사용하면 됨
	   
	   ;	   
	   /* @ModelAttribute */
	   @GetMapping("/ex04")
	   public String ex04(SampleDTO dto,@ModelAttribute("page") int page) {
		   log.info("dto : " +dto);
		   log.info("page : " +page);
		   return "/sample/ex04";
	   }
	   
	   /* 리턴타입 VO, DTO = 주로 JSON 타입의 데이터를 만들어서 변환하는 용도로 사용 */
	   /* JSON 변환을 위해서는 pom.xml에서 jackson-databind 의존관계 설정이 필요함 */
	   @GetMapping("/ex05")
	   public @ResponseBody SampleDTO ex05() {
	      log.info("/ex06.......");
	      SampleDTO dto = new SampleDTO();
	      dto.setName("골골즈");
	      dto.setAge(7);
	      return dto;
	   }
	   
	   /* 날짜 타입 변경 
	    : 데이터를 받으면 파라미터를 추출해서 객체에 담음 => 해당 메서드를 사용하여 날짜 추출 후 매개변수에 담음
	      자바는 날짜처리가 불편하므로, 컨트롤러가 날짜 형식의 데이터를 처리할 때 어떤 형식으로 처리 할 것인지 지정해야 함
	      => DTO의 @DateTimeFormat을 통해 Spring MVC 에게 컨트롤러로 전달되는 날짜 데이터를 설정된 형식으로 변환할 수 있게함
	      => Spring MVC 는 특정한 데이터 형식(=Date) 의 값을 컨트롤러 메서드의 파라미터로 변활할 때 사용
	      => 그래서 클라이언트로 부터 전달되는 날짜 데이터를 원하는 형식으로 변환하여 컨트롤러에서 사용할 수 있음
	   */
	   
	   // http://localhost:8880/sample/ex06?title=aa&dueDate=2000-12-11
	   @GetMapping("/ex06")
	   public String ex06(TodoDTO dto) {
		   log.info("dto : "+dto);
		   return "ex06";
	   }
	      
	   
	   /*
	     @PathVariable
	     : 요청 URL의 일부를 메서드 파라미터로 받아올 때 사용
	     주로 경로변수(path variable)를 추출할 떄 유용함 -> 경로 변수를 메서드 파라미터에 매핑함
	    */
	   
	   // http://localhost:8880/sample/ex07/10/33
	   @GetMapping("/ex07/{num}/{age}")
	   public void ex07(@PathVariable("num") int num, @PathVariable("age") int age) {
		   log.info("num : "+num);
		   log.info("age : "+age);
	   }
	   
	   /*
	     리턴타입 void = 해당 URL의 경로 그대로 jsp 파일의 이름으로 사용 => jsp 파일 경로도 동일하게 만듦
	    */
	   @GetMapping("/ex08")
	   public void ex08() {
		   log.info("/ex08...");
	   }
	   
	   /* ResponseEntity 타입
	     : HTTP 프로토콜 헤더 정보나 데이터 전달을 가능하게 하는 것
	     HttpHeaders 객체를 같이 전달할 수 있고, 이를 통해 원하는 HTTP 헤더 메세지를 가공하는 것이 가능
	     => 응답에 내용없이 HTTP 헤더 메세지만 전달하는 용도로 사용   
	    */
	   
	   /*
	     HTTP 상태 코드 반환 이유는 클라이언트와 서버간의 통신에서 발생하는 여러 상황을 명확하게 전달하기 위함임
	     스프링 MVC에서 HTTP 응답을 다룰때 사용함
	     아래코드의 경우 클라이언트가 GET 요청을 보내면 JSON 형식의 응답을 반환하고 응답 본문으로 사용한 JSON 형식의 문자열 생성
	     응답에 포함될 헤더 생성 => Content-Type 헤더를 application/json;charset=UTF-8 로 설정함
	     
	     메서드의 리턴값으로 ResponseEntity<String> 객체를 생성, 위에서 생성한 JSON문자열, 헤더, HTTP 상태코드
	     (= 여기서는 HttpStatus.OK) 를 포함시킴 => 생성된 ResponseEntity 객체 반환함
	   */
	   
	   @GetMapping("/ex09")
	   public ResponseEntity<String> ex09(){
		   log.info("ex09....");
		   // 반환받을 메세지를 설정
		   String msg = "{\"name\":\"김재돌\"}";
		   // 응답 헤더를 설정
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type", "application/json;charset=UTF-8");
		   return new ResponseEntity<>(msg,headers,HttpStatus.OK);
	   }
	   
	   
	   
	   
	   
}
