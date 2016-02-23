package org.beroot.streams;

import java.util.List;
import java.util.stream.Collectors;

import org.beroot.mongodb.exam.model.Image;

public class FlatMapStream {

	public List<String> getFlattenedTags(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(Collectors.toList());
	}

	public List<String> getDistinctFlattenedTags(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.distinct()
				.collect(Collectors.toList());
	}
}
