<?php 
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['patient_pwd']) && isset($_POST['doctor_id'])) {
$patient_id= intval($_POST['patient_id']);
$patient_pwd= $_POST['patient_pwd'];
$doctor_id= $_POST['doctor_id'];
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql inserting a new row 
$result1 = mysql_query("INSERT INTO patient(patient_id, patient_pwd, patient_name, height, gender, weight, age, leg_length, doctor_id) VALUES('$patient_id', '$patient_pwd', NULL, NULL, NULL, NULL, NULL, NULL, '$doctor_id')");
$result2= mysql_query("INSERT INTO settings(patient_id, alarm_or_not, start_hour, end_hour, start_minute, end_minute, doctor_advice, summary_frequency) VALUES('$patient_id', NULL, NULL, NULL, NULL, NULL, NULL, NULL)");
// check if row inserted or not
if($result1) {
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