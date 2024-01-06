package com.tipsontech.loganalyzer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tipsontech.loganalyzer.entity.ErrorLogEntry;
import com.tipsontech.loganalyzer.entity.LogEntry;

public class LogAnalyzerUtils {

	private static final Logger LOGGER = Logger.getLogger(LogAnalyzerUtils.class.getName());

	public static void analyzeLogsFromFile(String logFilePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				LogEntry logEntry = parseLogEntry(line, reader);
				if (logEntry != null && isException(logEntry)) {
					extractExceptionDetails(logEntry);
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error reading log file", e);
		}
	}
	
	public static void raiseIssueByStackTrace(Exception ex) {
		System.out.println(ex.getMessage());
		System.out.println(ex.getCause());
		System.out.println(ex.getClass());
		System.out.println(ex.getStackTrace());
	}

	private static LogEntry parseLogEntry(String logLine, BufferedReader reader) throws IOException {
		String regex = "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}) \\[(\\w+)] (\\w+) (.+?) - (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(logLine);

		if (matcher.matches()) {
			String timestamp = matcher.group(1);
			String threadName = matcher.group(2);
			String logLevel = matcher.group(3);
			String logger = matcher.group(4);
			String message = matcher.group(5);

			if ("ERROR".equals(logLevel) || "WARN".equals(logLevel)) {
				StringBuilder stackTrace = new StringBuilder();
				String stackTraceLine = reader.readLine();
				stackTrace.append(stackTraceLine).append("\n");
				while ((stackTraceLine = reader.readLine()) != null
						&& (stackTraceLine.startsWith("\t") || stackTraceLine.startsWith(" "))) {
					stackTrace.append(stackTraceLine).append("\n");
				}
				return new ErrorLogEntry(timestamp, threadName, logLevel, logger, message, stackTrace.toString());
			} else {
				return new LogEntry(timestamp, threadName, logLevel, logger, message);
			}
		} else {
			return null;
		}
	}

	private static boolean isException(LogEntry logEntry) {
		return logEntry.getLevel().equals("ERROR") || logEntry.getLevel().equals("WARN")
				|| logEntry.getLevel().equals("SEVERE");
	}

	private static void extractExceptionDetails(LogEntry logEntry) {
		System.out.println("Timestamp: " + logEntry.getTimestamp());
		System.out.println("Thread: " + logEntry.getThreadName());
		System.out.println("Log Level: " + logEntry.getLevel());
		System.out.println("Logger: " + logEntry.getLogger());
		System.out.println("Message: " + logEntry.getMessage());
		if (logEntry instanceof ErrorLogEntry) {
			System.out.println("Stack Trace: " + ((ErrorLogEntry) logEntry).getStackTrace());
		}
		System.out.println("=============================================");
	}
}
