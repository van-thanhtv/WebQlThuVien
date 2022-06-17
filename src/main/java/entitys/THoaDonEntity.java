package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tHoaDon", schema = "Library", catalog = "")
public class THoaDonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SoHoaDon")
    private int soHoaDon;
    @Basic
    @Column(name = "NgayLapHoaDon")
    private Date ngayLapHoaDon;
    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private TDaiLyEntity maDaiLy;
    @OneToMany(mappedBy = "soHoaDon")
    private List<TChiTienHoaDonEntity> entityList;

    @Override
    public String toString() {
        return "THoaDonEntity{}";
    }
}
