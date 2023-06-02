package com.example.demo1;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name="tuto")
class Tuto {

	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id", updatable = false, nullable = false)
	    private Long id;
  private String description;
  private String title;

  Tuto() {}

  Tuto(String description, String title) {

    this.description = description;
    this.title = title;
  }

  public Long getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTitle() {
    return this.title;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Tuto))
      return false;
    Tuto Tuto = (Tuto) o;
    return Objects.equals(this.id, Tuto.id) && Objects.equals(this.description, Tuto.description)
        && Objects.equals(this.title, Tuto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.description, this.title);
  }

  @Override
  public String toString() {
    return "Tuto{" + "id=" + this.id + ", description='" + this.description + '\'' + ", title='" + this.title + '\'' + '}';
  }
}