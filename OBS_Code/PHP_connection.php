<?php
$con=mysqli_connect("localhost","root","1234","boksung");

if(mysqli_connect_errno($con)){
	echo "Failed to connect to MySQL:" .mysqli_connect_error();
}
mysqli_set_charset($con,"utf8");

//========== 메인 쿼리 1		: [매장 이름 / 위치 / 음식 분류 / 전화번호 / 사진 URL] 
$resP1 = mysqli_query($con,"Select distinct
	M_Name, M_Location, LM_1st,	M_Tel, M_pic_url
	From MarketSetting
	Join LocalMenu On marketsetting.M_Ssn = LocalMenu.Lm_Ssn"
	);

//========== 메인 쿼리 2		: [매장 내 테이블 개수]
$resP2 = mysqli_query($con,"Select
	COUNT(MI_TableNum)
	From MarketSetting
	Join MarketInfo On MarketSetting.M_ssn=MarketInfo.MI_ssn
	GROUP BY M_Name
	ORDER BY M_Ssn ASC"
	);

//========== 메인 쿼리 3		: [매장 내 이용중인 테이블 개수]
$resP3 = mysqli_query($con,"Select
	COUNT(distinct M_Name, LS_tablenum)
	From MarketSetting
	Join LocalState On MarketSetting.M_ssn=LocalState.LS_ssn
	WHERE LS_State = '3'
	GROUP BY M_Name
	ORDER BY M_Ssn ASC"
	);

//=========== 메뉴 쿼리		: [업체 고유 ID별 메뉴 / 가격]
$Menu_Queary = "Select
LM_Name, LM_Charge
From MarketSetting
Join LocalMenu ON marketsetting.M_Ssn = LocalMenu.LM_Ssn
Where M_Ssn=";

//=========== 메뉴 사진 쿼리	: [메뉴 사진 URL]
$Menu_Picture = "Select
MP_pic_url
From menupicture
Where MP_Ssn=";


$resultP1 = array();
$Count_Val = 1;
while($row = mysqli_fetch_array($resP1)){	//메인 쿼리 1 로드
	$row2 = mysqli_fetch_array($resP2);		//메인 쿼리 2 로드
	$row3 = mysqli_fetch_array($resP3);		//메인 쿼리 3 로드
	array_push($resultP1,array(
	'M_Name'=>$row[0],						//매장 이름
	'M_Location'=>$row[1],					//매장 위치
	'LM_1st'=>$row[2],						//메뉴의 대분류
	'M_Tel'=>$row[3],						//매장 전화번호
	'M_pic_url'=>$row[4],					//매장 대표 사진 URL
	//'Tag_1'=>'{Market'.$Count_Val.':'.$row[4].'}',			//매장 메뉴 로드용 변수명
	'Tag_1'=>'Market'.$Count_Val,
	'Tag_2'=>'pic'.$Count_Val,				//메뉴 사진 로드용 변수명
	'MI_TableNum'=>$row2[0],				//매장 내 총 테이블 개수
	'LS_State'=>$row3[0]));					//매장 내 이용중인 테이블 개수
	$Count_Val++;
}

$result_tot = array('1Page'=>$resultP1);


for($i=1; $i<=$Count_Val-1; $i++){
	$resQ = mysqli_query($con,$Menu_Queary.$i);
	$Market_Res = array();
	while($rowQ = mysqli_fetch_array($resQ)){
		array_push($Market_Res,array(
			'LM_Name'=>$rowQ[0],			//메뉴 이름
			'LM_Charge'=>$rowQ[1]			//메뉴 가격
			));
	}
	$result_tot['Market'.$i] = $Market_Res;
}


for($i=1; $i<=$Count_Val-1; $i++){
	$resR= mysqli_query($con,$Menu_Picture.$i);
	$Menu_pic = array();
	while($rowR = mysqli_fetch_array($resR)){
		array_push($Menu_pic,array(
			'MP_pic_url'=>$rowR[0]
			));
	}
	$result_tot['pic'.$i] = $Menu_pic;
}

echo json_encode($result_tot);				//웹으로 도시
mysqli_close($con);
?>