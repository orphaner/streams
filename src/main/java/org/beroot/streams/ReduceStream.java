package org.beroot.streams;

import java.util.List;
import java.util.Optional;

import org.beroot.mongodb.exam.model.Image;

public class ReduceStream {
	
	public long sum(List<Image> images) {
		return images.stream()
				.map(image -> image.getSizeInByte())
				.reduce(0L, (x, y) -> x + y);
	}

	public long sumUsingLongSum(List<Image> images) {
		return images.stream()
				.map(image -> image.getSizeInByte())
				.reduce(0L, Long::sum);
	}
	
	public long multiply(List<Image> images) {
		return images.stream()
				.map(image -> image.getSizeInByte())
				.reduce(1L, (x, y) -> x * y);
	}
	
	public Optional<Long> multiplyWithoutIdentity(List<Image> images) {
		return images.stream()
				.map(image -> image.getSizeInByte())
				.reduce((x, y) -> x * y);
	}
}
