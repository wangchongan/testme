package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * SystemRequirePropDO
 */
public class SystemRequirePropDO {

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
	 *systemId
	 */
	private Integer systemId;
	   
	/**
	 *propCode
	 */
	private String propCode;
	   
	/**
	 *propName
	 */
	private String propName;
	   
	/**
	 *defaultValue
	 */
	private String defaultValue;
	   
	/**
	 *nullable
	 */
	private String nullable;
	   
	/**
	 *help
	 */
	private String help;
	   


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

	public void setSystemId(Integer systemId){ 
	   this.systemId = systemId; 
	}
	
	public Integer getSystemId(){ 
	   return this.systemId; 
	} 

	public void setPropCode(String propCode){ 
	   this.propCode = propCode; 
	}
	
	public String getPropCode(){ 
	   return this.propCode; 
	} 

	public void setPropName(String propName){ 
	   this.propName = propName; 
	}
	
	public String getPropName(){ 
	   return this.propName; 
	} 

	public void setDefaultValue(String defaultValue){ 
	   this.defaultValue = defaultValue; 
	}
	
	public String getDefaultValue(){ 
	   return this.defaultValue; 
	} 

	public void setNullable(String nullable){ 
	   this.nullable = nullable; 
	}
	
	public String getNullable(){ 
	   return this.nullable; 
	} 

	public void setHelp(String help){ 
	   this.help = help; 
	}
	
	public String getHelp(){ 
	   return this.help; 
	} 

}
