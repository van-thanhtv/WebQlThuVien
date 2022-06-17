<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Book Management</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="/book/store" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Tên sách</label>
        <input type="text" class="form-control" name="tenSach">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Tên Tác Giả</label>
        <input type="text" class="form-control" name="tenTacGia">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Giá Bán</label>
        <input type="text" class="form-control" name="giaBan">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Giá Bán Cho Đại Lý</label>
        <input type="text" class="form-control" name="giaBanChoDaiLy">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Số Trang</label>
        <input type="text" class="form-control" name="soTrang">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Nhà xuất bản</label>
        <select class="form-select" name="NhaXuatBan" >
            <c:forEach items="${ dspublisher }" var="o">
                <option  value="${ o.maNhaXuatBan }">
                        ${ o.tenNhaXuatBan }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mt-3">
    <button  class="btn btn-success">ADD</button>
    <button type="reset" class="btn btn-primary">Reset</button>
</div>
</form>
<br>


