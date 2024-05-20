package request.user_group_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGroupPojo {
    private Integer group_type_id;
    private Long organization_id;
    private String name;
    private String description;
    private String short_name;
    private Integer id;

    public UserGroupPojo() {
    }

    public UserGroupPojo(Integer group_type_id, Long organization_id, String name, String description, String short_name, Integer id) {
        this.group_type_id = group_type_id;
        this.organization_id = organization_id;
        this.name = name;
        this.description = description;
        this.short_name = short_name;
        this.id = id;
    }

    public void setGroup_type_id(Integer group_type_id) {
        this.group_type_id = group_type_id;
    }

    public Integer getGroup_type_id() {
        return group_type_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "UGroupPojo{" +
                        "group_type_id = '" + group_type_id + '\'' +
                        ",organization_id = '" + organization_id + '\'' +
                        ",name = '" + name + '\'' +
                        ",description = '" + description + '\'' +
                        ",short_name = '" + short_name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
