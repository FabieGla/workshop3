package detectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class RecursionDetector extends VoidVisitorAdapter <HashSet<Breakpoints>> {
	
	private ClassOrInterfaceDeclaration activeClass;
	private MethodDeclaration activeMethod;
	private HashMap<String, HashSet<Breakpoints>> recursiveMethods;
	private HashMap<String, Integer> methodCount;
	
	public RecursionDetector() {
		this.recursiveMethods = new HashMap<String, HashSet<Breakpoints>>();
		this.methodCount = new HashMap<String, Integer>();
	}
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, HashSet<Breakpoints> arg) {
		// TODO Auto-generated method stub
		activeClass = n;
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodDeclaration n, HashSet<Breakpoints> arg) {
		// TODO Auto-generated method stub
		methodCount.merge(n.getNameAsString(), 1, Integer::sum);
		activeMethod = n;
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodCallExpr n, HashSet<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		if (n.getName().equals(activeMethod.getName())) {
			int startline = activeMethod.getRange().get().begin.line;
			int endline = activeMethod.getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			recursiveMethods.computeIfAbsent(n.getNameAsString(), k -> new HashSet<Breakpoints>());
			recursiveMethods.get(methodName).add(new Breakpoints(className, methodName, startline, endline));
			
			if (methodCount.get(methodName) > 1) {
				arg.addAll(recursiveMethods.get(methodName));
				recursiveMethods.get(methodName).clear();
			}	
		}
	}
	
	
}
