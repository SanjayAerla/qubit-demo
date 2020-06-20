//package com.qubit.demo.model;
//
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.qubit.demo.util.Statics;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class User {
//
//	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	private String id;
//	
//	@JsonProperty(value = "fb_post_id")
//	private String fbUserId;
//	
//	@JsonProperty(value = "name")
//	private String name;
//	
//	@JsonProperty(value = "is_new")
//	private boolean isNew;
//	
//	@JsonProperty(value = "created_date")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Statics.DEFAULT_ZONED_FORMATT_WITH_GMT)
//	private Date createdDate; 
//	
//	@JsonProperty(value = "updated_date")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Statics.DEFAULT_ZONED_FORMATT_WITH_GMT)
//	private Date updatedDate;
//}
