package org.beroot.stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.beroot.mongodb.exam.model.Image;
import org.beroot.streams.ReduceStream;
import org.junit.Test;

public class ReduceStreamTest {
	
	private ReduceStream reduceStream = new ReduceStream();
	
	@Test
	public void sumUsingLongSumTest() {
		assertThat(reduceStream.sumUsingLongSum(getImages())).isEqualTo(4000L);
	}
	
	@Test
	public void sumTest() {
		assertThat(reduceStream.sum(getImages())).isEqualTo(4000L);
	}
	
	@Test
	public void multiplyTest() {
		assertThat(reduceStream.multiply(getImages())).isEqualTo(3999744L);
	}
	
	@Test
	public void multiplyTestWithoutIdentity() {
		Optional<Long> result = reduceStream.multiplyWithoutIdentity(getImages());
		assertThat(result).isNotNull();
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo(3999744L);
	}
	
	private List<Image> getImages() {
		Image image = Image.builder().sizeInByte(1984L).build();
		Image image2 = Image.builder().sizeInByte(2016L).build();
		return Arrays.asList(image, image2);
	}
}
