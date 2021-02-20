package ru.traxel.traxel.models;

import javax.persistence.*;

@Entity
@Table(name = "\"Music\"", schema = "public", catalog = "postgres")
public class Music {
    private Long id;
    private String authorname;
    private Integer year;
    private String title;
    private Integer price;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "authorname")
    public String getAuthorName() {
        return authorname;
    }

    public void setAuthorName(String authorname) {
        this.authorname = authorname;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Music that = (Music) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (authorname != null ? !authorname.equals(that.authorname) : that.authorname != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorname != null ? authorname.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
