package com.jialian.common.util;

public abstract class SerialNumber {

	public  String getSerialNumber() {
		return process();
	}

	protected abstract String process();
}
