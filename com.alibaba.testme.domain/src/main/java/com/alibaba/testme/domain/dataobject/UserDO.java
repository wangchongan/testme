package com.alibaba.testme.domain.dataobject;

import java.io.Serializable;

public class UserDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7868463220853943444L;
	
	private Long id;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}