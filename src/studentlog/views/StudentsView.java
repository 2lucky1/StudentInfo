package studentlog.views;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.google.gson.Gson;

import studentlog.model.Observer;
import studentlog.model.Root;
import studentlog.model.StudentsEntry;
import studentlog.model.TreeModel;
import studentlog.tree_providers.CustomTreeContentProvider;
import studentlog.tree_providers.CustomTreeLabelProvider;

public class StudentsView extends ViewPart implements Observer {

	public static final String ID = "studentlog.views.students";

	private TreeViewer treeViewer;

	private Root root;

	public StudentsView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeModel.getInstance().addObserver(this);
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		super.getSite().setSelectionProvider(treeViewer);
		treeViewer.setContentProvider(new CustomTreeContentProvider());
		treeViewer.setLabelProvider(new CustomTreeLabelProvider());
		root = TreeModel.getInstance().getRoot();
		treeViewer.setInput(root);
		
		DragSource ds = new DragSource(treeViewer.getTree(), DND.DROP_MOVE);

		ds.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		ds.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				IStructuredSelection selection = treeViewer.getStructuredSelection();
				StudentsEntry firstElement = (StudentsEntry)selection.getFirstElement();
				Gson gson = new Gson();
				String jsonStr = gson.toJson(firstElement);
		        event.data = jsonStr;
		      }
		    });
		
		
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@Override
	public void update(Root root) {
		treeViewer.setInput(root);

	}
	
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
}
