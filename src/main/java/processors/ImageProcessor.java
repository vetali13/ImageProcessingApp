package processors;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;

import config.ConfigurationProvider;

public class ImageProcessor {
	
	private ConfigurationProvider cp;
	
	public ImageProcessor() {
		
	}
	
	public ImageProcessor(ConfigurationProvider cp) {
		this.cp = cp;
	}
	
	public void transform(String fileName) throws IOException {

		BufferedImage bio = ImageIO.read( new File(cp.get("path.original") + fileName) );
		
		BufferedImageOp resampler = new ResampleOp(200, 200, ResampleOp.FILTER_LANCZOS);
		
		BufferedImage bir = resampler.filter(bio, null);
		ImageIO.write( bir, "jpeg", new File(cp.get("path.processed") + fileName) );
	}
	
	public void setConfigurationProvider(ConfigurationProvider cp) {
		this.cp = cp;
	}

}
