package controllers;

import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "HoaDonServlet", value = {"/receipt/index",
        "/receipt/sup",
        "/receipt/create",
        "/receipt/store",
        "/receipt/edit",
        "/receipt/update",
        "/receipt/delete",})
public class HoaDonServlet extends HttpServlet {
    private SachDao sachDao;
    private HoaDonDao hoaDonDao;
    private ChiTietHoaDonDao chiTietHoaDonDao;
    private DaiLyDao daiLyDao;
    private NhaXuatBanDao xuatBanDao;
    List<TChiTienHoaDonEntity> listHDTam;

    public HoaDonServlet() {
        this.chiTietHoaDonDao = new ChiTietHoaDonDao();
        this.hoaDonDao = new HoaDonDao();
        this.sachDao = new SachDao();
        this.xuatBanDao = new NhaXuatBanDao();
        this.daiLyDao = new DaiLyDao();
        this.listHDTam = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("sup")) {
            sup(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        }else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        }
    }
    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<TSachEntity> entityList = this.sachDao.all();
        List<TDaiLyEntity> List = this.daiLyDao.all();
        List<THoaDonEntity> listHD = this.hoaDonDao.all();
        request.setAttribute("dsDL",List);
        request.setAttribute("list",entityList);
        request.setAttribute("hd",listHD);
        request.setAttribute("listHDTam",this.listHDTam);
        request.setAttribute("view", "/views/admin/hoaDon/create.jsp");
        request.setAttribute("view1", "/views/admin/hoaDon/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    private void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
//        request.setAttribute("view", "/Views/user/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check = 0;
        if (listHDTam.size()>0){
            for (TChiTienHoaDonEntity ct:this.listHDTam) {
                if (ct.getMaSach().getMaSach()==id){
                    check++;
                    ct.setSoLuong(ct.getSoLuong()+1);
                }
            }
        }
        if (check==0){
            TChiTienHoaDonEntity tChiTienHoaDon = new TChiTienHoaDonEntity();
            TSachEntity sachEntity = this.sachDao.findByID(id);
            tChiTienHoaDon.setSoLuong(1);
            tChiTienHoaDon.setGhiChu("");
            tChiTienHoaDon.setMaSach(sachEntity);
            this.listHDTam.add(tChiTienHoaDon);
        }
        response.sendRedirect("/receipt/index");
    }

    private void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException, IOException {


    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            List<String> gichu = Arrays.asList(request.getParameterValues("ghiChu"));
            for (TChiTienHoaDonEntity o :this.listHDTam) {
                for (String g: gichu) {
                    if (this.listHDTam.indexOf(o)==gichu.indexOf(g)){
                        o.setGhiChu(g);
                    }
                }
            }
            THoaDonEntity hoaDon = new THoaDonEntity();
            String maDaiLy = request.getParameter("DaiLy");
            TDaiLyEntity daiLy = this.daiLyDao.findByID(Integer.parseInt(maDaiLy));
            hoaDon.setMaDaiLy(daiLy);
            hoaDon.setNgayLapHoaDon( new Date(new java.util.Date().getTime()));
            THoaDonEntity soHD = this.hoaDonDao.create(hoaDon);
            for (TChiTienHoaDonEntity o :this.listHDTam) {
                o.setSoHoaDon(soHD);
                this.chiTietHoaDonDao.create(o);
            }
            session.setAttribute("message", "Thêm Mới Thành Công");
            this.listHDTam.clear();
            response.sendRedirect("/receipt/index");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/receipt/index");
            e.printStackTrace();
        }
    }

    private void sup(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check =0;
        TChiTienHoaDonEntity entity = new TChiTienHoaDonEntity();
        if (listHDTam.size()>0){
            for (TChiTienHoaDonEntity ct:this.listHDTam) {
                if (ct.getMaSach().getMaSach()==id){
                    ct.setSoLuong(ct.getSoLuong()-1);
                    if (ct.getSoLuong()<=0){
                        check++;
                        entity=ct;
                    }
                }
            }
        }
        if (check!=0){
            this.listHDTam.remove(entity);
        }
        response.sendRedirect("/receipt/index");
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            THoaDonEntity entity = this.hoaDonDao.findByID(id);
            List<TChiTienHoaDonEntity> listCT = entity.getEntityList();
            this.chiTietHoaDonDao.deleteList(listCT);
            this.hoaDonDao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/receipt/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/receipt/index");
            e.printStackTrace();
        }
    }
}
