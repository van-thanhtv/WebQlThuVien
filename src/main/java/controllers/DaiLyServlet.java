package controllers;

import Dao.DaiLyDao;
import entitys.TDaiLyEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.SortedMap;

@WebServlet(name = "DaiLyServlet", value = {"/daiLy/index",
        "/daiLy/show",
        "/daiLy/create",
        "/daiLy/store",
        "/daiLy/edit",
        "/daiLy/update",
        "/daiLy/delete",})
public class DaiLyServlet extends HttpServlet {
    private DaiLyDao daiLyDao;

    public DaiLyServlet() {
        this.daiLyDao = new DaiLyDao();
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
        List<TDaiLyEntity> entityList = this.daiLyDao.all();
        request.setAttribute("list",entityList);
        request.setAttribute("view", "/views/admin/daiLy/create.jsp");
        request.setAttribute("view1", "/views/admin/daiLy/table.jsp");
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
            TDaiLyEntity o = this.daiLyDao.findByID(Integer.parseInt(ma));
            List<TDaiLyEntity> entityList = this.daiLyDao.all();
            request.setAttribute("list",entityList);request.setAttribute("o",o);
            request.setAttribute("view", "/views/admin/daiLy/edit.jsp");
            request.setAttribute("view1", "/views/admin/daiLy/table.jsp");
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
            TDaiLyEntity entity = new TDaiLyEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setMaDaiLy(Integer.parseInt(ma));
            this.daiLyDao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/daiLy/index");
        } catch (Exception e) {
            response.sendRedirect("/daiLy/index");
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
            TDaiLyEntity entity = new TDaiLyEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            this.daiLyDao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            response.sendRedirect("/daiLy/index");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/daiLy/index");
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
            TDaiLyEntity entity = this.daiLyDao.findByID(id);
            this.daiLyDao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/daiLy/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/daiLy/index");
            e.printStackTrace();
        }
    }
}
