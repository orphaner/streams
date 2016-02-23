package org.beroot.mongodb.exam;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.beroot.mongodb.exam.model.Album;
import org.beroot.mongodb.exam.model.Image;
import org.junit.Test;

public class OrphanDetectorTest {

	@Test
	public void imageIsInAlbum() {
		Image image = Image.builder().id(666).build();
		Album album = getAlbumWithImage(image);

		OrphanDetector orphanDetector = new OrphanDetector();
		assertThat(orphanDetector.imageIsInAlbum(album, image)).isTrue();
	}

	@Test
	public void imageIsNotInAlbum() {
		Image orphanImage = Image.builder().id(1984).build();
		Album album = getDummyAlbum();

		OrphanDetector orphanDetector = new OrphanDetector();
		assertThat(orphanDetector.imageIsInAlbum(album, orphanImage)).isFalse();
	}

	@Test
	public void imageIsAtLeastInOneAlbum() {
		Image image = Image.builder().id(666).build();
		Album album = getAlbumWithImage(image);
		Album dummyAlbum = getDummyAlbum();
		List<Album> collection = Arrays.asList(album, dummyAlbum);

		OrphanDetector orphanDetector = new OrphanDetector();
		orphanDetector.setAlbums(collection);
		orphanDetector.setImages(Arrays.asList(image));
		assertThat(orphanDetector.imageIsAtLeastInOneAlbum(image)).isTrue();
	}

	@Test
	public void imageIsOrphan() {
		Image orphanImage = Image.builder().id(1984).build();
		Album dummyAlbum = getDummyAlbum();
		List<Album> collection = Arrays.asList(dummyAlbum);

		OrphanDetector orphanDetector = new OrphanDetector();
		orphanDetector.setAlbums(collection);
		orphanDetector.setImages(Arrays.asList(orphanImage));
		assertThat(orphanDetector.imageIsAtLeastInOneAlbum(orphanImage)).isFalse();
	}
	
	@Test
	public void noOrphans() {
		Image image = Image.builder().id(666).build();
		Album album = getAlbumWithImage(image);
		List<Album> collection = Arrays.asList(album);

		OrphanDetector orphanDetector = new OrphanDetector();
		orphanDetector.setAlbums(collection);
		orphanDetector.setImages(Arrays.asList(image));
		List<Image> orphans = orphanDetector.getOrphanImages();
		assertThat(orphans).isEmpty();
	}
	
	@Test
	public void getSingleOrphan() {
		Image image = Image.builder().id(1984).build();
		Album dummyAlbum = getDummyAlbum();
		List<Album> collection = Arrays.asList(dummyAlbum);

		OrphanDetector orphanDetector = new OrphanDetector();
		orphanDetector.setAlbums(collection);
		orphanDetector.setImages(Arrays.asList(image));
		List<Image> orphans = orphanDetector.getOrphanImages();
		assertThat(orphans).hasSize(1);
		assertThat(orphans).containsOnly(image);
	}
	
	@Test
	public void getManyOrphan() {
		Image orphanImage = Image.builder().id(1984).build();
		Image orphanImage2 = Image.builder().id(2016).build();
		Album dummyAlbum = getDummyAlbum();
		List<Album> collection = Arrays.asList(dummyAlbum);

		OrphanDetector orphanDetector = new OrphanDetector();
		orphanDetector.setAlbums(collection);
		orphanDetector.setImages(Arrays.asList(orphanImage, orphanImage2));
		
		List<Image> orphans = orphanDetector.getOrphanImages();
		assertThat(orphans).hasSize(2);
		assertThat(orphans).containsOnly(orphanImage, orphanImage2);
	}
	
	private Album getDummyAlbum() {
		return Album.builder()
				.id(1)
				.images(Arrays.asList(123456789))
				.build();
	}

	private Album getAlbumWithImage(Image image) {
		return Album.builder()
				.id(1)
				.images(Arrays.asList(image.getId()))
				.build();
	}
}
