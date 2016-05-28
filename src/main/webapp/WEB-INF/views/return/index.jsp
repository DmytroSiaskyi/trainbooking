<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Повернення квитка</title>
</head>
<body>
  <div class="jspBody">
    <h2>Повернення квитка</h2>
     <form action="/return" method="POST" id="saleTickets">
         <p>
             Номер квитка:
             <input type="text" name="number" placeholder="Номер квитка" value="${id}">
         </p>
         <input type="submit" value="Пошук"><input type="submit" value="Повернути" name="return">
         <hr>
         <h2>${error}</h2>
         <div class="ticketsTable">
             <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
                 <th>Початкова станція</th>
                 <th>Кінцева станція</th>
                 <th>Дата</th>
                 <th># Поїзду</th>
                 <th>Місце</th>
                 <th>Тип вагону</th>
                 <th>Вагон</th>
                 <th>Ціна</th>
                 <tr>
                     <td>${ticket.start.name}</td>
                     <td>${ticket.end.name}</td>
                     <td>${ticket.date}</td>
                     <td>${ticket.places.carriage.trainNumber}</td>
                     <td>${ticket.places.number}</td>
                     <td>${ticket.places.carriage.type}</td>
                     <td>${ticket.places.carriage.number}</td>
                     <td>${ticket.price}</td>
                 </tr>
             </table>
         </div>
     </form>
      <c:if test="${ticket!=null}">
          <h2>До повернення: ${ticket.price * 0.8}грн</h2>
      </c:if>
  </div>
</body>
</html>
