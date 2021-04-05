package lt.vu.PSK1lab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Band.findAll", query = "select b from Band as b")
})
public class Band {
    private Long id;
    private String name;
    private Set<Festival> festivals;


    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(id, band.id) && Objects.equals(name, band.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "festivalband",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "festival_id"))
    public Set<Festival> getFestivals() {
        return festivals;
    }

    public void setFestivals(Set<Festival> festivals) {
        this.festivals = festivals;
    }
}
