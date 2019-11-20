package com.cczu.aqzf.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
 * @ClassName: Tdic_NoteTreeDto
 * @Description: 字典-tree共通
 * @author jason
 *
 */
public class Tdic_NoteTreeDto{
	
	@Setter
	@Getter
	private String id;//ID
	
	@Setter
	@Getter
	private String text;//名称
	
	@Setter
	@Getter
	private String pid;//父ID
	
	@Setter
	@Getter
	private List<com.cczu.model.entity.dto.Tdic_NoteTreeDto> children;//子
}
