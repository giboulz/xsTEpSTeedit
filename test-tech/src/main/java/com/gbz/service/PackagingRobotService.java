package com.gbz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.gbz.model.Box;
import com.gbz.model.Item;
import com.gbz.model.PackagedBoxes;

public class PackagingRobotService {

	public Optional<PackagedBoxes> packageItems(String itemsInSize) {

		if (itemsInSize.length() == 0) {
			return Optional.empty();
		}

		List<Item> items = convertListOfSizeToItem(itemsInSize);

		return Optional.of(packageItems(items));

	}

	private List<Item> convertListOfSizeToItem(String itemsInSize) {

		List<String> itemSizeList = Arrays.asList(itemsInSize.split(""));

		List<Item> items = itemSizeList.stream().map(x -> new Item(Integer.valueOf(x))).collect(Collectors.toList());

		return items;
	}

	private PackagedBoxes packageItems(List<Item> items) {

		PackagedBoxes packagedBoxes = new PackagedBoxes();

		items.stream().forEach(consumingAnItem(packagedBoxes));

		return packagedBoxes;
	}

	private Consumer<? super Item> consumingAnItem(PackagedBoxes packagedBoxes) {
		return x -> {
			Optional<Box> availableBoxForSpecifiedSize = packagedBoxes.getAvailableBoxForSpecifiedSize(x.getSize());

			Box TargetBox = availableBoxForSpecifiedSize.orElse(new Box());

			TargetBox.add(x);

			if (!availableBoxForSpecifiedSize.isPresent()) {
				packagedBoxes.add(TargetBox);
			}
		};
	}

}
