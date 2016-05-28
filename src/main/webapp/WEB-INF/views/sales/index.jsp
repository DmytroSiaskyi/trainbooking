<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Продаж | Залізнична каса</title>
</head>
<body>
    <div class="jspBody">
    <h2>Продаж квитків</h2>
        <form action="/sales" method="POST" id="saleTickets" >
            <p>
                Звідки
                <input type="text" name="start" placeholder="Звідки" value="${start}">
            </p>
            <p>
                Куди
                <input type="text" name="end" placeholder="Куди" value="${end}">
            </p>
            <p>
                Тип вагону
                <select name="type">
                    <option <c:if test="${type == 'тип'}">selected</c:if> value="тип">
                        Тип
                    </option>
                    <option <c:if test="${type == 'купе'}">selected</c:if> value="купе">
                        Купе
                    </option>
                    <option <c:if test="${type == 'плацкарт'}">selected</c:if> value="плацкарт">
                        Плацкарт
                    </option>
                    <option <c:if test="${type == 'люкс'}">selected</c:if> value="люкс">
                        Люкс
                    </option>
                </select>
            </p>
            <p>
                Дата
                <input type="date" name="date" value="${date}">
            </p>
           <input type="submit" value="Пошук"><input type="submit" name="flag"value="Відміна">
        </form>
        <h2>${resultText}</h2>
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
                <th>Забронювати</th>
                <c:forEach var="ticket" items="${ticketList}">
                 <tr>
                    <td>${ticket.start.name}</td>
                    <td>${ticket.end.name}</td>
                    <td>${ticket.date}</td>
                     <td>${ticket.places.carriage.trainNumber}</td>
                    <td>${ticket.places.number}</td>
                    <td>${ticket.places.carriage.type}</td>
                    <td>${ticket.places.carriage.number}</td>
                    <td>${ticket.price}</td>
                    <td><a href="/sales/confirm?id=${ticket.id}">Бронювати</a></td>
                  </tr>
                </c:forEach>
             </table>
        </div>
    </div>
</body>
</html>
