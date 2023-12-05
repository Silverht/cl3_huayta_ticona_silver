package com.huayta.ticona.silver.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorGeneral {
	private int codigo;
	private String message;
}
