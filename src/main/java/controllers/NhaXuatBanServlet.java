package controllers;

import Dao.NhaXuatBanDao;
import entitys.TDaiLyEntity;
import entitys.TNhaXuatBanEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NhaXuatBanServlet", value = {"/publisher/index",
        "/publisher/show",
        "/publisher/create",
        "/publisher/store",
        "/publisher/edit",
        "/publisher/update",
        "/publisher/delete",})
public class NhaXuatBanServlet extends HttpServlet {
    private NhaXuatBanDao dao;
    public NhaXuatBanServlet() {
        this.dao= new NhaXuatBanDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("show")) {
            show(request, response);
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
        List<TNhaXuatBanEntity> entityList = this.dao.all();
        request.setAttribute("list",entityList);
        request.setAttribute("view", "/views/admin/nhaXuatBan/create.jsp");
        request.setAttribute("view1", "/views/admin/nhaXuatBan/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    private void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("id");
            TNhaXuatBanEntity o = this.dao.findByID(Integer.parseInt(ma));
            List<TNhaXuatBanEntity> entityList = this.dao.all();
            request.setAttribute("list",entityList);request.setAttribute("o",o);
            request.setAttribute("view", "/views/admin/nhaXuatBan/edit.jsp");
            request.setAttribute("view1", "/views/admin/nhaXuatBan/table.jsp");
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
            TNhaXuatBanEntity entity = new TNhaXuatBanEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setMaNhaXuatBan(Integer.parseInt(ma));
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/publisher/index");
        } catch (Exception e) {
            response.sendRedirect("/publisher/index");
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
            TNhaXuatBanEntity entity = new TNhaXuatBanEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            response.sendRedirect("/publisher/index");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/publisher/index");
            e.printStackTrace();
        }
    }

    private void show(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            TNhaXuatBanEntity entity = this.dao.findByID(id);
            this.dao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/publisher/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/publisher/index");
            e.printStackTrace();
        }
    }
}
