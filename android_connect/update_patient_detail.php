<?php
    // array for JSON response
    $response= array();
    // check for required fields
    if(isset($_POST['patient_id']) && isset($_POST['age']) && isset($_POST['height']) && isset($_POST['gender']) && isset($_POST['weight'])) {
        $patient_id= $_POST['patient_id'];
        $age= $_POST['age'];
        $height= $_POST['height'];
        $gender= $_POST['gender'];
        $weight= $_POST['weight'];
        // include db connect class
        require_once__DIR__ . '/db_connect.php';
        // connecting to db
        $db= newDB_CONNECT();
        // mysql update row with matched patient_id
        $result= mysql_query("UPDATE patient SET age = '$age', height = '$height', gender = '$gender', weight = '$weight' WHERE patient_id = $patient_id");
        // check if row inserted or not
        if($result) {
            // successfully updated
            $response["success"] = 1;
            $response["message"] = "Product successfully updated.";
            // echoing JSON response
            echojson_encode($response);
        } else{
        } 
    } else{ 
        // required field is missing 
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echojson_encode($response); 
    } 
    ?>