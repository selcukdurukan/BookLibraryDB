package com.ba.boost.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	/*
	 * CASCADE ALL ÇÜNKÜ BİR BOOK SİLDİGİMİZDE, GUNCELLEDİGİMİZDE VS. ONE-TO-ONE İLİŞKİDE ONUN DETAIL BİLGİSİNİN DE SİLİNMESİNİ VS. İSTERİZ.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private BookDetail bookDetail;
	
	@ManyToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student activeStudent;

}
