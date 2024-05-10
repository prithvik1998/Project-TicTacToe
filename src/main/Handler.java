public interface Handler {

	boolean handleRequest(SimplePane p, char token);
	void setSuccessor(Handler next);

}