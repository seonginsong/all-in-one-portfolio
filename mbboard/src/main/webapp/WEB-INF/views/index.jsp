<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>접속 통계</title>
  <link rel="stylesheet" href="/css/whitemono-style.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
  <div class="card">
    <h2>접속자 통계 차트</h2>
    <div class="chart-grid">
      <div class="chart-item">
        <h3>전체</h3>
        <canvas id="allChart" width="300" height="160"></canvas>
      </div>
      <div class="chart-item">
        <h3>오늘</h3>
        <canvas id="todayChart" width="300" height="160"></canvas>
      </div>
    </div>
  </div>

  <div class="card">
    <h2>전체 접속자 수</h2>
    <table class="table">
      <tr>
        <th>ANONYMOUS</th>
        <th>MEMBER</th>
        <th>ADMIN</th>
      </tr>
      <tr>
        <td>${connectCountMapAll.ANONYMOUS}</td>
        <td>${connectCountMapAll.MEMBER}</td>
        <td>${connectCountMapAll.ADMIN}</td>
      </tr>
    </table>
  </div>

  <div class="card">
    <h2>오늘 접속자 수</h2>
    <table class="table">
      <tr>
        <th>ANONYMOUS</th>
        <th>MEMBER</th>
        <th>ADMIN</th>
      </tr>
      <tr>
        <td>${connectCountMapToday.ANONYMOUS}</td>
        <td>${connectCountMapToday.MEMBER}</td>
        <td>${connectCountMapToday.ADMIN}</td>
      </tr>
    </table>
  </div>

  <div class="card">
    <h2>현재 접속자 수 (세션 개수)</h2>
    <table class="table">
      <tr><th>TOTAL</th></tr>
      <tr><td>${currentConnectCount}</td></tr>
    </table>
  </div>

  <script>
    // 전체 접속자 수 차트
    new Chart(document.getElementById('allChart'), {
      type: 'bar',
      data: {
        labels: ['ANONYMOUS', 'MEMBER', 'ADMIN'],
        datasets: [{
          label: '전체 접속자 수',
          data: [
            ${connectCountMapAll.ANONYMOUS},
            ${connectCountMapAll.MEMBER},
            ${connectCountMapAll.ADMIN}
          ],
          backgroundColor: ['#222', '#555', '#aaa']
        }]
      },
      options: {
        responsive: false,
        plugins: { legend: { display: false } },
        scales: {
          y: {
            beginAtZero: true,
            ticks: { color: '#333', font: { size: 12 } },
            grid: { color: '#eee' }
          },
          x: {
            ticks: { color: '#333', font: { size: 12 } },
            grid: { color: '#eee' }
          }
        }
      }
    });

    // 오늘 접속자 수 차트
    new Chart(document.getElementById('todayChart'), {
      type: 'bar',
      data: {
        labels: ['ANONYMOUS', 'MEMBER', 'ADMIN'],
        datasets: [{
          label: '오늘 접속자 수',
          data: [
            ${connectCountMapToday.ANONYMOUS},
            ${connectCountMapToday.MEMBER},
            ${connectCountMapToday.ADMIN}
          ],
          backgroundColor: ['#222', '#555', '#aaa']
        }]
      },
      options: {
        responsive: false,
        plugins: { legend: { display: false } },
        scales: {
          y: {
            beginAtZero: true,
            ticks: { color: '#333', font: { size: 12 } },
            grid: { color: '#eee' }
          },
          x: {
            ticks: { color: '#333', font: { size: 12 } },
            grid: { color: '#eee' }
          }
        }
      }
    });
  </script>
 	<a href="/admin/adminHome">adminHome으로 돌아가기</a>
	<a href="/admin/memberList">멤버리스트</a>
</body>
</html>
