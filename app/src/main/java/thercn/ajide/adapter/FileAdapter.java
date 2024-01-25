package thercn.ajide.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.IDEActivity;
import java.io.IOException;
import thercn.ajide.utils.TLog;

public class FileAdapter<T> extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

	List<File> files;
	IDEActivity context;
	RecyclerView view;
	
	public FileAdapter(IDEActivity context, List<File> files, RecyclerView view) {
		this.context = context;
		this.files = files;
		this.view = view;
	}

	public File getItem(int position) {
		return files.get(position);
	}

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.file_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileAdapter.ViewHolder holder, int position) {

        final File selectedFile = files.get(position);
		if (position == 0) {
			holder.fileName.setText("..");
		} else {
			holder.fileName.setText(selectedFile.getName());
		}


        if (selectedFile.isDirectory()) {
			Bitmap fileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_folder);
			holder.fileIcon.setImageBitmap(fileImage);
		} else {
			Bitmap fileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_file);
			holder.fileIcon.setImageBitmap(fileImage);
		}

		long lastModifiedTime = selectedFile.lastModified();
        Date lastModifiedDate = new Date(lastModifiedTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(lastModifiedDate);

		String permission = "";
		if (selectedFile.canRead()) {
			permission = permission + "r";
		} else {
			permission = permission + "-";
		}
		if (selectedFile.canWrite()) {
			permission = permission + "w";
		} else {
			permission = permission + "-";
		}
		if (selectedFile.canExecute()) {
			permission = permission + "x";
		} else {
			permission = permission + "-";
		}
		holder.fileTime.setText(formattedDate + " " + permission);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
		
				@Override
				public void onClick(View v) {
					if (selectedFile.getParentFile() == null) {
						return;
					}
					if (selectedFile.isDirectory()) {
						List<File> newFiles = new ArrayList<File>();
						newFiles.add(selectedFile.getParentFile());
						File[] newDir = APPUtils.getFiles(selectedFile.getAbsolutePath());
						for (int i = 0; i < newDir.length; i++) {
							newFiles.add(newDir[i]);
						}
						view.setAdapter(new FileAdapter<File>(context, newFiles, view));
					} else {
						addEditFile(selectedFile);
					}
				}
			});

		holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					View menu = context.getLayoutInflater().inflate(R.layout.file_menu, null);
					AlertDialog dialog = new AlertDialog.Builder(context)
						.setView(menu)
						.create();
                    dialog.show();

					return false;
				}
			});

    }
	
	@Override
    public int getItemCount() {
        return files.size();
    }
	
	public void addEditFile(File file) {
		context.getLayout().addFileTab(file.getAbsolutePath());
	}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fileName;
		ImageView fileIcon;
		TextView fileTime;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_list_name);
            fileIcon = itemView.findViewById(R.id.file_list_image);
			fileTime = itemView.findViewById(R.id.file_list_time);
        }
    }

}
