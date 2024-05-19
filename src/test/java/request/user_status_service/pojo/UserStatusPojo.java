package request.user_status_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStatusPojo implements Serializable {
	private Integer id;
	private String name;
	private String description;

	public UserStatusPojo() {
	}

	public UserStatusPojo(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public void setId(int id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"UserStatusPojo{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}