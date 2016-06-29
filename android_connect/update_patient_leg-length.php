<?php
// array for response
$response= array(); 
// check for required fields 
if(isset($_POST['patient_id']) && isset($_POST['leg_length']) {
$patient_id= intval($_POST['patient_id']);
$leg_length= floatval($_POST['leg_length']);
// include db connect class
require_once__DIR__ . '/db_connect.php'; 
// connecting to db 
$db= new DB_CONNECT();
// mysql update row with matched patient_id
$result= mysql_query("UPDATE patient SET leg_length = '$leg_length' WHERE patient_id = $patient_id");
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