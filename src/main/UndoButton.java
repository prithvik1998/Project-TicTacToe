public class UndoButton extends CommandButton {
        public UndoButton(String text) {
            super(text);
            this.setText("Undo");
        }

        @Override
        public void Execute() {
            if (careTaker.getMementoUndoListSize() > 0 && getP().checkCells() != true) {

                originator.getStateFromMemento(careTaker.getUndoMemento());
                careTaker.addToRedoList(careTaker.removeLastUndo());

                getP().setPane(' ', originator.getRow(), originator.getColumn());
            }
        }
}
