
public class AdvancedPane extends DecoratorPane {

	AdvancedPane(Component pane) {
		super(pane);
	}

	@Override
	public void decorate() {
		super.decorate();

		NewButton newBtn = new NewButton("New");
		UndoButton undoBtn = new UndoButton("Undo");
		RedoButton redoBtn = new RedoButton("Redo");

		super.addButtons(newBtn, undoBtn, redoBtn);
	}
}
