<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link href="css/navbar-top-fixed.css" rel="stylesheet">

	<title></title>

  <script>
		function setChildValue1(index) {
    window.location.href = "MarketSetting.php?index=" + index;
   }
		function setChildValue2(index) {
  	window.location.href = "OrderPage.php?index=" + index;
   }
  </script>
  <style>

  th, td{
  		text-align: center;
  	}
  </style>

</head>
<body>
	
	<!-- Start 네비게이션 바 항목 * Ver 1.2  -->
  <?php
  $request_uri = $_SERVER['REQUEST_URI'];
  $nowID = substr(strstr($request_uri,"index="),6);

  $MarketPage_url = "MarketSetting.php?index=".$nowID;
  $OrderPage_url = "OrderPage.php?index=".$nowID;
  ?>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="MainPage.php">AjouBB Admin</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" onclick="setChildValue1(<?=$nowID?>)">Setting <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" onclick="setChildValue2(<?=$nowID?>)">Order System</a>
          </li>
        </ul>
      </div>
    </nav>
	<!-- End 네비게이션 바 항목 * Ver 1.2   -->

    <main role="main" class="container">

    	<table class="table table-striped table-sm">
			<thead>
			<tr>
				<th>주문 시간</th>
				<th>메뉴 이름</th>
				<th>좌석 번호</th>
				<th>상태</th>
			</tr>
			</thead>
		<tbody>
		 <body>
		  
		 </body>
			<?php
					//======================== DB 연결 ==============================
					$con=mysqli_connect("localhost","root","1234","boksung");
					if(mysqli_connect_errno($con)){
						echo "Failed to connect to MySQL:".mysqli_connect_error();
					}
					
					mysqli_set_charset($con,"utf8");
					//=========================도시 희망 쿼리
					$resN1 = mysqli_query($con,"Select LS_OrderTime, LM_Name, LS_TableNum, LS_State
					From LocalState
					JOIN LocalMenu ON Localstate.LS_Ordermenu = LocalMenu.LM_Num
					WHERE LM_Ssn=".$nowID." AND LS_Ssn =".$nowID
					);

					$i = 0;
					while($row = mysqli_fetch_array($resN1))
					{
						$value_A1[$i] = $row[0];
						$value_A2[$i] = $row[1];
						$value_A3[$i] = $row[2];
						$value_A4[$i] = $row[3];
						$i++;
					}
					
					for($j=0; $j<$i; $j++){
						echo "<tr><td>".$value_A1[$j]."</td>";
						echo "<td>".$value_A2[$j]."</td>";
						echo "<td>".$value_A3[$j]."</td>";
						echo "<td>".$value_A4[$j]."</td>";
					}
				?>
			</tbody>
		</table>


			<br/>
			<h4>[ 주문 ]</h4>
			<form method="POST"  action=<?=$OrderPage_url?>>
				테이블 번호 : <input type="text" style="width:50px;" name="table"/>
				메뉴 번호   : <input type="text" style="width:50px;" name="menu"/>
				<input type="submit" name="answer"/><br/>
			</form>
			<br/>
			<?php
				date_default_timezone_set('Asia/Seoul');
				$date_y = date("Y-m-d");
				$date_h = date("H:i:s");

			if(isset($_POST['table'])&&isset($_POST['menu'])){
				mysqli_query($con,"insert into LocalState(LS_Ssn,LS_OrderDate,LS_OrderTime,LS_TableNum,LS_OrderMenu,LS_State)"."values('".$nowID."','".$date_y."','".$date_h."','".$_POST['table']."','".$_POST['menu']."','3')");
				echo "<meta http-equiv='refresh' content='1;".$OrderPage_url."' />";
			}

			//=========================Ssn1에서 메뉴 로드
			$resN2 = mysqli_query($con,"Select LM_Num, LM_Name, LM_Charge From LocalMenu Where LM_Ssn=".$nowID);

			$i = 0;
			while($row = mysqli_fetch_array($resN2))
			{
				$value_B1[$i] = $row[0];
				$value_B2[$i] = $row[1];
				$value_B3[$i] = $row[2];
				$i++;
			}
			?>

			<table class="table table-striped table-sm">
			<thead><tr>
			<h4>[ 메뉴번호 참고 ]</h4>
			<th>메뉴번호</th>
			<th>메뉴이름</th>
			<th>가격</th>
			</thead><tbody>

			<?php
			for($j=0; $j<$i; $j++){
				echo "<tr><td>"; echo $value_B1[$j];
				echo "</td><td>"; echo $value_B2[$j];
				echo "</td><td>"; echo $value_B3[$j];
				echo "</td></tr>"; 
			}
			echo "</tbody></table>";

			
			?>


    </main>
    
	<?php
	mysqli_close($con);
	?>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>