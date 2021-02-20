package ru.traxel.traxel.models;

import javax.persistence.*;

@Entity
@Table(name = "\"Cart\"", schema = "public", catalog = "postgres")
public class Cart {
    private Long id;
    private String customeremail;

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
    @Column(name = "customeremail")
    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart that = (Cart) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customeremail != null ? !customeremail.equals(that.customeremail) : that.customeremail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customeremail != null ? customeremail.hashCode() : 0);
        return result;
    }
}
