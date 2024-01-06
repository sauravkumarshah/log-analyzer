package com.tipsontech.loganalyzer.entity;

public class LogEntry {
	private final String timestamp;
	private final String threadName;
	private final String level;
	private final String logger;
	private final String message;

	public LogEntry(String timestamp, String threadName, String level, String logger, String message) {
		this.timestamp = timestamp;
		this.threadName = threadName;
		this.level = level;
		this.logger = logger;
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getThreadName() {
		return threadName;
	}

	public String getLevel() {
		return level;
	}

	public String getLogger() {
		return logger;
	}

	public String getMessage() {
		return message;
	}
}
