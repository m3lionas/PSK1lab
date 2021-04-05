package lt.vu.PSK1lab.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member {
    private Long id;
    private String name;
    private Band band;

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
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "BAND_ID", referencedColumnName = "ID")
    public Band getBand() {
        return band;
    }

    public void setBand(Band bandByBandId) {
        this.band = band;
    }
}
