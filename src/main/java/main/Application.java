package main;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;

import behaviour.DirectoryObserver;
import config.ConfigurationProvider;
import processors.ImageProcessor;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("STARTING");
		
		DirectoryObserver dirObserver = new DirectoryObserver(new ConfigurationProvider());
		dirObserver.addFileProcessor(".jpg", new ImageProcessor());
		dirObserver.observe();
		
//		ImageProcessor ip = new ImageProcessor(new ConfigurationProvider());
//		ip.transform("crussader.jpg");

		System.out.println("FINISHING");

	}

}
