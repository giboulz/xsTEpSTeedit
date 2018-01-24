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

import com.gbz.model.Box;
import com.gbz.model.Item;

public class BoxTest {

	Box sut;

	@Before
	public void setUp() {
		this.sut = new Box();
	}

	@Test
	public void toString_shouldShowSizeOf1item_whenItContain1Item() {
		
		// arrange
		Item itemFixture = new Item(1);
		sut.add(itemFixture);
		// act
		String res = sut.toString();

		// assert
		assertThat(res, is("1"));
	}

	@Test
	public void toString_shouldShowSizeOf3item_whenItContain3Item() {
		
		// arrange
		sut.add(new Item(1));
		sut.add(new Item(2));
		sut.add(new Item(3));

		// act
		String res = sut.toString();

		// assert
		assertThat(res, is("123"));
	}

}
