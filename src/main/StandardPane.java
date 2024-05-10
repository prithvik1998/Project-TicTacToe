
public class StandardPane extends DecoratorPane {

	StandardPane(Component pane) {
		super(pane);
	}

	@Override
	public void decorate() {
		super.decorate();
		NewButton newbtn = new NewButton("New");
		super.addButtons(newbtn);
	}
}
