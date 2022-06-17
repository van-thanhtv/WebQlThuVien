package controllers;

import Dao.HoaDonDao;
import Dao.NhaXuatBanDao;
import Dao.SachDao;
import entitys.TDaiLyEntity;
import entitys.TNhaXuatBanEntity;
import entitys.TSachEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SachServlet", value = {"/book/index",
        "/book/show",
        "/book/create",
        "/book/store",
        "/book/edit",
        "/book/update",
        "/book/delete",})
public class SachServlet extends HttpServlet {
    private SachDao sachDao;
    private NhaXuatBanDao xuatBanDao;
    public SachServlet() {
        this.sachDao = new SachDao();
        this.xuatBanDao = new NhaXuatBanDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
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
        } else if (uri.contains("show")) {
            show(request, response);
        }
    }
    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<TSachEntity> entityList = this.sachDao.all();
        List<TNhaXuatBanEntity> List = this.xuatBanDao.all();
        request.setAttribute("dspublisher",List);
        request.setAttribute("list",entityList);
        request.setAttribute("view", "/views/admin/sach/create.jsp");
        request.setAttribute("view1", "/views/admin/sach/table.jsp");
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
        try {
            String ma = request.getParameter("id");
            TSachEntity o = this.sachDao.findByID(Integer.parseInt(ma));
            List<TSachEntity> entityList = this.sachDao.all();
            List<TNhaXuatBanEntity> List = this.xuatBanDao.all();
            request.setAttribute("dspublisher",List);
            request.setAttribute("list",entityList);request.setAttribute("o",o);
            request.setAttribute("view", "/views/admin/sach/edit.jsp");
            request.setAttribute("view1", "/views/admin/sach/table.jsp");
            request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException, IOException {
        HttpSession session = request.getSession();
        try {
            String ma = request.getParameter("id");
            TSachEntity entity = new TSachEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setMaSach(Integer.parseInt(ma));
            String maXB = request.getParameter("NhaXuatBan");
            TNhaXuatBanEntity xb = this.xuatBanDao.findByID(Integer.parseInt(maXB));
            entity.setMaNhaXuatBan(xb);
            this.sachDao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/book/index");
        } catch (Exception e) {
            response.sendRedirect("/book/index");
            session.setAttribute("error", "Cập Nhật Thất Bại");
            e.printStackTrace();
        }

    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            TSachEntity entity = new TSachEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            String maXB = request.getParameter("NhaXuatBan");
            TNhaXuatBanEntity xb = this.xuatBanDao.findByID(Integer.parseInt(maXB));
            entity.setMaNhaXuatBan(xb);
            this.sachDao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            response.sendRedirect("/book/index");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/book/index");
            e.printStackTrace();
        }
    }

    private void show(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HoaDonDao h = new HoaDonDao();
        h.xuatEX();
        response.sendRedirect("/book/index");
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            TSachEntity entity = this.sachDao.findByID(id);
            this.sachDao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/book/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/book/index");
            e.printStackTrace();
        }
    }
}
