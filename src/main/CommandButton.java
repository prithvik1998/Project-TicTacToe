import javafx.scene.control.Button;

class CommandButton extends Button implements ICommand {
	private SimplePane p;
	static Originator originator = new Originator();
	static CareTaker careTaker = new CareTaker();

	public CommandButton(String text) {
		super(text);
	}

	public void setPane(SimplePane myPane) {
		setP(myPane);
	}

	public void Execute() {
	}

	public SimplePane getP() {
		return p;
	}

	public void setP(SimplePane p) {
		this.p = p;
	}

}

