package jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dots", schema = "s285606", catalog = "studs")
public class DotsEntity {
    @Id
    private long id;
    private Float x;
    private Float y;
    private Float r;
    private String hit;

    @Basic
    @Column(name = "x", nullable = true, precision = 0)
    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y", nullable = true, precision = 0)
    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Basic
    @Column(name = "r", nullable = true, precision = 0)
    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    @Basic
    @Column(name = "hit", nullable = true, length = -1)
    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DotsEntity that = (DotsEntity) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(r, that.r) &&
                Objects.equals(hit, that.hit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, hit);
    }
}
