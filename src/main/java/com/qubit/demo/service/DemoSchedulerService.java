package com.qubit.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qubit.demo.Repository.FBLikeRepository;
import com.qubit.demo.Repository.PostRepository;
import com.qubit.demo.model.FBLike;
import com.qubit.demo.model.Post;
import com.qubit.demo.util.DAOLayer;
import com.qubit.demo.util.DemoUtils;
import com.qubit.demo.util.Statics;

@Service
public class DemoSchedulerService {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoSchedulerService.class);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Statics.DEFAULT_ZONED_FORMATT_WITH_GMT);

//	@Autowired
//	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	FBLikeRepository fbLikeRepository;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	DAOLayer dao;
	
	boolean postsCompleted = false;
	
	boolean likesCompleted = false;
	
	String nextPageURL = "";
	
	List<Post> postList = new ArrayList<>();
	List<FBLike> fbLikeList = new ArrayList<>();
	
	@Async
	public void saveNewUserData() {
		
	}
	
	@Async
	public void savePosts() throws Exception {
		
		int postsCount = 0;
		postList = new ArrayList<>();
		postsCompleted = false;
		nextPageURL = "";
		int count = 0;
		String latestPost = null;
		
		JSONObject data = DemoUtils.getData(Statics.POSTS);
		if(data != null && data.isEmpty())
			return;
		latestPost = dao.getlatestPostId();
		
		while(!postsCompleted) {
			boolean isCompleted = savePostsInBatch(data, latestPost, count, nextPageURL);
			if(isCompleted) {
				postsCompleted = true;
				break;
			}
			if(Statics.batchSize < postList.size()) {
				postsCount = postsCount + postList.size();
				postRepository.saveAll(postList);
				postList.clear();
				logger.info("Posts Saved into Data base : " + postsCount);
			}
			count = count +1;
		}
		if(!postList.isEmpty()) {
			postsCount = postsCount + postList.size();
			postRepository.saveAll(postList);
			postList.clear();
			logger.info("Posts Saved into Data base : " + postsCount);
		}
		
		
	}
	
	public boolean savePostsInBatch(JSONObject data, String latestPost, int count, String nextPageURL) {
		try {
			if (count == 0) {
				Map<String, Object> postsData = (Map<String, Object>) data.get("posts");
				if (postsData.containsKey("data")) {

					List<Map<String, Object>> postData = (List<Map<String, Object>>) postsData.get("data");
					
						for (Map<String, Object> postMapObj : postData) {

							Post postObj = new Post();
							postObj.setFbUserId(Statics.fbUserId);
							postObj.setPostId(postMapObj.get("id").toString());
							postObj.setPostCreatedTime(LocalDateTime.parse(postMapObj.get("created_time").toString(),formatter));
							if(postMapObj.containsKey("message"))
								postObj.setMessage(postMapObj.get("message").toString());
							else
								postObj.setMessage("");
							if (latestPost != null && postObj.getPostId().equalsIgnoreCase(latestPost)) {
								return true;
							}
							postObj.setCreatedDate(LocalDateTime.now());
							postList.add(postObj);
						}

				}
				if (postsData.containsKey("paging")) {
					
					Map<String, Object> page = (Map<String, Object>) postsData.get("paging");
					if (page.containsKey("next")) {
						this.nextPageURL =  page.get("next").toString();
					}else {
						this.nextPageURL =  "";
					}
				}

			}else {
				JSONObject jsonData = DemoUtils.getPageData(this.nextPageURL);
				if (jsonData.containsKey("data")) {

					List<Map<String, Object>> postData = (List<Map<String, Object>>) jsonData.get("data");
					
						for (Map<String, Object> postMapObj : postData) {

							Post postObj = new Post();
							postObj.setFbUserId(Statics.fbUserId);
							postObj.setPostId(postMapObj.get("id").toString());
							postObj.setPostCreatedTime(LocalDateTime.parse(postMapObj.get("created_time").toString(),formatter));
							if(postMapObj.containsKey("message"))
								postObj.setMessage(postMapObj.get("message").toString());
							else
								postObj.setMessage("");
							if (latestPost != null && postObj.getPostId().equalsIgnoreCase(latestPost)) {
								return true;
							}
							postObj.setCreatedDate(LocalDateTime.now());
							postList.add(postObj);
						}

				}
				if (jsonData.containsKey("paging")) {
					Map<String, Object> page = (Map<String, Object>) jsonData.get("paging");
					if (page.containsKey("next")) {
						this.nextPageURL =  page.get("next").toString();
					}else {
						this.nextPageURL =  "";
					}
				}

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	
	@Async
	public void saveFBLikes() throws Exception {
		
		int fbLikesCount = 0;
		fbLikeList = new ArrayList<>();
		postsCompleted = false;
		nextPageURL = "";
		int count = 0;
		String latestPageLikeId = null;
		
		JSONObject data = DemoUtils.getData(Statics.POSTS);
		if(data != null && data.isEmpty())
			return;
		latestPageLikeId = dao.getlatestFbLikePageId();
		
		while(!likesCompleted) {
			boolean isCompleted = saveFBLikesInBatch(data, latestPageLikeId, count, nextPageURL);
			if(isCompleted) {
				likesCompleted = true;
				break;
			}
			if(Statics.batchSize < fbLikeList.size()) {
				fbLikesCount = fbLikesCount + fbLikeList.size();
				fbLikeRepository.saveAll(fbLikeList);
				fbLikeList.clear();
				logger.info("Fb Likes Data Saved into Data base : " + fbLikesCount);
			}
			count = count +1;
		}
		if(!fbLikeList.isEmpty()) {
			fbLikesCount = fbLikesCount + fbLikeList.size();
			fbLikeRepository.saveAll(fbLikeList);
			fbLikeList.clear();
			logger.info("Fb Likes data Saved into Data base : " + fbLikesCount);
		}
		
		
	}
	
	public boolean saveFBLikesInBatch(JSONObject data, String latestPageLikeId, int count, String nextPageURL) {
		try {
			if (count == 0) {
				Map<String, Object> fbLikesData = (Map<String, Object>) data.get("likes");
				if (fbLikesData.containsKey("data")) {

					List<Map<String, Object>> fbLikeData = (List<Map<String, Object>>) fbLikesData.get("data");
					
						for (Map<String, Object> fbLikeMapObj : fbLikeData) {

							FBLike fbLikeObj = new FBLike();
							fbLikeObj.setFbUserId(Statics.fbUserId);
							fbLikeObj.setPageId(fbLikeMapObj.get("id").toString());
							fbLikeObj.setLikeCreatedTime(LocalDateTime.parse(fbLikeMapObj.get("created_time").toString(),formatter));
							if(fbLikeMapObj.containsKey("name"))
								fbLikeObj.setPageName(fbLikeMapObj.get("name").toString());
							else
								fbLikeObj.setPageName("");
							if (latestPageLikeId != null && fbLikeObj.getPageId().equalsIgnoreCase(latestPageLikeId)) {
								return true;
							}
							fbLikeObj.setCreatedDate(LocalDateTime.now());
							fbLikeList.add(fbLikeObj);
						}

				}
				if (fbLikesData.containsKey("paging")) {
					
					Map<String, Object> page = (Map<String, Object>) fbLikesData.get("paging");
					if (page.containsKey("next")) {
						this.nextPageURL =  page.get("next").toString();
					}else {
						this.nextPageURL =  "";
					}
				}

			}else {
				JSONObject jsonData = DemoUtils.getPageData(this.nextPageURL);
				if (jsonData.containsKey("data")) {
					List<Map<String, Object>> fbLikeData = (List<Map<String, Object>>) jsonData.get("data");
					for (Map<String, Object> fbLikeMapObj : fbLikeData) {

						FBLike fbLikeObj = new FBLike();
						fbLikeObj.setFbUserId(Statics.fbUserId);
						fbLikeObj.setPageId(fbLikeMapObj.get("id").toString());
						fbLikeObj.setLikeCreatedTime(LocalDateTime.parse(fbLikeMapObj.get("created_time").toString(),formatter));
						if(fbLikeMapObj.containsKey("name"))
							fbLikeObj.setPageName(fbLikeMapObj.get("name").toString());
						else
							fbLikeObj.setPageName("");
						if (latestPageLikeId != null && fbLikeObj.getPageId().equalsIgnoreCase(latestPageLikeId)) {
							return true;
						}
						fbLikeObj.setCreatedDate(LocalDateTime.now());
						fbLikeList.add(fbLikeObj);
					}

				}
				if (jsonData.containsKey("paging")) {
					Map<String, Object> page = (Map<String, Object>) jsonData.get("paging");
					if (page.containsKey("next")) {
						this.nextPageURL =  page.get("next").toString();
					}else {
						this.nextPageURL =  "";
					}
				}

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}
