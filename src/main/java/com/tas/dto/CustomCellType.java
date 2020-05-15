package com.tas.dto;

public enum CustomCellType {

	_STRING("STRING"),
	_DATE("DATE"),
	_DOLLARS("DOLLARS"),
	_DOUBLE("DOUBLE"),
	_INTEGER("INTEGER"),
	_FORMULA("_FORMULA"),
	_BOOLEAN("BOOLEAN");

	private CustomCellType(String label) {
		this.label = label;
	}

	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
