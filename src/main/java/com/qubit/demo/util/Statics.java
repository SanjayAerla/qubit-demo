package com.qubit.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Statics {
	
	public static final String DEFAULT_ZONED_FORMATT_WITH_GMT = "yyyy-MM-dd'T'HH:mm:ssxxxx";

	public static final String POSTS = "posts";
	public static final String LIKES = "likes";
	public static final int batchSize = 10;
	
	DateFormat format = new SimpleDateFormat(DEFAULT_ZONED_FORMATT_WITH_GMT);
	
}
