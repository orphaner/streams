package org.beroot.mongodb.exam.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
	private Integer id;
	private int width;
	private int height;
	private Long sizeInByte;
	private List<String> tags;
}
