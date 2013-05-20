package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * WorkSpaceDO
 */
public class WorkSpaceDO {

	/**
	 *id
	 */
	private Integer id;
	   
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
	 *name
	 */
	private String name;
	   
	/**
	 *userId
	 */
	private String userId;
	   
	/**
	 *systemId
	 */
	private Integer systemId;
	   


	public void setId(Integer id){ 
	   this.id = id; 
	}
	
	public Integer getId(){ 
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

	public void setName(String name){ 
	   this.name = name; 
	}
	
	public String getName(){ 
	   return this.name; 
	} 

	public void setUserId(String userId){ 
	   this.userId = userId; 
	}
	
	public String getUserId(){ 
	   return this.userId; 
	} 

	public void setSystemId(Integer systemId){ 
	   this.systemId = systemId; 
	}
	
	public Integer getSystemId(){ 
	   return this.systemId; 
	} 

}
