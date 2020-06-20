package com.qubit.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qubit.demo.util.Statics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@JsonProperty(value = "post_id")
	private String postId;
	
	@JsonProperty(value = "fb_user_id")
	private String fbUserId;
	
	@JsonProperty(value = "message")
	private String message;
	
	@JsonProperty(value = "post_created_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Statics.DEFAULT_ZONED_FORMATT_WITH_GMT)
	private LocalDateTime postCreatedTime; 
	
	@JsonProperty(value = "created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Statics.DEFAULT_ZONED_FORMATT_WITH_GMT)
	private LocalDateTime createdDate;
	
}
