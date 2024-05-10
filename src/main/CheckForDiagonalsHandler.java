public class CheckForDiagonalsHandler implements Handler {
    private Handler successor = null;

	public boolean handleRequest( SimplePane p, char token ) {
        boolean result = false;
        var c = p.getCells();
        if ((c[0][0].getToken() == token && c[1][1].getToken() == token && c[2][2].getToken() == token) ||
        (c[0][2].getToken() == token && c[1][1].getToken() == token && c[2][0].getToken() == token)) {
            result = true;
        }
        if ( result )
        {
            return result;
        }
        else
        {
            if ( successor != null )
                return successor.handleRequest(p, token);
            else
                return result;
        }
	}

	public void setSuccessor(Handler next) {
        this.successor = next ;
	}
}
