package com.ba.boost.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "book_details")
public class BookDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "is_borrowed", nullable = false)
	private boolean isBorrowed;
	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(name = "book_borrow_date", nullable = false)
	private LocalDate bookBorrowDate;
	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(name = "book_return_date", nullable = false)
	private LocalDate bookReturnDate;
	
	
	@OneToOne(mappedBy = "bookDetail")
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	

}
