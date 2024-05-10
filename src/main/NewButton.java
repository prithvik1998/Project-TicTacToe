public class NewButton extends CommandButton {
        public NewButton(String text) {
            super(text);
            this.setText("New");
        }

        @Override
        public void Execute() {
            getP().newGame();
        }
}