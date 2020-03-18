package com.liu.utils.security;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
public class PassportUser implements Serializable {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;;

	private Long id;

	private String name;

	private String email;

	private Integer type;

	private List<Long> clazzIds;

	private List<Long> collegeIds;

	private List<Long> instituteIds;

	private List<Long> departmentIds;

	/**
	 * 判断是否有资源
	 */
	public boolean hasResource() {
		return CollectionUtils.isNotEmpty(this.collegeIds)
				|| CollectionUtils.isNotEmpty(this.instituteIds)
				|| CollectionUtils.isNotEmpty(this.departmentIds)
				|| CollectionUtils.isNotEmpty(this.clazzIds);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Long> getClazzIds() {
		return clazzIds;
	}

	public void setClazzIds(List<Long> clazzIds) {
		this.clazzIds = clazzIds;
	}

	public List<Long> getCollegeIds() {
		return collegeIds;
	}

	public void setCollegeIds(List<Long> collegeIds) {
		this.collegeIds = collegeIds;
	}

	public List<Long> getInstituteIds() {
		return instituteIds;
	}

	public void setInstituteIds(List<Long> instituteIds) {
		this.instituteIds = instituteIds;
	}

	public List<Long> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<Long> departmentIds) {
		this.departmentIds = departmentIds;
	}
}
