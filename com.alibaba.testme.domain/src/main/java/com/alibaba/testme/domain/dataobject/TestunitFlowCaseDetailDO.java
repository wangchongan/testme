package com.alibaba.testme.domain.dataobject;
import java.util.Date;
/**
 * TestunitFlowCaseDetailDO
 */
public class TestunitFlowCaseDetailDO {

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
	 *testunitFlowCaseId
	 */
	private Integer testunitFlowCaseId;
	   
	/**
	 *testunitFlowDetailId
	 */
	private Integer testunitFlowDetailId;
	   
	/**
	 *gmtStart
	 */
	private Date gmtStart;
	   
	/**
	 *gmtEnd
	 */
	private Date gmtEnd;
	   
	/**
	 *gmtLastRun
	 */
	private Date gmtLastRun;
	   
	/**
	 *status
	 */
	private String status;
	   
	/**
	 *inParam
	 */
	private String inParam;
	   
	/**
	 *outParam
	 */
	private String outParam;
	   
	/**
	 *msg
	 */
	private String msg;
	   


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

	public void setTestunitFlowCaseId(Integer testunitFlowCaseId){ 
	   this.testunitFlowCaseId = testunitFlowCaseId; 
	}
	
	public Integer getTestunitFlowCaseId(){ 
	   return this.testunitFlowCaseId; 
	} 

	public void setTestunitFlowDetailId(Integer testunitFlowDetailId){ 
	   this.testunitFlowDetailId = testunitFlowDetailId; 
	}
	
	public Integer getTestunitFlowDetailId(){ 
	   return this.testunitFlowDetailId; 
	} 

	public void setGmtStart(Date gmtStart){ 
	   this.gmtStart = gmtStart; 
	}
	
	public Date getGmtStart(){ 
	   return this.gmtStart; 
	} 

	public void setGmtEnd(Date gmtEnd){ 
	   this.gmtEnd = gmtEnd; 
	}
	
	public Date getGmtEnd(){ 
	   return this.gmtEnd; 
	} 

	public void setGmtLastRun(Date gmtLastRun){ 
	   this.gmtLastRun = gmtLastRun; 
	}
	
	public Date getGmtLastRun(){ 
	   return this.gmtLastRun; 
	} 

	public void setStatus(String status){ 
	   this.status = status; 
	}
	
	public String getStatus(){ 
	   return this.status; 
	} 

	public void setInParam(String inParam){ 
	   this.inParam = inParam; 
	}
	
	public String getInParam(){ 
	   return this.inParam; 
	} 

	public void setOutParam(String outParam){ 
	   this.outParam = outParam; 
	}
	
	public String getOutParam(){ 
	   return this.outParam; 
	} 

	public void setMsg(String msg){ 
	   this.msg = msg; 
	}
	
	public String getMsg(){ 
	   return this.msg; 
	} 

}
