package com.qubit.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Statics {

//	@Autowired
//	private static Environment env;
//	
//	public static final String MY_ACCESS_TOKEN = env.getProperty("access.token");
//	public static final String DEFAULT_ZONED_FORMATT_WITH_GMT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String DEFAULT_ZONED_FORMATT_WITH_GMT = "yyyy-MM-dd'T'HH:mm:ssxxxx";

	public static final String POSTS = "posts";
	public static final int batchSize = 300;
	
	DateFormat format = new SimpleDateFormat(DEFAULT_ZONED_FORMATT_WITH_GMT);
	
	public static final String fbUserId = "1443874382466957";
	
}
