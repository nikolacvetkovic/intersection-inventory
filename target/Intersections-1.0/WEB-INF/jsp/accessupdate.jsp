<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="topupdate.jsp" %>
                    
                            <form class="login-page" action="accessupdate" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!=-1) window.location='./accessupdate?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Prilaz: <select class="form-unos" name="idAcc" onchange="if(this.value!=-1) window.location='./accessupdate?idAcc='+this.value+'&idInt=${selectedIntersection.id}'">
                                        <option value="-1">Odaberi prilaz</option>
                                        <c:forEach items="${accesses}" var="access">
                                            <option <c:if test="${access.id==selectedAccess.id}">selected</c:if>value="${access.id}">[&nbsp;${access.symbol}&nbsp;] &nbsp; ${access.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka prilaza: <input class="form-oznaka" type="text" name="symbol" value="${selectedAccess.symbol}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Naziv prilaza: <input class="form-proiz1" type="text" name="title" value="${selectedAccess.title}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <input class="form-proiz1" type="hidden" name="idAccess" value="${selectedAccess.id}"/>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <input class="btn btn-primary pull-right" type="submit" value="Unesi"/>
                                    </div>
                                </div>
                                
                            </form>
                        <!-- End Main Content -->
                    </div>
                    <!-- End Main Column -->
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottominput.jsp" %>