/*
	Mapper 인터페이스 사용
	 = MyBatis 어노테이션을 이용하여 SQL을 메서드에 추가하는 인터페이스
*/
package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	/* #1. MyBatis 어노테이션 이용 => SQL을 메서드에 추가 */
	@Select("SELECT sysdate from dual")
	public String getTime();
/* (다음작업) Mapper 설정 = root-context.xml 
	a. Mapper 설정
	  = MyBastis가 동작할때 Mapper를 인식할 수 있도록 추가적인 설정
	  = root-context.xml 하단 탭 Namespaces > mybatis-spring탭 선택
	b. <mybatis-spring:scan> 태그에 base-package 속성 설정
	  = 지정된 패키지의 모든 Mybatis 관련 어노테이션을 찾아서 처리
	  = 자동으로 처리할 패키지를 인식시키는 방식을 작성하는 것이 가장 편리
*/
	/* #2. xml을 이용하여 처리 */
	public String getTime2();
	
}
