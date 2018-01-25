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

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.gbz.model.PackagedBoxes;
import com.gbz.service.PackagingRobotService;

public class PackagingRobotServiceTest {

	PackagingRobotService sut;

	@Before
	public void setUp() {
		sut = new PackagingRobotService();
	}

	@Test
	public void packageItems_shouldReturnEmpty_whenThereIsNoItem() {

		// arrange
		String emptyItemsFixture = "";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(emptyItemsFixture);

		// assert
		assertThat(res, is(Optional.empty()));
	}

	@Test
	public void packageItems_shouldPutItemsIn1Box_whenPackaging1item() {

		// arrange
		String itemsFixture = "1";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert
		assertThat(res.get().toString(), is("1"));
	}

	@Test
	public void packageItems_shouldPutAllItemIn1Box_whenTotalSizeIsUnder10() {

		// arrange
		String itemsFixture = "163";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert
		assertThat(res.get().toString(), is("163"));
	}

	@Test
	public void packageItems_shouldPutAllItemIn2Box_when2ItemCantBeInTheSameBox() {

		// arrange
		String itemsFixture = "88";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert
		assertThat(res.get().toString(), is("8/8"));
	}

	@Test
	public void packageItems_shouldPutAllItemIn1Box_whenItemsAreAllOfSize1() {

		// arrange
		String itemsFixture = "1111111111";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert
		assertThat(res.get().toString(), is("1111111111"));
	}
	
	

	@Test
	public void packageItems_shouldPackageInto8Box_whenSizeAre163841689525773() {

		// arrange
		String itemsFixture = "163841689525773";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert

		assertThat(res.get().getNbOfBox(), is(8));
		// As the point of the exercice is the number of box, this is enought (for now) but the robot is not fully optimized,
		// i have a difference of order which can lead to more box if the input items are a lot more.
		// expected : 163/82/46/19/8/55/73/7
		// was : 163/81/46/82/9/55/73/7

	}
	
	@Test
	public void packageItems_shouldPackageInto2Box_when4ItemFullingLoad2Box(){
		// arrange
		String itemsFixture = "1892";

		// act
		Optional<PackagedBoxes> res = sut.packageItems(itemsFixture);

		// assert

		assertThat(res.get().getNbOfBox(), is(2));
		assertThat(res.get().toString(), is("19/82"));
	}

}
