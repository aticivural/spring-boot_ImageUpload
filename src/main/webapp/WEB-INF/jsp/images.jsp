<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Images</title>
    <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/lib/lightbox/css/lightbox.css" rel="stylesheet">
    <style>
        .number {
            font-size: 12px;
        }
    </style>
</head>
<body>
<%--content--%>
<div class="col-sm-12" style="padding-bottom: 200px">
    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading" style="font-weight: bold; font-size: 20px; text-align: center; ">IMAGES</div>

        <div class="panel-body">
            <div class="col-sm-12 ">
                <table class="table table-hover table-bordered">
                    <thead class="navbar-default">
                    <tr>
                        <th>Image</th>
                        <th>Image Name</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>View Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="image" items="${images}">
                        <tr style="cursor:hand">
                            <td class="number col-md-1">
                                <a href="${image.url}" data-lightbox="${image.name}" onclick="clickME('${image.imageId}')">
                                    <img id="myImg" src="${image.url}" height="100px" ; width="100px" ;/>
                                </a>
                            </td>
                            <td class="number col-md-2">
                                <c:out value="${image.name}"/>
                            </td>
                            <td class="number col-md-5">
                                <c:out value="${image.description}"/>
                            </td>
                            <td class="number col-md-2">
                                <c:out value="${image.date}"/>
                            </td>
                            <td class="number col-md-1">
                                <c:out value="${image.time}"/>
                            </td>
                            <td class="number col-md-1">
                                <p id="${image.imageId}_clickCount">${image.viewCount}</p>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="panel-footer" style="text-align: center">
            <a href="/upload" class="btn btn-primary" style="width: 200px;">New Image Upload</a>
        </div>
    </div>
</div>


<script>
    function clickME(clickedId) {
        var currentClickCount = document.getElementById(clickedId + '_clickCount').innerHTML;
        var temp = parseInt(currentClickCount);
        temp += 1;
        $.ajax({
            url: 'http://localhost:8080/updateImageViewCount?imageId=' + clickedId + '&imageViewCount=' + temp,
            async: false,
            error: function () {
                alert("Sorry could not proceed");
            },
            success: function () {
                document.getElementById(clickedId + '_clickCount').innerHTML = temp;
            },
            type: 'GET'
        });
    }
</script>

<script src="/lib/jquery/3.1.1/jquery.min.js"></script>
<script src="/lib/lightbox/js/lightbox.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
