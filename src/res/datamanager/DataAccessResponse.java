package res.datamanager;

public class DataAccessResponse {
	public boolean status;
	public ResponseType type;
	public Object response;
	public DataAccessResponse(boolean status, ResponseType type, Object response) {
		super();
		this.status = status;
		this.type = type;
		this.response = response;
	}
	public boolean getStatus() {
		return status;
	}
	public ResponseType getType() {
		return type;
	}
	public Object getResponse() {
		return response;
	}
}