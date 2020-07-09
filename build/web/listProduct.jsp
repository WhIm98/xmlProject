<%-- 
    Document   : listProduct
    Created on : Mar 23, 2020, 1:35:50 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>

        <c:set var="productDoc" value="${sessionScope.LISTPRODUCT}"/>
        <x:set var="listProduct" select="$productDoc//product"/>
        <title><x:out select="$listProduct/categoryName"/></title>



        <script>
            var list = new Array();
            var pageList = new Array();
            var currentPage = 1;
            var numberPerPage = 20;
            var numberOfPages = 0;
            function makeList() {
            <x:forEach var="productDetail" select="$listProduct" varStatus="counter">
                product1 = new Object();
                product1.name = "<x:out select="$productDetail/productName"/>";
                product1.price = "<x:out select="$productDetail/price"/>";
                product1.imageLink = "<x:out select="$productDetail/imageLink"/>";
                product1.categoryName = "<x:out select="$productDetail/categoryName"/>";
                product1.detailLink = "<x:out select="$productDetail/productLink"/>";
                list.push(product1);
            </x:forEach>
                numberOfPages = getNumberOfPage();
            }
            function getNumberOfPage() {
                return Math.ceil(list.length / numberPerPage);
            }

            function nextPage() {
                currentPage += 1;
                loadList();
            }

            function previousPage() {
                currentPage -= 1;
                loadList();
            }

            function firstPage() {
                currentPage = 1;
                loadList();
            }

            function lastPage() {
                currentPage = numberOfPages;
                loadList();
            }

            function loadList() {
                var begin = ((currentPage - 1) * numberPerPage);
                var end = begin + numberPerPage;

                pageList = list.slice(begin, end);
                drawList();
                check();
            }

            function check() {
                document.getElementById("next").disabled = currentPage == numberOfPages ? true : false;
                document.getElementById("previous").disabled = currentPage == 1 ? true : false;
                document.getElementById("first").disabled = currentPage == 1 ? true : false;
                document.getElementById("last").disabled = currentPage == numberOfPages ? true : false;
            }

            function drawList() {
                var productlistx = document.getElementById("listproduct");
                productlistx.innerHTML = "";
                for (r = 0; r < pageList.length; r++) {
                    var div = document.createElement("div");
                    div.setAttribute("class", "product");
                    var img = document.createElement("img");
                    img.setAttribute("class", "imgproduct");
                    img.setAttribute("src", pageList[r].imageLink);
                    var pname = document.createElement("p");
                    pname.setAttribute("class", "productname");
                    pname.innerHTML = pageList[r].name;
                    var pcate = document.createElement("p");
                    pcate.setAttribute("class", "productcate");
                    pcate.innerHTML = pageList[r].categoryName;
                    //                    var pbrand = document.createElement("p");
                    //                    pbrand.setAttribute("class", "productbrand");
                    //                    pbrand.innerHTML = pageList[r].brand;
                    var pprice = document.createElement("p");
                    //                    pprice.setAttribute("class", "productprice");
                    pprice.innerHTML = pageList[r].price.toLocaleString('de-DE', {
                        minimumFractionDigits: 0
                    }) + "đ";
                    var linkDetail = document.createElement("a");
                    linkDetail.setAttribute("style", "color:black");
                    linkDetail.setAttribute("href", pageList[r].detailLink);
                    linkDetail.appendChild(pname);
                    div.appendChild(img);
                    div.appendChild(pname);
                    div.appendChild(pcate);
                    //                    div.appendChild(pbrand);
                    div.appendChild(pprice);
                    linkDetail.appendChild(div)
                    productlistx.appendChild(linkDetail);
                    document.getElementById("paging").value = currentPage + "/" + numberOfPages;
                }
            }

            function load() {
                makeList();
                loadList();
            }

            window.onload = load;
        </script>

    </head>
    <div class="body">
        <c:set var="categoryList" value="${sessionScope.CATEGORYRESULT}"/>
        <x:set var="listCategory" select="$categoryList//category"/>
        <div class="top">
            <div class="logo">
                <h1>Product</h1>
            </div>
            <div class="search">
                <form action="SearchController" method="POST">
                    <input type="text" name="txtSearch" placeholder="Tìm kiếm: đồ đá bóng,...."/>
                    <input type="submit" name="action" value="Search">
                    <c:set var="searchList" value="${requestScope.SEARCHRESULT}"/>
                    <c:if test="${not empty requestScope.SEARCHRESULT}">
                        <x:set var="listSearch" select="$searchList//product"/>
                        <script>
                            function searchList() {
                            <x:forEach var="searchDetail" select="$listSearch" varStatus="counter">
                                product2 = new Object();
                                product2.name = "<x:out select="$searchDetail/productName"/>";
                                product2.price = "<x:out select="$searchDetail/price"/>";
                                product2.imageLink = "<x:out select="$searchDetail/imageLink"/>";
                                product2.categoryName = "<x:out select="$searchDetail/categoryName"/>";
                                searchList().push(product2);
                            </x:forEach>
                                numberOfPages = getNumberOfPage();

                            }
                            searchList();
                            loadList();

                        </script>
                    </c:if>

                </form>
            </div>
            
        </div>
        <div class="menu">
            <ul style="padding-left: 185px;">
                <li><a href="HomeServlet">Home</a></li>
                <li class="dropdown"><a href="javascript:void(0)">Category</a>
                    <div class="dropdown-content">
                        <x:forEach var="category" select="$listCategory" varStatus="counter">
                            <a href="ListProductByCategory?categoryId=<x:out select="$category/categoryID"/>"><x:out select="$category/categoryName"/></a>
                        </x:forEach>
                    </div>
                </li>
                <li><a href="SurveyController">Survey</a></li>
            </ul>
        </div>
    </div>
    <div class="main">
        <div class="mainlist">
            <div id="listproduct">

            </div>
            <div style="float: left;width: 100%;padding-top: 40px;padding-bottom: 20px">
                <input class="buttonPage" type="button" id="first" onclick="firstPage()" value="first" />
                <input class="buttonPage" type="button" id="previous" onclick="previousPage()" value="previous" />
                <input style="text-align: center" type="text" readonly="true" id="paging"/>
                <input class="buttonPage" type="button" id="next" onclick="nextPage()" value="next" />
                <input class="buttonPage" type="button" id="last" onclick="lastPage()" value="last" />
            </div>
        </div>
    </div>
</html>
