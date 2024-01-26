package thercn.ajide.adapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.views.IDECodeEditor;
import thercn.ajide.utils.APPUtils;

public class FileEditAdapter extends PagerAdapter {

	List<IDECodeEditor> openedFiles = new ArrayList<>();

    public int getCount() {
        return this.openedFiles.size();
    }

	@Override
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.openedFiles.get(i));
        return this.openedFiles.get(i);
    }

	@Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

	@Override
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View)obj);
    }

    public List<IDECodeEditor> getAllFileEditor() {
        return this.openedFiles;
    }

    public void addView(IDECodeEditor textEditor) {
        this.openedFiles.add(textEditor);
        notifyDataSetChanged();
    }

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void removeView(int index) {
		try {
			this.openedFiles.remove(index);
		} catch (IndexOutOfBoundsException e) {
			if (index > 0) {
				this.openedFiles.remove(index - 1);
			} else if (index <= 0) {
				this.openedFiles.remove(0);
			}

		}
		notifyDataSetChanged();
	}

	public void removeAllView() {
		this.openedFiles.clear();
		notifyDataSetChanged();
	}

	public IDECodeEditor getCurrentEditor(int index) {
		return openedFiles.get(index);
	}

	@Override         
	public CharSequence getPageTitle(int position) {
		return APPUtils.getFileName(getCurrentEditor(position).getCurrentFile());
	}


}
