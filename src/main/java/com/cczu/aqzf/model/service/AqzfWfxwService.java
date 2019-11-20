package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfWfxwClDao;
import com.cczu.aqzf.model.dao.AqzfWfxwDao;
import com.cczu.aqzf.model.entity.AQZF_WfxwClEntity;
import com.cczu.aqzf.model.entity.AQZF_WfxwEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("AqzfWfxwService")
public class AqzfWfxwService {

	@Resource
	private AqzfWfxwDao aqzfWfxwDao;
	@Resource
	private AqzfWfxwClDao aqzfWfxwClDao;
	
	/**
	 * 查询违法行为list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqzfWfxwDao.dataGrid(mapData);
		int getTotalCount=aqzfWfxwDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		aqzfWfxwDao.deleteInfo(id);
	}
	
	/**
	 * 添加违法行为信息
	 * @param wfxw
	 */
	public void addInfo(AQZF_WfxwEntity wfxw) {
		wfxw.setS1(new Timestamp(System.currentTimeMillis()));
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		aqzfWfxwDao.addInfo(wfxw);
	}

	/**
	 * 根据id查找违法行为信息
	 * @param id
	 * @return
	 */
	public AQZF_WfxwEntity findById(Long id) {
		return aqzfWfxwDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param wfxw
	 */
	public void updateInfo(AQZF_WfxwEntity wfxw) {
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		aqzfWfxwDao.updateInfo(wfxw);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="违法行为表.xls";
		List<Map<String, Object>> list=aqzfWfxwDao.getExport(mapData);
		String[] title={"违法行为","违法条款","违法条款详情","处罚依据","处罚标准","自由裁量依据"};  
		String[] keys={"m1","m2","m3","m4","m5","m7"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 获得违法行为list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getWfxwIdlist() {
		return aqzfWfxwDao.getWfxwIdlist();
	}
	
	/**
	 * 获得违法行为list填充下拉框（h5用）
	 * @return
	 */
	public List<Map<String, Object>> getWfxwIdlistForH5(Map<String, Object> map) {
		return aqzfWfxwDao.getWfxwIdlist(map);
	}
	
	/**
	 * 获取分类
	 */
	public String gettextjson() {
		List<Map<String, Object>> list = aqzfWfxwDao.gettextjson();
		return JsonMapper.getInstance().toJson(list);
	}
	
	//导入
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		Map<String,Object> resultmap = new HashMap<String, Object>();
        try {
        	InputStream input = (InputStream) map.get("content");  //建立输入流    
            Workbook wb  = new HSSFWorkbook(input);  
            Sheet sheet = wb.getSheetAt(0);    //获得第一个表单    
            List<CellRangeAddress> cras = getCombineCell(sheet); 
             
            int count = sheet.getLastRowNum()+1;//总行数  
            int result = 0,error = 0;
            if(count>5){
            	resultmap.put("total", count-5);
    			resultmap.put("returncode", "success");
	            for(int i = 5; i < count;i++){  
	                try {
	                	Row row = sheet.getRow(i);  
	                	AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
		                wfxw.setS1(new Timestamp(System.currentTimeMillis()));
						wfxw.setS2(new Timestamp(System.currentTimeMillis()));
						wfxw.setS3(0);
						wfxw.setID1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
						wfxw.setM1(this.replaceBlank(getCellValue(row.getCell(2))));
						wfxw.setM2(this.replaceBlank(getCellValue(row.getCell(3))));
						wfxw.setM3(this.replaceBlank(getCellValue(row.getCell(4))));
						wfxw.setM4(this.replaceBlank(getCellValue(row.getCell(5))));
						wfxw.setM5(this.replaceBlank(getCellValue(row.getCell(6))));
						wfxw.setM6(this.replaceBlank(getCellValue(row.getCell(0))));
						wfxw.setM7(this.replaceBlank(getCellValue(row.getCell(1))));
						aqzfWfxwDao.save(wfxw);
		                  
		                if(isMergedRegion(sheet,i,0)){  
		                    int lastRow = getRowNum(cras,sheet.getRow(i).getCell(0),sheet);  
		                    for(;i<=lastRow;i++){  
		                        row = sheet.getRow(i);  
		                        AQZF_WfxwClEntity wfxwcl = new AQZF_WfxwClEntity();
		                        wfxwcl.setID1(wfxw.getID());
		                        wfxwcl.setM1(this.replaceBlank(getCellValue(row.getCell(7))));
		                        wfxwcl.setM2(this.replaceBlank(getCellValue(row.getCell(8))));
		                        wfxwcl.setM3(this.replaceBlank(getCellValue(row.getCell(9))));
		                        wfxwcl.setM4(this.replaceBlank(getCellValue(row.getCell(10))));
		                        wfxwcl.setM5(this.replaceBlank(getCellValue(row.getCell(11))));
		                        aqzfWfxwClDao.save(wfxwcl);
		                        result++;
		                    }  
		                    i--;  
		                }else{  
		                    row = sheet.getRow(i);  
		                    AQZF_WfxwClEntity wfxwcl = new AQZF_WfxwClEntity();  
	                        wfxwcl.setID1(wfxw.getID());
	                        wfxwcl.setM1(this.replaceBlank(getCellValue(row.getCell(7))));
	                        wfxwcl.setM2(this.replaceBlank(getCellValue(row.getCell(8))));
	                        wfxwcl.setM3(this.replaceBlank(getCellValue(row.getCell(9))));
	                        wfxwcl.setM4(this.replaceBlank(getCellValue(row.getCell(10))));
	                        wfxwcl.setM5(this.replaceBlank(getCellValue(row.getCell(11))));
	                        aqzfWfxwClDao.save(wfxwcl);
		                    result++;
		                }  
		                
					} catch (Exception e) {
						error++;
					}
	                resultmap.put("success",result);
					resultmap.put("error", error);
	            }
            }else if(count==5){
    			resultmap.put("success",result);
    			resultmap.put("error", error);
    			resultmap.put("returncode", "warn");
    		}else if(count<5){
    			resultmap.put("success",result);
    			resultmap.put("error", error);
    			resultmap.put("returncode", "ext");
    		}
        } catch (Exception e) {
        	resultmap.put("returncode", "ext");
		}
        return resultmap;

	}
	
	public String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	/**    
    * 获取单元格的值    
    * @param cell    
    * @return    
    */      
    public String getCellValue(Cell cell){      
        if(cell == null) return "";      
        if(cell.getCellType() == Cell.CELL_TYPE_STRING){      
            return cell.getStringCellValue();      
        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){      
            return String.valueOf(cell.getBooleanCellValue());      
        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){      
            return cell.getCellFormula() ;      
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){      
            return String.valueOf((int)cell.getNumericCellValue()); 
        }  
        return "";      
    }  
    
    /**  
     * 合并单元格处理,获取合并行  
     * @param sheet  
     * @return List<CellRangeAddress>  
     */    
     public List<CellRangeAddress> getCombineCell(Sheet sheet)    
     {    
         List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();    
         //获得一个 sheet 中合并单元格的数量    
         int sheetmergerCount = sheet.getNumMergedRegions();    
         //遍历所有的合并单元格    
         for(int i = 0; i<sheetmergerCount;i++)     
         {    
             //获得合并单元格保存进list中    
             CellRangeAddress ca = sheet.getMergedRegion(i);    
             list.add(ca);    
         }    
         return list;    
     }  
       
     private int getRowNum(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet){  
         int xr = 0;  
         int firstC = 0;    
         int lastC = 0;    
         int firstR = 0;    
         int lastR = 0;    
         for(CellRangeAddress ca:listCombineCell)    
         {  
             //获得合并单元格的起始行, 结束行, 起始列, 结束列    
             firstC = ca.getFirstColumn();    
             lastC = ca.getLastColumn();    
             firstR = ca.getFirstRow();    
             lastR = ca.getLastRow();    
             if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)     
             {    
                 if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)     
                 {    
                     xr = lastR;  
                 }   
             }    
               
         }  
         return xr;  
           
     }  
     /**  
      * 判断单元格是否为合并单元格，是的话则将单元格的值返回  
      * @param listCombineCell 存放合并单元格的list  
      * @param cell 需要判断的单元格  
      * @param sheet sheet  
      * @return  
      */   
      public String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet)  
      throws Exception{   
          int firstC = 0;    
          int lastC = 0;    
          int firstR = 0;    
          int lastR = 0;    
          String cellValue = null;    
          for(CellRangeAddress ca:listCombineCell)    
          {  
              //获得合并单元格的起始行, 结束行, 起始列, 结束列    
              firstC = ca.getFirstColumn();    
              lastC = ca.getLastColumn();    
              firstR = ca.getFirstRow();    
              lastR = ca.getLastRow();    
              if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)     
              {    
                  if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)     
                  {    
                      Row fRow = sheet.getRow(firstR);    
                      Cell fCell = fRow.getCell(firstC);    
                      cellValue = getCellValue(fCell);    
                      break;    
                  }   
              }    
              else    
              {    
                  cellValue = "";    
              }    
          }    
          return cellValue;    
      }  
       
     /**    
     * 获取合并单元格的值    
     * @param sheet    
     * @param row    
     * @param column    
     * @return    
     */      
     public String getMergedRegionValue(Sheet sheet ,int row , int column){      
         int sheetMergeCount = sheet.getNumMergedRegions();      
               
         for(int i = 0 ; i < sheetMergeCount ; i++){      
             CellRangeAddress ca = sheet.getMergedRegion(i);      
             int firstColumn = ca.getFirstColumn();      
             int lastColumn = ca.getLastColumn();      
             int firstRow = ca.getFirstRow();      
             int lastRow = ca.getLastRow();      
                   
             if(row >= firstRow && row <= lastRow){      
                 if(column >= firstColumn && column <= lastColumn){      
                     Row fRow = sheet.getRow(firstRow);      
                     Cell fCell = fRow.getCell(firstColumn);      
                     return getCellValue(fCell) ;      
                 }      
             }      
         }      
               
         return null ;      
     }  
       
       
     /**   
     * 判断指定的单元格是否是合并单元格   
     * @param sheet    
     * @param row 行下标   
     * @param column 列下标   
     * @return   
     */    
     private boolean isMergedRegion(Sheet sheet,int row ,int column) {    
       int sheetMergeCount = sheet.getNumMergedRegions();    
       for (int i = 0; i < sheetMergeCount; i++) {    
         CellRangeAddress range = sheet.getMergedRegion(i);    
         int firstColumn = range.getFirstColumn();    
         int lastColumn = range.getLastColumn();    
         int firstRow = range.getFirstRow();    
         int lastRow = range.getLastRow();    
         if(row >= firstRow && row <= lastRow){    
             if(column >= firstColumn && column <= lastColumn){    
                 return true;    
             }    
         }  
       }    
       return false;    
     }  
}
