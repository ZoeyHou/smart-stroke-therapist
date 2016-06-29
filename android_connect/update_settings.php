<?php
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['alarm_or_not']) && isset($_POST['start_hour'] && isset($_POST['start_minute'] && isset($_POST['end_hour'] && isset($_POST['end_minute']) && isset($_POST['summary_frequency']) {
$patient_id= intval($_POST['patient_id']);
$alarm_or_not= intval($_POST['alarm_or_not']);
$start_hour= intval($_POST['start_hour']);
$start_minute= intval($_POST['start_minute']);
$end_hour= intval($_POST['end_hour']);
$end_minute= intval($_POST['end_minute']);
$summary_frequency= intval($_POST['summary_frequency']);
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql update row with matched patient_id
$result= mysql_query("UPDATE settings SET alarm_or_not = '$alarm_or_not', start_hour = '$start_hour', start_minute = '$start_minute', end_hour = '$end_hour', end_minute = '$end_minute', summary_frequency = '$summary_frequency' WHERE patient_id = $patient_id");
// check if row inserted or not
if($result) {
// successfully updated
$response["success"] = 1;
$response["message"] = "Product successfully updated.";
 // echoing JSON response
echo json_encode($response);
} else{
}
} else{
// required field is missing
$response["success"] = 0;
$response["message"] = "Required field(s) is missing";
// echoing JSON response
echo json_encode($response);
}
?>