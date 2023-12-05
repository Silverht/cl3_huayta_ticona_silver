package com.huayta.ticona.silver.controller.advice;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
	private String codigo;
	private List<Violation>violations=new ArrayList<Violation>();
}
