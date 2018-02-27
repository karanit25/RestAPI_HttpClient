package com.qa.data;

//This Class we need to create equivalent to JSON payload parameters, in this example only two payload variable Name and Job need to be initialized
//Exception will occur Unrecognized field "id" (class com.qa.data.Users), not marked as ignorable (2 known properties: "name", "job"])
//to resolve it create getter & setter for all the values which are returned as response i.e. id and createdAt
//This Class is called POJO - plain old java object
//This Java Class Users.java need to be converted to JSON object so that we can paas the payload with request
//This is called marshalling to convert Java object to JSON object for this we need a Utility which is provided by
//Jackson API
public class Users {

	String name;
	String job;
	String id;
	String createdAt;

	// Create default constructor
	public Users() {

	}

	// Generate Getters and Setters so that at RunTime we can take the values

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Users(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
