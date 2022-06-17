package Dao;

import entitys.THoaDonEntity;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HoaDonDao {
    private EntityManager em;
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    public HoaDonDao() {
        this.em = JpaUtil.getEntityManager();
    }
    public THoaDonEntity create(THoaDonEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }

    public THoaDonEntity update(THoaDonEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }
    public THoaDonEntity delete(THoaDonEntity entity) throws Exception{
        try {
            this.em.getTransaction().begin();
            this.em.remove(entity);
            this.em.getTransaction().commit();
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<THoaDonEntity> all() {
        String jpql = "SELECT obj from THoaDonEntity obj";
        TypedQuery<THoaDonEntity> query = this.em.createQuery(jpql, THoaDonEntity.class);
        List<THoaDonEntity> list = query.getResultList();
        return list;
    }

    public THoaDonEntity findByID(int id) {
        THoaDonEntity entity = this.em.find(THoaDonEntity.class, id);
        return entity;
    }
    public void xuatEX() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");

        List<THoaDonEntity> list = all();

        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("EmpNo");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("EmpNo");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Salary");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Grade");
        cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Bonus");
        cell.setCellStyle(style);

        // Data
        for (THoaDonEntity emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(emp.getSoHoaDon());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getNgayLapHoaDon());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getMaDaiLy().getMaDaiLy());
            // Grade (D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(emp.getMaDaiLy().getTenDaiLy());
            // Bonus (E)
            String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
            cell = row.createCell(4, CellType.FORMULA);
            cell.setCellFormula(formula);
        }
        File file = new File("/home/thanhkali/Downloads/quanLyThuVien/employee.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Created file: " + file.getAbsolutePath());

}
}
