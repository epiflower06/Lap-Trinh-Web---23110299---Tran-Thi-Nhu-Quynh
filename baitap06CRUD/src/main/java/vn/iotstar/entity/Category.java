package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
//@AllArgsConstructor
//@NoArgsContructor
//@Data
@Table (name ="categories")
@NamedQuery(name = "Category.findAll", query ="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryId")
	private int categoryID;

	@Column(name="Categoryname", columnDefinition ="NVARCHAR(200) NOT NULL")
	private String categoryname;

	@Column(name="Images", columnDefinition ="NVARCHAR(MAX) NULL")
	private String images;

	@Column(name="Status")
	private boolean status;

	// Quan hệ với User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	public Category() {}

	// Getters/Setters
	public int getCategoryId() { return this.categoryID; }
	public void setCategoryId(int categoryId) { this.categoryID= categoryId; }

	public String getCategoryname() { return categoryname; }
	public void setCategoryname(String categoryname) { this.categoryname = categoryname; }

	public String getImages() { return images; }
	public void setImages(String images) { this.images = images; }

	public boolean isStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
}