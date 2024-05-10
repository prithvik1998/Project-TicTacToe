public class CheckForRowsHandler implements Handler {
    private Handler successor = null;

	public boolean handleRequest( SimplePane p, char token ) {
        boolean result = false;
        var c = p.getCells();
        for (int i = 0; i < 3; i++) {
            if (c[i][0].getToken() == token && c[i][1].getToken() == token && c[i][2].getToken() == token) {
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
