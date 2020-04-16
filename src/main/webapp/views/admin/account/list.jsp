<%@include file="/common/taglib.jsp" %>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="DELETEAPI" value="/api/admin/accounts"></c:url>
<c:url var="URL" value="/admin/accounts"></c:url>
<html>
<head>
    <title></title>
</head>
<body>

<div>
    <form action="<c:url value='/admin/accounts'/>" id="formSubmit" method="GET">

        <div style="margin-top: 5px; margin-bottom: 7px;">
            Ngày bắt đầu:
            <input style="width: 160px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
            height: 30px; " type="text" id="txtFromDate" name="fromDate">
            Ngày kết thúc:
            <input style="width: 160px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
            height: 30px; " type="text" id="txtToDate" name="toDate">
            Trạng thái:
            <input style="width: 160px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
            height: 30px; " type="text" id="txtStatus" name="status">
            <button id="btnSearch" style=" height: 35px; margin-top: -3px;" type="submit" class="btn btn-success">Tìm kiếm</button>

            <a href="/admin/accounts/add?id=">
                <button style=" margin-top: 3px;  width:100px  ;margin-left: 50px;" type="button" class="btn btn-success">
                    Thêm</button>
            </a>
            <button id="btnDeleteMany" style=" margin-top: 5px;  width:100px  ;margin-left: 925px;" type="button" class="btn btn-danger">Xóa</button>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tài khoản</th>
                <th>Ngày tạo</th>
                <th>Người tạo</th>
                <th>Ngày sửa</th>
                <th>Người sửa</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
                <tr style="height: 30px">
                    <td>${item.ID}</td>
                    <td>${item.taiKhoan}</td>
                    <td>${item.ngayTao}</td>
                    <td>${item.nguoiTao}</td>
                    <td>${item.ngaySua}</td>
                    <td>${item.nguoiSua}</td>
                    <td>
<%--                        sua bai viet--%>
                        <c:url var='EditURL' value="/admin/accounts/add">
                            <c:param name="id" value= "${item.ID}"/>
                        </c:url>
                        <a title="Edit" href="${EditURL}">
                            <button id="btnEdit" type="button" class="btn btn-primary">
                                <i class="fa fa-pencil-square-o" aria-hidden="true" ></i>
                            </button>
                        </a>
                        <input type="checkbox" id="cbx_${item.ID}" value="${item.ID}">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<script src="<c:url value='/template/admin/jquery/jquery.min.js' />"></script>
<script>
    $(document).ready(
        $('#btnDeleteMany').click(function e() {
            var data = {};
            var ids = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['ids'] = ids;
            deleteMany(data);
        })

    );
    function deleteMany(data) {
        $.ajax({
                url: '${DELETEAPI}',
                type: 'DELETE',
                contentType: 'application/json',    //chi dinh loai data de gui ve server
                data: JSON.stringify(data),      //chi dinh data de gui toi server
                dataType: 'json',       //chi dinh loai data khi server tra ve
                success: function (result) {
                    window.location.href = "${URL}";
                },
                error: function (error) {
                    window.location.href = "${URL}";
                }
            }
        );
    }

</script>
</body>
</html>
