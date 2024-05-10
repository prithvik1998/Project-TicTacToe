public class CheckForColumnsHandler implements Handler {
    private Handler successor = null;

	public boolean handleRequest( SimplePane p, char token ) {
        boolean result = false;
        var c = p.getCells();
        for (int j = 0; j < 3; j++) {
			if (c[0][j].getToken() == token && c[1][j].getToken() == token && c[2][j].getToken() == token) {
				result = true;
                break;
			}
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
