package com.qubit.demo.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DAOLayer {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public String getlatestPostId() throws SQLException{  
		
		List<Map<String, Object>> mcresult = new ArrayList<>();
	    String mcQuery="select post_id from post order by post_created_time desc limit 1";  
	    mcresult= jdbcTemplate.queryForList(mcQuery);
	    if( null == mcresult || mcresult.isEmpty() || mcresult.get(0).get("post_id") == null)
	    	return "";
	    return mcresult.get(0).get("post_id").toString();
//	    return null;
	}
	
	public String getlatestFbLikePageId() throws SQLException{  
		
		List<Map<String, Object>> mcresult = new ArrayList<>();
	    String mcQuery="select page_id from fblike order by like_created_time desc limit 1";  
	    mcresult= jdbcTemplate.queryForList(mcQuery);
	    if( null == mcresult || mcresult.isEmpty() || mcresult.get(0).get("page_id") == null)
	    	return "";
	    return mcresult.get(0).get("page_id").toString();
	}
}