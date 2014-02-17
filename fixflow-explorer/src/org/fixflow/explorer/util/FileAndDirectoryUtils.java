/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author 徐海洋
 */
package org.fixflow.explorer.util;

import java.io.File;
import java.util.List;

import com.founder.fix.bpmn2extensions.coreconfig.ResourcePath;
import org.fixflow.core.ProcessEngineManagement;
import org.fixflow.core.impl.ProcessEngineConfigurationImpl;
import org.fixflow.util.FileUtil;

/**
 * 文件及文件夹的IO管理类
 * 职责：获取（文件及文件夹）数据信息
 * 开发者：徐海洋
 */
public class FileAndDirectoryUtils {
	
	private static int key = 1;
	private static String json = "",subJson = "";
	public static String privatePath = "";
	public static String sharedPath = "";
	
	static{
		ProcessEngineConfigurationImpl configuration = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration();
		List<ResourcePath> list = configuration.getResourcePathConfig().getResourcePath();
		for(ResourcePath resourcePath : list){
			if(configuration.FIXFLOW_EDITOR_PRIVATE_REPOSITORY.equals(resourcePath.getId())){
				privatePath = resourcePath.getPhysicalPath();
			}
			if(configuration.FIXFLOW_EDITOR_PUBLIC_REPOSITORY.equals(resourcePath.getId())){
				sharedPath = resourcePath.getPhysicalPath();
			}
		}
	}
	/**
	 * 构建文件及文件夹的层次结构对应的体现数据（json data）
	 * @param loginUserId
	 *          当前登入人的编号
	 * @param basePath
	 *          webcontent目录
	 */
	public static String buildLevelJsonDataWithLoginPerson(String loginUserId, String basePath) throws Exception{
		try{
			createLeve(new File(privatePath+File.separator+loginUserId+File.separator+"resolvent"));
			createLeve(new File(sharedPath+File.separator+"resolvent"));
			
			key =1;
			int privateKey = key++;
			int sharedKey = key++;
			json += ",{id:"+privateKey+",pId:0,name:'private',type:'dir',isParent:true}";
			json += ",{id:'"+sharedKey+"',pId:0,name:'shared',type:'dir',isParent:true}";
			iterationRead(new File(privatePath+File.separator+loginUserId),privateKey);
			iterationRead(new File(sharedPath),sharedKey);
		}catch(Exception e){
		}
		
		 json ="[" +json.substring(1)+ "]";
	    return json;
	}
	
	private static void createLeve(File file){
		if(!file.exists())  file.mkdirs();
	}
	
	public static void clear(){
		 key = 0;json = "";subJson="";
	}
	
	/**
	 *迭代目录层级提取json数据
	 * @param file 跟目录
	 * @param pid 树型结构的父节点
	 */
	private static void iterationRead(File file,int pid) throws Exception{
		File[] FList = file.listFiles();
		for (int i = 0; i < FList.length; i++){
			key ++;
			if (FList[i].isDirectory()==true){
				if(FList[i].getName().equals("resolvent")){
					json += ",{id:"+key+",pId:"+pid+",name:'"+FList[i].getName()+"',type:'dir',isParent:false}";
				}else
				json += ",{id:"+key+",pId:"+pid+",name:'"+FList[i].getName()+"',type:'dir',isParent:true}";
				iterationRead(FList[i],key);
			}else{
				//json += ",{id:"+key+",pId:"+pid+",name:'"+FList[i].getName()+"',type:'file'}";
			}
		}
	}
	 
	/**
	 * 创建文件或文件夹
	 * @param fileLeveStr文件层级字符串
	 *                 如：user/andming/...
	 * @param basePath
	 *               webcontent目录
	 * @throws Exception
	 */
	public static void createFileOrDirectory(String path) throws Exception{
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}else{
			throw new Exception("当前文件或文件夹已存在!");
		}
	}
	
	/**
	 * 读取我文件夹下的所有子文件信息
	 * @param fileLeveStr文件层级字符串
	 *                 如：user/andming/...
	 * @param basePath
	 *               webcontent目录
	 * @throws Exception
	 */
	public static String readSubFileAndDirectory(String path) throws Exception{
		File file = new File(path);
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		File[] FList = file.listFiles();
		for (int i = 0; i < FList.length; i++){
			if (FList[i].isDirectory()==true){
				subJson += ",{name:'"+FList[i].getName()+"',type:'dir'}";
				iterationRead(FList[i],key);
			}else{
				//只读取bpmn文件
				if(FList[i].getName().lastIndexOf(".bpmn") > -1){
					subJson += ",{name:'"+FList[i].getName()+"',type:'file'}";
				}
			}
		}
 
		if(subJson.equals("")){
			return "[]";
		}
 
		return "["+subJson.substring(1)+"]";
	}
	
	 /** 
     * 重命名文件或文件夹 
     *  
     * @param resFilePath 
     *            源文件路径 
     * @param newFilePath 
     *            重命名 文件路径 
     *            
     * @param basePath 
     *            webcontent目录
     *            
     * @return 操作成功标识 
     */  
    public static boolean renameFile(String resFilePath, String newFilePath) throws Exception{
        File resFile = new File(resFilePath);  
        if(resFilePath.equals(newFilePath)){
        	return true;
        }
        if(!resFile.exists()){
			return true;
		}
        File newFile = new File(newFilePath);  
		if(newFile.exists()){
			throw new Exception("当前文件或文件夹已存在!");
		}
		if(!newFile.isFile() &&!newFile.exists()){
			newFile.mkdir();
		}
		FileUtil.copyFolder(resFilePath, newFilePath,null);
		FileUtil.deleteFile(resFilePath);
		return true;
    }  
    
    /** 
     * 移动文件或文件夹 
     *  
     * @param resFileOrDirectory 
     *            源文件及文件夹
     * @param newFilePOrDirectory
     *            新文件及文件夹
     *            
     * @param basePath 
     *            webcontent目录
     *            
     * @return 操作成功标识 
     */  
    public static boolean moveFileAndDirectory(String resFileOrDirectory, String newFilePOrDirectory,String fileName) throws Exception{
    	  File resf = new File(resFileOrDirectory);
    	  if(!resf.exists()){
    		  return true;
    	  }
	      String newf = newFilePOrDirectory;
	      File fnewpath = new File(newf+File.separator+fileName);
	      if(!resf.isFile() && !fnewpath.exists())
	        fnewpath.mkdir();
	      if(resf.exists()){
	    	  FileUtil.copyFolder(resFileOrDirectory, newf+File.separator+fileName,null);
		      FileUtil.deleteFile(resFileOrDirectory);
	      }
	      return true;
    }  

}
