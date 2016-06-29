<?php 
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['relative_id']) && isset($_POST['relative_pwd']) && isset($_POST['patient_id'])) {
$relative_id= $_POST['relative_id   '];
$relative_pwd= $_POST['relative_pwd'];
$patient_id= $_POST['patient_id'];
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= newDB_CONNECT(); 
// mysql inserting a new row 
$result= mysql_query("INSERT INTO relative(relative_id, relative_pwd, patient_id) VALUES('$relative_id', '$relative_pwd', '$patient_id')");
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