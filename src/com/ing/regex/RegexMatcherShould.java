package com.ing.regex;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RegexMatcherShould {

	RegexMatcher matcher = new RegexMatcher();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throw_exception_for_four_digits() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("No match found");
		final String invalid_expression = "1234";

		matcher.match_three_consecutive_digits(invalid_expression);
	}

	@Test
	public void throw_exception_when_expression_contains_words() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("No match found");
		final String invalid_expression = "1a4";

		matcher.match_three_consecutive_digits(invalid_expression);
	}

	@Test
	public void return_true_when_expression_contains_three_consecutive_digits() throws Exception {
		final String valid_expression = "111";

		boolean hasMatched = matcher.match_three_consecutive_digits(valid_expression);
		assertTrue(hasMatched);
	}

	@Test
	public void throw_exception_for_invalid_ip_address() throws Exception {
		assertExpressionWithRegex("192.16.178.28");
		assertExpressionWithRegex("19.168.178.28");
		assertExpressionWithRegex("19216.178.28");
	}

	private void assertExpressionWithRegex(String invalid_expression) throws Exception {
		thrown.expectMessage("No match found: " + invalid_expression);
		matcher.match_ip_address(invalid_expression);
	}

	@Test
	public void return_true_for_valid_ip_address() throws Exception {
		String valid_ip_Address = "192.168.178.28";

		boolean hasMatched = matcher.match_ip_address(valid_ip_Address);
		assertTrue(hasMatched);
	}

	@Test
	public void throw_exception_for_illformatted_phone_number() throws Exception {
		String illformatted_phone_number = "+91-988697483";
		thrown.expectMessage("No match found: " + illformatted_phone_number);
		matcher.match_phone_number(illformatted_phone_number);
	}

	@Test
	public void return_true_for_valid_phone_number() throws Exception {
		String valid_phone_nr = "+91-98 86 974 835";
		boolean hasMatched = matcher.match_phone_number(valid_phone_nr);
		assertTrue(hasMatched);

		valid_phone_nr = "+91-9886974835";
		hasMatched = matcher.match_phone_number(valid_phone_nr);
		assertTrue(hasMatched);

		valid_phone_nr = "+91-9886 974835";
		hasMatched = matcher.match_phone_number(valid_phone_nr);
		assertTrue(hasMatched);
	}

	@Test
	public void throw_exception_for_line_starting_with_word_without_digit_at_the_end() throws Exception {
		String expression = "This line starts with word and ends with digits=abc";
		thrown.expectMessage("No match found: " + expression);

		matcher.match_line_starting_with_word_ending_with_digit(expression);
	}

	@Test
	public void return_true_for_line_starting_with_word_ending_with_digit() throws Exception {
		String expression = "This line starts with word and ends with digits=900";

		boolean hasMatched = matcher.match_line_starting_with_word_ending_with_digit(expression);
		assertTrue(hasMatched);
	}

	@Test
	public void throw_Exception_when_expression_contains_words_from_b_to_d() throws Exception {
		String expression = "this is a text containing b";
		thrown.expectMessage("No match found: " + expression);

		matcher.match_any_word_but_from_b_to_d(expression);
	}

	@Test
	public void return_true_when_expression_doesnt_contain_words_from_b_to_d() throws Exception {
		String expression = "this is a text";

		boolean hasMatched = matcher.match_any_word_but_from_b_to_d(expression);
		assertTrue(hasMatched);
	}

	@Test
	public void return_true_for_fractional_number_with_one_or_two_decimal_points() throws Exception {
		String expression = "200";
		boolean hasMatched = matcher.match_fractional_number_with_one_or_two_decimal_points(expression);
		assertTrue(hasMatched);

		expression = "200.1";
		matcher.match_fractional_number_with_one_or_two_decimal_points(expression);
		assertTrue(hasMatched);

		expression = "200.12";
		matcher.match_fractional_number_with_one_or_two_decimal_points(expression);
		assertTrue(hasMatched);

		expression = "200.123";
		thrown.expectMessage("No match found: " + expression);
		matcher.match_fractional_number_with_one_or_two_decimal_points(expression);
	}
}
