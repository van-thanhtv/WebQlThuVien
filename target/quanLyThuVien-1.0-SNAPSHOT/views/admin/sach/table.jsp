<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:if test="${empty list}">
    <p class="alert alert-warning">
        Vui Lòng Thêm Mới Dữ Liệu
    </p>
</c:if>
<c:if test="${!empty sessionScope.error}">
    <div class="alert alert-danger">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
    <div class="alert alert-success">
            ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"/>
</c:if>
<form action="/book/show" method="post">
    <button class="btn-info">Xuất</button>
</form>
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">Tên Sách</th>
        <th scope="col">Tên Tác Giả</th>
        <th scope="col">Giá Bán</th>
        <th scope="col">Giá Bán Cho Đại Lý</th>
        <th scope="col">Nhà Xuất Bản</th>
        <th scope="col">Số Trang</th>
        <th colspan="2">Manipulation</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="o">
        <tr>
            <td>${o.tenSach}</td>
            <td>${o.tenTacGia}</td>
            <td><fmt:formatNumber value="${o.giaBan}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${o.giaBanChoDaiLy}" pattern="#,###"/> VND</td>
            <td>${o.maNhaXuatBan.tenNhaXuatBan}</td>
            <td>${o.soTrang}</td>
            <td><a href="/book/edit?id=${o.maSach}" class="btn btn-danger">Edit</a></td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#c${o.maSach}">Delete</button>
            </td>
            <div id="c${o.maSach}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa  ${o.maSach} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/book/delete?id=${o.maSach}" method="post">
                                <button class="btn btn-danger">Delete</button>
                            </form>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    aria-label="Close">Hủy
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </tr>
    </c:forEach>
    </tbody>
</table>


