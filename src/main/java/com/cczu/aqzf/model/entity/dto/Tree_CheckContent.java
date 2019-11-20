package com.cczu.aqzf.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
 * @ClassName: Tree_CheckContent
 * @Description: 检查内容结构Tree
 * @author jason
 *
 */
public class Tree_CheckContent{
	
	@Setter
	@Getter
	private long id;//ID
	
	@Setter
	@Getter
	private String text;//节点文本
	
	@Setter
	@Getter
	private String ptext;//父节点内容
	
	@Setter
	@Getter
	private List<com.cczu.model.entity.dto.Tree_CheckContent> children;//子阶段
}
