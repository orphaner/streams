package org.beroot.stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.beroot.mongodb.exam.model.Image;
import org.beroot.streams.FlatMapStream;
import org.junit.Test;

public class FlatMapTest {

	private FlatMapStream flatMapStream = new FlatMapStream();

	@Test
	public void flattenedTagsTest() {
		assertThat(flatMapStream.getFlattenedTags(getImages()))
				.hasSize(4)
				.containsOnly("java", "stream", "java", "lambda");
	}

	@Test
	public void distinctFlattenedTagsTest() {
		assertThat(flatMapStream.getDistinctFlattenedTags(getImages()))
				.hasSize(3)
				.containsOnly("java", "stream", "lambda");
	}

	private List<Image> getImages() {
		Image image = Image.builder().tags(Arrays.asList("java", "stream")).build();
		Image image2 = Image.builder().tags(Arrays.asList("java", "lambda")).build();
		return Arrays.asList(image, image2);
	}
}
