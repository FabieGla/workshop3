package detectors;

public class Breakpoints {
	
	private String className;
	private String methodName;
	private Integer startline;
	private Integer endline;
	
	public Breakpoints(String className, String methodName, int startline, int endline) {
		this.className  = className;
		this.methodName = methodName;
		this.startline  = startline;
		this.endline    = endline;
	}
	

	@Override
	public String toString() {
		return "className="+this.className+",methodName="+this.methodName+",startline="+this.startline+",endline="+this.endline;
	}
	
	
	
	
}
