package cn.tuyucheng.acl.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "system_message")
public class NoticeMessage {

	@Id
	@Column
	private Integer id;
	@Column
	private String content;
}