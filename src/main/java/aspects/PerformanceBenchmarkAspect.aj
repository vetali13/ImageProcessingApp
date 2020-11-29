package aspects;

public aspect PerformanceBenchmarkAspect {

	pointcut whenImageIsTransformed(): call( public void transform(String) );
	
	private long processingTime;
	
	Object around(): whenImageIsTransformed() {
		
		processingTime = System.nanoTime();
		
	    Object result = proceed();
	    
	    processingTime = System.nanoTime() - processingTime;
	    
	    System.out.println("Processing time = " + processingTime/1000 + " ms");
	  
	    return result;

		
	}
}
