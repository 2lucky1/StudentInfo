package studentlog.views;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;

import studentlog.commands.OpenProfileHandler;
import studentlog.dnd.MyDragListener;
import studentlog.model.Folder;
import studentlog.model.Observer;
import studentlog.model.Root;
import studentlog.model.StudentsEntry;
import studentlog.model.StudentsGroup;
import studentlog.model.TreeModel;
import studentlog.tree_providers.CustomTreeContentProvider;
import studentlog.tree_providers.CustomTreeLabelProvider;

public class StudentsView extends ViewPart implements Observer {

	public static final String ID = "studentlog.views.students";

	private TreeViewer treeViewer;

	private Root root;

	private IStructuredSelection selection;

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

		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(treeViewer.getTree());
        treeViewer.getTree().setMenu(menu);
		getSite().registerContextMenu(menuManager, treeViewer);
		getSite().setSelectionProvider(treeViewer);
		
		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { EditorInputTransfer.getInstance() };
		treeViewer.addDragSupport(operations, transferTypes, new MyDragListener(treeViewer));
		
		treeViewer.addDoubleClickListener((event) -> {
			selection = treeViewer.getStructuredSelection();
			IHandlerService handlerService = getSite().getService(IHandlerService.class);
			Object entry = selection.getFirstElement();
			if (entry instanceof StudentsEntry) {
				try {
					handlerService.executeCommand(OpenProfileHandler.ID, null);
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}
			} else if(entry instanceof StudentsGroup || entry instanceof Folder){
				TreeItem treeItem = treeViewer.getTree().getSelection()[0];
				if(treeItem.getExpanded()){
					treeItem.setExpanded(false);
				}else{
					treeItem.setExpanded(true);
					treeViewer.refresh();
				}
				
			}
		});

	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	@Override
	public void update(Root root) {
		this.root = root;
		treeViewer.setInput(root);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
