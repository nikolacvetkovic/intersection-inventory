<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>

                            <form class="login-page" action="intersection" method="post" enctype="multipart/form-data">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./intersection?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Naziv raskrsnice: <input class="form-unos" type="text" name="title" value="${selectedIntersection.title}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka raskrsnice: <input class="form-oznaka" type="text" name="symbol" value="${selectedIntersection.symbol}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Koordinata x: <input class="form-oznaka" type="text" name="xCoordinate" value="${selectedIntersection.xcoordinate}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Koordinata y: <input class="form-oznaka" type="text" name="yCoordinate" value="${selectedIntersection.ycoordinate}"/>
                                </div>
                                <div>
                                    Dispozicija: <input type="file" name="pdf" />
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <input class="btn btn-primary pull-right" type="submit" value="Unesi"/>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        <!-- End Main Content -->
                    </div>
                    <!-- End Main Column -->
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>