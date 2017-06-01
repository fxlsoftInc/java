package com.jialian.common.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 				
* @ClassName: FlattenImageUtils
* @Description: 拼合图像工具类
* @author 刘德宜   wudihaike@vip.qq.com
* @date 2015年3月16日 下午4:58:43
 */
public class FlattenImageUtils {
	
	private static Graphics2D g = null;
	
	private static final Logger logger = LoggerFactory.getLogger(FlattenImageUtils.class);

	/**
	 * 导入本地图片到缓冲区
	 */
	public static BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			logger.error("导入本地图片到缓冲区失败",e); 
		}
		return null;
	}

	/**
	 * 导入网络图片到缓冲区
	 */
	public static BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			logger.error("导入网络图片到缓冲区失败",e); 
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				logger.error("生成新图片到本地失败",e); 
			}
		}
	}

	/**
	 * 合并图片
	 */
	public static BufferedImage modifyImagetogeter(BufferedImage blackground, BufferedImage qrc,int width, int height) {
		try {
			int w = blackground.getWidth();
			int h = blackground.getHeight();
			g = qrc.createGraphics();
			g.drawImage(blackground, width, height, w, h, null);
			g.dispose();
		} catch (Exception e) {
			logger.error("合并图片失败",e); 
		}
		return qrc;
	}

	public static void main(String[] args) {
		FlattenImageUtils flattenImageUtils = new FlattenImageUtils();
		BufferedImage d = FlattenImageUtils.loadImageUrl("http://smokeimg.hxshop.com//g1/M00/00/3E/rBAKBFUKfqCAAjscAAqXRihNX0s669.jpg");
		BufferedImage b = FlattenImageUtils.loadImageUrl("http://smokeimg3.hxshop.com/g1/M00/00/3E/rBAKBFUKfu6AeAmsAAFwCUVrwBk714.jpg");

		flattenImageUtils.writeImageLocal("E://cew.jpg", FlattenImageUtils.modifyImagetogeter(b, d,100,100));
		//将多张图片合在一起
		System.out.println("success");
	}
}
