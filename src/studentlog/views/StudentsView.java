package studentlog.views;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.services.ISourceProviderService;

import com.google.gson.Gson;

import studentlog.commands.OpenProfileHandler;
import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.Folder;
import studentlog.model.ITreeItem;
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

		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { EditorInputTransfer.getInstance() };
		treeViewer.addDragSupport(operations, transferTypes, new DragSourceListener() {

			@Override
			public void dragStart(DragSourceEvent event) {

			}

			@Override
			public void dragSetData(DragSourceEvent event) {
				if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)) { // if
					// support
					// EditorInputTransfer
					selection = treeViewer.getStructuredSelection();
					int i = 0;
					EditorInputTransfer.EditorInputData[] arrData = new EditorInputTransfer.EditorInputData[selection
							.size()];
					Iterator<?> iterator = selection.iterator();
					while (iterator.hasNext()) {
						StudentsEntry node = (StudentsEntry) iterator.next();
						if (node instanceof ITreeItem) {
							IEditorInput input = new StudentProfileEditorInput(((StudentsEntry) node).getName());
							arrData[i] = EditorInputTransfer.createEditorInputData(StudentProfileEditor.ID, input);
							i++;
						}
					}
					event.data = arrData;
				} // TODO Auto-generated method stub

			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				// TODO Auto-generated method stub

			}
		});

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

	// treeViewer.getTree().addMouseListener(listener);
	// DragSource ds = new DragSource(treeViewer.getTree(), DND.DROP_MOVE);
	// // DragSource ds = new DragSource(treeViewer.getTree(),
	// DND.DROP_MOVE);
	//
	// ds.setTransfer(new Transfer[] {TextTransfer.getInstance()});
	// ds.addDragListener(new DragSourceAdapter() {
	// public void dragSetData(DragSourceEvent event) {
	// IStructuredSelection selection = treeViewer.getStructuredSelection();
	// StudentsEntry firstElement =
	// (StudentsEntry)selection.getFirstElement();
	// Gson gson = new Gson();
	// String jsonStr = gson.toJson(firstElement);
	// event.data = jsonStr;
	// }
	// });
	// int operations = DND.DROP_COPY | DND.DROP_MOVE ;
	// Transfer[] transferTypes = new Transfer[] {
	// EditorInputTransfer.getInstance() };
	// // ds.setTransfer(transferTypes);
	// // ds.setTransfer(new Transfer[] {TextTransfer.getInstance()});
	// treeViewer.addDragSupport(operations, transferTypes, new
	// NodeDragListener(treeViewer));
	// // ds.addDragListener(new DragSourceAdapter() {
	// // public void dragSetData(DragSourceEvent event) {
	// // IStructuredSelection selection =
	// treeViewer.getStructuredSelection();
	// // StudentsEntry firstElement =
	// (StudentsEntry)selection.getFirstElement();
	// // Gson gson = new Gson();
	// // String jsonStr = gson.toJson(firstElement);
	// // event.data = jsonStr;
	// //
	// //// IStructuredSelection selection =
	// viewer.getStructuredSelection();
	// // int i=0;
	// // EditorInputTransfer.EditorInputData[] arrData=new
	// EditorInputTransfer.EditorInputData[selection.size()];
	// // Iterator<?> iterator=selection.iterator();
	// // while(iterator.hasNext()){
	// // TreeItem node=(TreeItem)iterator.next();
	// // if (node instanceof ITreeItem) {
	// // IEditorInput input = new StudentProfileEditorInput(((ITreeItem)
	// node).getName());
	// //
	// arrData[i]=EditorInputTransfer.createEditorInputData(StudentProfileEditor.ID,
	// input);
	// // i;
	// // }
	// // }
	// // event.data=arrData;
	// // }
	// // });
	//
	//
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

////	private class NodeDragListener implements DragSourceListener {
////
////		private final TreeViewer viewer;
////
////		public NodeDragListener(TreeViewer viewer) {
////			this.viewer = viewer;
////		}
////
////		@Override
////		public void dragStart(DragSourceEvent event) {
////			// TODO Autogenerated method stub
////			System.out.println("Start Drag");
////		}
////
////		@Override
////		public void dragSetData(DragSourceEvent event) {
////			if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)) { // if
////																						// support
////																						// EditorInputTransfer
////				IStructuredSelection selection = viewer.getStructuredSelection();
////				int i = 0;
////				EditorInputTransfer.EditorInputData[] arrData = new EditorInputTransfer.EditorInputData[selection
////						.size()];
////				Iterator<?> iterator = selection.iterator();
////				while (iterator.hasNext()) {
////					TreeItem node = (TreeItem) iterator.next();
////					if (node instanceof ITreeItem) {
////						IEditorInput input = new StudentProfileEditorInput(((ITreeItem) node).getName());
////						arrData[i] = EditorInputTransfer.createEditorInputData(StudentProfileEditor.ID, input);
////						i++;
////					}
////				}
////				event.data = arrData;
////			}
////		}
//
//		@Override
//		public void dragFinished(DragSourceEvent event) {
//			if (event.detail == DND.DROP_MOVE) {
//				System.out.println("Finished Drag");
//			}
//		}
//	}

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
