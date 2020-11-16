package behaviour;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.swing.event.ListSelectionEvent;

import org.apache.commons.io.FilenameUtils;

import config.ConfigurationProvider;
import processors.ImageProcessor;

public class DirectoryObserver {
	
	private ConfigurationProvider cp;
	private HashMap<String, ImageProcessor> processors;

	public DirectoryObserver(ConfigurationProvider cp) {
		this.cp = cp;
		processors = new HashMap<>();
	}
	
	public void addFileProcessor(String extension, ImageProcessor ip) {
		ip.setConfigurationProvider(cp);
		processors.put(extension, ip);
	}
	
	public void observe() throws InterruptedException, IOException {
		
		File rootDir = new File(cp.get("path.original"));
		
		// HW1: checking if the folder exists
		if ( rootDir.exists() ) {
			
			List<File> lastFiles = new ArrayList<>();
			
			while(true) {
				
				List<File> files = new ArrayList<>(Arrays.asList(rootDir.listFiles()));
				
				// HW3-4: removing files that are not JPGs or are directories
				files.removeIf(file -> ( !FilenameUtils.getExtension(file.getName()).equals("jpg") || file.isDirectory() ) );
				
				// HW2: Comparing arrays
				List<File> difference = new ArrayList<>(files);
				difference.removeAll(lastFiles);
				
				if ( !difference.isEmpty() ) {
					
					System.out.println("--------------------------------");
					System.out.println("FOUND FILES");
					
					// HW5: processing only new files
					for(File file : difference) {
						System.out.println(">>>>" + file);
						processors.get(".jpg").transform(file.getName());
					}
					lastFiles = files;
					
				}
				
				Thread.sleep(2000);
			}
		} 
		
		else {
			System.out.println("Observable folder does not exist!");
		}
	}

	
	
	
}
