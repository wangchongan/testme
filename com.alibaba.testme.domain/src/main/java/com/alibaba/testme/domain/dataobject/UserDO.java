package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * UserDO
 */
public class UserDO {

	/**
	 *id
	 */
	private Long id;
	   
	/**
	 *gmtCreate
	 */
	private Date gmtCreate;
	   
	/**
	 *creator
	 */
	private String creator;
	   
	/**
	 *gmtModified
	 */
	private Date gmtModified;
	   
	/**
	 *modifier
	 */
	private String modifier;
	   
	/**
	 *userName
	 */
	private String userName;
	   
	/**
	 *password
	 */
	private String password;
	   
	/**
	 *wangwang
	 */
	private String wangwang;
	   


	public void setId(Long id){ 
	   this.id = id; 
	}
	
	public Long getId(){ 
	   return this.id; 
	} 

	public void setGmtCreate(Date gmtCreate){ 
	   this.gmtCreate = gmtCreate; 
	}
	
	public Date getGmtCreate(){ 
	   return this.gmtCreate; 
	} 

	public void setCreator(String creator){ 
	   this.creator = creator; 
	}
	
	public String getCreator(){ 
	   return this.creator; 
	} 

	public void setGmtModified(Date gmtModified){ 
	   this.gmtModified = gmtModified; 
	}
	
	public Date getGmtModified(){ 
	   return this.gmtModified; 
	} 

	public void setModifier(String modifier){ 
	   this.modifier = modifier; 
	}
	
	public String getModifier(){ 
	   return this.modifier; 
	} 

	public void setUserName(String userName){ 
	   this.userName = userName; 
	}
	
	public String getUserName(){ 
	   return this.userName; 
	} 

	public void setPassword(String password){ 
	   this.password = password; 
	}
	
	public String getPassword(){ 
	   return this.password; 
	} 

	public void setWangwang(String wangwang){ 
	   this.wangwang = wangwang; 
	}
	
	public String getWangwang(){ 
	   return this.wangwang; 
	} 

}
