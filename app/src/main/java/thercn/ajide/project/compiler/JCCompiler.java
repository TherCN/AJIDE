package thercn.ajide.project.compiler;
import android.util.Log;
import com.sun.tools.javac.api.JavacTool;
import com.sun.tools.javac.util.Context;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import thercn.ajide.utils.APPUtils;


public class JCCompiler extends Thread {

	List<String> args;
	Map<String,String> sourceList;
	Context javacContext;
	public JCCompiler(List<String> args) {
		this.args = args;
		this.sourceList = new HashMap<>();
	}

	public JCCompiler addSource(String className, String str) {
		sourceList.put(className, str);
		return this;
	}

	public JCCompiler addSources(Map<String,String> sources) {
		sourceList.putAll(sources);
		return this;
	}

	public JCCompiler addSourceFromFile(String path) throws IOException {
		sourceList.put(APPUtils.getFileName(path).replace(".java", ""), APPUtils.readFile(path));
		return this;
	}

	public JCCompiler addSourceFromFiles(List<String> paths) throws IOException {
		for (String path : paths) {
			sourceList.put(APPUtils.getFileName(path).replace(".java", ""), APPUtils.readFile(path));
		}
		return this;
	}

	public boolean hasFile(String filePath) {
		return sourceList.get(filePath.replace(".java","")) != null;
	}
	
	public JCCompiler setJavaVersion(int version) {
		args.add("-source");
		args.add(String.valueOf(version));
		args.add("-target");
		args.add(String.valueOf(version));
		return this;
	}

	public List<Diagnostic<? extends JavaFileObject>> compile() {
		JavaCompiler compiler = JavacTool.create();
		DiagnosticCollector diagnostics = new DiagnosticCollector();
		StandardJavaFileManager stdfileManager = compiler.getStandardFileManager(diagnostics, null, null);

		List<JavaFileObject> compilationUnits = new ArrayList<>();
		for (String i : sourceList.keySet()) {
			JavaFileObject obj = new InMemoryJavaFileObject(i, sourceList.get(i));
			compilationUnits.add(obj);
		}	
		StringWriter writer = new StringWriter();
		JavaCompiler.CompilationTask task = compiler.getTask(writer, stdfileManager, diagnostics, args, null, compilationUnits);
        // 编译 Java 源代码
        Boolean success = task.call();
		Log.e("编译结果" , success.toString() + writer.toString());

        // 获取诊断信息
		result = diagnostics.getDiagnostics();
        return diagnostics.getDiagnostics();
	}

	List<Diagnostic<? extends JavaFileObject>> result;

	@Override
	public void start() {
		compile();
		super.start();
	}

	public List<Diagnostic<? extends JavaFileObject>> getOutput() {
		return result;
	}
	class InMemoryJavaFileObject extends SimpleJavaFileObject {
		private String contents;

		protected InMemoryJavaFileObject(String className, String contents) {
			super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.contents = contents;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return contents;
		}
	}
}
