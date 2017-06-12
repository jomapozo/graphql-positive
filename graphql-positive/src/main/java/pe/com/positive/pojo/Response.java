
package pe.com.positive.pojo;

/**
 * 
 * @Author: JomaPozo.
 * @Datecreation: 11 jun. 2017 20:12:56
 * @FileName: Response.java
 * @AuthorCompany: 
 * @version: 0.1
 * @Description: Pojo for restController response
 */
public class Response {

    private Long	 id;
    private String message;
    private Object obj;

    public Response(Long id, String content){
	  this.id = id;
	  this.message = content;
    }

    public Long getId(){
	  return id;
    }

    public void setId(Long id){
	  this.id = id;
    }

    public String getMessage(){
	  return message;
    }

    public void setMessage(String content){
	  this.message = content;
    }

    public Object getObj(){
	  return obj;
    }

    public void setObj(Object obj){
	  this.obj = obj;
    }

}
