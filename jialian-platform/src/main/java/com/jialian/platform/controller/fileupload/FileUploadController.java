package com.jialian.platform.controller.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

	//图片存储在项目里的相对路径
	private String imageUploadPath = "upload/";
	
	//项目发布路径
	private String webAppUrl = "jialian-platform/image/upload/";
	
	//图片存储在磁盘上的路径
    private String webAppRoot = getRootPath() + "/image/";
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	/**
	 * @Description: 图片上传
	 * @Param:@param objectId 图片依附实体ID
	 * @Param:@param objectNo 图片依附实体编号
	 * @Param:@param objectType 图片衣服实体类型
	 * @Param:@param myFile 图片文件
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月14日上午10:56:44
	 * @Version:1.0
	 */
	@RequestMapping("/imageUpload.do")
	@ResponseBody
	public JsonResult imageUpload(Long objectId, String objectNo,  Integer objectType, MultipartFile myFile, HttpServletResponse response){
		JsonResult result = new JsonResult();
		if(myFile == null || myFile.getSize() == 0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_FILE_IS_NULL);
			result.setMessage("上传文件为空");
			return result;
		}
//		if(objectId == null || objectNo == null || objectType == null){
//			result.setSuccess(false);
//			result.setStateCode(Const.ERROR_PARAM_CODE);
//			result.setMessage("参数错误");
//			return result;
//		}
		String originalFileName = myFile.getOriginalFilename();
		String contentType = myFile.getContentType();
		InputStream in;
		try {
			in = myFile.getInputStream();
			ServiceResult<ResourceInfo> resultService = imageUpload(objectId, objectType, objectNo, originalFileName, contentType, imageUploadPath, in);
			result.addData("resourceInfo", resultService.getData());
			result.setSuccess(true);
			result.setStateCode(Const.SUCCESS_CODE);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage("未知错误");
			result.setStateCode(Const.ERROR_UNKNOWN_CODE);
		}
		
		return result;
	}
	
	public ServiceResult<ResourceInfo> imageUpload(Long objectId, Integer objectType, String ObjectNo, 
			String originalFileName, String contentType,
			String imageUploadPath, InputStream in) {
		ServiceResult<ResourceInfo> result = new ServiceResult<ResourceInfo>();
		//上传图片文件夹绝对路径
		String basePath = webAppRoot + "/" + imageUploadPath;
		//创建路径
		String imagePathFragment = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "";
		//最终文件夹的绝对路径
		String originalImagePath = basePath+ imagePathFragment;
		//最终图片网址的相对路径
		String imageUrl = webAppUrl + imagePathFragment;
		//图片
		String uploadName = contentType;
		//上传文件的类型
		String imageType = uploadName.substring(uploadName.indexOf("/") + 1, uploadName.length());
		//上传文件的后缀名
		String extension = generateFileExtension(originalFileName);
		//新的文件名
		String newFileName = generateFileName(originalFileName);
		//
		String fullPath = originalImagePath+"/" + newFileName + extension;

		File imageFile = new File(originalImagePath); 
		if(!imageFile.exists()){
			imageFile.mkdirs();
		}
		//写入磁盘
		try {
			FileOutputStream fos = new FileOutputStream(fullPath);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0){
				fos.write(buffer, 0, len);
			}
			fos.close();
			
			/*BufferedImage srcBufferImage = ImageIO.read(in);
			
			BufferedImage scaledImage;
			BufferedImage scaledImage2;
			ScaleImage scaleImage = ScaleImage.getInstance();
			ScaleImage scaleImage2 = ScaleImage.getInstance();
			int yw = srcBufferImage.getWidth();
			int yh = srcBufferImage.getHeight();
			//int w = 400, h = 400;
			// 如果上传图片 宽高 比 压缩的要小 则不压缩
		
			Map<String , Integer> size75 =	this.getSmallImgSize(yw,yh,75);
			Map<String , Integer> size400=	this.getSmallImgSize(yw,yh,400);
			
			scaledImage = scaleImage.imageZoomOut(srcBufferImage, size75.get("w"), size75.get("h"));
			FileOutputStream out = new FileOutputStream(fullPath.replace("s_"+newFileName, "s1_"+newFileName));
			ImageIO.write(scaledImage, "jpeg", out);
		
			scaledImage2 = scaleImage2.imageZoomOut(srcBufferImage, size400.get("w"), size400.get("h"));
			FileOutputStream o = new FileOutputStream(fullPath);
			ImageIO.write(scaledImage2, "jpeg", o);*/
			
			//插入数据库
			ResourceInfo resourceInfo = new ResourceInfo();
			String objNo;
			if(ObjectNo == null){
				objNo = "PIC" + idWorker.nextId();
			}else{
				if(ObjectNo.length() > 14){
					objNo = ObjectNo;
				}else{
					objNo = ObjectNo + idWorker.nextId();
				}
			}
			resourceInfo.setNo(objNo);
			resourceInfo.setName(newFileName);
			resourceInfo.setExtension(extension);
			resourceInfo.setObjectId(objectId);
			resourceInfo.setObjectType(objectType == null ? null:objectType.shortValue());
			resourceInfo.setPath(imageUrl);
			resourceInfo.setType(imageType);
			resourceInfo = resourceInfoService.insertSelective(resourceInfo);
			result.setOk(true);
			result.setData(resourceInfo);
		} catch (IOException e) {
			result.setOk(false);
			result.setComment(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private String generateFileName(String fileName)
	{
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		return formatDate + random ;
	}

	private String generateFileExtension(String fileName){
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return extension;
	}
	
	@SuppressWarnings("unused")
	private Map<String, Integer> getSmallImgSize(int srcWi, int srcHi,double baseSize) {
		 double   srcW= srcWi;
		 double   srcH= srcHi;
		 double   w=baseSize ,h=baseSize ;
			if(srcW>=baseSize&&srcH>=baseSize){
				if(srcW>=srcH){
					w=baseSize;h=(baseSize/srcW)*srcH;
				}else{
					h=baseSize;w=(baseSize/srcH)*srcW;
				}
			}
			
			if(srcW>=baseSize&&srcH<baseSize){
				w=baseSize;h=(baseSize/srcW)*srcH;
			}
			
			if(srcH>=baseSize&&srcW<baseSize){
				h=baseSize;w=(baseSize/srcH)*srcW;
			}
			
			if(srcW<baseSize&&srcH<baseSize){
				if(srcW>=srcH){
					w=baseSize;h=(baseSize/srcW)*srcH;
				}else{
					h=baseSize;w=(baseSize/srcH)*srcW;
				}
			}
			int wi=(int)w;
			int hi=(int)h;
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("w", wi);
			map.put("h", hi);
			return map;
		}
	public String getRootPath() {
		String classPath =FileUploadController.class.getClassLoader().getResource("/").getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("%20", " ");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
		}
		return rootPath;
	}
}
