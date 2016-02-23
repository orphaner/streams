package org.beroot.mongodb.exam;

import java.util.List;
import java.util.stream.Collectors;

import org.beroot.mongodb.exam.model.Album;
import org.beroot.mongodb.exam.model.Image;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class OrphanDetector {
	
	private List<Album> albums;
	private List<Image> images;
	

	public List<Image> getAlbumedImages() {
		return images
				.stream()
				.filter(image -> imageIsAtLeastInOneAlbum(image))
				.collect(Collectors.toList());
	}
	
	public List<Image> getOrphanImages() {
		return images
				.stream()
				.filter(image -> !imageIsAtLeastInOneAlbum(image))
				.collect(Collectors.toList());
	}
	
	protected Boolean imageIsAtLeastInOneAlbum(Image image) {
		return albums
				.stream()
				.anyMatch(album -> {
					// Lambda here everything is possible ^^
					return imageIsInAlbum(album, image);
				});
	}

	protected Boolean imageIsInAlbum(Album album, Image image) {
		return album.getImages()
				.stream()
				.anyMatch(imageId -> imageId.equals(image.getId()));
	}
}
