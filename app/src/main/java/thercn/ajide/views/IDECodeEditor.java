package thercn.ajide.views;
import android.content.Context;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.IOException;
import thercn.ajide.activities.IDEActivity;
import thercn.ajide.utils.APPUtils;

public class IDECodeEditor extends CodeEditor {
	
	Context activity;
	String currentFile;
	
    public IDECodeEditor(Context context) {
		super(context);
		activity = context;
		
	}
    
	public void setFile(String filePath) throws IOException{
		setText(APPUtils.readFile(filePath));
		currentFile = filePath;
	}
	
	public interface OnSetTextListener {
		void onSetText(String text);
	}
	
    public String getCurrentFile() {
		return currentFile;
	}
	
	public void saveFile() throws IOException {
		APPUtils.writeTextToFile(currentFile, getText().toString());
	}
}
