package detectors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class Driver {
	private static final String FILE_PATH = "src/main/java/testcode/Calculator.java";
	
	public static void main(String args []) {
		try {
			CompilationUnit cu = JavaParser.parse(new FileInputStream(FILE_PATH));
			VoidVisitor<List<Breakpoints>> ucfd = new UselessControlFlowDetector();
			VoidVisitor<HashSet<Breakpoints>> rd = new RecursionDetector();
			List<Breakpoints> ucfdBps = new ArrayList<Breakpoints>();
			HashSet<Breakpoints> rdBps = new LinkedHashSet<Breakpoints>();
			System.out.println("Useless Control Flows:");
			ucfd.visit(cu, ucfdBps);
			ucfdBps.forEach(bp -> {
				System.out.println(bp.toString());
			});
			System.out.println("Polymorphic Recursions:");
			rd.visit(cu, rdBps);
			rdBps.forEach(bp -> {
				System.out.println(bp.toString());
			}); 

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
