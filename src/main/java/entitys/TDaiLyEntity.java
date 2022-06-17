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
@Table(name = "tDaiLy", schema = "Library", catalog = "")
public class TDaiLyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MaDaiLy")
    private int maDaiLy;
    @Basic
    @Column(name = "TenDaiLy")
    private String tenDaiLy;
    @Basic
    @Column(name = "TenChuDaiLy")
    private String tenChuDaiLy;
    @Basic
    @Column(name = "DiaChi")
    private String diaChi;
    @Basic
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @OneToMany(mappedBy = "maDaiLy")
    private List<THoaDonEntity> entityList;

    @Override
    public String toString() {
        return "TDaiLyEntity{}";
    }
}
