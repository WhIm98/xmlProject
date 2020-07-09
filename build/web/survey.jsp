<%-- 
    Document   : index
    Created on : Mar 18, 2020, 1:23:15 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <title>Survey</title>
    </head>
    <div class="body">
        <c:set var="categoryList" value="${sessionScope.CATEGORYRESULT}"/>
        <x:set var="listCategory" select="$categoryList//category"/>
        <c:set var="report" value="${requestScope.SURVEY}"/>
        <div class="top">
            <div class="logo">
                <h1>Report</h1>
            </div>
            <div class="search">
                <form action="SearchController" method="POST">
                    <input type="text" name="txtSearch" placeholder="Tìm kiếm: đồ đá bóng,...."/>
                    <input type="submit" name="action" value="Search">
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
                <%--<c:if test="${requestScope.SURVEY !=null}">
                    <c:if test = "${not empty requestScope.SURVEY}" var="table">
                        <table border="1">
                        <thead>
                            <tr>
                                
                                <th>Brand</th>
                                <th>Quantity</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.SURVEY}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${dto.brand}</td>
                                    <td>${dto.count}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                    </c:if>--%>
                <c:import url="WEB-INF/report.xsl" var="xsl"/>
                <x:transform xml="${report}" xslt="${xsl}"/>
            </div>
            
        </div>
    </div>
</html>
