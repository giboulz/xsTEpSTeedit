package com.gbz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PackagedBoxes {

	private List<Box> boxes;

	public PackagedBoxes() {
		this.boxes = new ArrayList<>();
	}

	public void add(Box box) {
		boxes.add(box);

	}

	public Optional<Box> getAvailableBoxForSpecifiedSize(int size) {
		Optional<Box> result = Optional.empty();

		result = boxThatCanFullyFillWhithGivenSize(size);

		if (!result.isPresent()) {

			result = boxThatCanContainGivenSize(size);
		}

		return result;

	}

	private Optional<Box> boxThatCanContainGivenSize(int size) {
		return boxes.stream().filter(x -> (x.getItemsSize() + size) <= Box.SIZE).findFirst();
	}

	private Optional<Box> boxThatCanFullyFillWhithGivenSize(int size) {
		return boxes.stream().filter(x -> (x.getItemsSize() + size) == Box.SIZE).findFirst();
	}

	public int getNbOfBox() {
		return boxes.size();
	}

	@Override
	public String toString() {
		return boxes.stream().map(Box::toString).collect(Collectors.joining("/"));
	}

}
