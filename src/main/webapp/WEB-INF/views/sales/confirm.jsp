<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Підтвердження</title>
</head>
<body>
  <div class="jspBody">
    <h2>Підтвердження продажу</h2>
    <div class="ticketsTable">
      <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
        <th>Початкова станція</th>
        <th>Кінцева станція</th>
        <th>Дата</th>
        <th># Поїзду</th>
        <th>Місце</th>
        <th>Тип вагону</th>
        <th>Вагон</th>
          <tr>
            <td>${ticket.start.name}</td>
            <td>${ticket.end.name}</td>
            <td>${ticket.date}</td>
            <td>${ticket.places.carriage.trainNumber}</td>
            <td>${ticket.places.number}</td>
            <td>${ticket.places.carriage.type}</td>
            <td>${ticket.places.carriage.number}</td>
          </tr>
      </table>
    </div>
    <h2>До сплати: ${ticket.price}грн</h2>
    <form action="/sales/sold?id=${ticket.id}" method="POST">
      <p>${errorMessage}</p>
      <p>Клієнт
       <input type="text" name="client">
      </p>
      <input type="submit" value="Продати">
    </form>
  </div>
</body>
</html>
