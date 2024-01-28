package thercn.ajide.views;

import android.view.View;
import android.widget.Button;
import thercn.ajide.R;

public class FileOperationView {
    View view;
	Button move;
	Button copy;
	Button delete;
	String[] args;
    public FileOperationView(View view) {
		this.view = view;
		delete = view.findViewById(R.id.delete);
		copy = view.findViewById(R.id.copy);
		move = view.findViewById(R.id.move);
	}
	
	public void setParameter(String... args) {
		this.args = args;
	}
    
	public void setOnClickEvent() {
		delete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					
				}
			});
	}
}
