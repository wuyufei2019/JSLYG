package com.cczu.sys.comm.utils;

import java.io.*;  
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
  
//网页全屏截图  
public class WebPagePicture {  
	
	// 日志输出对象  
    private static Log log = LogFactory.getLog(WebPagePicture.class);  
  
    /**
     * 截图
     * @param request
     * @param filePath
     * @param filedir
     * @param url
     * @param name
     * @return
     */
	public static Map<String,Object> getAjaxCotnent(HttpServletRequest request,String filePath,String filedir,String url,String name) {  
		Map<String,Object> mapdata=new HashMap<String,Object>();
		ServletContext context=request.getSession().getServletContext();  

    	String relPath = context.getRealPath(filePath); 
    	
    	StringBuilder tempPath = new StringBuilder(relPath);  
    	tempPath.append("/").append(filedir).append("/");  
    	tempPath.append(name);
		try {

	        Runtime rt = Runtime.getRuntime();   
	        String exec="phantomjs.exe c:/phantomjs/examples/"+url+" "+tempPath;
	        System.out.println(exec);
	        Process p = rt.exec(exec);
	
	        InputStream is = p.getInputStream();   
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));   
	        StringBuffer sbf = new StringBuffer();   
	        String tmp = "";   
			while((tmp = br.readLine())!=null){   
			    sbf.append(tmp);   
			}

		} catch (IOException e) {
			e.printStackTrace();
		}   
        
        mapdata.put("fileDirName", filedir+"\\"+name);
        mapdata.put("relPathName", tempPath);
        
        return mapdata;   
    }
	
	/** 
     * @category 拷贝文件 
     * @param src 源文件 
     * @param tar 目标路径 
     */  
    public void copy(InputStream src, String tar) {  
        // 判断源文件或目标路径是否为空  
        if (null == src   
                || null == tar   
                || tar.isEmpty()) {  
            return;  
        }  
        //InputStream srcIs = null;  
        OutputStream tarOs = null;  
        try {  
            //srcIs = new FileInputStream(src);  
            File tarFile = new File(tar);  
            tarOs = new FileOutputStream(tarFile);  
            byte[] buffer = new byte[4096];  
            int n = 0;  
            while (-1 != (n = src.read(buffer))) {  
                tarOs.write(buffer, 0, n);  
            }  
        } catch (IOException e) {             
            log.error("Copy File is Fali, Because "+e);  
        } finally {  
            try {  
                if (null != src) {  
                    src.close();  
                }  
                if (null != tarOs) {  
                    tarOs.close();  
                }  
            } catch (IOException e) {  
                log.error("Close Stream is Fail, Because "+e);  
            }  
        }  
    }

}  
