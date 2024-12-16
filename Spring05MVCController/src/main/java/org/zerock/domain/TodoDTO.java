/*
	@DateTimeFormat
	: 문자열로 형식에 맞게 호출하면 자동으로 데이터 타입을 변환해줌 (pattern = "")
 */


package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
}
