/*
	# 의존성 주입 테스트
	 : spring-test 모듈을 사용, 간단하게 테스트 스프링을 가동, 설정한 동작들이 일어남
	   이때 Junit 4.10이상 버전 사용
*/
package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
/* 테스트 환경 설정 = 현재 테스트 코드가 스프링 실행하는 역할을 할것을 의미 */
@RunWith(SpringJUnit4ClassRunner.class)
/* 테스트 스프링 컨텍스트 지정 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
/* 로그 설정 */
@Log4j
public class SampleTests {
	/* Lombok의 @Setter와 스프링의 @Autowired를 결합하여 해당 필드 restaurant에 의존성 주입 */
	@Setter(onMethod_ = {@Autowired}) /*@Autowired = 해당 인스턴스 변수에 스프링으로부터 자동으로 주입해 달라는 어노테이션*/
	private Restraurant restaurant;
	
/* JUnit에서 테스트를 하겠다는 어노테이션 => JUnit Test 기능을 실행 */
	@Test
	public void testExist() {
		// 주어진 객체가 null인지 아닌지 확인하며, 만약 null일 경우 테스트 실패로 나옴 
		// => 스프링 컨텍스트에서 restraurant 빈이 제대로 주입되었음을 의미함
		assertNotNull(restaurant);
		log.info("---------------------------------");
		log.info(restaurant.getChef());
	}
}
/*
	@Log4j
	 : 로그 객체를 생성. 로그 출력
	@RunWith
	 : 테스트시 어떤 의존관계의 라이브러리 클래스를 사용할 것인지를 지정
	@ContextConfiguration
	 : 테스트 관련 중요 어노테이션으로 실행시 어떤 설정정보를 읽어 들여야 하는지를 명시
	@Test
	 : JUnit에서 해당 메서드가 단위 테스트를 위한 메서드 임을 알려줌
*/
/*
	# 테스트 실행 결과 주목할 점
	 a. new Restraurant() 생성한 적이 없는데 객체가 자동으로 만들어짐
	    => 스프링은 관리가 필요한 객체(=Bean)를 어노테이션을 이용하여 객체를 생성,관리하는 '팩토리'기능을 가짐
	 b. Restraurant 객체에 Chef 타입의 객체가 주입되어 있음
	    => 스프링은 @Autowired와 같은 어노테이션을 이용, 직접 객체들을 자동으로 관리함(개발자가 관리를 전혀 안함)
	 c. 테스트 결과 의미 = 스프링 전체 개념을 이해하는데 가장 중요한 내용임
	   = 스프링 프레임워크가 동작하며 테스트 코드가 실행됨
	   = 동작시 필요한 객체들이 스프링에 등록됨
	   = 의존성 주입에 필요한 객체가 자동으로 주입됨
*/










