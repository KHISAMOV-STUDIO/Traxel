package ru.traxel.traxel.models;

import javax.persistence.*;

@Entity
@Table(name = "Cart", schema = "public", catalog = "postgres")
public class Cart {
    private Long id;
    private String customerEmail;

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
    @Column(name = "customerEmail")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart that = (Cart) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customerEmail != null ? !customerEmail.equals(that.customerEmail) : that.customerEmail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customerEmail != null ? customerEmail.hashCode() : 0);
        return result;
    }
}
