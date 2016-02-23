package org.beroot.mongodb.exam;

import java.util.List;
import java.util.function.Predicate;

import org.beroot.mongodb.exam.model.Image;

public class Exam7 {
	public static void main(String[] args) {
		MongoDAO dao = new MongoDAO();
		OrphanDetector detector = new OrphanDetector();
		detector.setAlbums(dao.getAlbums());
		detector.setImages(dao.getImages());
		
		List<Image> imagesInAlbum = detector.getAlbumedImages();
		System.out.println(imagesInAlbum.size());
		
		Predicate<Image> hasSunrises = image -> image.getTags().contains("sunrises");
		long count = imagesInAlbum.stream()
				.filter(hasSunrises)
				.count();
		System.out.println(count);
	}
}
