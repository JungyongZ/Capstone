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
  th{
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
            <a class="nav-link" onclick=$MarketPage_url>Setting <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" onclick="setChildValue2(<?=$nowID?>)">Order System</a>
          </li>
        </ul>
      </div>
    </nav>
	<!-- End 네비게이션 바 항목 * Ver 1.2   -->

	<?php
		//======================== DB 연결 ==============================
		$con=mysqli_connect("localhost","root","1234","boksung");
		if(mysqli_connect_errno($con)){
			echo "Failed to connect to MySQL:".mysqli_connect_error();
		}
		mysqli_set_charset($con,"utf8");
		//=========================도시 희망 쿼리
	?>




	<div class="card text-center">
		<div class="card-header">
		<br><h4>AjouBB Admin Page</h4>
		</div>
			<div class="card text-center">
			<br>
			<div class="container">
				<div id="accordion" role="tablist">
				  <div class="card"><div class="card-header" role="tab" id="headingOne"><h5 class="mb-0"><a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">메뉴 설정
	          	</a></h5>
		    		</div><div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
						<div class="card-body">
		    		<?php
							$resN1 = mysqli_query($con,"Select distinct M_Name,M_Location,M_Tel
								From MarketSetting Where M_Ssn=".$nowID);
							$row = mysqli_fetch_array($resN1);
							$M_Name = $row[0];
							$M_Location = $row[1];
							$M_Tel= $row[2];

							
						?>

	        	<!-- (Start) 메뉴 설정 창 -->
					    	<table class="table table-striped table-sm">
								<thead>
								<tr>
									<th>No.</th>
									<th>메뉴 이름</th>
									<th>가격</th>
									<th>메뉴 삭제</th>
									</tr>
								</thead>
									<tbody>
									 <body>
									  
									 </body>
									 <?php
									$resN2 = mysqli_query($con,"Select LM_Num, LM_Name, LM_Charge From LocalMenu Where LM_Ssn=".$nowID);

									$i = 0;
									while($row = mysqli_fetch_array($resN2))
									{

										$value_A1[$i] = $row[0];
										$value_A2[$i] = $row[1];
										$value_A3[$i] = $row[2];
										$i++;
									}
									
									for($j=0; $j<$i; $j++){
										echo "<tr><td height=\"10px\">".$value_A1[$j]."</td>";
										echo "<td>".$value_A2[$j]."</td>";
										echo "<td>".$value_A3[$j]."</td>";
										echo "<td><button onclick=\"setChildValue(".$value_A1[$j].")\" class=\"btn btn-outline-primary btn-sm\">삭제</button></td></tr>";
									}
									?>

									<form action=<?=$MarketPage_url?> method="POST">
									  <tr><td></td>
									  <div class="row">
									    <div class="col col-sm-2">
									      <td><input type="text-center" class="form-control" placeholder="추가할 메뉴" name="WebLM_Menu" required></td>
									    </div>
									    <div class="col col-sm-2">
									      <td><input type="text-center" class="form-control" placeholder="메뉴의 가격" name="WebLM_Charge" required></td>
									    </div>
									  </div>
									  <td><button class="btn btn-primary btn-sm" type="submit">등록</button></td></tr>
									</form>
		             
							</tbody>
						</table>
						<?php
		            if(isset($_REQUEST['WebLM_Menu'])&&isset($_REQUEST['WebLM_Charge'])){
			            echo "<br>데이터 확인! => Insert 예정.<br>";
			            echo $_REQUEST['WebLM_Menu'].' & '.$_REQUEST['WebLM_Charge'];
			          }
		            ?> 
            <!-- (End) 메뉴 및 테이블 정보 설정 창 -->
		      	</div></div>
		      </div>
				  <div class="card"><div class="card-header" role="tab" id="headingTwo"><h5 class="mb-0">
		        <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          	업체 기본 정보 설정</a></h5></div>
		    		<div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
		    		<div class="card-body">
		        	
		        	<!-- (Start) 메뉴 및 테이블 정보 설정 창 -->

						<form action=<?=$MarketPage_url?> method="POST">
						  <div class="row">
						    <div class="col-md-6 mb-3">
						      <label for="validationDefault01">업체 명</label>
						      <input type="text" class="form-control" id="validationDefault01" placeholder="First name" value="<?=$M_Name?>" name="WebM_Name" required>
						    </div>
						    <div class="col-md-6 mb-3">
						      <label for="validationDefault02">전화번호</label>
						      <input type="text" class="form-control" id="validationDefault02" placeholder="Last name" value="<?=$M_Tel?>" name="WebM_Tel" required>
						    </div>
						  </div>
						  <div class="row">
						    <div class="col-md-6 mb-3">
						      <label for="validationDefault03">주소</label>
						      <input type="text" class="form-control" id="validationDefault03" placeholder="City" value="<?=$M_Location?>" name="WebM_Location" required>
						      <div class="invalid-feedback">
						        올바른 주소를 입력해주세요!
						      </div>
						    </div>
						  </div>
						  <button class="btn btn-primary" type="submit">Submit form</button>
						</form>

            <?php
            if(isset($_REQUEST['WebM_Name'])&&isset($_REQUEST['WebM_Location'])&&isset($_REQUEST['WebM_Location'])){
	            echo "<br>데이터 확인! => Update 예정.<br>";
	            echo $_REQUEST['WebM_Name'].' & '.$_REQUEST['WebM_Location'].' & '.$_REQUEST['WebM_Location'];
	          }
            ?>  



						<!-- (End) 메뉴 및 테이블 정보 설정 창 -->

	        	</div>
	        	</div>
				  </div><br>
				</div>
			</div>

	  <div class="card-footer text-muted">
	    2018. Ajou Univ Team AjouBB
	  </div>
	</div>

	<?php
	mysqli_close($con);
	?>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>