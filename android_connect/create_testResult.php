<?php
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['avg_length']) && isset($_POST['avg_cadence']) && isset($_POST['avg_degree']) && isset($_POST['dcd_length']) && isset($_POST['dce_cadence']) && isset($_POST['dcd_degree']))
   {
       $patient_id= intval($_POST['patient_id']);
       $avg_length= floatval($_POST['avg_length']);
       $avg_cadence= floatval($_POST['avg_cadence']);
       $avg_cadence= floatval($_POST['avg_degree']);
       $avg_cadence= floatval($_POST['dcd_length']);
       $avg_cadence= floatval($_POST['dcd_cadence']);
       $avg_cadence= floatval($_POST['dcd_degree']);
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql inserting a new row 
$result= mysql_query("INSERT INTO test_result(patient_id, avg_length, avg_cadence, avg_degree, dcd_length, dcd_cadence, dcd_degree) VALUES($patient_id, $avg_length, $avg_cadence, $avg_degree, $dcd_length, $dcd_cadence, $dcd_degree)");
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