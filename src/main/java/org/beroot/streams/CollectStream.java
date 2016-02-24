package org.beroot.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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

	public List<String> collectWithListAccumulator(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(ArrayList::new, List::add, List::addAll);
	}

	public List<String> collectWithListCollector(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(Collectors.toList());
	}

	public Set<String> collectWithSetAccumulator(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(TreeSet::new, Set::add, Set::addAll);
	}

	public Set<String> collectWithSetCollector(List<Image> images) {
		return images.stream()
				.flatMap(image -> image.getTags().stream())
				.collect(Collectors.toSet());
	}
}
