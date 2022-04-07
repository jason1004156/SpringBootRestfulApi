package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@Column(name = "ISBN", unique = true, nullable = false, length = 13)
	@Comment("國際標準書號")
	private String ISBN;
	
	@Column(name = "Name")
	@Comment("書名")
	private String Name;
	
	@Column(name = "Author")
	@Comment("作者")
	private String Author;
	
	@Column(name = "Translator")
	@Comment("譯者")
	private String Translator;
	
	@Column(name = "Publisher")
	@Comment("出版商")
	private String Publisher;
	
	@Column(name = "PublicationDate")
	@Comment("出版日期")
	private Date PublicationDate;
	
	@Column(name = "Price")
	@Comment("價錢")
	private double Price;
	
	
	
	
}
