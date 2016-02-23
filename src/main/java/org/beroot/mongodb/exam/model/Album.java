package org.beroot.mongodb.exam.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Album {
	private Integer id;
	private List<Integer> images;
}
