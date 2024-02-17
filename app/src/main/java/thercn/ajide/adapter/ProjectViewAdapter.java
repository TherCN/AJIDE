package thercn.ajide.adapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.views.IDECodeEditor;

public class ProjectViewAdapter extends PagerAdapter{
    List<View> views = new ArrayList<>();

    public int getCount() {
        return this.views.size();
    }
	
	public ProjectViewAdapter(){};

	@Override
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.views.get(i));
        return this.views.get(i);
    }

	@Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

	@Override
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View)obj);
    }

    public List<View> getAllView() {
        return this.views;
    }

    public void addView(View textEditor) {
        this.views.add(textEditor);
        notifyDataSetChanged();
    }

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void removeView(int index) {
		try {
			this.views.remove(index);
		} catch (IndexOutOfBoundsException e) {
			if (index > 0) {
				this.views.remove(index - 1);
			} else if (index <= 0) {
				this.views.remove(0);
			}

		}
		notifyDataSetChanged();
	}

	public void removeAllView() {
		this.views.clear();
		notifyDataSetChanged();
	}
	
    
    
}
