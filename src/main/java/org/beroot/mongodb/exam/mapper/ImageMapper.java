package org.beroot.mongodb.exam.mapper;

import java.util.List;

import org.beroot.mongodb.exam.model.Image;
import org.bson.Document;

public class ImageMapper {
	
	@SuppressWarnings("unchecked")
	public static Image toImage(Document mongoImage) {
		return Image.builder()
				.id(mongoImage.getInteger("_id"))
				.height(mongoImage.getInteger("height"))
				.width(mongoImage.getInteger("width"))
				.tags((List<String>)mongoImage.get("tags"))
				.build();
	}
}
