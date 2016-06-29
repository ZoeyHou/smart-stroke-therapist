<?php 
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['relative_id']) && isset($_POST['relative_pwd']) && isset($_POST['patient_id'])) {
$relative_id= intval($_POST['relative_id']);
$relative_pwd= $_POST['relative_pwd'];
$patient_id= intval($_POST['patient_id']);
// include db connect class 
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql inserting a new row 
$result= mysql_query("INSERT INTO relative(relative_id, relative_pwd, patient_id) VALUES($relative_id, '$relative_pwd', $patient_id)");
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