package aspects;

import java.io.IOException;

public aspect LoggingAspect {

	pointcut whenImageIsTransformed(): call( public void transform(String) throws IOException );
	
	before(): whenImageIsTransformed() {
		
	    String methodArg = thisJoinPoint.getArgs()[0].toString();

		System.out.println("Method started. Transforming image: " + methodArg);
	}
	
	
	after() throwing (IOException e): whenImageIsTransformed() {
		  
		System.out.println("Threw an exception: " + e);
	      }
	
	
	after(): whenImageIsTransformed() {
		
		 String methodArg = thisJoinPoint.getArgs()[0].toString();
		
		System.out.println("Method ended. Image " + methodArg + " was transformed!");
	}
	
}
