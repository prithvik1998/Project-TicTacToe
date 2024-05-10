public class RedoButton extends CommandButton {
        public RedoButton(String text) {
            super(text);
            this.setText("Redo");
        }

        @Override
        public void Execute() {

            if (careTaker.getMementoRedoListSize() > 0) {

                originator.getStateFromMemento(careTaker.getRedoMemento());
                careTaker.addToUndoList(careTaker.removeLastRedo());

                char token = originator.getShape() == 'X' ? 'O' : 'X';

                getP().setPane(token, originator.getRow(), originator.getColumn());
            }
        }
}
