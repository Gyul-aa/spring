/*  
	#. 의존성 주입 테스트
	= Restaurant 객체에 Chef 객체 주입
	=> 스프링 의존성 주입 방법 = a. 생성자 이용 주입 b. setter 메서드 이용 주입
	=> 의존성 설정 방식 = a. XML 설정 b. 어노테이션 이용
	
[어노테이션]
	@Component 
	  a. 해당 클래스가 스프링에서 객체로 만들어서 관리하는 대상임을 명시
	  b. @Component가 있는 클래스를 스프링에서 관리하도록 @ComponentScan을 통해 지정 함
	     => 따라서, 해당 패키지에 있는 클래스들을 조사하여 @Component가 존재하는 클래스들의 객체를 생성하고 빈으로 관리함
	@Data
	  a. Lombok에서 자주 사용되는 어노테이션
	  b. @Getter, @Setter, @ToString, @RequredArgsConstructor, @EqualsAndHashCode등 결합형태 
	     => 한번에 모든 어노테이션 사용가능
	           
*/
package org.zerock.sample;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class Chef {

}
