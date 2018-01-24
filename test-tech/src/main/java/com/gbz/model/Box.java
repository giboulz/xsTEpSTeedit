package com.gbz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Box {

	public static final int SIZE = 10;

	List<Item> items = new ArrayList<>();

	public void add(Item item) {
		items.add(item);
	}

	public int getItemsSize() {
		return items.stream().collect(Collectors.summingInt(Item::getSize));
	}

	@Override
	public String toString() {
		return items.stream().map(Item::toString).collect(Collectors.joining(""));
	}

}
