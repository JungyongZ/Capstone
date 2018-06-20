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
   function setChildValue(index) {
    window.location.href = "MarketSetting.php?index=" + index;
   }
   function ResetPage(index) {
    window.location.href = "MainPage.php";
   }
   </script>


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
    <script src="http://malsup.github.com/jquery.form.js"></script> 
 
    <script> 
        // wait for the DOM to be loaded 
        $(document).ready(function() { 
            // bind 'myForm' and provide a simple callback function 
            $('#myForm').ajaxForm(function() { 
                alert("Thank you for your comment!"); 
            }); 
        }); 
    </script> 



	<script>

	  function submit_form() {

	    document.frm.target = 'ifrm';

	    document.frm.action = 'DataDelete.php';

	    document.frm.submit();

	  }

	</script>

</head>
<body>

	<!-- Start 네비게이션 바 항목 * Ver 1.0  -->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="MainPage.php">AjouBB Admin</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
      </ul>
    </div>
  </nav>
	<!-- End 네비게이션 바 항목 * Ver 1.0   -->

	<div class="container"><br>
		<h1 align="center">AjouBB 업체 상황</h1><br>
		
		<table class="table table-striped">
			<thead>
			<tr>
				<th>업체 ID</th>
				<th>업체 명</th>
				<th>주소</th>
				<th>연락처</th>
				<th>Status</th>
				<th>삭제</th>
			</tr>
			</thead>
		<tbody>
		
		<?php
			//======================== DB 연결 ==============================
			$con = mysqli_connect("localhost","root","1234","boksung");
			if(mysqli_connect_errno($con)){
				echo "Failed to connect to MySQL:".mysqli_connect_error();
			}
			
			mysqli_set_charset($con,"utf8");
			//=========================도시 희망 쿼리
			$resN1 = mysqli_query($con,"Select
				M_Ssn,M_Name,M_Location,M_Tel
				From MarketSetting"
			);

			?>


			<?php
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

					echo "<tr><td><button onclick=\"setChildValue(".$value_A1[$j].")\" class=\"btn btn-outline-primary btn-sm\">#".$value_A1[$j]." Admin</button></td>";
					echo "<td>".$value_A2[$j]."</td>";
					echo "<td>".$value_A3[$j]."</td>";
					echo "<td>".$value_A4[$j]."</td>";
					echo "<td>"."None"."</td>"; 
					$value_temp = $value_A1[$j];
					echo "<td>";
					echo "<form method='post' name=\"frm\">";
					echo "<input type=hidden name='Ssn_value' value='".$value_temp."'>";
					echo "<input type=button class=\"btn btn-outline-primary btn-sm\" value=\"delete\" onclick=\"submit_form()\"></td></tr>";
					echo "<iframe name=\"ifrm\" width='0' height='0' frameborder='0'></iframe>";
					echo "</form>";
				}
				mysqli_close($con);
				?>
						
			</tbody>
		</table>
		<hr/>

		<button type="submit" class="btn btn-default pull-down">업체 등록하기</button>

		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		        <span class="sr-only">Previous</span>
		      </a>
		    </li>
		    <li class="page-item active"><a class="page-link" href="#">1</a></li>
		    <li class="page-item disabled"><a class="page-link" href="#">2</a></li>
		    <li class="page-item disabled"><a class="page-link" href="#">3</a></li>
		    <li class="page-item disabled">
		      <a class="page-link" href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
		      </a>
		    </li>
		  </ul>
		</nav>

  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>