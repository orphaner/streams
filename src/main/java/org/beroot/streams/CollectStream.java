package org.beroot.streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.beroot.mongodb.exam.model.Image;

public class CollectStream {

	public Map<String, Long> countByTag(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(Collectors.groupingBy(
						returnInputTag -> returnInputTag,
						Collectors.counting()));
	}

	public Map<String, Long> countByTagUsingFunction(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(Collectors.groupingBy(
						Function.identity(),
						Collectors.counting()));
	}

	public String joinedDistinctTags(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.distinct()
				.collect(Collectors.joining(", "));
	}
}
