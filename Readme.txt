1. Use Postgres as a Database
2. Execute db script file - demo.db.script.txt
3. Need to Add "Access Token" and "User Id" Provided by FB graph API in application.properties file
	Access Token property name = access.token:
	User Id property name = fb.user.id
4. Set True/false to "test.mode.enable" in application.properties file
	true = Test Model Enabled , It wont save data, but methods will be called for every interval
	false = data will be saved to database


Features : 
	1. Two Schedulers have been used in this project - DemoScheduler.java 
		a. saveUserFBPostsData - to save user posts data
		b. saveUserFBLikesData - to save user likes data

