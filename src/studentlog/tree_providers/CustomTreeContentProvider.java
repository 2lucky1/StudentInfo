package studentlog.tree_providers;

import java.util.List;
import org.eclipse.jface.viewers.ITreeContentProvider;
import studentlog.model.ITreeItem;

public class CustomTreeContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		List<?> list = ((ITreeItem) inputElement).getChildren();
		if (list != null) {
			return list.toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<?> list = ((ITreeItem) parentElement).getChildren();
		if (list != null) {
			return list.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return ((ITreeItem) element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((ITreeItem) element).hasChildren();
	}
}
