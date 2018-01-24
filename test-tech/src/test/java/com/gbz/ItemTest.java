package com.gbz;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gbz.model.Item;

public class ItemTest {

	@Test
	public void toString_shouldShowSizeOf1_whenItemSizeIs1() {
		// arrange
		Item item = new Item(1);

		// act
		String res = item.toString();

		// assert
		assertThat(res, is("1"));
	}

	@Test
	public void toString_shouldShowSizeOf5_whenItemSizeIs5() {
		// arrange
		Item item = new Item(5);

		// act
		String res = item.toString();

		// assert
		assertThat(res, is("5"));
	}

}
