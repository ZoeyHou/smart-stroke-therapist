<?php

define("FREQUENCY",50);
define("MAXM",round(60*FREQUENCY/45));
define("M",round(1/2*(60*FREQUENCY/65+60*FREQUENCY/45)));
define("D",round(1/2*(60*FREQUENCY/45-60*FREQUENCY/65)));

function avg($floatArray,$count){
    $sum=0;
    for($i=0;$i<$count;$i++){
       $sum+=$floatArray[$i];
    }
    return ($sum/$count);
}

// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']))
   {
       $patient_id= intval($_POST['patient_id']);
   }
if(isset($_POST['acc_x'])and(isset($_POST['acc_y'])and(isset($_POST['acc_z'])))
   {
       $jsonAccX=$_POST['acc_x'];
       $jsonAccY=$_POST['acc_y'];
       $jsonAccZ=$_POST['acc_z'];
       $accX=json_decode($jsonAccX,TRUE);
       $accY=json_decode($jsonAccY,TRUE);
       $accZ=json_decode($jsonAccZ,TRUE);
       $count=count($accX);
       $maxAccX=0;
       for ($i=0;$i<MAXM;$i++){
          if($accX[$i]>$maxAccX) {
             $peak[0]=$i;
             $maxAccX=$accX[$i];
          }
       }
       $i=0;
       while ($peak[$i]+M+D<=$count){
          $i++;
          $maxAccX=0;
          for($j=$peak[$i]+M-D;$j<=$peak[$j]+M+D;$j++){
              if ($peak[$j]>$maxAccX){
                 $maxAccX=$peak[$j];
                 $peak[$i]=$peak[$j];
              }
          }
       }
       $cycle=$i;
       //calculate cadence array, avg_cadence, dcd_cadence 
       for ($i=0;$i<$cycle;$i++){
          $cadence[$i]=($peak[$i+1]-$peak[$i])/FREQUENCY*60;//seconds
       }
       //calculate cadence symmetry
       for ($i=0;$i<&cycle-1;$i++){
          if($cadence[$i]<=$cadence[$i+1]) $symmetry[$i]=$cadence[$i+1]/$cadence[i];
          else $symmetry[$i]=$cadence[$i]/$cadence[$i+1];
       }
       $avg_cadence=avg($cadence,$cycle);
       $dcd_cadence=avg($symmetry,$cycle-1);
   }
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql inserting a new row 
$result= mysql_query("INSERT INTO test_result(patient_id, avg_length, avg_cadence, avg_degree, dcd_length, dcd_cadence, dcd_degree) VALUES('$patient_id', '$avg_length', '$avg_cadence', '$avg_degree', '$dcd_length', '$dcd_cadence', '$dcd_degree')");
// check if row inserted or not 
if($result) { 
// successfully inserted into database 
$response["success"] = 1; 
$response["message"] = "Successfully created.";
// echoing JSON response 
echo json_encode($response);
} else{ 
// failed to insert row 
$response["success"] = 0; 
$response["message"] = "Oops! An error occurred.";
// echoing response
echo json_encode($response);
} 
} else{ 
// required field is missing 
$response["success"] = 0; 
$response["message"] = "Required field(s) is missing"; 
// echoing JSON response 
echo json_encode($response);
} 
?>