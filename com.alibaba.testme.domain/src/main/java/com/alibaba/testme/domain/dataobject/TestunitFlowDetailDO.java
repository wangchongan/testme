package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * TestunitFlowDetailDO
 */
public class TestunitFlowDetailDO {

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
	 *testunitFlowId
	 */
	private Integer testunitFlowId;
	   
	/**
	 *testunitId
	 */
	private Integer testunitId;
	   
	/**
	 *preTestunitId
	 */
	private Integer preTestunitId;
	   
	/**
	 *nextTestunitId
	 */
	private Integer nextTestunitId;
	   


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

	public void setTestunitFlowId(Integer testunitFlowId){ 
	   this.testunitFlowId = testunitFlowId; 
	}
	
	public Integer getTestunitFlowId(){ 
	   return this.testunitFlowId; 
	} 

	public void setTestunitId(Integer testunitId){ 
	   this.testunitId = testunitId; 
	}
	
	public Integer getTestunitId(){ 
	   return this.testunitId; 
	} 

	public void setPreTestunitId(Integer preTestunitId){ 
	   this.preTestunitId = preTestunitId; 
	}
	
	public Integer getPreTestunitId(){ 
	   return this.preTestunitId; 
	} 

	public void setNextTestunitId(Integer nextTestunitId){ 
	   this.nextTestunitId = nextTestunitId; 
	}
	
	public Integer getNextTestunitId(){ 
	   return this.nextTestunitId; 
	} 

}
