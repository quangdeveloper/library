<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <style type="text/css">
        .containnner{

        }
        .form-control{
            width:  250px;
        }


    </style>
</head>
<body>
<div>
    <div class= "containnner" style=" margin-left: 50px;
            margin-top: 30px;">
        <div class="dropdown-divider"></div>
        <form action="api/admin/accounts/add" method= "POST" >
            <div class="row">
                <div class="col">
                  ươu     <input type="text" style="width: 250px" class="form-control" placeholder="Họ và tên"><br>

                    <label>Ngày sinh:</label>
                    <input type="text" style="width: 250px" class="form-control" placeholder="Ngày sinh">

                    <br><label>Giới tính:</label>&ensp;
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio" checked>Nam
                        </label>
                    </div>&ensp;

                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio">Nữ
                        </label>
                    </div>
                    <br>
                    <label>Tên đăng nhâp:</label>
                    <input type="text" style="width: 250px" class="form-control" placeholder="First name"><br>

                    <label>Mật khẩu:</label>
                    <input type="text" style="width: 250px" class="form-control" placeholder="First name"><br>

                    <label>Email:</label>
                    <input type="text" style="width: 250px" class="form-control" placeholder="First name"><br>


                </div>

                <div class="col" style="margin-left:-250px; ">
                    <label>Số điện thoại:</label>
                    <input type="text" style="width: 250px" class="form-control" placeholder="First name"><br>
                    <h4>Quyền</h4>
                    <div class="form-check">
                        <label class="form-check-label" >
                            <input type="checkbox" class="form-check-input"  name="option1" value="something" checked>Option 1
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label" >
                            <input type="checkbox" class="form-check-input"  name="option1" value="something" checked>Option 1
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label" >
                            <input type="checkbox" class="form-check-input"  name="option1" value="something" checked>Option 1
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label" >
                            <input type="checkbox" class="form-check-input"  name="option1" value="something" checked>Option 1
                        </label>
                    </div>
                    <c:if test="${not empty model.id}">
                        <button style="margin: 150px 0 0 0;" type="button" class="btn btn-primary">Cập nhập thông tin</button>

                    </c:if>
                    <c:if test="${empty model.id}">
                        <button style="margin: 150px 0 0 0;" type="button" class="btn btn-primary">Thêm tài khoản</button>

                    </c:if>
                    <button style="margin: 150px 0 0 0;" type="reset" class="btn btn-secondary">Hủy bỏ</button>

                </div>
            </div>
        </form>


    </div>

</div>
</body>
</html>
