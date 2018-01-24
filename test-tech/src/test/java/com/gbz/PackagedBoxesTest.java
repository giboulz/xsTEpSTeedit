package com.gbz;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.gbz.model.Box;
import com.gbz.model.Item;
import com.gbz.model.PackagedBoxes;

public class PackagedBoxesTest {

	PackagedBoxes sut;

	@Before
	public void setUp() {
		this.sut = new PackagedBoxes();
	}

	@Test
	public void toString_shouldShowSizeOf1Box_whenItContaint1Box() {

		// arrange
		Box boxFixture = new Box();
		boxFixture.add(new Item(1));
		sut.add(boxFixture);

		// act
		String res = sut.toString();

		// assert
		assertThat(res, is("1"));
	}

	@Test
	public void toString_shouldShowSizeOf2BoxWithSlashSeparator_whenItContaint2Box() {

		// arrange
		Box boxFixture = new Box();
		boxFixture.add(new Item(1));
		sut.add(boxFixture);

		boxFixture = new Box();
		boxFixture.add(new Item(2));
		sut.add(boxFixture);

		// act
		String res = sut.toString();

		// assert
		assertThat(res, is("1/2"));
	}

	@Test
	public void toString_shouldShowSizeOf3BoxWithMultipleItems_whenItContains3BoxWithMultipleItems() {

		// arrange
		Box boxFixture = new Box();
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(2));
		boxFixture.add(new Item(3));
		sut.add(boxFixture);

		boxFixture = new Box();
		boxFixture.add(new Item(2));
		boxFixture.add(new Item(2));
		boxFixture.add(new Item(2));
		sut.add(boxFixture);

		boxFixture = new Box();
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(1));
		boxFixture.add(new Item(1));
		sut.add(boxFixture);

		// act
		String res = sut.toString();

		// assert
		assertThat(res, is("123/222/111111"));

	}

	@Test
	public void getAvailableBoxForSpecifiedSize_shouldGetEmpty_whenNoBoxeArePresent() {

		// arrange

		// act
		Optional<Box> res = sut.getAvailableBoxForSpecifiedSize(5);

		// assert
		assertThat(res, is(Optional.empty()));
	}

	@Test
	public void getAvailableBoxForSpecifiedSize_shouldGetEmpty_whenBoxCantHandleItemSize() {

		// arrange
		Box boxFixture = new Box();
		boxFixture.add(new Item(5));
		sut.add(boxFixture);
		// act
		Optional<Box> res = sut.getAvailableBoxForSpecifiedSize(6);

		// assert
		assertThat(res, is(Optional.empty()));
	}

	@Test
	public void getAvailableBoxForSpecifiedSize_shouldGetABox_when1EmptyBoxIsPresent() {

		// arrange
		Box boxFixture = new Box();

		sut.add(boxFixture);

		// act
		Optional<Box> res = sut.getAvailableBoxForSpecifiedSize(5);

		// assert
		assertThat(res, is(Optional.of(boxFixture)));
	}

	@Test
	public void getAvailableBoxForSpecifiedSize_shouldGetABox_when1BoxStillHaveEnoughtSpaceForItem() {

		// arrange
		Box boxFixture = new Box();
		boxFixture.add(new Item(5));
		sut.add(boxFixture);

		// act
		Optional<Box> res = sut.getAvailableBoxForSpecifiedSize(5);

		// assert
		assertThat(res, is(Optional.of(boxFixture)));
	}

}
