package pe.com.positive.pojo;

public class Response {

	private Long id;
	private String content;
	private Object obj;

	public Response(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
