package org.beroot.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.List;

import org.beroot.mongodb.exam.model.Image;
import org.beroot.streams.CollectStream;
import org.junit.Test;

public class CollectStreamTest {

	private CollectStream collectStream = new CollectStream();

	@Test
	public void countByTagTest() {
		assertThat(collectStream.countByTag(getImages()))
				.hasSize(3)
				.containsOnly(
						entry("java", 2L),
						entry("stream", 1L),
						entry("lambda", 1L));
	}

	@Test
	public void countByTagUsingFunctionTest() {
		assertThat(collectStream.countByTagUsingFunction(getImages()))
				.hasSize(3)
				.containsOnly(
						entry("java", 2L),
						entry("stream", 1L),
						entry("lambda", 1L));
	}

	@Test
	public void joinedTagsTest() {
		assertThat(collectStream.joinedDistinctTags(getImages()))
				.isEqualTo("java, stream, lambda");
	}

	@Test
	public void collectWithListAccumulatorTest() {
		assertThat(collectStream.collectWithListAccumulator(getImages()))
				.hasSize(4)
				.containsOnly("java", "stream", "java", "lambda");
	}

	@Test
	public void collectWithListCollectorTest() {
		assertThat(collectStream.collectWithListCollector(getImages()))
				.hasSize(4)
				.containsOnly("java", "stream", "java", "lambda");
	}

	@Test
	public void collectWithSetAccumulatorTest() {
		assertThat(collectStream.collectWithSetAccumulator(getImages()))
				.hasSize(3)
				.containsOnly("java", "stream", "lambda");
	}

	@Test
	public void collectWithSetCollectorTest() {
		assertThat(collectStream.collectWithSetCollector(getImages()))
				.hasSize(3)
				.containsOnly("java", "stream", "lambda");
	}

	private List<Image> getImages() {
		Image image = Image.builder().tags(Arrays.asList("java", "stream")).build();
		Image image2 = Image.builder().tags(Arrays.asList("java", "lambda")).build();
		return Arrays.asList(image, image2);
	}
}
