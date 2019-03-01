<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Private page</title>
    <style>
        .privateImgHidden{
            display: none;
        }

        img{
            width: 200px;
        }
    </style>
</head>
<body>
    <h2>Private page</h2>

    <script type="text/javascript">
        function showImage(){
            var img = document.getElementById("privateImage");

            if(img.hasAttribute("class")) {
                img.removeAttribute("class");
            }
            else{
                img.setAttribute("class", "privateImgHidden");
            }
        }
    </script>

    <a href="#" onclick="showImage()">Show Image</a><br>
    <img id="privateImage" class="privateImgHidden" src="https://avatars.mds.yandex.net/get-pdb/1615223/d4743ccb-f434-4f28-9f43-ee8c69771364/s1200">
</body>
</html>
