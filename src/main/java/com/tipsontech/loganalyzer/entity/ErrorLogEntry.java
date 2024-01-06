package com.tipsontech.loganalyzer.entity;

public class ErrorLogEntry extends LogEntry {
	private final String stackTrace;

	public ErrorLogEntry(String timestamp, String threadName, String level, String logger, String message,
			String stackTrace) {
		super(timestamp, threadName, level, logger, message);
		this.stackTrace = stackTrace;
	}

	public String getStackTrace() {
		return stackTrace;
	}
}
