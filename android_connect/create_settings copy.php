<?php
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['alarm_or_not']) && isset($_POST['start_hour'] && isset($_POST['start_minute'] && isset($_POST['end_hour'] && isset($_POST['end_minute']) && isset($_POST['doctor_advice'] && isset($_POST['summary_frequency']) {
$patient_id= $_POST['patient_id'];
$alarm_or_not= $_POST['alarm_or_not'];
$start_hour= $_POST['start_hour'];
$start_minute= $_POST['start_minute'];
$end_hour= $_POST['end_hour'];
$end_minute= $_POST['end_minute'];
$doctor_advice= $_POST['doctor_advice'];
$summary_frequency= $_POST['summary_frequency'];
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= newDB_CONNECT(); 
// mysql update row with matched patient_id
$result= mysql_query("INSERT INTO settings(patient_id, alarm_or_not, start_hour, start_minute, end_hour, end_minute, doctor_advice, summary_frequency) VALUES ('$patient_id', '$alarm_or_not', '$start_hour', '$start_minute', '$end_hour', '$end_minute', '$doctor_advice', '$summary_frequency')");
// check if row inserted or not
if($result) {
// successfully inserted into database
$response["success"] = 1;
$response["message"] = "Successfully created.";
// echoing JSON response
echojson_encode($response);
} else{
// failed to insert row
$response["success"] = 0;
$response["message"] = "Oops! An error occurred.";
// echoing response
echojson_encode($response);
}
} else{ 
// required field is missing
$response["success"] = 0;
$response["message"] = "Required field(s) is missing";
// echoing JSON response
echojson_encode($response);
}
?>