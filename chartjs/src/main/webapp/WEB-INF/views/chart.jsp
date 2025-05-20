<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>
<body>
	<h1>Chart.js 라이브러리 실습</h1>
	
	<div>
		<h1>나라별 평균 나이</h1>
		<canvas id="chart1" style="width:100%;max-width:700px">
		
		</canvas>
	</div>
	<div>
		<h1>성별간 가입자 수</h1>
		<canvas id="chart2" style="width:100%;max-width:700px">
		
		</canvas>
	</div>
	<div>
		<h1>년도별 나라별 가입자 수</h1>
		<canvas id="chart3" style="width:100%;max-width:700px">
		
		</canvas>
	</div>
	<div>
		<h1>년도별 전체 누적 가입자수</h1>
		<canvas id="chart4" style="width:100%;max-width:700px">
		
		</canvas>
	</div>
	
	
	<script>
		// chart1
		$.ajax({
			url : '/rest/avgAgeByCountry',
			type: 'post',
			success : function(data){
				console.log('chart1:', data);
				// chart1에 bar chart를 출력
				// chart.js가 bar chart에 요구하는 모델
				const xValues = []; // 나라
				const yValues = []; // 평균 나이
				const barColors = ["green","blue","orange","brown"];
				
				$(data).each(function(i, e){
					xValues.push(e.country);
					yValues.push(e.age)
				});
				
				new Chart("chart1", {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      backgroundColor: barColors,
					      data: yValues
					    }]
					  },
					  options: {
					    legend: {display: false},
					    scales: {
					      yAxes: [{
					        ticks: {
					          beginAtZero: true
					        }
					      }]
					    },

					    title: {
					      display: true,
					      text: "나라별 평균 나이"
					    }
					  }
					});
			}
		});
		
		// chart 2
		$.ajax({
			url : '/rest/countByGender',
			type: 'post',
			success : function(data){
				console.log('chart2:', data);
				// chart2에 bar chart를 출력
				// chart.js가 bar chart에 요구하는 모델
				const xValues = []; // 나라
				const yValues = []; // 가입자수
				const barColors = ["blue","red"];
				
				$(data).each(function(i, e){
					xValues.push(e.gender);
					yValues.push(e.count)
				});
				
				new Chart("chart2", {
					  type: "bar",
					  data: {
					    labels: xValues,
					    datasets: [{
					      backgroundColor: barColors,
					      data: yValues
					    }]
					  },
					  options: {
					    legend: {display: false},
					    scales: {
					      yAxes: [{
					        ticks: {
					          beginAtZero: true
					        }
					      }]
					    },

					    title: {
					      display: true,
					      text: "성별간 가입자 수"
					    }
					  }
					});
			}
		});
		// chart 3
		$.ajax({
			url : '/rest/countByYearAndCountry',
			type : 'post',
			success : function(data){
				console.log('chart3:', data)
				// start chart3
				// multiple lines
				const xValues = []; // 년도
				const d0 = []; // 독일
				const d1 = []; // 미국
				const d2 = []; // 한국
				const d3 = []; // 호주
				$(data).each(function(i, e){
					if(i%4==0) {
						xValues.push(e.year);
						d0.push(e.count);
					} else if(i%4==1) {
						d1.push(e.count);
					} else if(i%4==2) {
						d2.push(e.count);
					} else if(i%4==3) {
						d3.push(e.count);
					}
				});
				console.log(xValues);
				
				new Chart("chart3", {
				  type: "line",
				  data: {
				    labels: xValues,
				    datasets: [{ 
						label: '독일',
				      data: d0,
				      borderColor: "red",
				      fill: false
				    }, { 
				    	label: '미국',
				      data: d1,
				      borderColor: "green",
				      fill: false
				    }, { 
				    	label: '한국',
				      data: d2,
				      borderColor: "blue",
				      fill: false
				    }, { 
				    	label: '호주',
				      data: d3,
				      borderColor: "yellow",
				      fill: false
				    }]
				  },
				  options: {
				    legend: {display: true},
				    title: {
					      display: true,
					      text: "년도별 나라별 가입자 수"
					}
				  },
				 
				});
				// end chart3
			}
		});
		
		// chart 4
		$.ajax({
			url : '/rest/totalCountByYear',
			type : 'post',
			success : function(data){
				console.log('chart4:', data);
				
				const xValues = []; // 년도
				const d = [];
				$(data).each(function(i, e){
					xValues.push(e.year);
					d.push(e.count);
				});
					
				new Chart("chart4", {
				  type: "line",
				  data: {
				    labels: xValues,
				    datasets: [{
				      fill: false,
				      lineTension: 0,
				      backgroundColor: "rgba(0,0,255,1.0)",
				      borderColor: "rgba(0,0,255,0.1)",
				      data: d
				    }]
				  },
				  options: {
				    legend: {display: true},
				    title: {
					      display: true,
					      text: "년도별 전체 누적 가입자수"
					}
				  }
				});
			}
		});
	</script>
</body>
</html>