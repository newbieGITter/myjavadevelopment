package com.ing.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

	public boolean match_three_consecutive_digits(String expression) throws Exception {
		final Pattern pattern = Pattern.compile("\\d{1,3}");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches()) {
			printGroups(matcher);
			return true;
		} else {
			throw new Exception(" No match found for : " + expression);
		}
	}

	public boolean match_ip_address(String expression) throws Exception {
		final Pattern pattern = Pattern.compile("(\\d{3}).(\\d{3}).(\\d{2,3}).(\\d{2})");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches()) {
			printGroups(matcher);
			return true;
		} else {
			throw new Exception("No match found: " + expression);
		}
	}

	public boolean match_phone_number(String expression) throws Exception {
		// Example phone number +91-99 86 774 835
		Pattern pattern = Pattern.compile("(\\+\\d{2}-)?(\\d{2})\\s?(\\d{2})\\s?(\\d{3})\\s?(\\d{3})");
		Matcher matcher = pattern.matcher(expression);

		if (matcher.matches()) {
			printGroups(matcher);
			return true;
		} else {
			throw new Exception("No match found: " + expression);
		}

	}

	public boolean match_line_starting_with_word_ending_with_digit(String expression) throws Exception {
		Pattern pattern = Pattern.compile("(^\\w.+)(digit(s)?=)(\\d+$)");
		Matcher matcher = pattern.matcher(expression);

		if (matcher.matches()) {
			String digit = matcher.group(2);
			System.out.println(digit + matcher.group(3));
			return true;
		} else {
			throw new Exception("No match found: " + expression);
		}
	}

	public boolean match_any_word_but_from_b_to_d(String expression) throws Exception {
		Pattern pattern = Pattern.compile("([a-z&&[^b-d]]|\\s)*");
		Matcher matcher = pattern.matcher(expression);

		if (matcher.matches()) {
			printGroups(matcher);
			return true;
		} else {
			throw new Exception("No match found: " + expression);
		}
	}

	public boolean match_fractional_number_with_one_or_two_decimal_points(String expression) throws Exception {
		Pattern pattern = Pattern.compile("(\\d)+(.\\d{1,2})?");
		Matcher matcher = pattern.matcher(expression);

		if (matcher.matches()) {
			printGroups(matcher);
			return true;
		} else {
			throw new Exception("No match found: " + expression);
		}
	}

	private void printGroups(Matcher matcher) {
		int groupCount = matcher.groupCount();

		for (int i = 0; i <= groupCount; i++) {
			System.out.println(matcher.group(i));
		}
	}

}
