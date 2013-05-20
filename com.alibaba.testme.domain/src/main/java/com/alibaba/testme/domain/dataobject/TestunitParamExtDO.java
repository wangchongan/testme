package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * TestunitParamExtDO
 */
public class TestunitParamExtDO {

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
	 *valueName
	 */
	private String valueName;
	   
	/**
	 *value
	 */
	private String value;
	   
	/**
	 *testunitParamId
	 */
	private Integer testunitParamId;
	   


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

	public void setValueName(String valueName){ 
	   this.valueName = valueName; 
	}
	
	public String getValueName(){ 
	   return this.valueName; 
	} 

	public void setValue(String value){ 
	   this.value = value; 
	}
	
	public String getValue(){ 
	   return this.value; 
	} 

	public void setTestunitParamId(Integer testunitParamId){ 
	   this.testunitParamId = testunitParamId; 
	}
	
	public Integer getTestunitParamId(){ 
	   return this.testunitParamId; 
	} 

}
