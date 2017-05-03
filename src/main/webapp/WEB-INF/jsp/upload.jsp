<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>Upload</title>
    <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container" style="padding: 100px">
    <div class="panel panel-primary">
        <div class="panel-heading" style="font-weight: bold">Image Upload</div>
        <div class="panel-body">
            <form:form method="POST" action="/upload" enctype="multipart/form-data" commandName="imageObject">
                <input type="file" name="file"/><br/>
                <h4>Description</h4>
                <form:input type="text" class="form-control" path="description"/><br/><br/>
                <div>
                    <input type="submit" value="Upload" class="btn btn-primary "/>
                    <a href="/images" class="btn btn-success ">See all images</a>
                </div>
            </form:form>
        </div>
    </div>
</div>


<script src="/lib/jquery/3.1.1/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
