package thercn.ajide.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.activities.IDEActivity;
import thercn.ajide.activities.ProjectActivity;

public class ProjectItemViewAdapter<T> extends RecyclerView.Adapter<ProjectItemViewAdapter.ViewHolder> {

	ProjectActivity activity;
	List<String> allProjectPaths;
	
	public ProjectItemViewAdapter(ProjectActivity activity,List<String> projectPaths) {
		this.activity = activity;
		allProjectPaths = projectPaths;
	}
	@Override
	public void onBindViewHolder(ProjectItemViewAdapter.ViewHolder vH, final int p) {
		vH.layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(activity,IDEActivity.class);
					intent.putExtra("path",allProjectPaths.get(p));
					activity.startActivity(intent);
				}
			});
		vH.projectIcon.setImageResource(R.drawable.ic_java);
		vH.projectName.setText(new File(allProjectPaths.get(p)).getName());
		vH.projectPath.setText(allProjectPaths.get(p));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int p) {
		View view = LayoutInflater.from(activity).inflate(R.layout.project_list_item, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public int getItemCount() {
		return allProjectPaths.size();
	}
    
    class ViewHolder extends RecyclerView.ViewHolder{
		
		CardView layout;
		ImageView projectIcon;
		TextView projectName;
		TextView projectPath;
		
		ViewHolder(View v) {
			super(v);
			layout = v.findViewById(R.id.projectLayout);
			projectIcon = v.findViewById(R.id.project_icon);
			projectName = v.findViewById(R.id.project_name);
			projectPath = v.findViewById(R.id.project_path);
			
		}
	}
    
}
