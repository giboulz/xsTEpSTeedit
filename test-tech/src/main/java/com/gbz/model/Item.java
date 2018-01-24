package com.gbz.model;

public class Item {

	private int size;
	// no more atttribute ?

	public Item(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return String.valueOf(this.size);
	}

}
