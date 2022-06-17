package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tChiTienHoaDon", schema = "Library", catalog = "")
public class TChiTienHoaDonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "sohoadon")
    private THoaDonEntity soHoaDon;
    @ManyToOne
    @JoinColumn(name = "MaSach")
    private TSachEntity maSach;
    @Basic
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Basic
    @Column(name = "GhiChu")
    private String ghiChu;
}
