package org.beroot.mongodb.exam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.beroot.mongodb.exam.mapper.AlbumMapper;
import org.beroot.mongodb.exam.mapper.ImageMapper;
import org.beroot.mongodb.exam.model.Album;
import org.beroot.mongodb.exam.model.Image;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	private static final boolean PARALLEL_STREAM = false;

	private MongoDatabase examDb;

	public MongoDAO() {
		MongoClient client = new MongoClient("mongodb.biroute.org");
		examDb = client.getDatabase("exam");
	}

	public List<Album> getAlbums() {
		MongoCollection<Document> albumCollection = examDb.getCollection("albums");
		
		return StreamSupport
				.stream(albumCollection.find().spliterator(), PARALLEL_STREAM)
				//.map(AlbumMapper::toAlbum)
				.map(AlbumMapper.toAlbum)
				.collect(Collectors.toList());
	}

	public List<Image> getImages() {
		MongoCollection<Document> imageCollection = examDb.getCollection("images");
		
		return StreamSupport
				.stream(imageCollection.find().spliterator(), PARALLEL_STREAM)
				.map(ImageMapper::toImage)
				.collect(Collectors.toList());
	}
}
