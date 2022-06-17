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
@Table(name = "tNhaXuatBan", schema = "Library", catalog = "")
public class TNhaXuatBanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MaNhaXuatBan")
    private int maNhaXuatBan;
    @Basic
    @Column(name = "TenNhaXuatBan")
    private String tenNhaXuatBan;
    @Basic
    @Column(name = "DiaChi")
    private String diaChi;
    @Basic
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @OneToMany(mappedBy = "maNhaXuatBan")
    private List<TSachEntity> entityList;

    @Override
    public String toString() {
        return "TNhaXuatBanEntity{}";
    }
}
