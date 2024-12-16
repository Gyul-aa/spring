/*
	# 객체 리스트
 */
package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {
	public List<SampleDTO> list;
	// 생성자
	public SampleDTOList() {
		list = new ArrayList<>();
	}
}
