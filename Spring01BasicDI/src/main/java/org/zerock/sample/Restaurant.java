/*
   의존성 주입 테스트 = Restaurant 객체에 Chef 객체 주입
   어노테이션 = 기능을 갖고있는 메서드
   [어노테이션]
   @Setter
      a. setter 메서드 생성해 주는 역할
      b. 3가지 속성
         가. value = 접근 제한 속성
         나. onMethod_ = setter 메서드 생성시 메서드에 추가할 어노테이션 지정. (참고) JDK8버전 이후에는 _(언더바)붙여서 사용
         다. onParam = setter 메서드 파라미터에 어노테이션을 사용하는 경우에 적용
   @Autowired
      a. 스프링 내부에서 자신의 특정한 객체에 의존적이니 자신에게 해당 타입의 빈을 주입해주라는 표시임
      b. 스프링은 @Autowired를 보고 => 스프링 내부에 관리되는 객체(들)중 적당한 것이 있는지 확인
                        => 자동으로 주입해줌
      c. 내가 원한다고 코딩한 객체가 없으면 (에러) 발생 => 스프링이 제대로 객체들을 구성할수가 없으므로
         (예외메세지)
         org.springframework.beans.factory. ??????? 
         => Chef 타입의 객체(빈)을 찾을수 없어서 적어도 1개 이상의 해당 타입의 객체가 필요하다고 나옴 
 */
package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
   @Setter(onMethod_ = @Autowired)
   private Chef chef;
}
