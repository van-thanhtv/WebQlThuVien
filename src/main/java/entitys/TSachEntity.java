package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tSach", schema = "Library", catalog = "")
public class TSachEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MaSach")
    private int maSach;
    @Basic
    @Column(name = "TenSach")
    private String tenSach;
    @Basic
    @Column(name = "TenTacGia")
    private String tenTacGia;
    @Basic
    @Column(name = "GiaBan")
    private Double giaBan;
    @Basic
    @Column(name = "GiaBanChoDaiLy")
    private Double giaBanChoDaiLy;
    @ManyToOne
    @JoinColumn(name = "MaNhaXuatBan")
    private TNhaXuatBanEntity maNhaXuatBan;
    @Basic
    @Column(name = "SoTrang")
    private Integer soTrang;
    @OneToMany(mappedBy = "maSach")
    private List<TChiTienHoaDonEntity> entityList;

    @Override
    public String toString() {
        return "TSachEntity{}";
    }
}
