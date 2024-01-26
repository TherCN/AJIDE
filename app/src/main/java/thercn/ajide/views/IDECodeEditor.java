package thercn.ajide.views;
import io.github.rosemoe.sora.widget.CodeEditor;
import thercn.ajide.IDEActivity;
import thercn.ajide.utils.APPUtils;
import java.io.IOException;

public class IDECodeEditor extends CodeEditor {
	
	IDEActivity activity;
	String currentFile;
	
    public IDECodeEditor(IDEActivity context) {
		super(context);
		activity = context;
	}
    
	public void setFile(String filePath) throws IOException{
		setText(APPUtils.readFile(filePath));
		currentFile = filePath;
	}
	
    public String getCurrentFile() {
		return currentFile;
	}
	
	public void saveFile() throws IOException {
		APPUtils.writeTextToFile(currentFile, getText().toString());
	}
}
