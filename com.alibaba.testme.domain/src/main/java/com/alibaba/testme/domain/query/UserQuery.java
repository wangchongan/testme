package com.alibaba.testme.domain.query;

import java.io.Serializable;

public class UserQuery implements Serializable{
	
	private static final long serialVersionUID = -7868463220853943444L;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
