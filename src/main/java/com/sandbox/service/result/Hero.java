package com.sandbox.service.result;

public class Hero {
	
	private int id;
	
	private String name;
	
	private String localizedName;
	
	private String language;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", localizedName="
				+ localizedName + ", language=" + language + "]";
	}
	
}
