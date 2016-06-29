<?php 
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['patient_pwd']) && isset($_POST['doctor_id'])) {
$patient_id= $_POST['patient_id'];
$patient_pwd= $_POST['patient_pwd'];
$doctor_id= $_POST['doctor_id'];
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= newDB_CONNECT(); 
// mysql inserting a new row 
$result= mysql_query("INSERT INTO patient(patient_id, patient_pwd, patient_name, height, gender, weight, age, leg_length, doctor_id) VALUES('$patient_id', '$patient_pwd', NULL, NULL, NULL, NULL, NULL, NULL, '$patient_name')");
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