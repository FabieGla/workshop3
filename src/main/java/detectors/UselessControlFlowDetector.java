package detectors;


import java.util.List;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UselessControlFlowDetector extends VoidVisitorAdapter <List<Breakpoints>> {

	private ClassOrInterfaceDeclaration activeClass;
	private MethodDeclaration activeMethod;
	
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		activeClass = n;
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodDeclaration n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		activeMethod = n;
		super.visit(n, arg);
	}

	@Override
	public void visit(IfStmt n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.getThenStmt().isBlockStmt() && n.getThenStmt().asBlockStmt().isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getThenStmt().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
		else if (n.getThenStmt().isEmptyStmt()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getThenStmt().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
		
	}

	@Override
	public void visit(ForStmt n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.getBody().isBlockStmt() && n.getBody().asBlockStmt().isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getBody().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		} else if (n.getBody().isEmptyStmt()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getBody().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
		
	}

	@Override
	public void visit(SwitchStmt n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.getEntries().isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
		
	}

	@Override
	public void visit(SwitchEntry n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
	}

	@Override
	public void visit(DoStmt n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.getBody().isBlockStmt() && n.getBody().asBlockStmt().isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getBody().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
	}

	@Override
	public void visit(WhileStmt n, List<Breakpoints> arg) {
		// TODO Auto-generated method stub
		super.visit(n, arg);
		
		if (n.getBody().isBlockStmt() && n.getBody().asBlockStmt().isEmpty()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getBody().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		} else if (n.getBody().isEmptyStmt()) {
			int startline = n.getRange().get().begin.line;
			int endline = n.getBody().getRange().get().end.line;
			String className = activeClass.getNameAsString();
			String methodName = activeMethod.getNameAsString();
			arg.add(new Breakpoints(className, methodName, startline, endline));
		}
	}
	
	

	
	
	
}
