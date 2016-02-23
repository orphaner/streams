package org.beroot.mongodb.exam.mapper;

import java.util.List;
import java.util.function.Function;

import org.beroot.mongodb.exam.model.Album;
import org.bson.Document;

public class AlbumMapper {
	public static Function<Document, Album> toAlbum = new Function<Document, Album>() {
		
		@Override
		public Album apply(Document mongoAlbum) {
			return AlbumMapper.toAlbum(mongoAlbum);
		}
	};
	
	@SuppressWarnings("unchecked")
	public static Album toAlbum(Document mongoAlbum) {
		return Album.builder()
				.id(mongoAlbum.getInteger("_id"))
				.images((List<Integer>)mongoAlbum.get("images")) 
				.build();
	}
}
